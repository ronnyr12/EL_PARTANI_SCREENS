package com.example.el_partani_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;

public class addNewProfession extends AppCompatActivity {
    private static final String[]PROFESSION=new String[]{
            "מטמתיקה", "אנגלית", "אזרחות", "ספרות","מדעי המחשב",
    };
    private static final String[]LEVELS=new String[]{
            "א", "ב", "ג", "3"," 4","5"
    };
    GridView GVanpS;
    Button btn_saveNewProfession,btn_moveToPs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_profession);

        AutoCompleteTextView edittext =findViewById(R.id.ACTnewProfessionName);
        AutoCompleteTextView edittext2 =findViewById(R.id.ATCnewProfessionLevel);
        GVanpS =findViewById(R.id.GVanpS);
        btn_saveNewProfession =findViewById(R.id.btn_saveNewProfession);
        btn_moveToPs =findViewById(R.id.btn_moveToPs);
        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,PROFESSION);
        edittext.setAdapter(adapter);
        ArrayAdapter<String>adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,LEVELS);
        edittext2.setAdapter(adapter2);

        btn_saveNewProfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //לשמור את המקצוע לתוך הפייר בייס ולעשות TOAST
            }
        });

        btn_moveToPs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(addNewProfession.this, Profession_screen.class));

            }
        });
        //צריך לסדר את הקטע שאתה מחליק אחורה אז זה מחזיר רק למסך שאני רוצה ולא לכל מסך שהיה לפני.







    }
}