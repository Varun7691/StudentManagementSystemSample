package varun.com.studentmanagementsystemsample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.IncidentClassificationBean;

/**
 * Created by Varun Barve on 6/30/2016.
 */
public class IncidentClassificationSpinnerAdapter extends ArrayAdapter<IncidentClassificationBean> {

    Context context;
    LayoutInflater inflater;
    ArrayList<IncidentClassificationBean> list;

    public IncidentClassificationSpinnerAdapter(Context context, ArrayList<IncidentClassificationBean> list) {
        super(context, R.layout.incident_classification_spinner_layout, list);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    class ViewHolder {
        TextView name;
    }

    // IMPORTANT FOR SPINNERS
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
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

        holder.name.setText(list.get(position).getIncidentClassificationName());

        return convertView;
    }
}
