package varun.com.studentmanagementsystemsample.utils;

/**
 * Created by Administrator on 7/14/2016.
 */
public class Student {
    int userID, userSpecificID, roleId, userTypeID, studentRegID, schoolID, classID, sectionID;
    String userName;

    public Student(int userID, int userSpecificID, int roleId, int userTypeID, int studentRegID, int schoolID, int classID, int sectionID, String userName) {
        this.userID = userID;
        this.userSpecificID = userSpecificID;
        this.roleId = roleId;
        this.userTypeID = userTypeID;
        this.studentRegID = studentRegID;
        this.schoolID = schoolID;
        this.classID = classID;
        this.sectionID = sectionID;
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserSpecificID() {
        return userSpecificID;
    }

    public void setUserSpecificID(int userSpecificID) {
        this.userSpecificID = userSpecificID;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(int userTypeID) {
        this.userTypeID = userTypeID;
    }

    public int getStudentRegID() {
        return studentRegID;
    }

    public void setStudentRegID(int studentRegID) {
        this.studentRegID = studentRegID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
