package varun.com.studentmanagementsystemsample.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

    public void createLoginSession(boolean isLogin, int userId, String userName, int roleId, int userType) {

        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_USER_NAME, userName);
        editor.putInt(KEY_ROLE_ID, roleId);
        editor.putInt(KEY_USER_TYPE, userType);
        editor.commit();
    }

    public User getUserDetails() {

        int userId = pref.getInt(KEY_USER_ID, -1);
        String userName = pref.getString(KEY_USER_NAME, "");
        int userType = pref.getInt(KEY_USER_TYPE, -1);
        int roleId = pref.getInt(KEY_ROLE_ID, -1);

        User user = new User(userId, roleId, userType, userName);

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
