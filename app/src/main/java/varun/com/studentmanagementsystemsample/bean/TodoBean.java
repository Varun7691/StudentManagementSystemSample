package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by VarunBarve on 21/04/2016.
 */
public class TodoBean {

    private String todoListID, userID, studentRegID, status, isActive, title, description, dtCreated;

    public TodoBean(String todoListID, String userID, String studentRegID, String status, String isActive, String title, String description, String dtCreated) {
        this.todoListID = todoListID;
        this.userID = userID;
        this.studentRegID = studentRegID;
        this.status = status;
        this.isActive = isActive;
        this.title = title;
        this.description = description;
        this.dtCreated = dtCreated;
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

    public String getStudentRegID() {
        return studentRegID;
    }

    public void setStudentRegID(String studentRegID) {
        this.studentRegID = studentRegID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
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

    public String getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(String dtCreated) {
        this.dtCreated = dtCreated;
    }
}
