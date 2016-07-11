package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by VarunBarve on 21/04/2016.
 */
public class TodoBean {

    private String todoListID, userID, userTypeID, studentRegID, schoolID, title, description;

    public TodoBean(String todoListID, String userID, String userTypeID, String studentRegID, String schoolID, String title, String description) {
        this.todoListID = todoListID;
        this.userID = userID;
        this.userTypeID = userTypeID;
        this.studentRegID = studentRegID;
        this.schoolID = schoolID;
        this.title = title;
        this.description = description;
    }

    public String getTodoListID() {
        return todoListID;
    }

    public void setTodoListID(String todoListID) {
        this.todoListID = todoListID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(String userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getStudentRegID() {
        return studentRegID;
    }

    public void setStudentRegID(String studentRegID) {
        this.studentRegID = studentRegID;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
