package com.rajat.projecta.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajat.projecta.Helper.ServiceProviderHelper;
import com.rajat.projecta.R;

import java.util.ArrayList;


public class ServiceProviderAdapter  extends RecyclerView.Adapter<ServiceProviderAdapter.SeviceProviderViewHolder>{

    ArrayList <ServiceProviderHelper> list;
    ServiceproviderClickInterface serviceproviderClickInterface;

    // clickable constructor
    public ServiceProviderAdapter(ArrayList <ServiceProviderHelper> list,ServiceproviderClickInterface serviceproviderClickInterface){
        this.list=list;
        this.serviceproviderClickInterface=serviceproviderClickInterface;
    }

    //original constructor
    public ServiceProviderAdapter(ArrayList <ServiceProviderHelper> list){
        this.list=list;
    }


    @NonNull
    @Override
    public SeviceProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.service_provider_list_layout,parent,false);
        return new SeviceProviderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeviceProviderViewHolder holder, int position) {
        ServiceProviderHelper helper = list.get(position);
        holder.img.setImageResource(helper.getImage());
        holder.title.setText(helper.getTitle());
        holder.rating.setText(helper.getRating());
        holder.info.setText(helper.getInfo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //RecyclerView ClickListner
    public class SeviceProviderViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title;
        TextView rating;
        TextView info;
        public SeviceProviderViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.service_provider_image);
            title = (TextView)itemView.findViewById(R.id.service_provider_title);
            rating = (TextView)itemView.findViewById(R.id.service_provider_rating);
            info = (TextView)itemView.findViewById(R.id.service_provider_info);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    serviceproviderClickInterface.onSpItemClick(getAdapterPosition());
                }
            });
        }
    }
    public interface ServiceproviderClickInterface{
        void onSpItemClick(int position);
    }
}
