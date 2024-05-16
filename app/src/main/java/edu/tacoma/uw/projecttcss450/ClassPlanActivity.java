package edu.tacoma.uw.projecttcss450;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClassPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_class_plan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
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

        String apply = getString(R.string.To_Apply);
        String graduate = getString(R.string.To_Graduate);
        String classPlan = getString(R.string.Class_Plan);

        findViewById(R.id.ApplyButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("http://students.washington.edu/whwheoeo/toApplyMajor.html");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });

        findViewById(R.id.GraduationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("http://students.washington.edu/whwheoeo/toGraduation.html");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });

        Button classButton = findViewById(R.id.myPlanButton);
        classButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassPlanActivity.this, AddCourseActivity.class);
                startActivity(intent);
            }
        });

    }
}