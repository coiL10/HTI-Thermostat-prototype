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

public class week_wednesday extends AppCompatActivity {

    WeekProgram wpg;
    int index;
    Button wednesDayStart1, wednesDayEnd1, wednesDayStart2, wednesDayEnd2, wednesDayStart3, wednesDayEnd3, wednesDayStart4, wednesDayEnd4, wednesDayStart5, wednesDayEnd5;
    Boolean allowed, intervalSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_wednesday);

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

        wednesDayStart1 = (Button) findViewById(R.id.editTextWednesday1);
        wednesDayEnd1 = (Button) findViewById(R.id.editTextWednesday2);
        wednesDayStart2 = (Button) findViewById(R.id.editTextWednesday3);
        wednesDayEnd2 = (Button) findViewById(R.id.editTextWednesday4);
        wednesDayStart3 = (Button) findViewById(R.id.editTextWednesday5);
        wednesDayEnd3 = (Button) findViewById(R.id.editTextWednesday6);
        wednesDayStart4 = (Button) findViewById(R.id.editTextWednesday7);
        wednesDayEnd4 = (Button) findViewById(R.id.editTextWednesday8);
        wednesDayStart5 = (Button) findViewById(R.id.editTextWednesday9);
        wednesDayEnd5 = (Button) findViewById(R.id.editTextWednesday10);

        allowed = true;


        wednesDayStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_wednesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Wednesday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour < wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            wednesDayStart1.setText(String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Wednesday").set(0, new Switch("day", true, String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        wednesDayEnd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_wednesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Wednesday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour < wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Wednesday").get(0).getTime_Int() < wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour > wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()) || hour < wpg.data.get("Wednesday").get(0).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            wednesDayEnd1.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Wednesday").set(1, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        wednesDayStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_wednesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Wednesday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour < wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            wednesDayStart2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Wednesday").set(2, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        wednesDayEnd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_wednesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Wednesday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour < wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Wednesday").get(2).getTime_Int() < wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour > wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()) || hour < wpg.data.get("Wednesday").get(2).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            wednesDayEnd2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Wednesday").set(3, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        wednesDayStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_wednesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Wednesday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour < wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            wednesDayStart3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Wednesday").set(4, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        wednesDayEnd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_wednesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Wednesday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour < wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Wednesday").get(4).getTime_Int() < wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour > wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()) || hour < wpg.data.get("Wednesday").get(4).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            wednesDayEnd3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Wednesday").set(5, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        wednesDayStart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_wednesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Wednesday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour < wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            wednesDayStart4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Wednesday").set(6, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        wednesDayEnd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_wednesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Wednesday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour < wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Wednesday").get(6).getTime_Int() < wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour > wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()) || hour < wpg.data.get("Wednesday").get(6).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            wednesDayEnd4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Wednesday").set(7, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        wednesDayStart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_wednesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Wednesday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour < wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            wednesDayStart5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Wednesday").set(8, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        wednesDayEnd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_wednesday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Wednesday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour < wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Wednesday").get(8).getTime_Int() < wpg.data.get("Wednesday").get(2*i).getTime_Int() && hour > wpg.data.get("Wednesday").get(2*i + 1).getTime_Int()) || hour < wpg.data.get("Wednesday").get(8).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            wednesDayEnd5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Wednesday").set(9, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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
                            wpg.data.get("Wednesday").set(5, new Switch("day", true, "07:30"));
                            wpg.data.get("Wednesday").set(1, new Switch("night", true, "08:30"));
                            wpg.data.get("Wednesday").set(6, new Switch("day", true, "18:00"));
                            wpg.data.get("Wednesday").set(7, new Switch("day", true, "12:00"));
                            wpg.data.get("Wednesday").set(8, new Switch("day", true, "18:00"));
                            boolean duplicates = wpg.duplicates(wpg.data.get("Wednesday"));
                            System.out.println("Duplicates found "+duplicates);
                            */
                    //Upload the updated program
                    //HeatingSystem.setWeekProgram(wpg);
                    wednesDayStart1.post(new Runnable() {
                        @Override
                        public void run() {
                            wednesDayStart1.setText(wpg.data.get("Wednesday").get(0).getTime());
                        }
                    });
                    wednesDayStart2.post(new Runnable() {
                        @Override
                        public void run() {
                            wednesDayStart2.setText(wpg.data.get("Wednesday").get(2).getTime());
                        }
                    });
                    wednesDayStart3.post(new Runnable() {
                        @Override
                        public void run() {
                            wednesDayStart3.setText(wpg.data.get("Wednesday").get(4).getTime());
                        }
                    });
                    wednesDayStart4.post(new Runnable() {
                        @Override
                        public void run() {
                            wednesDayStart4.setText(wpg.data.get("Wednesday").get(6).getTime());
                        }
                    });
                    wednesDayStart5.post(new Runnable() {
                        @Override
                        public void run() {
                            wednesDayStart5.setText(wpg.data.get("Wednesday").get(8).getTime());
                        }
                    });
                    wednesDayEnd1.post(new Runnable() {
                        @Override
                        public void run() {
                            wednesDayEnd1.setText(wpg.data.get("Wednesday").get(1).getTime());
                        }
                    });
                    wednesDayEnd2.post(new Runnable() {
                        @Override
                        public void run() {
                            wednesDayEnd2.setText(wpg.data.get("Wednesday").get(3).getTime());
                        }
                    });
                    wednesDayEnd3.post(new Runnable() {
                        @Override
                        public void run() {
                            wednesDayEnd3.setText(wpg.data.get("Wednesday").get(5).getTime());
                        }
                    });
                    wednesDayEnd4.post(new Runnable() {
                        @Override
                        public void run() {
                            wednesDayEnd4.setText(wpg.data.get("Wednesday").get(7).getTime());
                        }
                    });
                    wednesDayEnd5.post(new Runnable() {
                        @Override
                        public void run() {
                            wednesDayEnd5.setText(wpg.data.get("Wednesday").get(9).getTime());
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
            if ((wpg.data.get("Wednesday").get(2*i).getState() ^ wpg.data.get("Wednesday").get(2*i+1).getState())){
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
            week_wednesday.super.onBackPressed();
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
