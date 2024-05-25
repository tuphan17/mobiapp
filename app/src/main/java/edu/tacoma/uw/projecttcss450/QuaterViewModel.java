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

public class QuaterViewModel extends AndroidViewModel {

    private UserManager userManager;
    private MutableLiveData<JSONObject> mResponse;

    private MutableLiveData<List<Quater>> mQuaterList;

    public QuaterViewModel(@NonNull Application application) {
        super(application);
        mResponse = new MutableLiveData<>();
        mResponse.setValue(new JSONObject());
        mQuaterList = new MutableLiveData<>();
        mQuaterList.setValue(new ArrayList<>());
        userManager = new UserManager(application);

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

    public void addQuater(String year, String course1, String course2, String course3,int userId) {
        String url = "https://students.washington.edu/dinhtu/add_quater.php";
        if (userManager != null) {
            userManager.addQuarter(year, course1, course2, course3, userId);
        } else {
            // Handle the case when userManager is null
            Log.e("QuaterViewModel", "UserManager is null");
        }
        JSONObject body = new JSONObject();
        try {
            body.put("year", year);
            body.put("course1", course1);
            body.put("course2", course2);
            body.put("course3", course3);
            body.put("userId", userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        userManager.addQuarter(year, course1, course2, course3, userId);
        Request request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                body, //no body for this get request
                mResponse::setValue,
                this::handleError);

        Log.i("QuaterViewModel", request.getUrl().toString());
        request.setRetryPolicy(new DefaultRetryPolicy(
                10_000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Instantiate the RequestQueue and add the request to the queue
        Volley.newRequestQueue(getApplication().getApplicationContext())
                .add(request);

    }

    public void addQuaterListObserver(@NonNull LifecycleOwner owner,
                                      @NonNull Observer<? super List<Quater>> observer) {
        mQuaterList.observe(owner, observer);
    }

    private void handleResult(final JSONObject result) {
        try {
            String data = result.getString("Quaters");
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Quater quater = new Quater(obj.getString(Quater.YEAR),
                        obj.getString(Quater.COURSE1),
                        obj.getString(Quater.COURSE2),
                        obj.getString(Quater.COURSE3));
                quater.setUserId(obj.getInt("userId")); // Set the user ID for the quarter
                mQuaterList.getValue().add(quater);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR!", e.getMessage());
        }
        mQuaterList.setValue(mQuaterList.getValue());
    }

    public void getQuaters(int userId) {

        String url = "https://students.washington.edu/dinhtu/get_quater.php?userId=" + userId;

        Request request = new JsonObjectRequest(

                Request.Method.GET,

                url,

                null, //no body for this get request

                this::handleResult,

                this::handleError);



        request.setRetryPolicy(new DefaultRetryPolicy(

                10_000,

                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,

                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        //Instantiate the RequestQueue and add the request to the queue

        Volley.newRequestQueue(getApplication().getApplicationContext())

                .add(request);

    }
}
