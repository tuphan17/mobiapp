/*
This class takes care of authorization view model
 */
package edu.tacoma.uw.projecttcss450;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AuthViewModel extends AndroidViewModel {
    private final MutableLiveData<AuthResult> authResult = new MutableLiveData<>();
    private final RequestQueue requestQueue;
    private final UserManager userManager;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        requestQueue = Volley.newRequestQueue(application);
        userManager = new UserManager(application);
    }
    private void sendRequest(int method, String url, JSONObject body, boolean isLogin, String email) {
        JsonObjectRequest request = new JsonObjectRequest(
                method,
                url,
                body,
                response -> handleResponse(response, isLogin, email),
                error -> handleError(error)
        );

        requestQueue.add(request);
    }
    public void loginUser(String email, String password) {
        String url = "https://students.washington.edu/dinhtu/login.php";
        JSONObject body = createRequestBody(email, password);
        sendRequest(Request.Method.POST, url, body, true, email);
    }

    public void registerUser(String email, String password) {
        String url = "https://students.washington.edu/dinhtu/register_user.php";
        JSONObject body = createRequestBody(email, password);
        sendRequest(Request.Method.POST, url, body, false, email);
    }

    private JSONObject createRequestBody(String email, String password) {
        JSONObject body = new JSONObject();
        try {
            body.put("email", email);
            body.put("password", password);
        } catch (JSONException e) {
            // Handle exception
        }
        return body;
    }


    private void handleResponse(JSONObject response, boolean isLogin, String email) {
        try {
            String result = response.getString("result");
            if (result.equals("success")) {
                int userId = response.getInt("userId");
                User user = new User(userId, email);
                JSONArray courses = null;
                if (isLogin) {
                    courses = response.getJSONArray("courses");
                    user.setCourses(deserializeCoursesFromJson(courses));
                }
                userManager.storeUser(user);
                authResult.setValue(new AuthResult(true, userId, courses, null));
            } else {
                authResult.setValue(new AuthResult(false, 0, null, "Authentication failed"));
            }
        } catch (JSONException e) {
            // Handle exception
            authResult.setValue(new AuthResult(false, 0, null, "Error parsing server response"));
        }
    }
    private List<Course> deserializeCoursesFromJson(JSONArray coursesJson) {
        List<Course> courses = new ArrayList<>();
        try {
            for (int i = 0; i < coursesJson.length(); i++) {
                JSONObject courseJson = coursesJson.getJSONObject(i);
                String courseName = courseJson.getString("courseName");
                String courseCode = courseJson.getString("courseCode");
                courses.add(new Course(courseName, courseCode));
            }
        } catch (JSONException e) {
            // Handle exception
        }
        return courses;
    }

    private void handleError(VolleyError error) {
        // Handle error
        authResult.setValue(new AuthResult(false, 0, null, "Network error"));
    }

    public LiveData<AuthResult> getAuthResult() {
        return authResult;
    }

    public User getUser() {
        return userManager.getUser();
    }

    public void addCourse(Course course) {
        userManager.addCourse(course);
    }

    public List<Quater> getQuarters() {
        return userManager.getQuarters();
    }


}