package com.example.el_partani_screens;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Profession_screen extends AppCompatActivity {
    GridView gridView;
    professionAdapter professionAdapter;
    String[] professionsName;
    int[] professionImage;
    FloatingActionButton btn_ANP;
    Dialog dialogps;
    Intent intent;
    String name = "";
    ArrayList<Profession> professions_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession_screen);
        Slidr.attach(this);
        intent = getIntent();
        name = intent.getStringExtra("name");
        //todo - show user name and class (get from databae)

        professionsName = new String[]{"מתמטיקה ", "אנגלית", "עברית ", "ספרות ", "היסטוריה"};
        professionImage = new int[]{R.drawable.mathbackground, R.drawable.englishbackground,
                R.drawable.hebrowbackground, R.drawable.safrotbackground, R.drawable.historybackground};

        gridView = findViewById(R.id.gridView);
        btn_ANP = findViewById(R.id.btn_ANP);
        btn_ANP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profession_screen.this, addNewProfession.class));

            }
        });

        professionAdapter = new professionAdapter(Profession_screen.this,
                professionsName, professionImage);
        gridView.setAdapter(professionAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "את/ה לחצת על " + professionsName[+position],
                        Toast.LENGTH_SHORT).show();
                Log.d("tag", professionsName[position]);
                startActivity(new Intent(Profession_screen.this, MainActivity.class));


            }
        });
        //todo -  לשנות צבע לכפתור המרחף


    }
}