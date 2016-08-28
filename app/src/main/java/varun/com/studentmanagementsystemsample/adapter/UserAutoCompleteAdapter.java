package varun.com.studentmanagementsystemsample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.UserTypeBean;

/**
 * Created by Varun Barve on 8/28/2016.
 */
public class UserAutoCompleteAdapter extends ArrayAdapter<UserTypeBean> {

    Context context;
    LayoutInflater inflater;
    ArrayList<UserTypeBean> list, tempList, suggestions;

    public UserAutoCompleteAdapter(Context context, ArrayList<UserTypeBean> list) {
        super(context, R.layout.incident_classification_spinner_layout, list);
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        tempList = new ArrayList<UserTypeBean>(list); // this makes the difference.
        suggestions = new ArrayList<UserTypeBean>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.incident_classification_spinner_layout, parent, false);

            holder.name = (TextView) convertView.findViewById(R.id.incident_classification_spinner_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(list.get(position).getValue());

        return convertView;
    }

    class ViewHolder {
        TextView name;
    }
}
