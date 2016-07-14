package varun.com.studentmanagementsystemsample.bean;

import java.io.Serializable;

/**
 * Created by Varun on 4/17/2016.
 */
public class HomeBean implements Serializable {

    private int studentID;
    private int schoolID;
    private int avrageAttendance;
    private int avragePerformance;
    private String studentFirstName;
    private int academicYearID;
    private int classID;

    public HomeBean(int studentID, int schoolID, int avrageAttendance, int avragePerformance, String studentFirstName, int academicYearID, int classID) {
        this.studentID = studentID;
        this.schoolID = schoolID;
        this.avrageAttendance = avrageAttendance;
        this.avragePerformance = avragePerformance;
        this.studentFirstName = studentFirstName;
        this.academicYearID = academicYearID;
        this.classID = classID;
    }

    public int getAcademicYearID() {
        return academicYearID;
    }

    public void setAcademicYearID(int academicYearID) {
        this.academicYearID = academicYearID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public int getAvrageAttendance() {
        return avrageAttendance;
    }

    public void setAvrageAttendance(int avrageAttendance) {
        this.avrageAttendance = avrageAttendance;
    }

    public int getAvragePerformance() {
        return avragePerformance;
    }

    public void setAvragePerformance(int avragePerformance) {
        this.avragePerformance = avragePerformance;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }
}
