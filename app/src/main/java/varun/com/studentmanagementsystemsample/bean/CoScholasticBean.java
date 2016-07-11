package varun.com.studentmanagementsystemsample.bean;

import java.util.ArrayList;

/**
 * Created by Varun Barve on 6/30/2016.
 */
public class CoScholasticBean {

    String typeDesc;
    ArrayList<CoScholasticChildItemBean> list;

    public CoScholasticBean(String typeDesc, ArrayList<CoScholasticChildItemBean> list) {
        this.typeDesc = typeDesc;
        this.list = list;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public ArrayList<CoScholasticChildItemBean> getList() {
        return list;
    }

    public void setList(ArrayList<CoScholasticChildItemBean> list) {
        this.list = list;
    }
}
