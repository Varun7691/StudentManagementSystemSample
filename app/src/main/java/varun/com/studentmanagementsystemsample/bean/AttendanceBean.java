package varun.com.studentmanagementsystemsample.bean;

import java.util.Date;

/**
 * Created by VarunBarve on 13/05/2016.
 */
public class AttendanceBean {

    private Date attendanceDate;
    private boolean attendanceStatus;

    public AttendanceBean(Date attendanceDate, boolean attendanceStatus) {
        this.attendanceDate = attendanceDate;
        this.attendanceStatus = attendanceStatus;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public boolean isAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(boolean attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
