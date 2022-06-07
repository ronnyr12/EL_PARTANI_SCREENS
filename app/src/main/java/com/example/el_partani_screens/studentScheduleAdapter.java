package com.example.el_partani_screens;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class studentScheduleAdapter extends BaseAdapter {
     Context context;
     List<Student> object;

    public studentScheduleAdapter(Context context, List<Student> object) {
        this.context = context;
        this.object = object;
    }

    public studentScheduleAdapter(studentSchedule context, int i, ArrayList<Teacher_schedule_row> arr) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
