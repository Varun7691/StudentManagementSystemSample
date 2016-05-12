package varun.com.studentmanagementsystemsample.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.adapter.TodoAdapter;
import varun.com.studentmanagementsystemsample.bean.TodoBean;
import varun.com.studentmanagementsystemsample.database.TodoListSQLHelper;
import varun.com.studentmanagementsystemsample.utils.DividerItemDecoration;

/**
 * Created by Varun on 4/2/2016.
 */
public class ToDoListFragment extends Fragment {

    RecyclerView rvTodoList;
    ArrayList<TodoBean> list;
    TodoAdapter adapter;
    FloatingActionButton todo_fab;
    private TodoListSQLHelper todoListSQLHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_to_do_list, container, false);

        rvTodoList = (RecyclerView) rootView.findViewById(R.id.rvTodoList);
        todo_fab = (FloatingActionButton) rootView.findViewById(R.id.todo_fab);

        list = new ArrayList<>();
        list.add(new TodoBean("1", "Todo List", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        list.add(new TodoBean("1", "Todo List", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        list.add(new TodoBean("1", "Todo List", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        list.add(new TodoBean("1", "Todo List", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        list.add(new TodoBean("1", "Todo List", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        list.add(new TodoBean("1", "Todo List", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));

        adapter = new TodoAdapter(ToDoListFragment.this.getActivity(), list);

        rvTodoList.setAdapter(adapter);
        rvTodoList.setLayoutManager(new LinearLayoutManager(ToDoListFragment.this.getActivity()));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(ToDoListFragment.this.getActivity(), DividerItemDecoration.VERTICAL_LIST);
        rvTodoList.addItemDecoration(itemDecoration);

        todo_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialog();
            }
        });

        return rootView;
    }

    public void displayAlertDialog() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final EditText etUsername = (EditText) alertLayout.findViewById(R.id.et_Username);
        final EditText etPassword = (EditText) alertLayout.findViewById(R.id.et_Password);

        AlertDialog.Builder alert = new AlertDialog.Builder(ToDoListFragment.this.getActivity());
        alert.setTitle("ADD TASK");
        alert.setView(alertLayout);
        alert.setCancelable(true);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(ToDoListFragment.this.getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("ADD", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // code for matching password
//                String user = etUsername.getText().toString();
//                String pass = etPassword.getText().toString();
////                Toast.makeText(ToDoListFragment.this.getActivity(), "Username: " + user + " Password: " + pass, Toast.LENGTH_SHORT).show();
//
//                // String todoTaskInput = todoET.getText().toString();
//                todoListSQLHelper = new TodoListSQLHelper(ToDoListFragment.this.getActivity());
//                SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.clear();
//
//                //write the Todo task input into database table
//                values.put(TodoListSQLHelper.TODO_TITLE, user);
//                values.put(TodoListSQLHelper.TODO_DESC, pass);
//                sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
//
//                //update the Todo task list UI
//                updateTodoList();


            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private void updateTodoList() {
        todoListSQLHelper = new TodoListSQLHelper(ToDoListFragment.this.getActivity());
//        SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getReadableDatabase();
//        //cursor to read todo task list from database
//        Cursor cursor = sqLiteDatabase.query(TodoListSQLHelper.TABLE_NAME,
//                new String[]{TodoListSQLHelper._ID, TodoListSQLHelper.TODO_TITLE},
//                null, null, null, null, null);

//        TodoListSQLHelper db = new TodoListSQLHelper(getActivity());

        try {
            Cursor c = todoListSQLHelper.getTodoList();
            c.moveToFirst();
            if (c.getCount() != 0) {
                do {
                    String id = c.getString(0);
                    String title = c.getString(1);
                    String desc = c.getString(2);

                    list.add(new TodoBean(id, title, desc));

                } while (c.moveToNext());
                c.close();
                todoListSQLHelper.close();
            }
        } catch (SQLException e) {
            System.out.println("data not found");

        }

        adapter.notifyDataSetChanged();
    }
}
