package com.example.el_partani_screens;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ParatniSchedule extends AppCompatActivity {
    Intent intent;
    String t_name = "", s_name = "", prof_name = "";
    ListView lv_partani;
    TextView tv_teacher_nme, tv_proff;
    String phone="";
    DatabaseReference meetRef, partaniRef;
    ArrayList<Metting> meetings;
    ArrayList<Partni> partaniyot;
    MeetingAdapter meetingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paratni_schedule);

        connectAll();
        retriveData();
        setPhone();

        //now here in the code the is of meetings is fill
        lv_partani.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("xxx",
                        meetings.get(position).getDay()+" "+meetings.get(position).getHour());

                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                String date = df.format(Calendar.getInstance().getTime());
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                Partni p = new Partni(meetings.get(position).getDay(),
                        meetings.get(position).getHour(), t_name, s_name, date, phone);

                partaniRef = FirebaseDatabase.getInstance().getReference("Partaniyot/").push();
                partaniRef.setValue(p);

                //add - send sms
                Intent intent = new Intent(ParatniSchedule.this,
                        studentSchedule.class);
                intent.putExtra("name", s_name);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

    private void setPhone() {
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("csv");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String teacher = snapshot.child("name").getValue().toString();
                    if(teacher.equals(t_name)) {
                        phone = snapshot.child("phone").getValue().toString();
                        return;
                    }
                    else{

                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void retriveData() {
        meetRef = FirebaseDatabase.getInstance().getReference("TeacherMeetings/");

        meetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                meetings = new ArrayList<Metting>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Metting m = data.getValue(Metting.class);
                    Log.d("xx", "t name:"+t_name);
                    Log.d("xx", "proff:"+prof_name);
                    if (t_name.equals(m.getKey()))
                        meetings.add(m);
                }

                for (Metting mt: meetings) {
                    Log.d("xx", mt.getDay()+" "+mt.getHour());
                }
                meetingAdapter = new MeetingAdapter(ParatniSchedule.this,0,
                        0,meetings);
                lv_partani.setAdapter(meetingAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void connectAll() {
        lv_partani = findViewById(R.id.lv_partani);
        tv_teacher_nme = findViewById(R.id.tv_teacher_nme);
        tv_proff = findViewById(R.id.tv_proff);

        intent = getIntent();
        t_name = intent.getStringExtra("t_name");
        s_name = intent.getStringExtra("s_name");
        prof_name = intent.getStringExtra("prof_name");

        tv_teacher_nme.setText(t_name);
        tv_proff.setText(prof_name);
        partaniyot = new ArrayList<Partni>();
        meetings = new ArrayList<Metting>();

    }
}