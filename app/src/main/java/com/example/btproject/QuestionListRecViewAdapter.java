package com.example.btproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionListRecViewAdapter extends RecyclerView.Adapter<QuestionListRecViewAdapter.ViewHolder> {
    private ArrayList<Question> questionsList = new ArrayList<>();


    public QuestionListRecViewAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_list_item_buttons, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull QuestionListRecViewAdapter.ViewHolder holder, int position) {


        holder.currentQuestionList.setText(questionsList.get(position).getQuestionContent());
        holder.rbchoiceAList.setText(questionsList.get(position).getChoiceA());
        holder.rbchoiceBList.setText(questionsList.get(position).getChoiceB());
        holder.rbchoiceCList.setText(questionsList.get(position).getChoiceC());
        holder.rbchoiceDList.setText(questionsList.get(position).getChoiceD());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionsList.size() != 0) {
                    questionsList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, questionsList.size());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    public void setQuestionsList(ArrayList<Question> questionsList) {
        this.questionsList = questionsList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currentQuestionList;
        private RadioButton rbchoiceAList, rbchoiceBList, rbchoiceCList, rbchoiceDList;
        private Button updateBtn, deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            currentQuestionList = itemView.findViewById(R.id.currentQuestionList);
            rbchoiceAList = itemView.findViewById(R.id.rbchoiceAList);
            rbchoiceBList = itemView.findViewById(R.id.rbchoiceBList);
            rbchoiceCList = itemView.findViewById(R.id.rbchoiceCList);
            rbchoiceDList = itemView.findViewById(R.id.rbchoiceDList);
            updateBtn = itemView.findViewById(R.id.updateBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
