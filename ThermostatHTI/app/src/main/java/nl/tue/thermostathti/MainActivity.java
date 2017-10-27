package nl.tue.thermostathti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.thermostatapp.util.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    float desiredTempVal;
    float currentTempVal, targetTempVal;
    int currentTime_int;
    TextView desiredTemp, currentTemp, currentTime, nextSwitchView;
    ImageView DayOrNight, tempRaise;
    Thermometer thermometer;
    SeekBar seekBarDesiredTemp;
    Intent intent;
    String day, time, currentTemperature, targetTemperature, nextSwitch, dayTemp, nightTemp;
    Switch currentSwitch;
    WeekProgram wpg;
    ToggleButton vacations;
    boolean vacationBool;






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


        ImageButton bPlus = (ImageButton) findViewById(R.id.bPlus);
        ImageButton bMinus = (ImageButton) findViewById(R.id.bMinus);
        seekBarDesiredTemp = (SeekBar) findViewById(R.id.seekBarDesiredTemp);
        thermometer = (Thermometer) findViewById(R.id.thermometer2);
        vacations = (ToggleButton) findViewById(R.id.toggleButton);

        desiredTemp = (TextView) findViewById(R.id.desiredTemp);
        nextSwitchView = (TextView) findViewById(R.id.nextSwitch);


        desiredTemp.setText(String.format("%.1f", desiredTempVal) + "\u2103");
        thermometer.setCurrentTemp(desiredTempVal);


        seekBarDesiredTemp.setProgress((int)desiredTempVal);
        final int step = 1;
        final int max = 30;
        final int min = 5;
        seekBarDesiredTemp.setMax( (max - min) / step );

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    currentTemperature = HeatingSystem.get("targetTemperature");
                    desiredTempVal = Float.parseFloat(currentTemperature);
                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();

        desiredTempVal = 15.0f;
        currentTemp = (TextView) findViewById(R.id.currentTemp);
        currentTime = (TextView) findViewById(R.id.currentTime);

        tempRaise = (ImageView) findViewById(R.id.tempRaise);
        DayOrNight = (ImageView) findViewById(R.id.DayOrNight);


        ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(5);

        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        day = "";
                        time = "";
                        dayTemp = "21.0";
                        nightTemp = "21.0";
                        currentTemperature = "21.0";
                        targetTemperature = "21.0";
                        nextSwitch = "midnight";
                        try {
                            wpg = HeatingSystem.getWeekProgram();
                            //getParam = HeatingSystem.get("currentTemperature");
                            day = HeatingSystem.get("day");
                            time = HeatingSystem.get("time");
                            currentTemperature = HeatingSystem.get("currentTemperature");
                            targetTemperature = HeatingSystem.get("targetTemperature");
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

                            String front = time.substring(0, 2); // Get the first 2 digits if they
                            // are there.
                            String back = time.substring(3, 5); // Get the last 2 digits if they
                            // are there.

                            int front_int = Integer.parseInt(front);
                            int back_int = Integer.parseInt(back);
                            currentTime_int = front_int * 100
                                    + (int) ((float) back_int / 60.0 * 100.0);

                            int i = 0;
                            while (i < 10){
                                Switch j = wpg.data.get(day).get(i);
                                if (currentTime_int >= j.getTime_Int()){
                                    currentSwitch = j;
                                    if (i == 9){
                                        nextSwitch = "midnight";
                                    } else {
                                        if (j.getState()) {
                                            nextSwitch = wpg.data.get(day).get(i + 1).getTime();
                                        }
                                    }
                                    nextSwitchView.post(new Runnable() {
                                        @Override
                                        public void run() {
                                           nextSwitchView.setText(nextSwitch);
                                        }
                                    });
                                }
                                i++;
                            }


                            currentTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    currentTemp.setText(currentTemperature + "\u2103");
                                }
                            });
                            currentTime.post(new Runnable() {
                                @Override
                                public void run() {
                                    currentTime.setText(day + ", " + time);
                                }
                            });

                            vacationBool = HeatingSystem.getVacationMode();

                        } catch (Exception e) {
                            System.err.println("Error from getdata "+e);
                        }



                        vacations.post(new Runnable() {
                            @Override
                            public void run() {
                                vacations.setChecked(vacationBool);
                            }
                        });

                        try {
                            currentTempVal = Float.parseFloat(currentTemperature);
                            targetTempVal = Float.parseFloat(targetTemperature);

                            seekBarDesiredTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    seekBarDesiredTemp.setProgress((int) targetTempVal-5);
                                }
                            });
                            thermometer.post(new Runnable() {
                                @Override
                                public void run() {
                                    thermometer.setCurrentTemp(targetTempVal);
                                }
                            });

                            desiredTemp.post(new Runnable() {
                                @Override
                                public void run() {
                                    desiredTemp.setText(String.format("%.1f", targetTempVal) + "\u2103");
                                }
                            });

                            if (currentTempVal < targetTempVal) {
                                tempRaise.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        tempRaise.setImageResource(R.drawable.arrowup664);
                                    }
                                });
                            } else {
                                if (currentTempVal > targetTempVal) {
                                    tempRaise.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            tempRaise.setImageResource(R.drawable.arrow64);
                                        }
                                    });

                                } else {
                                    tempRaise.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            tempRaise.setImageResource(android.R.color.transparent);
                                        }
                                    });
                                }
                            }

                            if (!vacationBool) {
                                if (currentSwitch.getType().equals("night") && targetTempVal == Float.parseFloat(nightTemp) || nextSwitch.equals("midnight")) {
                                    DayOrNight.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            DayOrNight.setImageResource(R.drawable.moon64);
                                        }
                                    });
                                } else if (currentSwitch.getType().equals("day") && targetTempVal == Float.parseFloat(dayTemp)) {
                                    DayOrNight.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            DayOrNight.setImageResource(R.drawable.sun24);
                                        }
                                    });
                                } else {
                                    DayOrNight.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            DayOrNight.setImageResource(R.drawable.user64);
                                        }
                                    });
                                }
                            }

                        } catch (Exception e) {
                            System.err.println("Error from parseFloat "+e);
                            tempRaise.post(new Runnable() {
                                @Override
                                public void run() {
                                    tempRaise.setImageResource(android.R.color.transparent);
                                }
                            });
                        }
                    }
                }).start();
            }
        }, 0, 1, TimeUnit.SECONDS);




        seekBarDesiredTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    desiredTempVal = min + (i * step);
                    thermometer.setCurrentTemp(desiredTempVal);
                    desiredTemp.setText(desiredTempVal + "\u2103");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HeatingSystem.put("targetTemperature", Float.toString(desiredTempVal));
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

        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (desiredTempVal < max){
                    desiredTempVal = desiredTempVal + 0.1f;
                    desiredTemp.setText(String.format("%.1f", desiredTempVal) + "\u2103");
                    thermometer.setCurrentTemp(desiredTempVal);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HeatingSystem.put("targetTemperature", Float.toString(desiredTempVal));
                            } catch (Exception e) {
                                System.err.println("Error from putdata " + e);
                            }
                        }
                    }).start();
                }
            }
        });

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((desiredTempVal > min)) {
                    desiredTempVal = desiredTempVal - 0.1f;
                    desiredTemp.setText(String.format("%.1f", desiredTempVal) + "\u2103");
                    thermometer.setCurrentTemp(desiredTempVal);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HeatingSystem.put("targetTemperature", Float.toString(desiredTempVal));
                            } catch (Exception e) {
                                System.err.println("Error from putdata " + e);
                            }
                        }
                    }).start();
                }
            }
        });

        vacations.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HeatingSystem.put("weekProgramState", "off");
                            } catch (Exception e) {
                                System.err.println("Error from putdata " + e);
                            }
                            DayOrNight.post(new Runnable() {
                                @Override
                                public void run() {
                                    DayOrNight.setImageResource(R.drawable.palm64);
                                }
                            });
                        }
                    }).start();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                HeatingSystem.put("weekProgramState", "on");
                            } catch (Exception e) {
                                System.err.println("Error from putdata " + e);
                            }
                        }
                    }).start();
                }
            }
        });


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
