package com.example.el_partani_screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class professionAdapter extends BaseAdapter {
    Context context;
    String[] professionsName;
    int[]professionImage;

    LayoutInflater inflater;

    public professionAdapter(Context context, String[] professionsName, int[] professionImage) {
        this.context = context;
        this.professionsName = professionsName;
        this.professionImage = professionImage;
    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    public int getCount() {

        return professionsName.length;
    }


    public Object getItem(int position)
    {
        return professionImage[position];
    }


    public long getItemId(int position) {

        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.activity_profession_adapter, null);
        }

        ImageView imageView = convertView.findViewById(R.id.profession_image);
        TextView textView = convertView.findViewById(R.id.profession_name);

        imageView.setImageResource(professionImage[position]);
        textView.setText(professionsName[position]);
        return convertView;
    }

}