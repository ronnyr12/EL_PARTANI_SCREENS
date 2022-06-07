package com.example.el_partani_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.r0adkll.slidr.Slidr;

public class addPrivateHourForAdmin extends AppCompatActivity {
    private Button btn_saveNewPrivateHour,btn_goBackToMainForAdminFromeANPH;
    EditText ETnewPrivateHourDayAndHour,ETnewPrivateHourTime,ETnewPrivateHourAvailableOrNot;
    DatabaseReference privateHourRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_private_hour_for_admin);

        Slidr.attach(this);


        ETnewPrivateHourDayAndHour = findViewById(R.id.ETnewPrivateHourDayAndHour);
        ETnewPrivateHourTime = findViewById(R.id.ETnewPrivateHourTime);
        ETnewPrivateHourAvailableOrNot = findViewById(R.id.ETnewPrivateHourAvailableOrNot);
        btn_goBackToMainForAdminFromeANPH = findViewById(R.id.btn_goBackToMainForAdminFromeANPH);
        btn_saveNewPrivateHour = findViewById(R.id.btn_saveNewPrivateHour);

        btn_goBackToMainForAdminFromeANPH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btn_goBackToMainForAdminFromeANPH) {
                    Intent intent = new Intent(addPrivateHourForAdmin.this, adminMainScreen.class);
                    startActivity(intent);
                }
            }
        });
        btn_saveNewPrivateHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btn_saveNewPrivateHour) {
                    if (addPrivetHour2Db()) {
                        Intent intent = new Intent(addPrivateHourForAdmin.this, Profession_screen.class);
                        startActivity(intent);
                    } else {
                        //todo- prompt error in adding teacher details
                    }
                }
            }
        });
    }
    private boolean addPrivetHour2Db() {
        //todo - adding an extra hour by teacher (excedded hours )
        //צריך לעבור על זה עם רוני אם זה בכלל הולך לfire base

        return true;
    }
}