package varun.com.studentmanagementsystemsample.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.TodoBean;

/**
 * Created by VarunBarve on 21/04/2016.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    ArrayList<TodoBean> list;
    Context context;
    LayoutInflater inflater;

    public TodoAdapter(Context context, ArrayList<TodoBean> list) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.todo_list_item, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.desc.setText(list.get(position).getDesc());
        holder.todoContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlertDialog(list.get(position).getTitle(), list.get(position).getDesc());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void displayAlertDialog(String title, String desc) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
        final EditText etUsername = (EditText) alertLayout.findViewById(R.id.et_Username);
        final EditText etPassword = (EditText) alertLayout.findViewById(R.id.et_Password);

        etUsername.setText(title);
        etPassword.setText(desc);

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("ADD TASK");
        alert.setView(alertLayout);
        alert.setCancelable(true);
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alert.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;

        public LinearLayout todoContainer;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.todo_title);
            desc = (TextView) itemView.findViewById(R.id.todo_desc);

            todoContainer = (LinearLayout) itemView.findViewById(R.id.todo_container);
        }
    }
}
