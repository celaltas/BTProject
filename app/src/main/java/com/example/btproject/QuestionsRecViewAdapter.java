package com.example.btproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionsRecViewAdapter extends RecyclerView.Adapter<QuestionsRecViewAdapter.ViewHolder> {
    private ArrayList<Question> questionsList = new ArrayList<>();
    private Context context;
    int textChoiceQuantity;
    int choiceTrueId=0;
    int trueResponse=0;
    int currentPosition;
    int previosPosition = -1;



    public QuestionsRecViewAdapter(Context context, String textChoiceQuantity) {
        this.context=context;
        this.textChoiceQuantity=Integer.parseInt(textChoiceQuantity);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.questions_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull  QuestionsRecViewAdapter.ViewHolder holder, int position) {
        holder.currentQuestion.setText(questionsList.get(position).getQuestionContent());
        holder.rbchoiceA.setText(questionsList.get(position).getChoiceA());
        holder.rbchoiceB.setText(questionsList.get(position).getChoiceB());
        holder.rbchoiceC.setText(questionsList.get(position).getChoiceC());
        holder.rbchoiceD.setText(questionsList.get(position).getChoiceD());


        RadioButton[] buttonArray = {holder.rbchoiceA,holder.rbchoiceB,holder.rbchoiceC,holder.rbchoiceD};
        int i=0;
        int j=4-textChoiceQuantity;

        String choiceTrue = questionsList.get(position).getChoiceTrue();
        while (i<buttonArray.length && j>0){
            if(!(buttonArray[i].getText().equals(choiceTrue))){
                buttonArray[i].setVisibility(View.GONE);
                j--;
            }else {
                choiceTrueId = buttonArray[i].getId();
            }
            i++;
        }

        holder.rgChoicesList.clearCheck();
        currentPosition=position;

        holder.rgChoicesList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if(checkedId==choiceTrueId && previosPosition!=currentPosition){
                trueResponse++;
            }
            previosPosition=currentPosition;
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





    public class ViewHolder  extends RecyclerView.ViewHolder{
        private TextView currentQuestion;
        private RadioButton rbchoiceA,rbchoiceB,rbchoiceC,rbchoiceD;
        private RadioGroup rgChoicesList;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            currentQuestion = itemView.findViewById(R.id.currentQuestion);
            rbchoiceA = itemView.findViewById(R.id.rbchoiceA);
            rbchoiceB = itemView.findViewById(R.id.rbchoiceB);
            rbchoiceC = itemView.findViewById(R.id.rbchoiceC);
            rbchoiceD = itemView.findViewById(R.id.rbchoiceD);
            rgChoicesList = itemView.findViewById(R.id.rgChoicesList);










        }


    }



}
