package varun.com.studentmanagementsystemsample.bean;

import java.util.Date;

/**
 * Created by VarunBarve on 13/05/2016.
 */
public class AttendanceBean {

    private Date attendanceDate;
    private String attendanceStatus;

    public AttendanceBean(Date attendanceDate, String attendanceStatus) {
        this.attendanceDate = attendanceDate;
        this.attendanceStatus = attendanceStatus;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
