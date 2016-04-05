package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by Varun on 4/2/2016.
 */
public class PerformanceBean {
    private String subjectExamType, examType, subject, marks, examDate;

    public PerformanceBean(String subjectExamType, String examType, String subject, String marks, String examDate) {
        this.subjectExamType = subjectExamType;
        this.examType = examType;
        this.subject = subject;
        this.marks = marks;
        this.examDate = examDate;
    }

    public String getSubjectExamType() {
        return subjectExamType;
    }

    public void setSubjectExamType(String subjectExamType) {
        this.subjectExamType = subjectExamType;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }
}
