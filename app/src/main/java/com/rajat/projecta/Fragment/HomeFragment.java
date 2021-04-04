package com.rajat.projecta.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rajat.projecta.Adapter.CategoryAdapter;
import com.rajat.projecta.Adapter.ServiceProviderAdapter;
import com.rajat.projecta.BookActivity;
import com.rajat.projecta.CategoryListActivity;
import com.rajat.projecta.Helper.ServiceProviderHelper;
import com.rajat.projecta.MainActivity;
import com.rajat.projecta.R;
import com.rajat.projecta.RecentListActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements CategoryAdapter.CategoryclickInterface, ServiceProviderAdapter.ServiceproviderClickInterface {
    //Declaration
    private TextView AtTopName;
    ArrayList<String> cat_list = new ArrayList<>();
    ArrayList<ServiceProviderHelper> sp_list = new ArrayList<>();
    ArrayList<ServiceProviderHelper> sp_list_Driver = new ArrayList<>();
    ArrayList<ServiceProviderHelper> sp_list_Maid = new ArrayList<>();
    ArrayList<ServiceProviderHelper> sp_list_Gardner = new ArrayList<>();
    ServiceProviderAdapter adapter;
    FirebaseUser user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        //settingDisplayName
        AtTopName = view.findViewById(R.id.atTopName);
//        String nameFromMainActivity = MainActivity.getName();
        AtTopName.setText(user.getDisplayName());

        //RecyclerView For Displaying Categories list Horizontal
        RecyclerView category_list = view.findViewById(R.id.category_list);
        category_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        //Values are declared here
        cat_list.add("Driver"); cat_list.add("Maid"); cat_list.add("Gardner");
        cat_list.add("Driver"); cat_list.add("Maid"); cat_list.add("Gardner");
        cat_list.add("Driver"); cat_list.add("Maid"); cat_list.add("Gardner");
        category_list.setAdapter(new CategoryAdapter(cat_list, (CategoryAdapter.CategoryclickInterface) this));

        //RecyclerView For Displaying ServiceProvider list Horizontal
        RecyclerView serviceprovider_list = view.findViewById(R.id.service_provider_list);
        serviceprovider_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        //values are declared  here
        sp_list_Driver.add(new ServiceProviderHelper(R.drawable.driver,"Driver1","5 star","he is a 5star driver"));
        sp_list_Driver.add(new ServiceProviderHelper(R.drawable.driver,"Driver2","5 star","he is a 5star driver"));
        sp_list_Driver.add(new ServiceProviderHelper(R.drawable.driver,"Driver3","4.5 star","he is a 4.5star driver"));
        sp_list.addAll(sp_list_Driver);

        sp_list_Maid.add(new ServiceProviderHelper(R.drawable.maid,"maid1","5 star","he is a 5 star maid"));
        sp_list_Maid.add(new ServiceProviderHelper(R.drawable.maid,"maid2","5 star","he is a 5 star maid"));
        sp_list_Maid.add(new ServiceProviderHelper(R.drawable.maid,"maid3","4.5 star","he is a 4.5 star maid"));

        sp_list_Gardner.add(new ServiceProviderHelper(R.drawable.gardner,"gard1","5 star","he is a 5star gardner"));
        sp_list_Gardner.add(new ServiceProviderHelper(R.drawable.gardner,"gard2","5 star","he is a 5star gardner"));
        sp_list_Gardner.add(new ServiceProviderHelper(R.drawable.gardner,"gard3","4.5 star","he is a 4.5star gardner"));

        adapter = new ServiceProviderAdapter(sp_list, (ServiceProviderAdapter.ServiceproviderClickInterface) this);
        serviceprovider_list.setAdapter(adapter);

        //Category Sell All Button ClickListner
        TextView cat_see_all;
        cat_see_all = view.findViewById(R.id.category_see_all_link);
        cat_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryListActivity.class);
                startActivity(intent);
            }
        });

        //Recent Sell All Button ClickListner
        TextView recent_see_all;
        recent_see_all = view.findViewById(R.id.recent_see_all_link);
        recent_see_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RecentListActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    //Changing Category List on click on each Category
    @Override
    public void onItemClick(int position) {
        if(cat_list.get(position)=="Driver"){
            sp_list.clear();
            adapter.notifyItemRemoved(position);
            sp_list.addAll(sp_list_Driver);
            adapter.notifyDataSetChanged();

        }
        if(cat_list.get(position)=="Maid"){
            sp_list.clear();
            adapter.notifyItemRemoved(position);
            sp_list.addAll(sp_list_Maid);
            adapter.notifyDataSetChanged();
        }
        if(cat_list.get(position)=="Gardner"){
            sp_list.clear();
            adapter.notifyItemRemoved(position);
            sp_list.addAll(sp_list_Gardner);
            adapter.notifyDataSetChanged();
        }
    }

    //Service Provider ClickListner & Passing Values
    @Override
    public void onSpItemClick(int position) {

        String title = sp_list.get(position).getTitle();
        String rating = sp_list.get(position).getRating();
        String info = sp_list.get(position).getInfo();
        int image = sp_list.get(position).getImage();
        Intent intent = new Intent(getActivity(), BookActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("rating",rating);
        intent.putExtra("info",info);
        intent.putExtra("profile",image);

        startActivity(intent);
    }

}
