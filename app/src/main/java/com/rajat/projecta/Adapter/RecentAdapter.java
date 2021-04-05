package com.rajat.projecta.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajat.projecta.Artist;
import com.rajat.projecta.R;

import java.util.ArrayList;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentViewHolder>{
    private int[] images ;
    private Context context;
    ArrayList<Artist> artistList = new ArrayList<>();

    //Constructor
    public RecentAdapter(Context context,int[] images,ArrayList<Artist> artistList){
        this.context = context;
        this.images = images;
        this.artistList = artistList;
    }

    //ViewHolder-to Display view
    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recent_item_list,parent,false);
        return new RecentViewHolder(view);
    }

    //For Binding Values
    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, int position) {
        Artist artist = artistList.get(position);
        holder.recent_item_image.setImageResource(images[position]);
        holder.recent_item_order_name.setText(artist.Name);
        holder.recent_item_order_cost.setText(""+artist.Cost);
        holder.recent_item_order_status.setText(artist.status);
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    //Recycler View For Recent Orders
    public class RecentViewHolder extends RecyclerView.ViewHolder {
        ImageView recent_item_image;
        TextView recent_item_order_name,recent_item_order_cost,recent_item_order_status;
        public RecentViewHolder(@NonNull View itemView) {
            super(itemView);
            recent_item_image = itemView.findViewById(R.id.recent_item_image);
            recent_item_order_name = itemView.findViewById(R.id.recent_item_order_name);
            recent_item_order_cost = itemView.findViewById(R.id.recent_item_order_cost);
            recent_item_order_status = itemView.findViewById(R.id.recent_item_order_status);
        }
    }
}
