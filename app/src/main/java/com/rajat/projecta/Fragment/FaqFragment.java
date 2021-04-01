package com.rajat.projecta.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajat.projecta.Adapter.CategoryAdapter;
import com.rajat.projecta.Adapter.FaqAdapter;
import com.rajat.projecta.R;

import java.util.ArrayList;

public class FaqFragment extends Fragment {

    ArrayList<String> Questions_list = new ArrayList<>();
    ArrayList<String> Answers_list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq,container,false);

        //RecyclerView For Displaying Question list Horizontal

        RecyclerView recyclerQues = view.findViewById(R.id.faq_recyclerview);
        recyclerQues.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

        //Values are declared here
        Questions_list.add("Question1"); Questions_list.add("Question2"); Questions_list.add("Question3");
        Questions_list.add("Question4"); Questions_list.add("Question5"); Questions_list.add("Question6");
        Questions_list.add("Question7"); Questions_list.add("Question8"); Questions_list.add("Question9");
        Questions_list.add("Question10");


        Answers_list.add("answer1"); Answers_list.add("answer2"); Answers_list.add("answer3");
        Answers_list.add("answer4"); Answers_list.add("answer5"); Answers_list.add("answer6");
        Answers_list.add("answer7"); Answers_list.add("answer8"); Answers_list.add("answer9");
        Answers_list.add("answer10");

        recyclerQues.setAdapter(new FaqAdapter(Questions_list,Answers_list));

        return view;
    }
}
