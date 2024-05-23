package edu.tacoma.uw.projecttcss450;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserPlan implements Serializable {
    private List<Quater> mQuarters;
    private List<Course> mCourses;

    public UserPlan() {
        mQuarters = new ArrayList<>();
        mCourses = new ArrayList<>();
    }

    public void addQuarter(Quater quarter) {
        mQuarters.add(quarter);
    }

    public void addCourse(Course course) {
        mCourses.add(course);
    }

    public List<Quater> getQuarters() {
        return mQuarters;
    }

    public List<Course> getCourses() {
        return mCourses;
    }

    public void setQuarters(List<Quater> quarters) {
    }

    public void setCourses(List<Course> courses) {
    }
}