package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by VarunBarve on 20/04/2016.
 */
public class LibraryBean {

    private String userID, userTypeID, studentRegID, schoolID, allocatedDate, DueDate, allocatedTo, lateCharges, authorName, extendedDay, bookName, lateReturnChergePerDay, defaulAllocatedDay, maximumNoOfExtn, allotedExtension;

    public LibraryBean(String userID, String userTypeID, String studentRegID, String schoolID, String allocatedDate, String dueDate, String allocatedTo, String lateCharges, String authorName, String extendedDay, String bookName, String lateReturnChergePerDay, String defaulAllocatedDay, String maximumNoOfExtn, String allotedExtension) {
        this.userID = userID;
        this.userTypeID = userTypeID;
        this.studentRegID = studentRegID;
        this.schoolID = schoolID;
        this.allocatedDate = allocatedDate;
        DueDate = dueDate;
        this.allocatedTo = allocatedTo;
        this.lateCharges = lateCharges;
        this.authorName = authorName;
        this.extendedDay = extendedDay;
        this.bookName = bookName;
        this.lateReturnChergePerDay = lateReturnChergePerDay;
        this.defaulAllocatedDay = defaulAllocatedDay;
        this.maximumNoOfExtn = maximumNoOfExtn;
        this.allotedExtension = allotedExtension;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getLateReturnChergePerDay() {
        return lateReturnChergePerDay;
    }

    public void setLateReturnChergePerDay(String lateReturnChergePerDay) {
        this.lateReturnChergePerDay = lateReturnChergePerDay;
    }

    public String getDefaulAllocatedDay() {
        return defaulAllocatedDay;
    }

    public void setDefaulAllocatedDay(String defaulAllocatedDay) {
        this.defaulAllocatedDay = defaulAllocatedDay;
    }

    public String getMaximumNoOfExtn() {
        return maximumNoOfExtn;
    }

    public void setMaximumNoOfExtn(String maximumNoOfExtn) {
        this.maximumNoOfExtn = maximumNoOfExtn;
    }

    public String getAllotedExtension() {
        return allotedExtension;
    }

    public void setAllotedExtension(String allotedExtension) {
        this.allotedExtension = allotedExtension;
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

    public String getAllocatedDate() {
        return allocatedDate;
    }

    public void setAllocatedDate(String allocatedDate) {
        this.allocatedDate = allocatedDate;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public String getAllocatedTo() {
        return allocatedTo;
    }

    public void setAllocatedTo(String allocatedTo) {
        this.allocatedTo = allocatedTo;
    }

    public String getLateCharges() {
        return lateCharges;
    }

    public void setLateCharges(String lateCharges) {
        this.lateCharges = lateCharges;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getExtendedDay() {
        return extendedDay;
    }

    public void setExtendedDay(String extendedDay) {
        this.extendedDay = extendedDay;
    }
}
