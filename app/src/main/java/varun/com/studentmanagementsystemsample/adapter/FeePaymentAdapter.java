package varun.com.studentmanagementsystemsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.FeePaymentBean;

/**
 * Created by Varun on 4/2/2016.
 */
public class FeePaymentAdapter extends
        RecyclerView.Adapter<FeePaymentAdapter.ViewHolder> {

    ArrayList<FeePaymentBean> list;
    Context context;
    LayoutInflater inflater;

    public FeePaymentAdapter(Context context, ArrayList<FeePaymentBean> list) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.fee_payment_list_item, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.component.setText(list.get(position).getComponent());
        holder.amount.setText(list.get(position).getAmount());
        holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView component, amount, date;

        public ViewHolder(View itemView) {
            super(itemView);

            component = (TextView) itemView.findViewById(R.id.component);
            amount = (TextView) itemView.findViewById(R.id.amount);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
