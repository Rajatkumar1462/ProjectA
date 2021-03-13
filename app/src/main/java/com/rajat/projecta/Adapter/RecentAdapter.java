package com.rajat.projecta.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.rajat.projecta.R;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentViewHolder>{
    private int[] images;
    private String[] data;

    //Constructor
    public RecentAdapter(int[] images,String[] data){
        this.images=images;
        this.data=data;
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
        holder.imgicon.setImageResource(images[position]);
        holder.orderinfo.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    //Recycler View For Recent Orders
    public class RecentViewHolder extends RecyclerView.ViewHolder {
        ImageView imgicon;
        TextView orderinfo;
        public RecentViewHolder(@NonNull View itemView) {
            super(itemView);
            imgicon = itemView.findViewById(R.id.RecentImage);
            orderinfo =itemView.findViewById(R.id.RecentOrderInfo);
        }
    }
}
