package edu.tacoma.uw.projecttcss450;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        // Clear the saved login state from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.LOGGEDIN), false);
        editor.apply();

        // Show a toast message
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();

        // Navigate to the login activity or fragment
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // Optional: Finish the current activity to prevent going back to it using the back button
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences(getString(R.string.LOGIN_PREFS), Context.MODE_PRIVATE);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (!mSharedPreferences.getBoolean(getString(R.string.LOGGEDIN), false)) {
            // User is not logged in, navigate to the login activity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }



        //Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottomNavHome);
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


        Button parkingButton = findViewById(R.id.ParkingButton);
        parkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParkingActivity.class);
                startActivity(intent);
            }
        });

        Button aboutButton = findViewById(R.id.AboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        Button campusButton = findViewById(R.id.CampusMapButton);
        campusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CampusMapActivity.class);
                startActivity(intent);
            }
        });

        Button classButton = findViewById(R.id.ClassPlanButton);
        classButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ClassPlanActivity.class);
                startActivity(intent);
            }
        });

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }


}