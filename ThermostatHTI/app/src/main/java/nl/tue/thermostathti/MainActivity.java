package nl.tue.thermostathti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import org.thermostatapp.util.*;

public class MainActivity extends AppCompatActivity {

    float desiredTempVal = 21.0f;
    TextView desiredTemp, currentTemp, currentTime;
    Thermometer thermometer;
    SeekBar seekBarDesiredTemp;
    Intent intent;

    Button sync;
    String day, time, currentTemperature, targetTemperature;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    intent = new Intent(MainActivity.this, week_overview.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    intent = new Intent(MainActivity.this, Help.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* Use BASE_ADDRESS dedicated for your group,
		 * change 100 to you group number
		 */
        HeatingSystem.BASE_ADDRESS = "http://wwwis.win.tue.nl/2id40-ws/5";
        HeatingSystem.WEEK_PROGRAM_ADDRESS = HeatingSystem.BASE_ADDRESS + "/weekProgram";


        sync = (Button)findViewById(R.id.sync);
        currentTemp = (TextView) findViewById(R.id.currentTemp);
        currentTime = (TextView) findViewById(R.id.currentTime);


        sync.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        day = "";
                        time = "";
                        currentTemperature = "";
                        try {
                            //getParam = HeatingSystem.get("currentTemperature");
                            day = HeatingSystem.get("day");
                            time = HeatingSystem.get("time");
                            currentTemperature = HeatingSystem.get("currentTemperature");
                            /*
									HeatingSystem.get("day");
									HeatingSystem.get("time");
									HeatingSystem.get("targetTemperature");
									HeatingSystem.get("dayTemperature");
									HeatingSystem.get("nightTemperature");
									HeatingSystem.get("weekProgramState");
							*/
                            currentTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    currentTemp.setText(currentTemperature);
                                }
                            });
                            currentTime.post(new Runnable() {
                                @Override
                                public void run() {
                                    currentTime.setText(time);
                                }
                            });
                        } catch (Exception e) {
                            System.err.println("Error from getdata "+e);
                        }
                    }
                }).start();
            }
        });




        ImageButton bPlus = (ImageButton) findViewById(R.id.bPlus);
        ImageButton bMinus = (ImageButton) findViewById(R.id.bMinus);
        seekBarDesiredTemp = (SeekBar) findViewById(R.id.seekBarDesiredTemp);
        thermometer = (Thermometer) findViewById(R.id.thermometer2);

        desiredTemp = (TextView) findViewById(R.id.desiredTemp);
        desiredTemp.setText(String.format("%.1f", desiredTempVal) + "\u2103");
        thermometer.setCurrentTemp(desiredTempVal);

        seekBarDesiredTemp.setProgress((int)desiredTempVal);
        final int step = 1;
        final int max = 30;
        final int min = 5;
        seekBarDesiredTemp.setMax( (max - min) / step );
        seekBarDesiredTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                desiredTempVal = min + (i * step);
                thermometer.setCurrentTemp(desiredTempVal);
                desiredTemp.setText(desiredTempVal + "\u2103");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (desiredTempVal <= max){
                    desiredTempVal = desiredTempVal + 0.1f;
                    desiredTemp.setText(String.format("%.1f", desiredTempVal) + "\u2103");
                    thermometer.setCurrentTemp(desiredTempVal);
                }
            }
        });

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((desiredTempVal >= min)) {
                    desiredTempVal = desiredTempVal - 0.1f;
                    desiredTemp.setText(String.format("%.1f", desiredTempVal) + "\u2103");
                    thermometer.setCurrentTemp(desiredTempVal);
                }
            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
