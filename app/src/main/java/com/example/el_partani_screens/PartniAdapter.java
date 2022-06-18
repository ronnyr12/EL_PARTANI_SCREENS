package com.example.el_partani_screens;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PartniAdapter extends ArrayAdapter<Partni> {
    private Context ct;
    private List<Partni> partnis;

    public PartniAdapter(Context context, int resource, int textViewResourceId, List<Partni> partnis) {
        super(context, resource, textViewResourceId, partnis);
        this.ct = context;
        this.partnis = partnis;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater i = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = i.inflate(R.layout.meeting_details, null);


        if (partnis.size() > 0) {
            Metting mtg = partnis.get(position);
            TextView tv_day = view.findViewById(R.id.tv_day);
            TextView tv_time = view.findViewById(R.id.tv_time);

            tv_time.setText(mtg.getHour());
            tv_day.setText(mtg.getDay());


        }
        return view;

    }
}