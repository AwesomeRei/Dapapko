package com.example.andre.firebasecweather;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Andre on 12/14/2015.
 */
public class BahanListAdapter extends ArrayAdapter<Bahan> {
    Bahan[] listBahan;
    Context context;
    public BahanListAdapter(Context context, Bahan[] resource) {
        super(context,R.layout.bahan_list_fix,resource);
        this.listBahan = resource;
        this.context = context;
    }
    @Override
    public View getView(int position,View convertView, ViewGroup parent){
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.bahan_list_fix,parent,false);
        TextView bahanView = (TextView)convertView.findViewById(R.id.bahanItem);
        CheckBox cek = (CheckBox) convertView.findViewById(R.id.checkBox);
        bahanView.setText(listBahan[position].getItem());
        if(listBahan[position].getValue() == 1)
            cek.setChecked(true);
        else
            cek.setChecked(false);
        return convertView;
    }
}
