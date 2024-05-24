package edu.tacoma.uw.projecttcss450;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_course);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottomNavClassPlan);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottomNavHome) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (itemId == R.id.bottomNavClassPlan) {
                startActivity(new Intent(this, ClassPlanActivity.class));
                return true;
            } else if (itemId == R.id.bottomNavCampusMap) {
                startActivity(new Intent(this, CampusMapActivity.class));
                return true;
            } else if (itemId == R.id.bottomNavParking) {
                startActivity(new Intent(this, ParkingActivity.class));
                return true;

                // Add more cases for additional menu items
            }
            return false;
        });
    }

}