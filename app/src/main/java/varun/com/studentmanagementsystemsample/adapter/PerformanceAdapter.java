package varun.com.studentmanagementsystemsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.PerformanceBean;

/**
 * Created by Varun on 4/2/2016.
 */
public class PerformanceAdapter extends
        RecyclerView.Adapter<PerformanceAdapter.ViewHolder> {

    ArrayList<PerformanceBean> list;
    Context context;
    LayoutInflater inflater;

    public PerformanceAdapter(Context context, ArrayList<PerformanceBean> list) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public PerformanceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.performance_list_item, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(PerformanceAdapter.ViewHolder holder, int position) {
        holder.subject.setText(list.get(position).getSubject());
        holder.date.setText(list.get(position).getExamDate());
        holder.marks.setText(list.get(position).getMarks());
        holder.subjectExamType.setText(list.get(position).getSubjectExamType());
        holder.examType.setText(list.get(position).getExamType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectExamType, examType, subject, marks, date;

        public ViewHolder(View itemView) {
            super(itemView);

            subjectExamType = (TextView) itemView.findViewById(R.id.subjectExamType);
            examType = (TextView) itemView.findViewById(R.id.examType);
            subject = (TextView) itemView.findViewById(R.id.subject);
            marks = (TextView) itemView.findViewById(R.id.marks);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
