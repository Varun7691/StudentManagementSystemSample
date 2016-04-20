package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by Varun on 4/2/2016.
 */
public class FeePaymentBean {
    private String component, amount, date, feesDue;

    public FeePaymentBean(String component, String amount, String date, String feesDue) {
        this.component = component;
        this.amount = amount;
        this.date = date;
        this.feesDue = feesDue;
    }

    public String getComponent() {
        return component;
    }

    public String getFeesDue() {
        return feesDue;
    }

    public void setFeesDue(String feesDue) {
        this.feesDue = feesDue;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

