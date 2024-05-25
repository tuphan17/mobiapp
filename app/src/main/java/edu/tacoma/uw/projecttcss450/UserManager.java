package edu.tacoma.uw.projecttcss450;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class UserManager {
    private static final String SHARED_PREF_NAME = "user_data";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_COURSES = "courses";
    private static final String KEY_QUARTERS = "quarters";
    private Gson gson;
    private SharedPreferences sharedPreferences;
    private User currentUser;

    public UserManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void storeUser(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, user.getUserId());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_COURSES, serializeCoursesToJson(user.getCourses()));
        editor.apply();
        currentUser = user;
    }

    public User getUser() {
        if (currentUser == null) {
            int userId = sharedPreferences.getInt(KEY_USER_ID, 0);
            String email = sharedPreferences.getString(KEY_EMAIL, "");
            List<Course> courses = deserializeCoursesFromJson(sharedPreferences.getString(KEY_COURSES, "[]"));
            currentUser = new User(userId, email);
            currentUser.setCourses(courses);
        }
        return currentUser;
    }

    public void addCourse(Course course) {
        currentUser.getCourses().add(course);
        storeUser(currentUser);
    }

    private String serializeCoursesToJson(List<Course> courses) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Course>>() {}.getType();
        return gson.toJson(courses, type);
    }

    private List<Course> deserializeCoursesFromJson(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Course>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public List<Quater> getQuarters() {
        List<Quater> quarters = new ArrayList<>();
        String quartersJson = sharedPreferences.getString(KEY_QUARTERS, "[]");
        Type type = new TypeToken<List<Quater>>() {}.getType();
        quarters = gson.fromJson(quartersJson, type);
        return quarters;
    }

    private void storeQuarters(List<Quater> quarters) {
        String quartersJson = gson.toJson(quarters);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_QUARTERS, quartersJson);
        editor.apply();
    }
    public void addQuarter(String year, String course1, String course2, String course3, int userId) {
        Quater quater = new Quater(year, course1, course2, course3);
        quater.setUserId(userId); // Set the user ID for the quarter
        List<Quater> quarters = getQuarters();
        quarters.add(quater);
        storeQuarters(quarters);
    }

}