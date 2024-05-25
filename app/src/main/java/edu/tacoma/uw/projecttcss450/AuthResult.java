/*
This class takes care of authorization.
 */
package edu.tacoma.uw.projecttcss450;

import org.json.JSONArray;

public class AuthResult {
    private final boolean success;
    private final int userId;
    private final JSONArray courses;
    private final String errorMessage;

    public AuthResult(boolean success, int userId, JSONArray courses, String errorMessage) {
        this.success = success;
        this.userId = userId;
        this.courses = courses;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getUserId() {
        return userId;
    }

    public JSONArray getCourses() {
        return courses;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}