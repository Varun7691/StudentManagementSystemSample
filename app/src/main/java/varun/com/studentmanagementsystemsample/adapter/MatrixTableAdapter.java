package varun.com.studentmanagementsystemsample.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import varun.com.studentmanagementsystemsample.R;

/**
 * Created by VarunBarve on 20/04/2016.
 */
public class MatrixTableAdapter<T> extends BaseTableAdapter {

    private final static int WIDTH_DIP = 100;
    private final static int HEIGHT_DIP = 40;
    private final int width;
    private final int height;
    private Context context;
    private LayoutInflater inflater;
    private T[][] table;

    public MatrixTableAdapter(Context context) {
        this(context, null);
    }

    public MatrixTableAdapter(Context context, T[][] table) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resources r = context.getResources();

        width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, WIDTH_DIP, r.getDisplayMetrics()));
        height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HEIGHT_DIP, r.getDisplayMetrics()));

        setInformation(table);
    }

    public void setInformation(T[][] table) {
        this.table = table;
    }

    @Override
    public int getRowCount() {
        return table.length - 1;
    }

    @Override
    public int getColumnCount() {
        return table[0].length - 1;
    }

    @Override
    public View getView(int row, int column, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            if (getItemViewType(row, column) == 0) {
                convertView = inflater.inflate(R.layout.item_table1_header, parent, false);
            } else if (getItemViewType(row, column) == 1) {
                convertView = inflater.inflate(R.layout.item_table1, parent, false);
            }
            holder.cellText = (TextView) convertView.findViewById(R.id.text1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.cellText.setText(table[row + 1][column + 1].toString());
        return convertView;
    }

    @Override
    public int getHeight(int row) {
        return height;
    }

    @Override
    public int getWidth(int column) {
        return width;
    }

    @Override
    public int getItemViewType(int row, int column) {
        if (row < 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    class ViewHolder {
        TextView cellText;
    }
}
