package nl.tue.thermostathti;

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

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    int desiredTempVal = 210;
    TextView desiredTemp;
    Thermometer thermometer;
    SeekBar seekBarDesiredTemp;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton bPlus = (ImageButton) findViewById(R.id.bPlus);
        ImageButton bMinus = (ImageButton) findViewById(R.id.bMinus);
        seekBarDesiredTemp = (SeekBar) findViewById(R.id.seekBarDesiredTemp);
        thermometer = (Thermometer) findViewById(R.id.thermometer2);

        desiredTemp = (TextView) findViewById(R.id.desiredTemp);
        desiredTemp.setText((double)desiredTempVal/10 + "\u2103");
        thermometer.setCurrentTemp((float)desiredTempVal/10f);
        seekBarDesiredTemp.setProgress(desiredTempVal/10);
        seekBarDesiredTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                desiredTempVal = i*10;
                desiredTemp.setText((double)i + "\u2103");
                thermometer.setCurrentTemp((float)i);
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
                desiredTempVal++;
                desiredTemp.setText((double)desiredTempVal/10 + "\u2103");
                thermometer.setCurrentTemp((float)desiredTempVal/10f);
                seekBarDesiredTemp.setProgress(desiredTempVal/10);
            }
        });

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desiredTempVal--;
                desiredTemp.setText((double)desiredTempVal/10 + "\u2103");
                thermometer.setCurrentTemp((float)desiredTempVal/10f);
                seekBarDesiredTemp.setProgress(desiredTempVal/10);
            }
        });


        mTextMessage = (TextView) findViewById(R.id.textView4);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
