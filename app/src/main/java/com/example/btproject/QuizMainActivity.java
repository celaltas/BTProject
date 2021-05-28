package com.example.btproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class QuizMainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String FILE_NAME = "QuestionList.bin";
    private ImageView leftIcon;
    private TextView toolbarText;
    private Button createQuestionBtn, listQuestionsBtn, startQuizBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);
        toolbarText = findViewById(R.id.toolbarText);
        toolbarText.setText("Quiz App");

        leftIcon = findViewById(R.id.leftIcon);
        createQuestionBtn = findViewById(R.id.createQuestionBtn);
        listQuestionsBtn = findViewById(R.id.listQuestionsBtn);
        startQuizBtn = findViewById(R.id.startQuizBtn);


        leftIcon.setOnClickListener(this);
        createQuestionBtn.setOnClickListener(this);
        listQuestionsBtn.setOnClickListener(this);
        startQuizBtn.setOnClickListener(this);




    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.leftIcon) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.createQuestionBtn) {
            Intent intent = new Intent(this, CreateQuestionActivity.class);
            intent.putExtra("FILE_NAME", FILE_NAME);
            startActivity(intent);
        }
        if (v.getId() == R.id.listQuestionsBtn) {
            Intent intent = new Intent(this, QuestionsListviewActivity.class);
            intent.putExtra("FILE_NAME", FILE_NAME);
            startActivity(intent);
        }
        if (v.getId() == R.id.startQuizBtn) {
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("FILE_NAME", FILE_NAME);
            startActivity(intent);
        }
    }


}