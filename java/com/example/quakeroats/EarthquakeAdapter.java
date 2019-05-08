package com.example.quakeroats;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * An {@link EarthquakeAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link Earthquake} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR="of";
    String primaryLocation;
    String locationOffset;
    /**
     * Constructs a new {@link EarthquakeAdapter}.
     *
     * @param context of the app
     * @param earthquakes is the list of earthquakes, which is the data source of the adapter
     */
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0,earthquakes);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
       // inflate a new list item layout.
        View listItemView= LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        // Find the earthquake at the given position in the list of earthquakes

        Earthquake currentEarthquake=getItem(position);

        // Find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = getMagnitudeFormat(currentEarthquake.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Get the original location string from the Earthquake object,
        // which can be in the format of "5km N of Cairo, Egypt" or "Pacific-Antarctic Ridge"
        String originalLocation=currentEarthquake.getLocation();

        // Check whether the originalLocation string contains the " of " text
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            // Split the string into different parts (as an array of Strings)
            // based on the " of " text. We expect an array of 2 Strings, where
            // the first String will be "5km N" and the second String will be "Cairo, Egypt".
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            // Location offset should be "5km N " + " of " --> "5km N of"
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            // Primary location should be "Cairo, Egypt"
            primaryLocation = parts[1];
        } else {
            // Otherwise, there is no " of " text in the originalLocation string.
            // Hence, set the default location offset to say "Near the".
            locationOffset = getContext().getString(R.string.near_by);
            // The primary location will be the full location string "Pacific-Antarctic Ridge".
            primaryLocation = originalLocation;
        }
        // Find the TextView with view ID location
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primarylocation);
        // Display the location of the current earthquake in that TextView
        primaryLocationView.setText(primaryLocation);

        // Find the TextView with view ID location offset
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.offsetlocation);
        // Display the location offset of the current earthquake in that TextView
        locationOffsetView.setText(locationOffset);

        TextView date=(TextView)listItemView.findViewById(R.id.date);
        //getDate method convert long format to string
        String dateStr=getDate(currentEarthquake.getTimeInMilliseconds());
        // Display the date of the current earthquake in that TextView
        date.setText(dateStr);
        // Find the TextView with view ID time
        TextView time=(TextView)listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String timeStr=getTime(currentEarthquake.getTimeInMilliseconds());
        // Display the time of the current earthquake in that TextView
        time.setText(timeStr);
        // Return the list item view that is now showing the appropriate data
        return listItemView;

    }
    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String getMagnitudeFormat(double magnitude) {

        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String getDate(long timeInMilliseconds) {
        Date dateObject = new Date(timeInMilliseconds);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("DD MM, yyyy");
        return dateFormatter.format(dateObject);
    }
    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String getTime(long timeInMilliseconds) {
        Date dateObject = new Date(timeInMilliseconds);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm a");
        return dateFormatter.format(dateObject);
    }


    /**
     * Return the color for the magnitude circle based on the intensity of the earthquake.
     *
     * @param magnitude of the earthquake
     */
     private int getMagnitudeColor(double magnitude){
         int magnitudeColorResourceId;
         int magnitudeInt = (int) magnitude;
         switch (magnitudeInt) {
             case 0:
             case 1:
                 magnitudeColorResourceId = R.color.magnitude1;
                 break;
             case 2:
                 magnitudeColorResourceId = R.color.magnitude2;
                 break;
             case 3:
                 magnitudeColorResourceId = R.color.magnitude3;
                 break;
             case 4:
                 magnitudeColorResourceId = R.color.magnitude4;
                 break;
             case 5:
                 magnitudeColorResourceId = R.color.magnitude5;
                 break;
             case 6:
                 magnitudeColorResourceId = R.color.magnitude6;
                 break;
             case 7:
                 magnitudeColorResourceId = R.color.magnitude7;
                 break;
             case 8:
                 magnitudeColorResourceId = R.color.magnitude8;
                 break;
             case 9:
                 magnitudeColorResourceId = R.color.magnitude9;
                 break;
             default:
                 magnitudeColorResourceId = R.color.magnitude10plus;
                 break;
         }

         return getContext().getColor(magnitudeColorResourceId);
     }

}
