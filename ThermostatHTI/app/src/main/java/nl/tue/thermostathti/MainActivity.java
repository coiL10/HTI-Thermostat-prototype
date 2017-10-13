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

    float desiredTempVal = 21.0f;
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
                desiredTempVal = desiredTempVal + 0.1f;
                desiredTemp.setText(String.format("%.1f", desiredTempVal) + "\u2103");
                thermometer.setCurrentTemp(desiredTempVal);
            }
        });

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                desiredTempVal = desiredTempVal - 0.1f;
                desiredTemp.setText(String.format("%.1f", desiredTempVal) + "\u2103");
                thermometer.setCurrentTemp(desiredTempVal);
            }
        });


        mTextMessage = (TextView) findViewById(R.id.textView4);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
