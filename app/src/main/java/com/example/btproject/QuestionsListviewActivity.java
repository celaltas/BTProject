package com.example.btproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class QuestionsListviewActivity extends AppCompatActivity {
    ArrayList<Question> questions;
    private RecyclerView questionsListRecView;
    private ImageView leftIcon;
    private TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_listview);
        toolbarText = findViewById(R.id.toolbarText);
        toolbarText.setText("Questions List");
        leftIcon = findViewById(R.id.leftIcon);
        questionsListRecView = findViewById(R.id.questionsListRecView);
        initializeRecyclerView();
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionsListviewActivity.this, QuizMainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void initializeRecyclerView() {
        questions = readFromFile();
        QuestionListRecViewAdapter adapter = new QuestionListRecViewAdapter();
        adapter.setQuestionsList(questions);
        questionsListRecView.setAdapter(adapter);
        questionsListRecView.setLayoutManager(new LinearLayoutManager(this));

    }

    public ArrayList<Question> readFromFile() {
        FileInputStream fis = null;
        ArrayList<Question> questions = new ArrayList<>();
        Question question;
        String FILE_NAME = getIntent().getStringExtra("FILE_NAME");
        try {
            fis = openFileInput(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while ((question = (Question) ois.readObject()) != null) {
                questions.add(question);
            }
            ois.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return questions;
    }
}

