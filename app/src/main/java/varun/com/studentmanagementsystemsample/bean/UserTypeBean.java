package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by Varun Barve on 8/28/2016.
 */
public class UserTypeBean {
    private String id, value;

    public UserTypeBean(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
