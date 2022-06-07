package com.example.el_partani_screens;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class AllPRofessionAdapter extends ArrayAdapter<Profession> {

    Context context;
    List<Profession> professions;

    public AllPRofessionAdapter(Context context, int resource, int textViewResourceId, List<Profession> professions) {
        super(context, resource, textViewResourceId, professions);

        this.context=context;
        this.professions=professions;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_row_profession, parent, false);

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        Profession temp = professions.get(position);
        tvTitle.setText(temp.getName());

        return view;
    }
}
