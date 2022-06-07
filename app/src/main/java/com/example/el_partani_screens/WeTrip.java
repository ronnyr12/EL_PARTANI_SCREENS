package com.example.el_partani_screens;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class WeTrip extends AppCompatActivity implements View.OnClickListener {
    Button btn_add, btnAll, btn_add_excel_teachers;
    DatabaseReference proffessionRef;
    ListView listView_profession;
    ArrayList<Profession> professions;
    AllPRofessionAdapter adapter;

    //todo - change app icon (use the logo)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_trip);
        Profession pr1 = new Profession("math");
        proffessionRef = FirebaseDatabase.getInstance().getReference("/Profession");
        listView_profession = findViewById(R.id.lv_professions);

        btn_add = findViewById(R.id.btn_add_wetrip);
        btn_add.setOnClickListener(this);
        btn_add = findViewById(R.id.btn_add_wetrip);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProfession2Firebase(pr1);
            }
        });
        btnAll = findViewById(R.id.btn_all);
        btnAll.setOnClickListener(this);
    }

    private void retrivedata() {
        proffessionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                professions = new ArrayList<Profession>();


                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Profession p = data.getValue(Profession.class);
                    professions.add(p);
                    Log.d("ofri", p.getName());
                }


                adapter = new AllPRofessionAdapter(WeTrip.this,
                        0, 0, professions);

                listView_profession.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addProfession2Firebase(Profession pr1) {

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        // Post p = new Post(et_title.getText().toString(),et_body.getText().toString(), uid, "" );

        proffessionRef = FirebaseDatabase.getInstance().getReference("Profession").push();
        //p.key = proffessionRef.getKey();
        proffessionRef.setValue(pr1);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_excel_teachers:
                //todo - https://www.youtube.com/watch?v=RO0LMwpoGPg&list=PL8ica4TxO2rnAFLF2Gh1GK6_QIRiKufbV&index=9
                break;
            case R.id.btn_all:
                retrivedata();
                break;
            case R.id.btn_add_wetrip:
                //todo - because a proffesion is created with image
                // must do the next tutorial - https://www.youtube.com/watch?v=MfCiiTEwt3g
        }
    }
}