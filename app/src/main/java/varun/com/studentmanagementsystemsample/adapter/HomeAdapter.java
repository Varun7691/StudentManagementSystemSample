package varun.com.studentmanagementsystemsample.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.MainActivity;
import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.HomeBean;

/**
 * Created by Varun on 4/17/2016.
 */
public class HomeAdapter extends
        RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    ArrayList<HomeBean> list;
    Context context;
    LayoutInflater inflater;
    AppCompatActivity activity;

    public HomeAdapter(Context context, ArrayList<HomeBean> list) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity = (AppCompatActivity) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.home_list_item, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getStudentFirstName());
        holder.avgAttendance.setText("Average Attendance: " + list.get(position).getAvrageAttendance() + "%");
        holder.avgperformance.setText("Average Performance: " + list.get(position).getAvragePerformance() + "%");
        holder.studentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("StudentId", ""+list.get(position).getStudentID());
                context.startActivity(intent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, avgAttendance, avgperformance;
        public ImageView image;
        Button studentDetails;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.child_name);
            avgAttendance = (TextView) itemView.findViewById(R.id.child_avg_attendance);
            avgperformance = (TextView) itemView.findViewById(R.id.child_avg_performance);
            image = (ImageView) itemView.findViewById(R.id.child_image);
            studentDetails = (Button) itemView.findViewById(R.id.student_details);
        }
    }
}
