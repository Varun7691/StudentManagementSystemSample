package varun.com.studentmanagementsystemsample.bean;

import java.io.Serializable;

/**
 * Created by Varun Barve on 6/30/2016.
 */
public class CoScholasticChildItemBean implements Serializable {

    String description, remark, grade, typeDesc;

    public CoScholasticChildItemBean(String description, String remark, String grade, String typeDesc) {
        this.description = description;
        this.remark = remark;
        this.grade = grade;
        this.typeDesc = typeDesc;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
