/*
This class holds information of users used in authentication.
 */
package edu.tacoma.uw.projecttcss450;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String email;
    private List<Course> courses = new ArrayList<>();

    public User(int userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}