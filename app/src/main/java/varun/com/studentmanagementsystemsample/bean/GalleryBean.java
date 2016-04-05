package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by Varun on 4/3/2016.
 */
public class GalleryBean {

    private int image;
    private String imageBase64, caption, imageId, description;

    public GalleryBean(int image) {
        this.image = image;
    }

    public GalleryBean(int image, String imageBase64, String caption, String imageId, String description) {
        this.image = image;
        this.imageBase64 = imageBase64;
        this.caption = caption;
        this.imageId = imageId;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
