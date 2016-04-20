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

import varun.com.studentmanagementsystemsample.EventDetailActivity;
import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.EventBean;

/**
 * Created by Varun on 4/19/2016.
 */
public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder> {

    ArrayList<EventBean> list;
    Context context;
    LayoutInflater inflater;

    public EventRecyclerAdapter(Context context, ArrayList<EventBean> list) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.event_list_item, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.startDate.setText(list.get(position).getStartDate());
        holder.endDate.setText(list.get(position).getEndDate());
        holder.eventListContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, startDate, endDate;

        public LinearLayout eventListContainer;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.event_name);
            startDate = (TextView) itemView.findViewById(R.id.event_startDate);
            endDate = (TextView) itemView.findViewById(R.id.event_endDate);

            eventListContainer = (LinearLayout) itemView.findViewById(R.id.event_list_container);
        }
    }
}