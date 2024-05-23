package edu.tacoma.uw.projecttcss450;

import java.io.Serializable;

public class Course implements Serializable {
    private String mCourseName;
    private String mCourseCode;

    public Course(String courseName, String courseCode) {
        mCourseName = courseName;
        mCourseCode = courseCode;
    }

    public String getCourseName() {
        return mCourseName;
    }

    public String getCourseCode() {
        return mCourseCode;
    }
}