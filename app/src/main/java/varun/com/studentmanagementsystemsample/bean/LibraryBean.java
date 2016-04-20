package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by VarunBarve on 20/04/2016.
 */
public class LibraryBean {
    private String bookName, id, allocatedDate, dueDate;

    public LibraryBean(String bookName, String id, String allocatedDate, String dueDate) {
        this.bookName = bookName;
        this.id = id;
        this.allocatedDate = allocatedDate;
        this.dueDate = dueDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAllocatedDate() {
        return allocatedDate;
    }

    public void setAllocatedDate(String allocatedDate) {
        this.allocatedDate = allocatedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
