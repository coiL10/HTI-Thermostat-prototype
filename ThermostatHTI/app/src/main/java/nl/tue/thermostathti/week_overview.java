package nl.tue.thermostathti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.Switch;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class week_overview extends AppCompatActivity {

    Intent intent;
    SeekBar seekBarDay, seekBarNight;
    String dayTemp, nightTemp;
    float dayTempVal, nightTempVal;
    TextView dayTempText, nightTempText;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(week_overview.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dashboard:
                    intent = new Intent(week_overview.this, week_overview.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    intent = new Intent(week_overview.this, Help.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_overview);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        HeatingSystem.BASE_ADDRESS = "http://wwwis.win.tue.nl/2id40-ws/5";
        HeatingSystem.WEEK_PROGRAM_ADDRESS = HeatingSystem.BASE_ADDRESS + "/weekProgram";

        seekBarDay = (SeekBar) findViewById(R.id.seekBarDay);
        seekBarNight = (SeekBar) findViewById(R.id.seekBarNight);
        nightTempText = (TextView) findViewById(R.id.nightTempText);
        dayTempText = (TextView) findViewById(R.id.dayTempText);


        final int step = 1;
        final int max = 30;
        final int min = 5;


        ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(5);

        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dayTemp = "21.0";
                        nightTemp = "21.0";
                        try {
                            dayTemp = HeatingSystem.get("dayTemperature");
                            nightTemp = HeatingSystem.get("nightTemperature");
                            /*
									HeatingSystem.get("day");
									HeatingSystem.get("time");
									HeatingSystem.get("targetTemperature");
									HeatingSystem.get("dayTemperature");
									HeatingSystem.get("nightTemperature");
									HeatingSystem.get("weekProgramState");
							*/

                            dayTempVal = Float.parseFloat(dayTemp);
                            nightTempVal = Float.parseFloat(nightTemp);
                        } catch (Exception e) {
                            System.err.println("Error from getdata "+e);
                        }

                        seekBarDay.post(new Runnable() {
                            @Override
                            public void run() {
                                seekBarDay.setProgress((int) (dayTempVal-5));
                            }
                        });

                        seekBarNight.post(new Runnable() {
                            @Override
                            public void run() {
                                seekBarNight.setProgress((int) (nightTempVal-5));
                            }
                        });

                        dayTempText.post(new Runnable() {
                            @Override
                            public void run() {
                                dayTempText.setText(String.format("%.1f", dayTempVal));
                            }
                        });

                        nightTempText.post(new Runnable() {
                            @Override
                            public void run() {
                                nightTempText.setText(String.format("%.1f", nightTempVal));
                            }
                        });
                    }
                }).start();
            }
        }, 0, 1, TimeUnit.SECONDS);



        seekBarDay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    dayTempVal = min + (i * step);
                    dayTempText.setText(String.format("%.1f", dayTempVal));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HeatingSystem.put("dayTemperature", Float.toString(dayTempVal));
                            } catch (Exception e) {
                                System.err.println("Error from putdata " + e);
                            }
                        }
                    }).start();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBarNight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    nightTempVal = min + (i * step);
                    nightTempText.setText(String.format("%.1f", nightTempVal));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HeatingSystem.put("nightTemperature", Float.toString(nightTempVal));
                            } catch (Exception e) {
                                System.err.println("Error from putdata " + e);
                            }
                        }
                    }).start();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }

}
