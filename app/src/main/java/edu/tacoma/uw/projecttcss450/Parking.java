package edu.tacoma.uw.projecttcss450;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Parking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        // Initialize the ListView and set the adapter
        ListView listView = findViewById(R.id.parking_list);
        String[] parkingLocations = {
                "Court 17 Parking Garage",
                "Tacoma Dome Station Parking Garage",
                "Broadway Parking Garage",
                "Market Street Parking Garage",
                "Pacific Avenue Parking Garage"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, parkingLocations);
        listView.setAdapter(adapter);
    }
}