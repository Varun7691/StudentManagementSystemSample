package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by Varun on 4/2/2016.
 */
public class FeePaymentBean {
    private String componentName, feesPaid, amountDue, dueDate;

    public FeePaymentBean(String componentName, String feesPaid, String amountDue, String dueDate) {
        this.componentName = componentName;
        this.feesPaid = feesPaid;
        this.amountDue = amountDue;
        this.dueDate = dueDate;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(String feesPaid) {
        this.feesPaid = feesPaid;
    }

    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}

