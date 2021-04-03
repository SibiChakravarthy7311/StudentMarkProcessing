package com.example.studentmarkprocessing;

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

public class customListAdapter extends ArrayAdapter<ListviewItem> {
    private Context context;
    private int resource;

    public customListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListviewItem> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String roll=getItem(position).getRoll();
        String name=getItem(position).getName();
        String ccet1=getItem(position).getCcet1();
        String ccet2=getItem(position).getCcet2();
        String ccet3=getItem(position).getCcet3();
        String sem=getItem(position).getSem();

//        Item itemdata=new Item(name,productpic,profilepic,price);

        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(resource,parent,false);

        TextView tvroll=(TextView) convertView.findViewById(R.id.rollno);
        TextView tvname=(TextView) convertView.findViewById(R.id.name);
        TextView tvccet1=(TextView) convertView.findViewById(R.id.ccet1);
        TextView tvccet2=(TextView) convertView.findViewById(R.id.ccet2);
        TextView tvccet3=(TextView) convertView.findViewById(R.id.ccet3);
        TextView tvsem=(TextView) convertView.findViewById(R.id.sem);

        tvroll.setText(roll);
        tvname.setText(name);
        tvccet1.setText(ccet1);
        tvccet2.setText(ccet2);
        tvccet3.setText(ccet3);
        tvsem.setText(sem);

        return convertView;
    }
}
