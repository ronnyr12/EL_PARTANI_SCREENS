package com.example.el_partani_screens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Teacher_scheduleAdapter extends ArrayAdapter<Teacher_schedule_row> {
    private  Context ct;
    private ArrayList<Teacher_schedule_row> arr;

    public Teacher_scheduleAdapter(@NonNull Context context, int resource, @NonNull List<Teacher_schedule_row> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater i = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(R.layout.teacher_schedule_row,null);
        }

        if (arr.size()>0){
            Teacher_schedule_row tsr = arr.get(position);
            ImageView TimgITS = convertView.findViewById(R.id.TimgITS);
            TextView txvDate = convertView.findViewById(R.id.txvDate);
            TextView txvtime = convertView.findViewById(R.id.txvtime);

           /*todo-
           לא הבנתי מה הוא עשה בסרטון כיאלו מריפה הוא לקח את המשתנים האלה בדקה 24:29
           שם הסרטון: andoid studio create listview dish beautiful
            TimgITS.setImageResource(d.image);
            txvDate.setText(d.name);
            txvtime.setText(d.numItem+"Items");
            */



        }
        return convertView;

    }
}
