package varun.com.studentmanagementsystemsample.utils;

/**
 * Created by VarunBarve on 12/05/2016.
 */
public class User {

    int userID;
    int userSpecificID;
    int roleId;
    int userTypeID;
    int studentRegID;
    int schoolID;
    int classID;
    int sectionID;
    int termID;
    int academicYearID;
    String userName;

    public User(int userID, int roleId, int userTypeID, String userName) {
        this.userID = userID;
        this.roleId = roleId;
        this.userTypeID = userTypeID;
        this.userName = userName;
    }

    public User(int userID, int userSpecificID, int roleId, int userTypeID, int studentRegID, int schoolID, int classID, int sectionID, String userName, int termID, int academicYearID) {
        this.userID = userID;
        this.userSpecificID = userSpecificID;
        this.roleId = roleId;
        this.userTypeID = userTypeID;
        this.studentRegID = studentRegID;
        this.schoolID = schoolID;
        this.classID = classID;
        this.sectionID = sectionID;
        this.userName = userName;
        this.termID = termID;
        this.academicYearID = academicYearID;
    }

    public int getAcademicYearID() {
        return academicYearID;
    }

    public void setAcademicYearID(int academicYearID) {
        this.academicYearID = academicYearID;
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

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

}
