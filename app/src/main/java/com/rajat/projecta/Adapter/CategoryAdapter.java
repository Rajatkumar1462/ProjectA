package com.rajat.projecta.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajat.projecta.Fragment.HomeFragment;
import com.rajat.projecta.R;

import java.util.ArrayList;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    //Declaration
    ArrayList<String> categories ;
    CategoryclickInterface categoryclickInterface;

    //Constructor
    public CategoryAdapter(ArrayList<String> categories,CategoryclickInterface categoryclickInterface){
        this.categories=categories;
        this.categoryclickInterface=categoryclickInterface;
    }
    public CategoryAdapter(ArrayList<String> categories, HomeFragment homeFragment){
        this.categories=categories;
    }

    //ViewHolder-to Display view
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_list_layout,parent,false);
        return new CategoryViewHolder(view);
    }

    //For Binding Values
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.txtview.setText(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    //RecyclerView ClickListner
    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView txtview;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtview = itemView.findViewById(R.id.category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryclickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
    public interface CategoryclickInterface{
        void onItemClick(int position);
    }
}
