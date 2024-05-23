package edu.tacoma.uw.projecttcss450;

import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class AddCourseActivity extends AppCompatActivity {
    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_course);

        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        mUserViewModel.addUserDataObserver(this, this::onUserDataReceived);

        // Add the AddCourse fragment to the activity
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new AddCourse())
                    .commit();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void onUserDataReceived(UserPlan userData) {
        // Get the user's quarters and courses
        List<Quater> quarters = userData.getQuarters();
        List<Course> courses = userData.getCourses();

        // Display the user's quarters
        for (Quater quarter : quarters) {
            String quarterInfo = "Year: " + quarter.getYear() + "\n";
            quarterInfo += "Course 1: " + quarter.getCourse1() + "\n";
            quarterInfo += "Course 2: " + quarter.getCourse2() + "\n";
            quarterInfo += "Course 3: " + quarter.getCourse3() + "\n";

            // Display the quarter information in a TextView or a RecyclerView, for example
            Log.d("UserData", quarterInfo);
        }

        // Display the user's courses
        for (Course course : courses) {
            String courseInfo = "Course Name: " + course.getCourseName() + "\n";
            courseInfo += "Course Code: " + course.getCourseCode() + "\n";

            // Display the course information in a TextView or a RecyclerView, for example
            Log.d("UserData", courseInfo);
        }
    }
}