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

public class week_thursday extends AppCompatActivity {

    WeekProgram wpg;
    int index;
    Button thursDayStart1, thursDayEnd1, thursDayStart2, thursDayEnd2, thursDayStart3, thursDayEnd3, thursDayStart4, thursDayEnd4, thursDayStart5, thursDayEnd5;
    Boolean allowed, intervalSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_thursday);

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

        thursDayStart1 = (Button) findViewById(R.id.editTextThursday1);
        thursDayEnd1 = (Button) findViewById(R.id.editTextThursday2);
        thursDayStart2 = (Button) findViewById(R.id.editTextThursday3);
        thursDayEnd2 = (Button) findViewById(R.id.editTextThursday4);
        thursDayStart3 = (Button) findViewById(R.id.editTextThursday5);
        thursDayEnd3 = (Button) findViewById(R.id.editTextThursday6);
        thursDayStart4 = (Button) findViewById(R.id.editTextThursday7);
        thursDayEnd4 = (Button) findViewById(R.id.editTextThursday8);
        thursDayStart5 = (Button) findViewById(R.id.editTextThursday9);
        thursDayEnd5 = (Button) findViewById(R.id.editTextThursday10);

        allowed = true;


        thursDayStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_thursday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Thursday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Thursday").get(2*i).getTime_Int() && hour < wpg.data.get("Thursday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            thursDayStart1.setText(String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Thursday").set(0, new Switch("day", true, String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        thursDayEnd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_thursday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Thursday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Thursday").get(2*i).getTime_Int() && hour < wpg.data.get("Thursday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Thursday").get(0).getTime_Int() < wpg.data.get("Thursday").get(2*i).getTime_Int() && hour > wpg.data.get("Thursday").get(2*i + 1).getTime_Int()) || hour < wpg.data.get("Thursday").get(0).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            thursDayEnd1.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Thursday").set(1, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        thursDayStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_thursday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Thursday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Thursday").get(2*i).getTime_Int() && hour < wpg.data.get("Thursday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            thursDayStart2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Thursday").set(2, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        thursDayEnd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_thursday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Thursday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Thursday").get(2*i).getTime_Int() && hour < wpg.data.get("Thursday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Thursday").get(2).getTime_Int() < wpg.data.get("Thursday").get(2*i).getTime_Int() && hour > wpg.data.get("Thursday").get(2*i + 1).getTime_Int()) || hour < wpg.data.get("Thursday").get(2).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            thursDayEnd2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Thursday").set(3, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        thursDayStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_thursday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Thursday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Thursday").get(2*i).getTime_Int() && hour < wpg.data.get("Thursday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            thursDayStart3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Thursday").set(4, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        thursDayEnd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_thursday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Thursday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Thursday").get(2*i).getTime_Int() && hour < wpg.data.get("Thursday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Thursday").get(4).getTime_Int() < wpg.data.get("Thursday").get(2*i).getTime_Int() && hour > wpg.data.get("Thursday").get(2*i + 1).getTime_Int()) || hour < wpg.data.get("Thursday").get(4).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            thursDayEnd3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Thursday").set(5, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        thursDayStart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_thursday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Thursday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Thursday").get(2*i).getTime_Int() && hour < wpg.data.get("Thursday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            thursDayStart4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Thursday").set(6, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        thursDayEnd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_thursday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Thursday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Thursday").get(2*i).getTime_Int() && hour < wpg.data.get("Thursday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Thursday").get(6).getTime_Int() < wpg.data.get("Thursday").get(2*i).getTime_Int() && hour > wpg.data.get("Thursday").get(2*i + 1).getTime_Int()) || hour < wpg.data.get("Thursday").get(6).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            thursDayEnd4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Thursday").set(7, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        thursDayStart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_thursday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Thursday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Thursday").get(2*i).getTime_Int() && hour < wpg.data.get("Thursday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            thursDayStart5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Thursday").set(8, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        thursDayEnd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_thursday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Thursday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Thursday").get(2*i).getTime_Int() && hour < wpg.data.get("Thursday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Thursday").get(8).getTime_Int() < wpg.data.get("Thursday").get(2*i).getTime_Int() && hour > wpg.data.get("Thursday").get(2*i + 1).getTime_Int()) || hour < wpg.data.get("Thursday").get(8).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            thursDayEnd5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Thursday").set(9, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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
                            wpg.data.get("Thursday").set(5, new Switch("day", true, "07:30"));
                            wpg.data.get("Thursday").set(1, new Switch("night", true, "08:30"));
                            wpg.data.get("Thursday").set(6, new Switch("day", true, "18:00"));
                            wpg.data.get("Thursday").set(7, new Switch("day", true, "12:00"));
                            wpg.data.get("Thursday").set(8, new Switch("day", true, "18:00"));
                            boolean duplicates = wpg.duplicates(wpg.data.get("Thursday"));
                            System.out.println("Duplicates found "+duplicates);
                            */
                    //Upload the updated program
                    //HeatingSystem.setWeekProgram(wpg);
                    thursDayStart1.post(new Runnable() {
                        @Override
                        public void run() {
                            thursDayStart1.setText(wpg.data.get("Thursday").get(0).getTime());
                        }
                    });
                    thursDayStart2.post(new Runnable() {
                        @Override
                        public void run() {
                            thursDayStart2.setText(wpg.data.get("Thursday").get(2).getTime());
                        }
                    });
                    thursDayStart3.post(new Runnable() {
                        @Override
                        public void run() {
                            thursDayStart3.setText(wpg.data.get("Thursday").get(4).getTime());
                        }
                    });
                    thursDayStart4.post(new Runnable() {
                        @Override
                        public void run() {
                            thursDayStart4.setText(wpg.data.get("Thursday").get(6).getTime());
                        }
                    });
                    thursDayStart5.post(new Runnable() {
                        @Override
                        public void run() {
                            thursDayStart5.setText(wpg.data.get("Thursday").get(8).getTime());
                        }
                    });
                    thursDayEnd1.post(new Runnable() {
                        @Override
                        public void run() {
                            thursDayEnd1.setText(wpg.data.get("Thursday").get(1).getTime());
                        }
                    });
                    thursDayEnd2.post(new Runnable() {
                        @Override
                        public void run() {
                            thursDayEnd2.setText(wpg.data.get("Thursday").get(3).getTime());
                        }
                    });
                    thursDayEnd3.post(new Runnable() {
                        @Override
                        public void run() {
                            thursDayEnd3.setText(wpg.data.get("Thursday").get(5).getTime());
                        }
                    });
                    thursDayEnd4.post(new Runnable() {
                        @Override
                        public void run() {
                            thursDayEnd4.setText(wpg.data.get("Thursday").get(7).getTime());
                        }
                    });
                    thursDayEnd5.post(new Runnable() {
                        @Override
                        public void run() {
                            thursDayEnd5.setText(wpg.data.get("Thursday").get(9).getTime());
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
            if ((wpg.data.get("Thursday").get(2*i).getState() ^ wpg.data.get("Thursday").get(2*i+1).getState())){
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
            week_thursday.super.onBackPressed();
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
