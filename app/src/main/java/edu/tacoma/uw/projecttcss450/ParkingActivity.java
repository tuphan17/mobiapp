package edu.tacoma.uw.projecttcss450;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ParkingActivity extends AppCompatActivity {

    private String[] parkingLocations = {
            "Court 17 Parking Garage",
            "Tacoma Dome Station Parking Garage",
            "Broadway Parking Garage",
            "Market Street Parking Garage",
            "Pacific Avenue Parking Garage"
    };

    private int[] parkingImages = {
            R.drawable.court_17_parking,
            R.drawable.tacoma_dome_parking,
            R.drawable.broadway_parking,
            R.drawable.market_street_parking,
            R.drawable.pacific_avenue_parking
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        ListView listView = findViewById(R.id.parking_list);
        ParkingAdapter adapter = new ParkingAdapter(this, parkingLocations, parkingImages);
        listView.setAdapter(adapter);
    }
}