package com.example.el_partani_screens;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MeetingAdapter extends ArrayAdapter<Metting> {
    private  Context ct;
    private List<Metting> mettings;

    public MeetingAdapter( Context context, int resource, int textViewResourceId,  List<Metting> mettings) {
        super(context, resource, textViewResourceId, mettings);
        this.ct = context;
        this.mettings = mettings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater i = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = i.inflate(R.layout.meeting_details,null);


        if (mettings.size()>0){
            Metting mtg = mettings.get(position);
            TextView tv_day = view.findViewById(R.id.tv_day);
            TextView tv_time = view.findViewById(R.id.tv_time);

            tv_time.setText(mtg.getHour());
            tv_day.setText(mtg.getDay());


        }
        return view;

    }
}
