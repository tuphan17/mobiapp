package edu.tacoma.uw.projecttcss450;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ParkingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        // Initialize the ListView and set the adapter
        ListView listView = findViewById(R.id.parking_list);
        String[] parkingLocations = {
                "Court 17 ParkingActivity Garage",
                "Tacoma Dome Station ParkingActivity Garage",
                "Broadway ParkingActivity Garage",
                "Market Street ParkingActivity Garage",
                "Pacific Avenue ParkingActivity Garage"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, parkingLocations);
        listView.setAdapter(adapter);
    }
}