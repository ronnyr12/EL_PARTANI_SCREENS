package com.example.el_partani_screens;

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

import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

public class Teacher_schedule extends AppCompatActivity {
    ListView lsvts;
    Dialog dialogts;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_schedule);
        Slidr.attach(this);


        lsvts = findViewById(R.id.lvTecherSchedule);
        ArrayList<Teacher_schedule_row> arr = new ArrayList<>();

        arr.add(new Teacher_schedule_row(R.drawable.teacherpic4,11,"יום ראשון שיעור רביעי:"));
        arr.add(new Teacher_schedule_row(R.drawable.teacherpic1,8,"יום שני שיעור ראשון:"));
        arr.add(new Teacher_schedule_row(R.drawable.teacherpic2,9,"יום שלישי שיעור שני :"));
        arr.add(new Teacher_schedule_row(R.drawable.teacherpic3,13,"יום שלישי שיעור שישי:"));
        arr.add(new Teacher_schedule_row(R.drawable.teacherpic5,15,"יום רביעי שיעור שמיני:"));
        arr.add(new Teacher_schedule_row(R.drawable.teacherpic6,10,"יום חמישי שיעור שלישי:"));

        Teacher_scheduleAdapter adapter = new Teacher_scheduleAdapter(this,0,arr);
        lsvts.setAdapter(adapter);
        dialogts = new Dialog(Teacher_schedule.this);
        dialogts.setContentView(R.layout.custom_dialog_tss);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            dialogts.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_teacher_schedule));
        }
        dialogts.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogts.setCancelable(false);

        Button btn_okey_tss = dialogts.findViewById(R.id.btn_okey_tss);
        Button btn_cancel_tss = dialogts.findViewById(R.id.btn_cancel_tss);

        btn_okey_tss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Teacher_schedule.this, "you are made an apoiment in this hour. :)", Toast.LENGTH_SHORT).show();
                    String message = "you have a new apoinment in this hour";// צריך להוסיף את השם של התלמיד
                    String phoneNumber="0504718888"; //צריך לשנות את זה למספר של המורה
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
                    intent.putExtra("sms_body", message);
                    startActivity(intent);
                dialogts.dismiss();
                startActivity(new Intent(Teacher_schedule.this, studentSchedule.class));
            }
        });
        btn_cancel_tss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Teacher_schedule.this, "you didnt made an apoiment in this hour. :(", Toast.LENGTH_SHORT).show();
                dialogts.dismiss();
            }
        });

        lsvts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogts.show();
            }
        });
    }
}