package varun.com.studentmanagementsystemsample.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 7/14/2016.
 */
public class ImagesBean implements Serializable {

    String imageID, schoolID, imageTitle, imageDescription, imageTakenDate, imagePath;

    public ImagesBean(String imageID, String schoolID, String imageTitle, String imageDescription, String imageTakenDate, String imagePath) {
        this.imageID = imageID;
        this.schoolID = schoolID;
        this.imageTitle = imageTitle;
        this.imageDescription = imageDescription;
        this.imageTakenDate = imageTakenDate;
        this.imagePath = imagePath;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getImageTakenDate() {
        return imageTakenDate;
    }

    public void setImageTakenDate(String imageTakenDate) {
        this.imageTakenDate = imageTakenDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
