package edu.tacoma.uw.projecttcss450;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ParkingAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] parkingLocations;
    private int[] parkingImages;

    public ParkingAdapter(Context context, String[] parkingLocations, int[] parkingImages) {
        super(context, R.layout.parking_list_item, parkingLocations);
        this.context = context;
        this.parkingLocations = parkingLocations;
        this.parkingImages = parkingImages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.parking_list_item, parent, false);

        TextView parkingName = view.findViewById(R.id.parking_name);
        ImageView parkingImage = view.findViewById(R.id.parking_image);
        Button mapButton = view.findViewById(R.id.map_button);

        parkingName.setText(parkingLocations[position]);
        parkingImage.setImageResource(parkingImages[position]);

        mapButton.setOnClickListener(v -> {
            // Open Google Maps with the parking location
            String url = "https://www.google.com/maps/search/?api=1&query=" + Uri.encode(parkingLocations[position]);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        });

        return view;
    }
}