package nl.tue.thermostathti;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.thermostatapp.util.*;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by s136664 on 10/27/2017.
 */

public class week_sunday extends AppCompatActivity {

    WeekProgram wpg;
    int index;
    Button sunDayStart1, sunDayEnd1, sunDayStart2, sunDayEnd2, sunDayStart3, sunDayEnd3, sunDayStart4, sunDayEnd4, sunDayStart5, sunDayEnd5;
    Boolean allowed, intervalSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_sunday);

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

        sunDayStart1 = (Button) findViewById(R.id.editTextSunday1);
        sunDayEnd1 = (Button) findViewById(R.id.editTextSunday2);
        sunDayStart2 = (Button) findViewById(R.id.editTextSunday3);
        sunDayEnd2 = (Button) findViewById(R.id.editTextSunday4);
        sunDayStart3 = (Button) findViewById(R.id.editTextSunday5);
        sunDayEnd3 = (Button) findViewById(R.id.editTextSunday6);
        sunDayStart4 = (Button) findViewById(R.id.editTextSunday7);
        sunDayEnd4 = (Button) findViewById(R.id.editTextSunday8);
        sunDayStart5 = (Button) findViewById(R.id.editTextSunday9);
        sunDayEnd5 = (Button) findViewById(R.id.editTextSunday10);

        allowed = true;


        sunDayStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_sunday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Sunday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Sunday").get(2*i).getTime_Int() && hour < wpg.data.get("Sunday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            sunDayStart1.setText(String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Sunday").set(0, new Switch("day", true, String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute)));
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast overlap = Toast.makeText(getApplicationContext(), "The switch was not added: a new switch can't overlap with an already active switch.", Toast.LENGTH_LONG);
                                    overlap.show();
                                }
                            });
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        sunDayEnd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_sunday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Sunday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Sunday").get(2*i).getTime_Int() && hour < wpg.data.get("Sunday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Sunday").get(0).getTime_Int() < wpg.data.get("Sunday").get(2*i).getTime_Int() && hour > wpg.data.get("Sunday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            sunDayEnd1.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Sunday").set(1, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast overlap = Toast.makeText(getApplicationContext(), "The switch was not added: a new switch can't overlap with an already active switch.", Toast.LENGTH_LONG);
                                    overlap.show();
                                }
                            });
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        sunDayStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_sunday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Sunday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Sunday").get(2*i).getTime_Int() && hour < wpg.data.get("Sunday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            sunDayStart2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Sunday").set(2, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast overlap = Toast.makeText(getApplicationContext(), "The switch was not added: a new switch can't overlap with an already active switch.", Toast.LENGTH_LONG);
                                    overlap.show();
                                }
                            });
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        sunDayEnd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_sunday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Sunday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Sunday").get(2*i).getTime_Int() && hour < wpg.data.get("Sunday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Sunday").get(2).getTime_Int() < wpg.data.get("Sunday").get(2*i).getTime_Int() && hour > wpg.data.get("Sunday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            sunDayEnd2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Sunday").set(3, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast overlap = Toast.makeText(getApplicationContext(), "The switch was not added: a new switch can't overlap with an already active switch.", Toast.LENGTH_LONG);
                                    overlap.show();
                                }
                            });
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        sunDayStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_sunday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Sunday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Sunday").get(2*i).getTime_Int() && hour < wpg.data.get("Sunday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            sunDayStart3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Sunday").set(4, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast overlap = Toast.makeText(getApplicationContext(), "The switch was not added: a new switch can't overlap with an already active switch.", Toast.LENGTH_LONG);
                                    overlap.show();
                                }
                            });
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        sunDayEnd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_sunday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Sunday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Sunday").get(2*i).getTime_Int() && hour < wpg.data.get("Sunday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Sunday").get(4).getTime_Int() < wpg.data.get("Sunday").get(2*i).getTime_Int() && hour > wpg.data.get("Sunday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            sunDayEnd3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Sunday").set(5, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast overlap = Toast.makeText(getApplicationContext(), "The switch was not added: a new switch can't overlap with an already active switch.", Toast.LENGTH_LONG);
                                    overlap.show();
                                }
                            });
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        sunDayStart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_sunday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Sunday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Sunday").get(2*i).getTime_Int() && hour < wpg.data.get("Sunday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            sunDayStart4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Sunday").set(6, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast overlap = Toast.makeText(getApplicationContext(), "The switch was not added: a new switch can't overlap with an already active switch.", Toast.LENGTH_LONG);
                                    overlap.show();
                                }
                            });
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        sunDayEnd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_sunday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Sunday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Sunday").get(2*i).getTime_Int() && hour < wpg.data.get("Sunday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Sunday").get(6).getTime_Int() < wpg.data.get("Sunday").get(2*i).getTime_Int() && hour > wpg.data.get("Sunday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            sunDayEnd4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Sunday").set(7, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast overlap = Toast.makeText(getApplicationContext(), "The switch was not added: a new switch can't overlap with an already active switch.", Toast.LENGTH_LONG);
                                    overlap.show();
                                }
                            });
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        sunDayStart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_sunday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Sunday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Sunday").get(2*i).getTime_Int() && hour < wpg.data.get("Sunday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            sunDayStart5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Sunday").set(8, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast overlap = Toast.makeText(getApplicationContext(), "The switch was not added: a new switch can't overlap with an already active switch.", Toast.LENGTH_LONG);
                                    overlap.show();
                                }
                            });
                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        sunDayEnd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_sunday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Sunday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Sunday").get(2*i).getTime_Int() && hour < wpg.data.get("Sunday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Sunday").get(8).getTime_Int() < wpg.data.get("Sunday").get(2*i).getTime_Int() && hour > wpg.data.get("Sunday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            sunDayEnd5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Sunday").set(9, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast overlap = Toast.makeText(getApplicationContext(), "The switch was not added: a new switch can't overlap with an already active switch.", Toast.LENGTH_LONG);
                                    overlap.show();
                                }
                            });
                        }
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
                            wpg.data.get("Sunday").set(5, new Switch("day", true, "07:30"));
                            wpg.data.get("Sunday").set(1, new Switch("night", true, "08:30"));
                            wpg.data.get("Sunday").set(6, new Switch("day", true, "18:00"));
                            wpg.data.get("Sunday").set(7, new Switch("day", true, "12:00"));
                            wpg.data.get("Sunday").set(8, new Switch("day", true, "18:00"));
                            boolean duplicates = wpg.duplicates(wpg.data.get("Sunday"));
                            System.out.println("Duplicates found "+duplicates);
                            */
                    //Upload the updated program
                    //HeatingSystem.setWeekProgram(wpg);
                    sunDayStart1.post(new Runnable() {
                        @Override
                        public void run() {
                            sunDayStart1.setText(wpg.data.get("Sunday").get(0).getTime());
                        }
                    });
                    sunDayStart2.post(new Runnable() {
                        @Override
                        public void run() {
                            sunDayStart2.setText(wpg.data.get("Sunday").get(2).getTime());
                        }
                    });
                    sunDayStart3.post(new Runnable() {
                        @Override
                        public void run() {
                            sunDayStart3.setText(wpg.data.get("Sunday").get(4).getTime());
                        }
                    });
                    sunDayStart4.post(new Runnable() {
                        @Override
                        public void run() {
                            sunDayStart4.setText(wpg.data.get("Sunday").get(6).getTime());
                        }
                    });
                    sunDayStart5.post(new Runnable() {
                        @Override
                        public void run() {
                            sunDayStart5.setText(wpg.data.get("Sunday").get(8).getTime());
                        }
                    });
                    sunDayEnd1.post(new Runnable() {
                        @Override
                        public void run() {
                            sunDayEnd1.setText(wpg.data.get("Sunday").get(1).getTime());
                        }
                    });
                    sunDayEnd2.post(new Runnable() {
                        @Override
                        public void run() {
                            sunDayEnd2.setText(wpg.data.get("Sunday").get(3).getTime());
                        }
                    });
                    sunDayEnd3.post(new Runnable() {
                        @Override
                        public void run() {
                            sunDayEnd3.setText(wpg.data.get("Sunday").get(5).getTime());
                        }
                    });
                    sunDayEnd4.post(new Runnable() {
                        @Override
                        public void run() {
                            sunDayEnd4.setText(wpg.data.get("Sunday").get(7).getTime());
                        }
                    });
                    sunDayEnd5.post(new Runnable() {
                        @Override
                        public void run() {
                            sunDayEnd5.setText(wpg.data.get("Sunday").get(9).getTime());
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

    @Override
    public void onBackPressed() {
        intervalSet = true;
        index = 0;
        for (int i = 0; i < 5; i++) {
            if ((wpg.data.get("Sunday").get(2*i).getState() ^ wpg.data.get("Sunday").get(2*i+1).getState())){
                intervalSet = false;
                index = i;
            }
        }

        if (!intervalSet){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast overlap = Toast.makeText(getApplicationContext(), "The switch interval " + index + " is not complete. Please add beginning and ending times", Toast.LENGTH_LONG);
                    overlap.show();
                }
            });
        } else {
            week_sunday.super.onBackPressed();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();    //Call the back button's method
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
