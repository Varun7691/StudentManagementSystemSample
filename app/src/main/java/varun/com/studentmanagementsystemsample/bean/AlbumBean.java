package varun.com.studentmanagementsystemsample.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 7/14/2016.
 */
public class AlbumBean {

    String albumID, albumTitle, albumDescription, albumCreationDate;
    ArrayList<ImagesBean> imageList;

    public AlbumBean(String albumID, String albumTitle, String albumDescription, String albumCreationDate, ArrayList<ImagesBean> imageList) {
        this.albumID = albumID;
        this.albumTitle = albumTitle;
        this.albumDescription = albumDescription;
        this.albumCreationDate = albumCreationDate;
        this.imageList = imageList;
    }

    public String getAlbumID() {
        return albumID;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
    }

    public String getAlbumCreationDate() {
        return albumCreationDate;
    }

    public void setAlbumCreationDate(String albumCreationDate) {
        this.albumCreationDate = albumCreationDate;
    }

    public ArrayList<ImagesBean> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<ImagesBean> imageList) {
        this.imageList = imageList;
    }
}
