package edu.tacoma.uw.projecttcss450;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserViewModel extends AndroidViewModel {
    private MutableLiveData<UserPlan> mUserPlan;

    private MutableLiveData<JSONObject> mResponse;
    public UserViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());
        mUserPlan = new MutableLiveData<UserPlan>();

    }
    public void addUserDataObserver(@NonNull LifecycleOwner owner,
                                    @NonNull Observer<UserPlan> observer) {
        mUserPlan.observe(owner, observer);
    }

    private void handleUserDataResponse(JSONObject response) {
        try {
            if (response.has("userId")) {
                int userId = response.getInt("userId");
                String email = response.getString("email");
                JSONArray coursesArray = response.getJSONArray("courses");
                List<Course> courses = new ArrayList<>();

                for (int i = 0; i < coursesArray.length(); i++) {
                    JSONObject courseObj = coursesArray.getJSONObject(i);
                    String courseName = courseObj.getString("courseName");
                    String courseCode = courseObj.getString("courseCode");
                    Course course = new Course(courseName, courseCode);
                    courses.add(course);
                }

                UserPlan userPlan = new UserPlan();
                userPlan.setUserId(userId);
                userPlan.setEmail(email);
                userPlan.setCourses(courses);
                mUserPlan.setValue(userPlan);
            } else {
                // Handle the case when the server returns an empty JSON object
                UserPlan emptyUserPlan = new UserPlan();
                mUserPlan.setValue(emptyUserPlan);
            }
        } catch (JSONException e) {
            Log.e("UserViewModel", "Error parsing user data response", e);
        }
    }

    public void addResponseObserver(@NonNull LifecycleOwner owner,
                                    @NonNull Observer<? super JSONObject> observer) {
        mResponse.observe(owner, observer);
    }

    private void handleError(final VolleyError error) {
        if (Objects.isNull(error.networkResponse)) {
            try {
                mResponse.setValue(new JSONObject("{" +
                        "error:\"" + error.getMessage() +
                        "\"}"));
            } catch (JSONException e) {
                Log.e("JSON PARSE", "JSON Parse Error in handleError");
            }
        } else {
            String data = new String(error.networkResponse.data, Charset.defaultCharset())
                    .replace('\"', '\'');
            try {
                mResponse.setValue(new JSONObject("{" +
                        "code:" + error.networkResponse.statusCode +
                        ", data:\"" + data +
                        "\"}"));
            } catch (JSONException e) {
                Log.e("JSON PARSE", "JSON Parse Error in handleError");
            }
        }
    }

    public void addUser(Account account) {
        String url = "https://students.washington.edu/dinhtu/register_user.php";
        JSONObject body = new JSONObject();
        try {
            body.put("email", account.getEmail());
            body.put("password", account.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Request request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                body,
                mResponse::setValue,
                this::handleError);

        Log.i("UserViewModel", request.getUrl().toString());
        request.setRetryPolicy(new DefaultRetryPolicy(
                10_000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Instantiate the RequestQueue and add the request to the queue
        Volley.newRequestQueue(getApplication().getApplicationContext())
                .add(request);
    }

    public void authenticateUser(Account account) {
        String url = "https://students.washington.edu/dinhtu/login.php";
        JSONObject body = new JSONObject();
        try {
            body.put("email", account.getEmail());
            body.put("password", account.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Request request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                body,
                mResponse::setValue,
                this::handleError);

        Log.i("UserViewModel", request.getUrl().toString());
        request.setRetryPolicy(new DefaultRetryPolicy(
                10_000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Instantiate the RequestQueue and add the request to the queue
        Volley.newRequestQueue(getApplication().getApplicationContext())
                .add(request);
    }

    public void fetchUserData(String userId) {
        String url = "https://students.washington.edu/dinhtu/get_user_data.php";
        JSONObject body = new JSONObject();
        try {
            body.put("userId", userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Request request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                body,
                this::handleUserDataResponse,
                this::handleError);

        Log.i("UserViewModel", request.getUrl().toString());
        request.setRetryPolicy(new DefaultRetryPolicy(
                10_000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(getApplication().getApplicationContext())
                .add(request);
    }



}
