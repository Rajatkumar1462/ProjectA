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
        Questions_list.add("How to book a professional on the Assumere App?"); Questions_list.add("Are your service provider qualified ?"); Questions_list.add("Customer also require verification for booking?");
        Questions_list.add("Can I place a service request without registering on your app?"); Questions_list.add("How are the professional rated?"); Questions_list.add("What if I am not happy with the service or the service provider?");
        Questions_list.add("Why we have to validate the service provider at our place?"); Questions_list.add("How to book a service?"); Questions_list.add("How do I reschedule a service booked on Assumere?");
        Questions_list.add("How do I know the request has been confirmed?");


        Answers_list.add("Booking a professional on the Assumere App is Fast and Simple:" +"\n" +
                                "1. Once you open the app on your smartphone, choose the service you" +"\n" +
                                "would like to book" +"\n" +
                                "2. Enter the details along with schedule that works best for you" +"\n" +
                                "3. The service provider will then arrive at your place on schedule date. Our" +"\n" +
                                "team might also call you before the visit"); Answers_list.add("Absolutely! We only bring a professional who have been recommended and" +"\n" +
                                "certified. We check following documents and police verification. After that" +"\n" +
                                "they will allow to work with Assumere"); Answers_list.add("The customer need to submit the documents which are asking and mobile" +"\n" +
                                "number verification at the time of booking.");
        Answers_list.add("While we highly recommend you register with us first, and then request a" +"\n" +
                                "service."); Answers_list.add("After each service is completed, our customer are asked to rate the service" +"\n" +
                                "on different parameters and judge the overall satisfaction of service."); Answers_list.add("If you have any complaint, suggestion or feedback with regard to the" +"\n" +
                                "service quality, or our service provider, pricing etc please mail us to" +"\n" +
                                "cs@assumere.in. It is advised to do this within 24hrs of your service.");
        Answers_list.add("By validating the service provider through QR code, we able to know the" +"\n" +
                                "service provider will reach to you or not."); Answers_list.add("1. Search for the service you need" +"\n" +
                                "2. Open the service and follow the instruction as you go forward" +"\n" +
                                "3. Once you have booked the service, wait for the professional to be" +"\n" +
                                "assigned" +"\n" +
                                "4. The assigned professional will reach the address you have provided and" +"\n" +
                                "provide the service"); Answers_list.add("Currently We are not Providing Reschedule Feature. We are looking forward for it");
        Answers_list.add("Once you place a booking, you will receive a confirmation via SMS or email" +"\n" +
                                "informing you that the booking has been accepted.");

        recyclerQues.setAdapter(new FaqAdapter(Questions_list,Answers_list));

        return view;
    }
}
