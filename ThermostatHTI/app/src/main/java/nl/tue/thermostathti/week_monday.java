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

public class week_monday extends AppCompatActivity {

    WeekProgram wpg;
    int index;
    Button monDayStart1, monDayEnd1, monDayStart2, monDayEnd2, monDayStart3, monDayEnd3, monDayStart4, monDayEnd4, monDayStart5, monDayEnd5;
    Boolean allowed, intervalSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_monday);

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

        monDayStart1 = (Button) findViewById(R.id.editTextMonday1);
        monDayEnd1 = (Button) findViewById(R.id.editTextMonday2);
        monDayStart2 = (Button) findViewById(R.id.editTextMonday3);
        monDayEnd2 = (Button) findViewById(R.id.editTextMonday4);
        monDayStart3 = (Button) findViewById(R.id.editTextMonday5);
        monDayEnd3 = (Button) findViewById(R.id.editTextMonday6);
        monDayStart4 = (Button) findViewById(R.id.editTextMonday7);
        monDayEnd4 = (Button) findViewById(R.id.editTextMonday8);
        monDayStart5 = (Button) findViewById(R.id.editTextMonday9);
        monDayEnd5 = (Button) findViewById(R.id.editTextMonday10);

        allowed = true;


        monDayStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_monday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Monday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Monday").get(2*i).getTime_Int() && hour < wpg.data.get("Monday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            monDayStart1.setText(String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Monday").set(0, new Switch("day", true, String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        monDayEnd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_monday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Monday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Monday").get(2*i).getTime_Int() && hour < wpg.data.get("Monday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Monday").get(0).getTime_Int() < wpg.data.get("Monday").get(2*i).getTime_Int() && hour > wpg.data.get("Monday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                        monDayEnd1.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Monday").set(1, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        monDayStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_monday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Monday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Monday").get(2*i).getTime_Int() && hour < wpg.data.get("Monday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                        monDayStart2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Monday").set(2, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        monDayEnd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_monday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Monday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Monday").get(2*i).getTime_Int() && hour < wpg.data.get("Monday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Monday").get(2).getTime_Int() < wpg.data.get("Monday").get(2*i).getTime_Int() && hour > wpg.data.get("Monday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                        monDayEnd2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Monday").set(3, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        monDayStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_monday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Monday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Monday").get(2*i).getTime_Int() && hour < wpg.data.get("Monday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                        monDayStart3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Monday").set(4, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        monDayEnd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_monday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Monday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Monday").get(2*i).getTime_Int() && hour < wpg.data.get("Monday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Monday").get(4).getTime_Int() < wpg.data.get("Monday").get(2*i).getTime_Int() && hour > wpg.data.get("Monday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                        monDayEnd3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Monday").set(5, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        monDayStart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_monday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Monday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Monday").get(2*i).getTime_Int() && hour < wpg.data.get("Monday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                        monDayStart4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Monday").set(6, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        monDayEnd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_monday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Monday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Monday").get(2*i).getTime_Int() && hour < wpg.data.get("Monday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Monday").get(6).getTime_Int() < wpg.data.get("Monday").get(2*i).getTime_Int() && hour > wpg.data.get("Monday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                        monDayEnd4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Monday").set(7, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        monDayStart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_monday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Monday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Monday").get(2*i).getTime_Int() && hour < wpg.data.get("Monday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                        monDayStart5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Monday").set(8, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        monDayEnd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_monday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Monday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Monday").get(2*i).getTime_Int() && hour < wpg.data.get("Monday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Monday").get(8).getTime_Int() < wpg.data.get("Monday").get(2*i).getTime_Int() && hour > wpg.data.get("Monday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                        monDayEnd5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                        wpg.data.get("Monday").set(9, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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
                            wpg.data.get("Monday").set(5, new Switch("day", true, "07:30"));
                            wpg.data.get("Monday").set(1, new Switch("night", true, "08:30"));
                            wpg.data.get("Monday").set(6, new Switch("day", true, "18:00"));
                            wpg.data.get("Monday").set(7, new Switch("day", true, "12:00"));
                            wpg.data.get("Monday").set(8, new Switch("day", true, "18:00"));
                            boolean duplicates = wpg.duplicates(wpg.data.get("Monday"));
                            System.out.println("Duplicates found "+duplicates);
                            */
                    //Upload the updated program
                    //HeatingSystem.setWeekProgram(wpg);
                    monDayStart1.post(new Runnable() {
                        @Override
                        public void run() {
                            monDayStart1.setText(wpg.data.get("Monday").get(0).getTime());
                        }
                    });
                    monDayStart2.post(new Runnable() {
                        @Override
                        public void run() {
                            monDayStart2.setText(wpg.data.get("Monday").get(2).getTime());
                        }
                    });
                    monDayStart3.post(new Runnable() {
                        @Override
                        public void run() {
                            monDayStart3.setText(wpg.data.get("Monday").get(4).getTime());
                        }
                    });
                    monDayStart4.post(new Runnable() {
                        @Override
                        public void run() {
                            monDayStart4.setText(wpg.data.get("Monday").get(6).getTime());
                        }
                    });
                    monDayStart5.post(new Runnable() {
                        @Override
                        public void run() {
                            monDayStart5.setText(wpg.data.get("Monday").get(8).getTime());
                        }
                    });
                    monDayEnd1.post(new Runnable() {
                        @Override
                        public void run() {
                            monDayEnd1.setText(wpg.data.get("Monday").get(1).getTime());
                        }
                    });
                    monDayEnd2.post(new Runnable() {
                        @Override
                        public void run() {
                            monDayEnd2.setText(wpg.data.get("Monday").get(3).getTime());
                        }
                    });
                    monDayEnd3.post(new Runnable() {
                        @Override
                        public void run() {
                            monDayEnd3.setText(wpg.data.get("Monday").get(5).getTime());
                        }
                    });
                    monDayEnd4.post(new Runnable() {
                        @Override
                        public void run() {
                            monDayEnd4.setText(wpg.data.get("Monday").get(7).getTime());
                        }
                    });
                    monDayEnd5.post(new Runnable() {
                        @Override
                        public void run() {
                            monDayEnd5.setText(wpg.data.get("Monday").get(9).getTime());
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
            if ((wpg.data.get("Monday").get(2*i).getState() ^ wpg.data.get("Monday").get(2*i+1).getState())){
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
            week_monday.super.onBackPressed();
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
