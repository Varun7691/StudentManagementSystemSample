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
import varun.com.studentmanagementsystemsample.ImagesActivity;
import varun.com.studentmanagementsystemsample.R;
import varun.com.studentmanagementsystemsample.bean.AlbumBean;
import varun.com.studentmanagementsystemsample.bean.GalleryBean;

/**
 * Created by Varun on 4/3/2016.
 */
public class GalleryAdapter extends
        RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    ArrayList<AlbumBean> list;
    Context context;
    LayoutInflater inflater;

    public GalleryAdapter(Context context, ArrayList<AlbumBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.gallery_list_item, parent, false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder holder, final int position) {

        holder.caption.setText(list.get(position).getAlbumTitle());
        Picasso.with(context).load("http://103.7.130.46:8082" + list.get(position).getImageList().get(0).getImagePath()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImagesActivity.class);
                intent.putExtra("IMAGE LIST", list.get(position).getImageList());
                context.startActivity(intent);
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
