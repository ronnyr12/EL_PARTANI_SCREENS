package com.example.el_partani_screens;

import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class studentSchedule extends AppCompatActivity {
    ListView lvStudentSchedule;
    FloatingActionButton btn_moveToPS;
    Dialog dialogss;
    TextView tv_name_st;
    String name = "";
    Intent intent;
    DatabaseReference  partaniRef;
    ArrayList<Partni> partaniyot;
    PartniAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_schedule_screen);

        intent = getIntent();
        name = intent.getStringExtra("name");
        tv_name_st = findViewById(R.id.tv_name_st);
        tv_name_st.setText(name);
        lvStudentSchedule = findViewById(R.id.lvStudentSchedule);
        ArrayList<Teacher_schedule_row> arr = new ArrayList<>();

        retriveData();

        Teacher_scheduleAdapter adapter = new Teacher_scheduleAdapter(this,0,arr);

        dialogss = new Dialog(studentSchedule.this);
        dialogss.setContentView(R.layout.custom_dialog_sss);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            dialogss.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_teacher_schedule));
        }
        dialogss.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogss.setCancelable(false);

        Button btn_okey_tss = dialogss.findViewById(R.id.btn_okey_tss);

        /*btn_okey_tss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(studentSchedule.this, "you deleted your apoiment in this hour. :)", Toast.LENGTH_SHORT).show();
                String message = "your apoinment in this hour has been canceld";// צריך להוסיף את השם של התלמיד
                String phoneNumber="0504718888"; //צריך לשנות את זה למספר של המורה
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
                intent.putExtra("sms_body", message);
                startActivity(intent);
                dialogss.dismiss();
            }
        });
        btn_cancel_tss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(studentSchedule.this, "you saved your apoiment in this hour. :(", Toast.LENGTH_SHORT).show();
                dialogss.dismiss();
            }
        });*/
        lvStudentSchedule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogss.show();

            }
        });
        lvStudentSchedule.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Partni p = partaniyot.get(position);
                DatabaseReference current = FirebaseDatabase.getInstance().getReference("Partaniyot/" + p.getKey());
                current.removeValue();
                Toast.makeText(studentSchedule.this, "you deleted your apoiment in this hour. :)", Toast.LENGTH_SHORT).show();
                String message = "your apoinment in this hour has been canceld";// צריך להוסיף את השם של התלמיד
                String phoneNumber="0504718888"; //צריך לשנות את זה למספר של המורה
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
                intent.putExtra("sms_body", message);
                startActivity(intent);
                return true;


            }
        });
        btn_moveToPS = findViewById(R.id.btn_moveToPS);
        btn_moveToPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(studentSchedule.this, Profession_screen.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }

    public void retriveData() {
        partaniRef = FirebaseDatabase.getInstance().getReference("Partaniyot/");

        partaniRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                partaniyot = new ArrayList<Partni>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Partni prt = data.getValue(Partni.class);
                    String st_nm = data.child("studentName").getValue().toString();
                    if(st_nm.equals(name)) {
                        partaniyot.add(prt);
                    }

                }


                adapter = new PartniAdapter(studentSchedule.this,0,
                        0,partaniyot);
                lvStudentSchedule.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}