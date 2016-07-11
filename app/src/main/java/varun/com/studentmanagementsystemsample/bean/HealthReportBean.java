package varun.com.studentmanagementsystemsample.bean;

import java.io.Serializable;

/**
 * Created by Varun Barve on 6/30/2016.
 */
public class HealthReportBean implements Serializable {

    String hieght, weight, bloodGroup, dental, visionLeft, visionRight;

    public String getHieght() {
        return hieght;
    }

    public void setHieght(String hieght) {
        this.hieght = hieght;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDental() {
        return dental;
    }

    public void setDental(String dental) {
        this.dental = dental;
    }

    public String getVisionLeft() {
        return visionLeft;
    }

    public void setVisionLeft(String visionLeft) {
        this.visionLeft = visionLeft;
    }

    public String getVisionRight() {
        return visionRight;
    }

    public void setVisionRight(String visionRight) {
        this.visionRight = visionRight;
    }
}
