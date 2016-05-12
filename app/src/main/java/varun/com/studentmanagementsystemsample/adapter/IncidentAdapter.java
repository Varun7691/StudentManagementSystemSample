package varun.com.studentmanagementsystemsample.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.IncidentDetailsActivity;
import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.FeePaymentBean;
import varun.com.studentmanagementsystemsample.bean.IncidentBean;

/**
 * Created by Varun on 4/17/2016.
 */
public class IncidentAdapter extends
        RecyclerView.Adapter<IncidentAdapter.ViewHolder> {

    ArrayList<IncidentBean> list;
    Context context;
    LayoutInflater inflater;

    public IncidentAdapter(Context context, ArrayList<IncidentBean> list) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.incident_list_item, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.against.setText("Raised against: " + list.get(position).getIncidentAgainst());
        holder.incidentFor.setText("Raised by: " + list.get(position).getIncidentFor());
        holder.date.setText(list.get(position).getIncidentDate());
        holder.nature.setText(list.get(position).getIncidentNature());
        holder.incidentListItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IncidentDetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView against, incidentFor, date, nature;
        public LinearLayout incidentListItemContainer;

        public ViewHolder(View itemView) {
            super(itemView);

            against = (TextView) itemView.findViewById(R.id.incident_against);
            incidentFor = (TextView) itemView.findViewById(R.id.incident_for);
            date = (TextView) itemView.findViewById(R.id.incident_date);
            nature = (TextView) itemView.findViewById(R.id.incident_nature);
            incidentListItemContainer = (LinearLayout) itemView.findViewById(R.id.incident_list_item_container);
        }
    }
}
