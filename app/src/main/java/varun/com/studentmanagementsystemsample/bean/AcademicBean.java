package varun.com.studentmanagementsystemsample.bean;

/**
 * Created by VarunBarve on 26/05/2016.
 */
public class AcademicBean {

    private String studentId, subject, academicYear, term, board, FA1, FA2, totalFA, SA1, totalFASA1, rank;

    public AcademicBean(String studentId, String subject, String academicYear, String term, String board, String FA1, String FA2, String totalFA, String SA1, String totalFASA1, String rank) {
        this.studentId = studentId;
        this.subject = subject;
        this.academicYear = academicYear;
        this.term = term;
        this.board = board;
        this.FA1 = FA1;
        this.FA2 = FA2;
        this.totalFA = totalFA;
        this.SA1 = SA1;
        this.totalFASA1 = totalFASA1;
        this.rank = rank;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getFA1() {
        return FA1;
    }

    public void setFA1(String FA1) {
        this.FA1 = FA1;
    }

    public String getFA2() {
        return FA2;
    }

    public void setFA2(String FA2) {
        this.FA2 = FA2;
    }

    public String getTotalFA() {
        return totalFA;
    }

    public void setTotalFA(String totalFA) {
        this.totalFA = totalFA;
    }

    public String getSA1() {
        return SA1;
    }

    public void setSA1(String SA1) {
        this.SA1 = SA1;
    }

    public String getTotalFASA1() {
        return totalFASA1;
    }

    public void setTotalFASA1(String totalFASA1) {
        this.totalFASA1 = totalFASA1;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
