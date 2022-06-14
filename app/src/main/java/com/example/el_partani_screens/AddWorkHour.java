package com.example.el_partani_screens;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;

public class AddWorkHour extends AppCompatActivity implements View.OnClickListener {
    CheckBox cb_11, cb_12, cb_13, cb_14, cb_15, cb_16, cb_17, cb_18, cb_19;
    CheckBox cb_21, cb_22, cb_23, cb_24, cb_25, cb_26, cb_27, cb_28, cb_29;
    CheckBox cb_31, cb_32, cb_33, cb_34, cb_35, cb_36, cb_37, cb_38, cb_39;
    CheckBox cb_41, cb_42, cb_43, cb_44, cb_45, cb_46, cb_47, cb_48, cb_49;
    CheckBox cb_51, cb_52, cb_53, cb_54, cb_55, cb_56, cb_57, cb_58, cb_59;

    Button btn_submit_hours;
    DatabaseReference meetRef;
    ArrayList<Metting> workHours, meetings;
    Intent intent;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_hour);
        initiAll();
        intent = getIntent();
        name = intent.getStringExtra("name");


        btn_submit_hours.setOnClickListener(this);
        retriveData();

    }

    @Override
    public void onClick(View view) {
        if (view == btn_submit_hours) {
            //sunday
            if (cb_11.isChecked()) {
                workHours.add(new Metting("sunday", "lesson 1"));
            }
            if (cb_12.isChecked()) {
                workHours.add(new Metting("sunday", "lesson 2"));
            }
            if (cb_13.isChecked()) {
                workHours.add(new Metting("sunday", "lesson 3"));
            }
            if (cb_14.isChecked()) {
                workHours.add(new Metting("sunday", "lesson 4"));
            }
            if (cb_15.isChecked()) {
                workHours.add(new Metting("sunday", "lesson 5"));
            }
            if (cb_16.isChecked()) {
                workHours.add(new Metting("sunday", "lesson 6"));
            }
            if (cb_17.isChecked()) {
                workHours.add(new Metting("sunday", "lesson 7"));
            }
            if (cb_18.isChecked()) {
                workHours.add(new Metting("sunday", "lesson 8"));
            }
            if (cb_19.isChecked()) {
                workHours.add(new Metting("sunday", "lesson 9"));
            }
            //monday
            if (cb_21.isChecked()) {
                workHours.add(new Metting("monday", "lesson 1"));
            }
            if (cb_22.isChecked()) {
                workHours.add(new Metting("monday", "lesson 2"));
            }
            if (cb_23.isChecked()) {
                workHours.add(new Metting("monday", "lesson 3"));
            }
            if (cb_24.isChecked()) {
                workHours.add(new Metting("monday", "lesson 4"));
            }
            if (cb_25.isChecked()) {
                workHours.add(new Metting("monday", "lesson 5"));
            }
            if (cb_26.isChecked()) {
                workHours.add(new Metting("monday", "lesson 6"));
            }
            if (cb_27.isChecked()) {
                workHours.add(new Metting("monday", "lesson 7"));
            }
            if (cb_28.isChecked()) {
                workHours.add(new Metting("monday", "lesson 8"));
            }
            if (cb_29.isChecked()) {
                workHours.add(new Metting("monday", "lesson 9"));
            }
            //tuesday
            if (cb_31.isChecked()) {
                workHours.add(new Metting("tuesday", "lesson 1"));
            }
            if (cb_32.isChecked()) {
                workHours.add(new Metting("tuesday", "lesson 2"));
            }
            if (cb_33.isChecked()) {
                workHours.add(new Metting("tuesday", "lesson 3"));
            }
            if (cb_34.isChecked()) {
                workHours.add(new Metting("tuesday", "lesson 4"));
            }
            if (cb_35.isChecked()) {
                workHours.add(new Metting("tuesday", "lesson 5"));
            }
            if (cb_36.isChecked()) {
                workHours.add(new Metting("tuesday", "lesson 6"));
            }
            if (cb_37.isChecked()) {
                workHours.add(new Metting("tuesday", "lesson 7"));
            }
            if (cb_38.isChecked()) {
                workHours.add(new Metting("tuesday", "lesson 8"));
            }
            if (cb_39.isChecked()) {
                workHours.add(new Metting("tuesday", "lesson 9"));
            }
            //wednesday
            if (cb_41.isChecked()) {
                workHours.add(new Metting("wednesday", "lesson 1"));
            }
            if (cb_42.isChecked()) {
                workHours.add(new Metting("wednesday", "lesson 2"));
            }
            if (cb_43.isChecked()) {
                workHours.add(new Metting("wednesday", "lesson 3"));
            }
            if (cb_44.isChecked()) {
                workHours.add(new Metting("wednesday", "lesson 4"));
            }
            if (cb_45.isChecked()) {
                workHours.add(new Metting("wednesday", "lesson 5"));
            }
            if (cb_46.isChecked()) {
                workHours.add(new Metting("wednesday", "lesson 6"));
            }
            if (cb_47.isChecked()) {
                workHours.add(new Metting("wednesday", "lesson 7"));
            }
            if (cb_48.isChecked()) {
                workHours.add(new Metting("wednesday", "lesson 8"));
            }
            if (cb_49.isChecked()) {
                workHours.add(new Metting("wednesday", "lesson 9"));
            }
            //thursday
            if (cb_51.isChecked()) {
                workHours.add(new Metting("thursday", "lesson 1"));
            }
            if (cb_52.isChecked()) {
                workHours.add(new Metting("thursday", "lesson 2"));
            }
            if (cb_53.isChecked()) {
                workHours.add(new Metting("thursday", "lesson 3"));
            }
            if (cb_54.isChecked()) {
                workHours.add(new Metting("thursday", "lesson 4"));
            }
            if (cb_55.isChecked()) {
                workHours.add(new Metting("thursday", "lesson 5"));
            }
            if (cb_56.isChecked()) {
                workHours.add(new Metting("thursday", "lesson 6"));
            }
            if (cb_57.isChecked()) {
                workHours.add(new Metting("thursday", "lesson 7"));
            }
            if (cb_58.isChecked()) {
                workHours.add(new Metting("thursday", "lesson 8"));
            }
            if (cb_59.isChecked()) {
                workHours.add(new Metting("thursday", "lesson 9"));
            }
            for (Metting m : workHours) {
                addMetting(m);
            }
        }
    }//endOf_onclick

    private void addMetting(Metting m) {
        meetRef = FirebaseDatabase.getInstance().getReference("TeacherMeetings/").push();

        Log.d("tag", "partani:" + m.getDay() + " " + m.getHour());
        //add to frirebase with key
        Log.d("tag", meetRef.getKey());
        //meetref is concreate initilaize and referenced
        m.setKey(name);
        meetRef.setValue(m);
    }

    /**
     * retrieves data from database
     */
    public void retriveData() {
        meetRef = FirebaseDatabase.getInstance().getReference("TeacherMeetings/");

        meetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                meetings = new ArrayList<Metting>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Metting m = data.getValue(Metting.class);
                    //add checking if m is of the name םכ בוררקמא אקשביקר
                    meetings.add(m);
                    checkMetting(m);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void delteAfterShowing() {
        DatabaseReference current = FirebaseDatabase.getInstance().getReference("TeacherMeetings/" );

        meetRef = FirebaseDatabase.getInstance().getReference("TeacherMeetings/");
            meetRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    meetings = new ArrayList<Metting>();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Metting m = data.getValue(Metting.class);
                        if(m.getKey().equals(name))
                            current.removeValue();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }

    private void checkMetting(Metting m) {
        if(m.getDay().equals("sunday")){
            if(m.getHour().equals("lesson 1"))
                cb_11.setChecked(true);
            else if(m.getHour().equals("lesson 2"))
                cb_12.setChecked(true);
            else if(m.getHour().equals("lesson 3"))
                cb_13.setChecked(true);
            else if(m.getHour().equals("lesson 4"))
                cb_14.setChecked(true);
            else if(m.getHour().equals("lesson 5"))
                cb_15.setChecked(true);
            else if(m.getHour().equals("lesson 6"))
                cb_16.setChecked(true);
            else if(m.getHour().equals("lesson 7"))
                cb_17.setChecked(true);
            else if(m.getHour().equals("lesson 8"))
                cb_18.setChecked(true);
            else if(m.getHour().equals("lesson 9"))
                cb_19.setChecked(true);
        }
        else if(m.getDay().equals("monday")){
            if(m.getHour().equals("lesson 1"))
                cb_21.setChecked(true);
            else if(m.getHour().equals("lesson 2"))
                cb_22.setChecked(true);
            else if(m.getHour().equals("lesson 3"))
                cb_23.setChecked(true);
            else if(m.getHour().equals("lesson 4"))
                cb_24.setChecked(true);
            else if(m.getHour().equals("lesson 5"))
                cb_25.setChecked(true);
            else if(m.getHour().equals("lesson 6"))
                cb_26.setChecked(true);
            else if(m.getHour().equals("lesson 7"))
                cb_27.setChecked(true);
            else if(m.getHour().equals("lesson 8"))
                cb_28.setChecked(true);
            else if(m.getHour().equals("lesson 9"))
                cb_29.setChecked(true);
        }
        else if(m.getDay().equals("tuesday")){
            if(m.getHour().equals("lesson 1"))
                cb_31.setChecked(true);
            else if(m.getHour().equals("lesson 2"))
                cb_32.setChecked(true);
            else if(m.getHour().equals("lesson 3"))
                cb_33.setChecked(true);
            else if(m.getHour().equals("lesson 4"))
                cb_34.setChecked(true);
            else if(m.getHour().equals("lesson 5"))
                cb_35.setChecked(true);
            else if(m.getHour().equals("lesson 6"))
                cb_36.setChecked(true);
            else if(m.getHour().equals("lesson 7"))
                cb_37.setChecked(true);
            else if(m.getHour().equals("lesson 8"))
                cb_38.setChecked(true);
            else if(m.getHour().equals("lesson 9"))
                cb_39.setChecked(true);
        }
        else if(m.getDay().equals("wednesday")){
            if(m.getHour().equals("lesson 1"))
                cb_41.setChecked(true);
            else if(m.getHour().equals("lesson 2"))
                cb_42.setChecked(true);
            else if(m.getHour().equals("lesson 3"))
                cb_43.setChecked(true);
            else if(m.getHour().equals("lesson 4"))
                cb_44.setChecked(true);
            else if(m.getHour().equals("lesson 5"))
                cb_45.setChecked(true);
            else if(m.getHour().equals("lesson 6"))
                cb_46.setChecked(true);
            else if(m.getHour().equals("lesson 7"))
                cb_47.setChecked(true);
            else if(m.getHour().equals("lesson 8"))
                cb_48.setChecked(true);
            else if(m.getHour().equals("lesson 9"))
                cb_49.setChecked(true);
        }
        else{
            if(m.getHour().equals("lesson 1"))
                cb_51.setChecked(true);
            else if(m.getHour().equals("lesson 2"))
                cb_52.setChecked(true);
            else if(m.getHour().equals("lesson 3"))
                cb_53.setChecked(true);
            else if(m.getHour().equals("lesson 4"))
                cb_54.setChecked(true);
            else if(m.getHour().equals("lesson 5"))
                cb_55.setChecked(true);
            else if(m.getHour().equals("lesson 6"))
                cb_56.setChecked(true);
            else if(m.getHour().equals("lesson 7"))
                cb_57.setChecked(true);
            else if(m.getHour().equals("lesson 8"))
                cb_58.setChecked(true);
            else if(m.getHour().equals("lesson 9"))
                cb_59.setChecked(true);
        }
    }

    /**
     * referencing lements in the activity
     */
    private void initiAll() {
        workHours = new ArrayList<Metting>();
        btn_submit_hours = findViewById(R.id.btn_submit_hours);

        cb_11 = findViewById(R.id.cb_11);
        cb_12 = findViewById(R.id.cb_12);
        cb_13 = findViewById(R.id.cb_13);
        cb_14 = findViewById(R.id.cb_14);
        cb_15 = findViewById(R.id.cb_15);
        cb_16 = findViewById(R.id.cb_16);
        cb_17 = findViewById(R.id.cb_17);
        cb_18 = findViewById(R.id.cb_18);
        cb_19 = findViewById(R.id.cb_19);

        cb_21 = findViewById(R.id.cb_21);
        cb_22 = findViewById(R.id.cb_22);
        cb_23 = findViewById(R.id.cb_23);
        cb_24 = findViewById(R.id.cb_24);
        cb_25 = findViewById(R.id.cb_25);
        cb_26 = findViewById(R.id.cb_26);
        cb_27 = findViewById(R.id.cb_27);
        cb_28 = findViewById(R.id.cb_28);
        cb_29 = findViewById(R.id.cb_29);

        cb_31 = findViewById(R.id.cb_31);
        cb_32 = findViewById(R.id.cb_32);
        cb_33 = findViewById(R.id.cb_33);
        cb_34 = findViewById(R.id.cb_34);
        cb_35 = findViewById(R.id.cb_35);
        cb_36 = findViewById(R.id.cb_36);
        cb_37 = findViewById(R.id.cb_37);
        cb_38 = findViewById(R.id.cb_38);
        cb_39 = findViewById(R.id.cb_39);

        cb_41 = findViewById(R.id.cb_41);
        cb_42 = findViewById(R.id.cb_42);
        cb_43 = findViewById(R.id.cb_43);
        cb_44 = findViewById(R.id.cb_44);
        cb_45 = findViewById(R.id.cb_45);
        cb_46 = findViewById(R.id.cb_46);
        cb_47 = findViewById(R.id.cb_47);
        cb_48 = findViewById(R.id.cb_48);
        cb_49 = findViewById(R.id.cb_49);

        cb_51 = findViewById(R.id.cb_51);
        cb_52 = findViewById(R.id.cb_52);
        cb_53 = findViewById(R.id.cb_53);
        cb_54 = findViewById(R.id.cb_54);
        cb_55 = findViewById(R.id.cb_55);
        cb_56 = findViewById(R.id.cb_56);
        cb_57 = findViewById(R.id.cb_57);
        cb_58 = findViewById(R.id.cb_58);
        cb_59 = findViewById(R.id.cb_59);

    }


}