package varun.com.studentmanagementsystemsample.constants;

/**
 * Created by VarunBarve on 12/05/2016.
 */
public class Api {

    public static final String BASE_URL = "http://103.7.130.46:8092/";

    public static final String LOGIN_URL = BASE_URL + "api/Login/getLogin";
    public static final String GET_CHILDREN_URL = BASE_URL + "api/Parent/getChild";
    public static final String ATTENDANCE_URL = BASE_URL + "api/Attendance/getAttendance";
    public static final String EVENTS_URL = BASE_URL + "api/Event/getEvents";
    public static final String PERFORMANCE_URL = BASE_URL + "api/Performance/getPerformance";
    public static final String INCIDENT_OVERVIEW_URL = BASE_URL + "api/Incident/getIncidentOverallList";
    public static final String LIBRARY_URL = BASE_URL + "api/Library/getLibraryDetails";
    public static final String TODO_LIST_URL = BASE_URL + "api/TodoList/getTodoList";
    public static final String ADD_TODO_URL = BASE_URL + "api/TodoList/addTodoList";
    public static final String UPDATE_TODO_URL = BASE_URL + "api/TodoList/updateTodoList";
    public static final String TIME_TABLE_URL = BASE_URL + "api/TimeTable/getTimeTableDetails";
    public static final String FEE_PAYMENT_URL = BASE_URL + "api/FeePayment/getFeePayment";
    public static final String STUDENT_REPORT_URL = BASE_URL + "api/StudentReport/getStudentReport";
    public static final String INCIDENT_CLASSIFICATION_URL = BASE_URL + "api/Incident/getIncidentClassification";
    public static final String CREATE_INCIDENT_URL = BASE_URL + "api/Incident/createIncident";
    public static final String IMAGE_GALLERY_URL = BASE_URL + "api/Gallery/getImageGallery";
    public static final String USER_AUTOCOMPLETE_URL = BASE_URL + "api/Incident/getIncidentAgainstName";


}
