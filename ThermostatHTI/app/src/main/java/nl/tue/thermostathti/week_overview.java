package nl.tue.thermostathti;

import android.content.Intent;
import android.media.Image;
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

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.Switch;
import org.thermostatapp.util.WeekProgram;

public class week_overview extends AppCompatActivity {

    WeekProgram wpg;
    Intent intent;
    SeekBar seekBarDay, seekBarNight;
    String dayTemp, nightTemp;
    float dayTempVal, nightTempVal;
    TextView dayTempText, nightTempText;
    View mondayView, tuesdayView, wednesdayView, thursdayView, fridayView, saturdayView, sundayView;
    Button reset;
    ImageButton bMinusDay, bMinusNight, bPlusDay, bPlusNight;

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wpg = HeatingSystem.getWeekProgram();
                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();


        seekBarDay = (SeekBar) findViewById(R.id.seekBarDay);
        seekBarNight = (SeekBar) findViewById(R.id.seekBarNight);
        nightTempText = (TextView) findViewById(R.id.nightTempText);
        dayTempText = (TextView) findViewById(R.id.dayTempText);
        mondayView = findViewById(R.id.mondayView);
        tuesdayView = findViewById(R.id.tuesdayView);
        wednesdayView = findViewById(R.id.wednesdayView);
        thursdayView = findViewById(R.id.thursdayView);
        fridayView = findViewById(R.id.fridayView);
        saturdayView = findViewById(R.id.saturdayView);
        sundayView = findViewById(R.id.sundayView);
        reset = (Button) findViewById(R.id.reset);
        bMinusDay = (ImageButton) findViewById(R.id.bMinusDay);
        bMinusNight = (ImageButton) findViewById(R.id.bMinusNight);
        bPlusDay = (ImageButton) findViewById(R.id.bPlusDay);
        bPlusNight = (ImageButton) findViewById(R.id.bPlusNight);


        final int step = 1;
        final int max = 30;
        final int min = 5;



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

        bPlusDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayTempVal < max){
                    dayTempVal = dayTempVal + 0.1f;
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
                    onResume();
                }
            }
        });

        bPlusNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nightTempVal < max){
                    nightTempVal = nightTempVal + 0.1f;
                    dayTempText.setText(String.format("%.1f", nightTempVal));
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
                    onResume();
                }
            }
        });

        bMinusDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayTempVal > min){
                    dayTempVal = dayTempVal - 0.1f;
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
                    onResume();
                }
            }
        });

        bMinusNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nightTempVal > min){
                    nightTempVal = nightTempVal - 0.1f;
                    dayTempText.setText(String.format("%.1f", nightTempVal));
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
                    onResume();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wpg.setDefault();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            HeatingSystem.setWeekProgram(wpg);
                        } catch (Exception e) {
                            System.err.println("Error from getdata " + e);
                        }
                    }
                }).start();
            }
        });


        mondayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(week_overview.this, week_monday.class);
                startActivity(intent);
            }
        });

        tuesdayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(week_overview.this, week_tuesday.class);
                startActivity(intent);
            }
        });

        wednesdayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(week_overview.this, week_wednesday.class);
                startActivity(intent);
            }
        });

        thursdayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(week_overview.this, week_thursday.class);
                startActivity(intent);
            }
        });

        fridayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(week_overview.this, week_friday.class);
                startActivity(intent);
            }
        });

        saturdayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(week_overview.this, week_saturday.class);
                startActivity(intent);
            }
        });

        sundayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(week_overview.this, week_sunday.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
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

}
