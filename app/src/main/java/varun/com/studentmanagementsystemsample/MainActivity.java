package varun.com.studentmanagementsystemsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import varun.com.studentmanagementsystemsample.constants.Constants;
import varun.com.studentmanagementsystemsample.fragments.AttendanceFragment;
import varun.com.studentmanagementsystemsample.fragments.EventsFragment;
import varun.com.studentmanagementsystemsample.fragments.FeePaymentFragment;
import varun.com.studentmanagementsystemsample.fragments.GalleryFragment;
import varun.com.studentmanagementsystemsample.fragments.IncidentsFragment;
import varun.com.studentmanagementsystemsample.fragments.LibraryFragment;
import varun.com.studentmanagementsystemsample.fragments.PerformanceFragment;
import varun.com.studentmanagementsystemsample.fragments.StudentReportFragment;
import varun.com.studentmanagementsystemsample.fragments.TimeTableFragment;
import varun.com.studentmanagementsystemsample.fragments.ToDoListFragment;
import varun.com.studentmanagementsystemsample.utils.SessionManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String studentId, schoolId;
    public static int globalPosition;
    Fragment fragment;
    SessionManager sessionManager;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager = new SessionManager(MainActivity.this);

        studentId = getIntent().getStringExtra("StudentId");
        schoolId = getIntent().getStringExtra("SchoolId");
        globalPosition = getIntent().getIntExtra("GLOBAL POSITION", 0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SelectItem(1);
        hideItem();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
//            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//            startActivity(intent);
//            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void hideItem() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();

        int userType = sessionManager.getUserDetails().getUserTypeID();

        if (userType == Constants.USER_TYPE_STUDENT) {
            nav_Menu.findItem(R.id.nav_home).setVisible(false);
        } else {
            nav_Menu.findItem(R.id.nav_home).setVisible(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            SelectItem(0);
        } else if (id == R.id.nav_performance) {
            SelectItem(1);
        } else if (id == R.id.nav_event) {
            SelectItem(2);
        } else if (id == R.id.nav_attendance) {
            SelectItem(3);
        } else if (id == R.id.nav_fee_payment) {
            SelectItem(4);
        } else if (id == R.id.nav_incidents) {
            SelectItem(5);
        } else if (id == R.id.nav_timetable) {
            SelectItem(6);
        } else if (id == R.id.nav_library) {
            SelectItem(7);
        } else if (id == R.id.nav_student_report) {
            SelectItem(8);
        } else if (id == R.id.nav_to_do_list) {
            SelectItem(9);
        } else if (id == R.id.nav_gallery) {
            SelectItem(10);
        } else if (id == R.id.nav_logout) {
            SelectItem(11);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void SelectItem(int position) {

        switch (position) {
            case 0:
//                fragment = new HomeFragment();
//
//                android.support.v4.app.FragmentTransaction ftHome = getSupportFragmentManager()
//                        .beginTransaction();
//                ftHome.replace(R.id.frame_container, fragment);
//                ftHome.commit();

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();

                break;
            case 1:
                getSupportActionBar().setTitle("Performance");

                fragment = new PerformanceFragment();

                android.support.v4.app.FragmentTransaction ftPerformance = getSupportFragmentManager()
                        .beginTransaction();
                ftPerformance.replace(R.id.frame_container, fragment);
                ftPerformance.commit();
                break;
            case 2:
                getSupportActionBar().setTitle("Event");

                fragment = new EventsFragment();

                android.support.v4.app.FragmentTransaction ftEvents = getSupportFragmentManager()
                        .beginTransaction();
                ftEvents.replace(R.id.frame_container, fragment);
                ftEvents.commit();
                break;
            case 3:
                getSupportActionBar().setTitle("Attendance");

                fragment = new AttendanceFragment();

                android.support.v4.app.FragmentTransaction ftAttendance = getSupportFragmentManager()
                        .beginTransaction();
                ftAttendance.replace(R.id.frame_container, fragment);
                ftAttendance.commit();
                break;
            case 4:
                getSupportActionBar().setTitle("Fee Payment");

                fragment = new FeePaymentFragment();

                android.support.v4.app.FragmentTransaction ftFeePayment = getSupportFragmentManager()
                        .beginTransaction();
                ftFeePayment.replace(R.id.frame_container, fragment);
                ftFeePayment.commit();
                break;
            case 5:
                getSupportActionBar().setTitle("Incidents");

                fragment = new IncidentsFragment();

                android.support.v4.app.FragmentTransaction ftIncidents = getSupportFragmentManager()
                        .beginTransaction();
                ftIncidents.replace(R.id.frame_container, fragment);
                ftIncidents.commit();
                break;
            case 6:
                getSupportActionBar().setTitle("Time Table");

                fragment = new TimeTableFragment();

                android.support.v4.app.FragmentTransaction ftTimeTable = getSupportFragmentManager()
                        .beginTransaction();
                ftTimeTable.replace(R.id.frame_container, fragment);
                ftTimeTable.commit();
                break;
            case 7:
                getSupportActionBar().setTitle("Library");

                fragment = new LibraryFragment();

                android.support.v4.app.FragmentTransaction ftLibrary = getSupportFragmentManager()
                        .beginTransaction();
                ftLibrary.replace(R.id.frame_container, fragment);
                ftLibrary.commit();
                break;
            case 8:
                getSupportActionBar().setTitle("Student Report");

                fragment = new StudentReportFragment();

                android.support.v4.app.FragmentTransaction ftStudentReport = getSupportFragmentManager()
                        .beginTransaction();
                ftStudentReport.replace(R.id.frame_container, fragment);
                ftStudentReport.commit();
                break;
            case 9:
                getSupportActionBar().setTitle("Todo List");

                fragment = new ToDoListFragment();

                android.support.v4.app.FragmentTransaction ftToDoList = getSupportFragmentManager()
                        .beginTransaction();
                ftToDoList.replace(R.id.frame_container, fragment);
                ftToDoList.commit();
                break;
            case 10:
                getSupportActionBar().setTitle("Gallery");

                fragment = new GalleryFragment();

                android.support.v4.app.FragmentTransaction ftGallery = getSupportFragmentManager()
                        .beginTransaction();
                ftGallery.replace(R.id.frame_container, fragment);
                ftGallery.commit();
                break;
            case 11:
                SessionManager sessionManager = new SessionManager(MainActivity.this);
                sessionManager.logoutUser();
                Intent logOutIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logOutIntent);
                finish();

                break;
            default:
                break;
        }
    }
}
