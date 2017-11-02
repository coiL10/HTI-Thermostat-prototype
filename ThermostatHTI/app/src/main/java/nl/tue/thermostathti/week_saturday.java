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

public class week_saturday extends AppCompatActivity {

    WeekProgram wpg;
    int index;
    Button saturDayStart1, saturDayEnd1, saturDayStart2, saturDayEnd2, saturDayStart3, saturDayEnd3, saturDayStart4, saturDayEnd4, saturDayStart5, saturDayEnd5;
    Boolean allowed, intervalSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_saturday);

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

        saturDayStart1 = (Button) findViewById(R.id.editTextSaturday1);
        saturDayEnd1 = (Button) findViewById(R.id.editTextSaturday2);
        saturDayStart2 = (Button) findViewById(R.id.editTextSaturday3);
        saturDayEnd2 = (Button) findViewById(R.id.editTextSaturday4);
        saturDayStart3 = (Button) findViewById(R.id.editTextSaturday5);
        saturDayEnd3 = (Button) findViewById(R.id.editTextSaturday6);
        saturDayStart4 = (Button) findViewById(R.id.editTextSaturday7);
        saturDayEnd4 = (Button) findViewById(R.id.editTextSaturday8);
        saturDayStart5 = (Button) findViewById(R.id.editTextSaturday9);
        saturDayEnd5 = (Button) findViewById(R.id.editTextSaturday10);

        allowed = true;


        saturDayStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_saturday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Saturday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Saturday").get(2*i).getTime_Int() && hour < wpg.data.get("Saturday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            saturDayStart1.setText(String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Saturday").set(0, new Switch("day", true, String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        saturDayEnd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_saturday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Saturday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Saturday").get(2*i).getTime_Int() && hour < wpg.data.get("Saturday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Saturday").get(0).getTime_Int() < wpg.data.get("Saturday").get(2*i).getTime_Int() && hour > wpg.data.get("Saturday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            saturDayEnd1.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Saturday").set(1, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        saturDayStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_saturday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Saturday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Saturday").get(2*i).getTime_Int() && hour < wpg.data.get("Saturday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            saturDayStart2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Saturday").set(2, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        saturDayEnd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_saturday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Saturday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Saturday").get(2*i).getTime_Int() && hour < wpg.data.get("Saturday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Saturday").get(2).getTime_Int() < wpg.data.get("Saturday").get(2*i).getTime_Int() && hour > wpg.data.get("Saturday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            saturDayEnd2.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Saturday").set(3, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        saturDayStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_saturday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Saturday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Saturday").get(2*i).getTime_Int() && hour < wpg.data.get("Saturday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            saturDayStart3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Saturday").set(4, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        saturDayEnd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_saturday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Saturday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Saturday").get(2*i).getTime_Int() && hour < wpg.data.get("Saturday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Saturday").get(4).getTime_Int() < wpg.data.get("Saturday").get(2*i).getTime_Int() && hour > wpg.data.get("Saturday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            saturDayEnd3.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Saturday").set(5, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        saturDayStart4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_saturday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Saturday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Saturday").get(2*i).getTime_Int() && hour < wpg.data.get("Saturday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            saturDayStart4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Saturday").set(6, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        saturDayEnd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_saturday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Saturday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Saturday").get(2*i).getTime_Int() && hour < wpg.data.get("Saturday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Saturday").get(6).getTime_Int() < wpg.data.get("Saturday").get(2*i).getTime_Int() && hour > wpg.data.get("Saturday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            saturDayEnd4.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Saturday").set(7, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        saturDayStart5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_saturday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Saturday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if (hour > wpg.data.get("Saturday").get(2*i).getTime_Int() && hour < wpg.data.get("Saturday").get(2*i + 1).getTime_Int()){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            saturDayStart5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Saturday").set(8, new Switch("day", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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

        saturDayEnd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowed = true;
                TimePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                mTimePicker = new TimePickerDialog(week_saturday.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        for (int i = 0; i < 5; i++) {
                            if (wpg.data.get("Saturday").get(2*i).getState()){
                                int hour = selectedHour*100 + selectedMinute;
                                if ((hour > wpg.data.get("Saturday").get(2*i).getTime_Int() && hour < wpg.data.get("Saturday").get(2*i + 1).getTime_Int()) || (wpg.data.get("Saturday").get(8).getTime_Int() < wpg.data.get("Saturday").get(2*i).getTime_Int() && hour > wpg.data.get("Saturday").get(2*i + 1).getTime_Int())){
                                    allowed = false;
                                }
                            }
                        }
                        if (allowed) {
                            saturDayEnd5.setText(String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute));
                            wpg.data.get("Saturday").set(9, new Switch("night", true, String.format("%02d",selectedHour) + ":" + String.format("%02d", selectedMinute)));
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
                            wpg.data.get("Saturday").set(5, new Switch("day", true, "07:30"));
                            wpg.data.get("Saturday").set(1, new Switch("night", true, "08:30"));
                            wpg.data.get("Saturday").set(6, new Switch("day", true, "18:00"));
                            wpg.data.get("Saturday").set(7, new Switch("day", true, "12:00"));
                            wpg.data.get("Saturday").set(8, new Switch("day", true, "18:00"));
                            boolean duplicates = wpg.duplicates(wpg.data.get("Saturday"));
                            System.out.println("Duplicates found "+duplicates);
                            */
                    //Upload the updated program
                    //HeatingSystem.setWeekProgram(wpg);
                    saturDayStart1.post(new Runnable() {
                        @Override
                        public void run() {
                            saturDayStart1.setText(wpg.data.get("Saturday").get(0).getTime());
                        }
                    });
                    saturDayStart2.post(new Runnable() {
                        @Override
                        public void run() {
                            saturDayStart2.setText(wpg.data.get("Saturday").get(2).getTime());
                        }
                    });
                    saturDayStart3.post(new Runnable() {
                        @Override
                        public void run() {
                            saturDayStart3.setText(wpg.data.get("Saturday").get(4).getTime());
                        }
                    });
                    saturDayStart4.post(new Runnable() {
                        @Override
                        public void run() {
                            saturDayStart4.setText(wpg.data.get("Saturday").get(6).getTime());
                        }
                    });
                    saturDayStart5.post(new Runnable() {
                        @Override
                        public void run() {
                            saturDayStart5.setText(wpg.data.get("Saturday").get(8).getTime());
                        }
                    });
                    saturDayEnd1.post(new Runnable() {
                        @Override
                        public void run() {
                            saturDayEnd1.setText(wpg.data.get("Saturday").get(1).getTime());
                        }
                    });
                    saturDayEnd2.post(new Runnable() {
                        @Override
                        public void run() {
                            saturDayEnd2.setText(wpg.data.get("Saturday").get(3).getTime());
                        }
                    });
                    saturDayEnd3.post(new Runnable() {
                        @Override
                        public void run() {
                            saturDayEnd3.setText(wpg.data.get("Saturday").get(5).getTime());
                        }
                    });
                    saturDayEnd4.post(new Runnable() {
                        @Override
                        public void run() {
                            saturDayEnd4.setText(wpg.data.get("Saturday").get(7).getTime());
                        }
                    });
                    saturDayEnd5.post(new Runnable() {
                        @Override
                        public void run() {
                            saturDayEnd5.setText(wpg.data.get("Saturday").get(9).getTime());
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
            if ((wpg.data.get("Saturday").get(2*i).getState() ^ wpg.data.get("Saturday").get(2*i+1).getState())){
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
            week_saturday.super.onBackPressed();
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
