package varun.com.studentmanagementsystemsample.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.bean.HomeBean;
import varun.com.studentmanagementsystemsample.constants.Constants;

/**
 * Created by VarunBarve on 12/05/2016.
 */
public class SessionManager {

    public static final String IS_AUTH = "IsAuth";

    public static final String KEY_USER_ID = Constants.KEY_USER_ID;
    public static final String KEY_USER_NAME = Constants.KEY_USER_NAME;
    public static final String KEY_ROLE_ID = Constants.KEY_ROLE_ID;
    public static final String KEY_USER_TYPE = Constants.KEY_USER_TYPE;
    public static final String KEY_USER_SPECIFIC_ID = Constants.KEY_USER_SPECIFIC_ID;
    public static final String KEY_USER_STUDENT_ID = Constants.KEY_STUDENT_ID;
    public static final String KEY_USER_SCHOOL_ID = Constants.KEY_SCHOOL_ID;
    public static final String KEY_USER_CLASS_ID = Constants.KEY_CLASS_ID;
    public static final String KEY_USER_SECTION_ID = Constants.KEY_SECTION_ID;
    public static final String KEY_USER_TERM_ID = "termID";
    public static final String KEY_USER_ACADEMIC_YEAR_ID = "academicYearID";

    public static final String KEY_STUDENT_LIST = "StudentList";

    // Sharedpref file name
    private static final String PREF_NAME = "LOGGED";
    private static final String IS_LOGIN = "IsLoggedIn";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private String TAG = "shareoffer";

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(boolean isLogin, int userId, String userName, int roleId, int userType, int userSpecificID, int studentRegID, int schoolID, int classID, int sectionID, int termID, int academicYearID) {

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_USER_NAME, userName);
        editor.putInt(KEY_ROLE_ID, roleId);
        editor.putInt(KEY_USER_TYPE, userType);
        editor.putInt(KEY_USER_SPECIFIC_ID, userSpecificID);
        editor.putInt(KEY_USER_STUDENT_ID, studentRegID);
        editor.putInt(KEY_USER_SCHOOL_ID, schoolID);
        editor.putInt(KEY_USER_CLASS_ID, classID);
        editor.putInt(KEY_USER_SECTION_ID, sectionID);
        editor.putInt(KEY_USER_TERM_ID, termID);
        editor.putInt(KEY_USER_ACADEMIC_YEAR_ID, academicYearID);
        editor.commit();
    }

    public void createStudentLoginSession(boolean isLogin, int userId, String userName, int roleId, int userType, int userSpecificID, int studentRegID, int schoolID, int classID, int sectionID, int termID, int academicYearID) {

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_USER_NAME, userName);
        editor.putInt(KEY_ROLE_ID, roleId);
        editor.putInt(KEY_USER_TYPE, userType);
        editor.putInt(KEY_USER_SPECIFIC_ID, userSpecificID);
        editor.putInt(KEY_USER_STUDENT_ID, studentRegID);
        editor.putInt(KEY_USER_SCHOOL_ID, schoolID);
        editor.putInt(KEY_USER_CLASS_ID, classID);
        editor.putInt(KEY_USER_SECTION_ID, sectionID);
        editor.putInt(KEY_USER_TERM_ID, termID);
        editor.putInt(KEY_USER_ACADEMIC_YEAR_ID, academicYearID);
        editor.commit();
    }


    public ArrayList<HomeBean> getStudentList() {

        try {
            return (ArrayList<HomeBean>) ObjectSerializer
                    .deserialize(pref.getString(KEY_STUDENT_LIST, ObjectSerializer
                            .serialize(new ArrayList<HomeBean>())));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setStudentList(ArrayList<HomeBean> studentList) {
        try {
            editor.putString(KEY_STUDENT_LIST, ObjectSerializer.serialize(studentList));
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUserDetails() {

        int userId = pref.getInt(KEY_USER_ID, -1);
        String userName = pref.getString(KEY_USER_NAME, "");
        int userType = pref.getInt(KEY_USER_TYPE, -1);
        int roleId = pref.getInt(KEY_ROLE_ID, -1);
        int userSpecificID = pref.getInt(KEY_USER_SPECIFIC_ID, -1);
        int studentRegID = pref.getInt(KEY_USER_STUDENT_ID, -1);
        int schoolID = pref.getInt(KEY_USER_SCHOOL_ID, -1);
        int classID = pref.getInt(KEY_USER_CLASS_ID, -1);
        int sectionID = pref.getInt(KEY_USER_SECTION_ID, -1);
        int termID = pref.getInt(KEY_USER_TERM_ID, -1);
        int academicYearID = pref.getInt(KEY_USER_ACADEMIC_YEAR_ID, -1);

        User user = new User(userId, userSpecificID, roleId, userType, studentRegID, schoolID, classID, sectionID, userName, termID, academicYearID);

        return user;
    }

    public User getStudentDetails() {

        int userId = pref.getInt(KEY_USER_ID, -1);
        String userName = pref.getString(KEY_USER_NAME, "");
        int userType = pref.getInt(KEY_USER_TYPE, -1);
        int roleId = pref.getInt(KEY_ROLE_ID, -1);
        int userSpecificID = pref.getInt(KEY_USER_SPECIFIC_ID, -1);
        int studentRegID = pref.getInt(KEY_USER_STUDENT_ID, -1);
        int schoolID = pref.getInt(KEY_USER_SCHOOL_ID, -1);
        int classID = pref.getInt(KEY_USER_CLASS_ID, -1);
        int sectionID = pref.getInt(KEY_USER_SECTION_ID, -1);
        int termID = pref.getInt(KEY_USER_TERM_ID, -1);
        int academicYearID = pref.getInt(KEY_USER_ACADEMIC_YEAR_ID, -1);

        User user = new User(userId, userSpecificID, roleId, userType, studentRegID, schoolID, classID, sectionID, userName, termID, academicYearID);

        return user;
    }

    public boolean isLoggedIn() {
        Log.d(TAG, "" + pref.getBoolean(IS_LOGIN, false));
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }
}

