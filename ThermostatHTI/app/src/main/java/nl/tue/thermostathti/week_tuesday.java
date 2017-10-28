package nl.tue.thermostathti;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import org.thermostatapp.util.*;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by s136664 on 10/27/2017.
 */

public class week_tuesday extends AppCompatActivity {

    WeekProgram wpg;
    Button tuesDayStart1, tuesDayEnd1, tuesDayStart2, tuesDayEnd2, tuesDayStart3, tuesDayEnd3, tuesDayStart4, tuesDayEnd4, tuesDayStart5, tuesDayEnd5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_tuesday);

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

        tuesDayStart1 = (Button) findViewById(R.id.editTextTuesday1);
        tuesDayEnd1 = (Button) findViewById(R.id.editTextTuesday2);
        tuesDayStart2 = (Button) findViewById(R.id.editTextTuesday3);
        tuesDayEnd2 = (Button) findViewById(R.id.editTextTuesday4);
        tuesDayStart3 = (Button) findViewById(R.id.editTextTuesday5);
        tuesDayEnd3 = (Button) findViewById(R.id.editTextTuesday6);
        tuesDayStart4 = (Button) findViewById(R.id.editTextTuesday7);
        tuesDayEnd4 = (Button) findViewById(R.id.editTextTuesday8);
        tuesDayStart5 = (Button) findViewById(R.id.editTextTuesday9);
        tuesDayEnd5 = (Button) findViewById(R.id.editTextTuesday10);


        tuesDayStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_tuesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tuesDayStart1.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Tuesday").set(0, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        tuesDayEnd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_tuesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tuesDayEnd1.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Tuesday").set(1, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        tuesDayStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_tuesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tuesDayStart2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Tuesday").set(2, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        tuesDayEnd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_tuesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tuesDayEnd2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Tuesday").set(3, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        tuesDayStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_tuesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tuesDayStart3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Tuesday").set(4, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        tuesDayEnd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_tuesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tuesDayEnd3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Tuesday").set(5, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        tuesDayStart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_tuesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tuesDayStart4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Tuesday").set(6, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        tuesDayEnd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_tuesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tuesDayEnd4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Tuesday").set(7, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        tuesDayStart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_tuesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tuesDayStart5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Tuesday").set(8, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        tuesDayEnd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_tuesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tuesDayEnd5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Tuesday").set(9, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Set the week program to default
                            /*
                            wpg.data.get("Tuesday").set(5, new Switch("day", true, "07:30"));
                            wpg.data.get("Tuesday").set(1, new Switch("night", true, "08:30"));
                            wpg.data.get("Tuesday").set(6, new Switch("day", true, "18:00"));
                            wpg.data.get("Tuesday").set(7, new Switch("day", true, "12:00"));
                            wpg.data.get("Tuesday").set(8, new Switch("day", true, "18:00"));
                            boolean duplicates = wpg.duplicates(wpg.data.get("Tuesday"));
                            System.out.println("Duplicates found "+duplicates);
                            */
                    //Upload the updated program
                    //HeatingSystem.setWeekProgram(wpg);
                    tuesDayStart1.post(new Runnable() {
                        @Override
                        public void run() {
                            tuesDayStart1.setText(wpg.data.get("Tuesday").get(0).getTime());
                        }
                    });
                    tuesDayStart2.post(new Runnable() {
                        @Override
                        public void run() {
                            tuesDayStart2.setText(wpg.data.get("Tuesday").get(2).getTime());
                        }
                    });
                    tuesDayStart3.post(new Runnable() {
                        @Override
                        public void run() {
                            tuesDayStart3.setText(wpg.data.get("Tuesday").get(4).getTime());
                        }
                    });
                    tuesDayStart4.post(new Runnable() {
                        @Override
                        public void run() {
                            tuesDayStart4.setText(wpg.data.get("Tuesday").get(6).getTime());
                        }
                    });
                    tuesDayStart5.post(new Runnable() {
                        @Override
                        public void run() {
                            tuesDayStart5.setText(wpg.data.get("Tuesday").get(8).getTime());
                        }
                    });
                    tuesDayEnd1.post(new Runnable() {
                        @Override
                        public void run() {
                            tuesDayEnd1.setText(wpg.data.get("Tuesday").get(1).getTime());
                        }
                    });
                    tuesDayEnd2.post(new Runnable() {
                        @Override
                        public void run() {
                            tuesDayEnd2.setText(wpg.data.get("Tuesday").get(3).getTime());
                        }
                    });
                    tuesDayEnd3.post(new Runnable() {
                        @Override
                        public void run() {
                            tuesDayEnd3.setText(wpg.data.get("Tuesday").get(5).getTime());
                        }
                    });
                    tuesDayEnd4.post(new Runnable() {
                        @Override
                        public void run() {
                            tuesDayEnd4.setText(wpg.data.get("Tuesday").get(7).getTime());
                        }
                    });
                    tuesDayEnd5.post(new Runnable() {
                        @Override
                        public void run() {
                            tuesDayEnd5.setText(wpg.data.get("Tuesday").get(9).getTime());
                        }
                    });




                } catch (Exception e) {
                    System.err.println("Error from getdata " + e);
                }
            }
        }).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
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
}
