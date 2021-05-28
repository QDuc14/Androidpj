package com.example.nationinformation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Geonames> {
    Context context;
    ArrayList<Geonames> geonames;


    public CustomAdapter(@NonNull Context context, int layoutTobeInflated, ArrayList<Geonames> geonames) {
        super(context, R.layout.listnation, geonames);
        this.context = context;
        this.geonames = geonames;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.listnation,null);
        TextView txtCity = (TextView) row.findViewById(R.id.txtCity);

        txtCity.setText(geonames.get(position).getCountryName().toString());

        return row;

    }

}
