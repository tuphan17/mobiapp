package edu.tacoma.uw.projecttcss450;

import java.io.Serializable;

public class Quater implements Serializable {

    private String mYear;

    private String mCourse1;

    private String mCourse2;

    private String mCourse3;

    public final static String YEAR = "year";

    public final static String COURSE1 = "course1";

    public final static String COURSE2 = "course2";

    public final static String COURSE3 = "course3";

    public Quater(String year, String course1, String course2, String course3) {
        setYear(year);
        setCourse1(course1);
        setCourse2(course2);
        setCourse3(course3);
    }

    public void setYear(String year){
        mYear = year;
    }

    public void setCourse1(String course1){
        mCourse1 = course1;
    }

    public void setCourse2(String course2){
        mCourse2 = course2;
    }

    public void setCourse3(String course3){
        mCourse3 = course3;
    }

    public String getYear(){
        return mYear;
    }

    public String getCourse1(){
        return mCourse1;
    }

    public String getCourse2(){
        return mCourse2;
    }

    public String getCourse3(){
        return mCourse3;
    }
}
