package com.rajat.projecta.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rajat.projecta.Adapter.CategoryAdapter;
import com.rajat.projecta.Adapter.ServiceProviderAdapter;
import com.rajat.projecta.BookActivity;
import com.rajat.projecta.CategoryListActivity;
import com.rajat.projecta.FragmentActivity;
import com.rajat.projecta.Helper.ServiceProviderBookHelper;
import com.rajat.projecta.Helper.ServiceProviderHelper;
import com.rajat.projecta.R;
import com.rajat.projecta.RecentListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class HomeFragment extends Fragment implements CategoryAdapter.CategoryclickInterface, ServiceProviderAdapter.ServiceproviderClickInterface{
    //Declaration
    private TextView AtTopName;
    private TextView Recent_Order_Sp_Name;
    private TextView Recent_Order_Cost;
    private TextView Recent_Order_Status;

    ArrayList<String> cat_list = new ArrayList<>();
    ArrayList<ServiceProviderHelper> sp_list = new ArrayList<>();
    ArrayList<ServiceProviderHelper> sp_list_Cook = new ArrayList<>();
    ArrayList<ServiceProviderHelper> sp_list_Driver = new ArrayList<>();
    ArrayList<ServiceProviderHelper> sp_list_Maid = new ArrayList<>();
    ArrayList<ServiceProviderHelper> sp_list_Gardner = new ArrayList<>();
    ArrayList<ServiceProviderHelper> sp_list_Baby_Sitter = new ArrayList<>();

    DatabaseReference dbr = FirebaseDatabase.getInstance().getReference();
    DatabaseReference dfh = dbr.child("BabySitter").child("BabySitter1");
    ValueEventListener valueEventListener;
    ServiceProviderAdapter adapter;
    FirebaseUser user;
    RecyclerView serviceprovider_list;
    ServiceProviderBookHelper lastorder = new ServiceProviderBookHelper();
    HashMap<String, HashMap<String, String>> resultList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        //settingDisplayName
        Recent_Order_Sp_Name = view.findViewById(R.id.recent_order_name);
        Recent_Order_Cost  = view.findViewById(R.id.recent_order_cost);
        Recent_Order_Status = view.findViewById(R.id.recent_order_status);
        AtTopName = view.findViewById(R.id.atTopName);
//        String nameFromMainActivity = MainActivity.getName();
        AtTopName.setText(user.getDisplayName());

        //RecyclerView For Displaying Categories list Horizontal
        RecyclerView category_list = view.findViewById(R.id.category_list);
        category_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        //Values are declared here
        cat_list.add("Cook"); cat_list.add("Driver");
        cat_list.add("Maid"); cat_list.add("Gardner");
        cat_list.add("Baby Sitter");
        category_list.setAdapter(new CategoryAdapter(cat_list, (CategoryAdapter.CategoryclickInterface) this));

        //RecyclerView For Displaying ServiceProvider list Horizontal
        serviceprovider_list = view.findViewById(R.id.service_provider_list);
        serviceprovider_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));


        //values are declared  here
        sp_list_Cook.add(new ServiceProviderHelper(R.drawable.cook,"Suresh","5 star","3 year Experience"));
        sp_list_Cook.add(new ServiceProviderHelper(R.drawable.cook,"Mamta Devi","4.7 star","1 year Experience"));
        sp_list_Cook.add(new ServiceProviderHelper(R.drawable.cook,"Gopal","3.7 star","6 Month Experience"));
        sp_list.addAll(sp_list_Cook);


        loadValues("Driver", sp_list_Driver, R.drawable.driver);
        loadValues("Maid", sp_list_Maid, R.drawable.maid);
        loadValues("Gardener", sp_list_Gardner, R.drawable.gardner);
        loadValues("BabySitter", sp_list_Baby_Sitter, R.drawable.baby_sitter);

        adapter = new ServiceProviderAdapter(sp_list, (ServiceProviderAdapter.ServiceproviderClickInterface) this);
        serviceprovider_list.setAdapter(adapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        dbr = FirebaseDatabase.getInstance().getReference();
        final Query lastnode = dbr.child("start").child(user.getUid()).child("All Orders").orderByChild("Time").limitToLast(1);
        lastnode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Long temp = 0L;
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        ServiceProviderBookHelper serviceProviderBookHelper = snapshot.getValue(ServiceProviderBookHelper.class);
                        if(serviceProviderBookHelper.Time>temp){
                            temp = serviceProviderBookHelper.Time;
                            lastorder  = serviceProviderBookHelper;
                        }
                    }
                }
                Recent_Order_Sp_Name.setText(lastorder.Name);
                Recent_Order_Cost.setText("" +lastorder.Cost);
                Recent_Order_Status.setText(lastorder.Status);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Category See All Button ClickListner
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
        if(cat_list.get(position)=="Cook"){
            sp_list.clear();
            adapter.notifyItemRemoved(position);
            sp_list.addAll(sp_list_Cook);
            adapter.notifyDataSetChanged();

        }
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
        if(cat_list.get(position)=="Baby Sitter"){
            sp_list.clear();
            adapter.notifyItemRemoved(position);
            sp_list.addAll(sp_list_Baby_Sitter);
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

    public void loadValues(String field, ArrayList<ServiceProviderHelper> list, int path){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("SP").child(field).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                resultList = (HashMap<String, HashMap<String, String>>) snapshot.getValue();
                for(Map.Entry<String, HashMap<String, String>> set : resultList.entrySet()){
                    HashMap<String, String> x = set.getValue();
                    list.add(new ServiceProviderHelper(path,x.get("Name"),String.valueOf(x.get("Rating"))+" Star",String.valueOf(x.get("Experience"))+" year Experience"));
                    Log.i("", String.valueOf(R.drawable.cook));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
