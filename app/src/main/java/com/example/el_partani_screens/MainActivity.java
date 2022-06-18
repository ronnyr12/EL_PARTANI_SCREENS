package com.example.el_partani_screens;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String studentName="", proffession="";
    ListView lvT;
    ArrayList<Teacher> teachersList;
    TeacherAdapter teacheradapter;
    DatabaseReference teachersRef;
    Intent intent;
TextView profession_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teachersRef = FirebaseDatabase.getInstance().getReference("csv");

        intent = getIntent();
        Slidr.attach(this);
        proffession = intent.getStringExtra("prof");
        studentName = intent.getStringExtra("name");
        profession_name = findViewById(R.id.profession_name);
        profession_name.setText("selected subject: "+proffession);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ////select teacher screen..
        Bitmap teacherpic1 = BitmapFactory.decodeResource(getResources(),R.drawable.teacherpic1);
        Bitmap teacherpic2 = BitmapFactory.decodeResource(getResources(),R.drawable.teacherpic2);
        Bitmap teacherpic3 = BitmapFactory.decodeResource(getResources(),R.drawable.teacherpic3);
        Bitmap teacherpic4 = BitmapFactory.decodeResource(getResources(),R.drawable.teacherpic4);
        Bitmap teacherpic5 = BitmapFactory.decodeResource(getResources(),R.drawable.teacherpic5);
        Bitmap teacherpic6 = BitmapFactory.decodeResource(getResources(),R.drawable.teacherpic6);



        /*Teacher t1 = new Teacher("רוני רובן ", teacherpic1, "054000","no email","cs");
        Teacher t2 = new Teacher("ארגנטינה  ", teacherpic2, "054000","no email","math");
        Teacher t3 = new Teacher("מסי  " ,teacherpic3, "054000","no email","english");
        Teacher t4 = new Teacher("גורגיה  ",  teacherpic4, "054000","no email","sport");
        Teacher t5 = new Teacher("פיקצו ",  teacherpic5, "054000","no email","hebrew");
        Teacher t6 = new Teacher("פיקה פיקה  ", teacherpic6, "054000","no email","talking");*/
        lvT =(ListView)findViewById(R.id.lvT);

        teacherByProffession(proffession);
       /* teachersList.add(t1);
        teachersList.add(t2);
        teachersList.add(t3);
        teachersList.add(t4);
        teachersList.add(t5);
        teachersList.add(t6);*/

        lvT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Teacher t = teachersList.get(position);
                Intent intent= new Intent(MainActivity.this,ParatniSchedule.class );
                intent.putExtra("t_name", t.getName());
                intent.putExtra("s_name", studentName);
                intent.putExtra("prof_name", proffession);
                startActivity(intent);
                finish();            }
        });

    }

    private void teacherByProffession(String proffession) {
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("csv");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                teachersList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String t_name = snapshot.child("name").getValue().toString();
                    String t_proff =snapshot.child("subject").getValue().toString();
//                    String t_phone=snapshot.child("phone").getValue().toString();
                    if(t_proff.equals(proffession))
                        teachersList.add(new Teacher(t_name));
                }
                teacheradapter = new TeacherAdapter(MainActivity.this,0,0,teachersList);
                lvT.setAdapter((ListAdapter) teacheradapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}