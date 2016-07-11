package varun.com.studentmanagementsystemsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.CoScholasticChildItemBean;

/**
 * Created by Varun Barve on 6/30/2016.
 */
public class CoScholasticAdapter extends RecyclerView.Adapter<CoScholasticAdapter.ViewHolder> {

    ArrayList<CoScholasticChildItemBean> list;
    Context context;
    LayoutInflater inflater;

    public CoScholasticAdapter(Context context, ArrayList<CoScholasticChildItemBean> list) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CoScholasticAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.coscholastic_list_layout, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(CoScholasticAdapter.ViewHolder holder, int position) {
        holder.description.setText(list.get(position).getDescription());
        holder.remark.setText(list.get(position).getRemark());
        holder.grade.setText(list.get(position).getGrade());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView description, remark, grade;

        public ViewHolder(View itemView) {
            super(itemView);

            description = (TextView) itemView.findViewById(R.id.description_label);
            remark = (TextView) itemView.findViewById(R.id.remark_label);
            grade = (TextView) itemView.findViewById(R.id.grade_label);
        }
    }
}
