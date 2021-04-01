package com.rajat.projecta.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajat.projecta.Fragment.HomeFragment;
import com.rajat.projecta.R;

import java.util.ArrayList;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqViewHolder>{

    private ArrayList<String> Questions ;
    private ArrayList<String> Answers ;
    private boolean expandable=false;

    public FaqAdapter (ArrayList<String> Questions,ArrayList<String> Answers ){
        this.Questions=Questions;
        this.Answers = Answers;
    }

    @NonNull
    @Override
    public FaqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_card_view,parent,false);
        return new FaqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqAdapter.FaqViewHolder holder, int position) {
        holder.question.setText(Questions.get(position));
        holder.answer.setText(Answers.get(position));

        boolean expandables = expandable;
        holder.linearLayoutInner.setVisibility(expandables ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return Questions.size();
    }

    public class FaqViewHolder extends RecyclerView.ViewHolder {
        TextView question,answer;
        LinearLayout linearLayoutOuter,linearLayoutInner;
        public FaqViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.faq_question);
            answer = itemView.findViewById(R.id.faq_answer);

            linearLayoutOuter = itemView.findViewById(R.id.faq_linear_layout);
            linearLayoutInner = itemView.findViewById(R.id.faq_expand_layout);

            linearLayoutOuter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandable = !expandable;
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
