package varun.com.studentmanagementsystemsample.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import varun.com.studentmanagementsystemsample.GalleryDetailActivity;
import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.AlbumBean;
import varun.com.studentmanagementsystemsample.bean.ImagesBean;

/**
 * Created by Administrator on 7/14/2016.
 */
public class ImageAdapter extends
        RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    ArrayList<ImagesBean> list;
    Context context;
    LayoutInflater inflater;

    public ImageAdapter(Context context, ArrayList<ImagesBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.gallery_list_item, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, final int position) {

        holder.caption.setText(list.get(position).getImageTitle());
        Picasso.with(context).load("http://103.7.130.46:8092" + list.get(position).getImagePath()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, GalleryDetailActivity.class);
//                intent.putExtra("IMAGE", list.get(position).getImage());
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        TextView caption;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.gallery_image);
            caption = (TextView) itemView.findViewById(R.id.caption);
        }
    }
}
