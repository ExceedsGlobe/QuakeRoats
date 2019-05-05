package com.example.quakeroats;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0,earthquakes);
    }

    
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        View listItemView= LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        Earthquake currentEarthquake=getItem(position);
        TextView magnitude=(TextView)listItemView.findViewById(R.id.magnitude);
        magnitude.setText(String.valueOf(currentEarthquake.getmMagnitude()));
        TextView location=(TextView)listItemView.findViewById(R.id.location);
        location.setText(currentEarthquake.getmLocation());
        TextView date=(TextView)listItemView.findViewById(R.id.date);
        date.setText(currentEarthquake.getDate());
        return listItemView;

    }
}
