package varun.com.studentmanagementsystemsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.LibraryBean;

/**
 * Created by VarunBarve on 20/04/2016.
 */
public class LibraryAdapter extends
        RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    ArrayList<LibraryBean> list;
    Context context;
    LayoutInflater inflater;

    public LibraryAdapter(Context context, ArrayList<LibraryBean> list) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = inflater.inflate(R.layout.library_list_item, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getBookName());
        holder.allocatedDate.setText(list.get(position).getAllocatedDate());
        holder.dueDate.setText(list.get(position).getDueDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, allocatedDate, dueDate;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.book_name);
            allocatedDate = (TextView) itemView.findViewById(R.id.book_allocated_date);
            dueDate = (TextView) itemView.findViewById(R.id.book_due_date);
        }
    }
}

