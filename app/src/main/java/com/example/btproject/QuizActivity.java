package com.example.btproject;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {
    ArrayList<Question> questions;
    ArrayList<Question> randomQuestionList;
    private RecyclerView questionsRecView;
    private ProgressBar progressBar;
    private TextView questionNumber;
    private int progressStatus = 1;
    private Button nextBtn, fnshBtn;
    private ImageView leftIcon;
    private TextView toolbarText;
    String textQuestionQuantity,textChoiceQuantity;
    QuestionsRecViewAdapter adapter;
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        progressBar = findViewById(R.id.progressBar);
        questionNumber = findViewById(R.id.questionNumber);
        nextBtn = findViewById(R.id.nextBtn);
        questionsRecView = findViewById(R.id.questionsRecView);
        toolbarText = findViewById(R.id.toolbarText);
        toolbarText.setText("Quiz");
        leftIcon = findViewById(R.id.leftIcon);
        fnshBtn = findViewById(R.id.fnshBtn);




        readFromSharedPref();
        initializeRecyclerView();
        initializeProgressBar();

        fnshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSharePref();
                saveExam();
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                startActivity(intent);

            }
        });




        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProgressBar();
                if (progressStatus >= progressBar.getMax()) {
                    nextBtn.setEnabled(false);
                    fnshBtn.setVisibility(View.VISIBLE);
                }
                questionsRecView.scrollToPosition(progressStatus-1);
                //System.out.println(rgChoicesList.getCheckedRadioButtonId());

            }
        });
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QuizActivity.this, QuizMainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void saveExam() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                EXTERNAL_STORAGE_PERMISSION_CODE);
        String FILE_NAME = "exam.txt";
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, FILE_NAME);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            for(Question question: randomQuestionList){
                fos.write((question.toString() + "\n").getBytes());

            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void addSharePref() {
        SharedPreferences sharedPreferences = getSharedPreferences("settingsinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int score = adapter.trueResponse;
        editor.putInt("score", score);
        editor.commit();
    }

    public void startProgressBar() {
        progressStatus++;
        progressBar.setProgress(progressStatus);
        questionNumber.setText(String.valueOf(progressStatus) + "/" + progressBar.getMax());

    }

    public void initializeProgressBar() {
        int listSize = randomQuestionList.size();
        progressBar.setMax(listSize);
        progressBar.setProgress(progressStatus);
        questionNumber.setText(String.valueOf(progressStatus) + "/" + progressBar.getMax());

    }

    public void initializeRecyclerView() {
        questions = readFromFile();
        //Collections.shuffle(questions);
        randomQuestionList = new ArrayList(questions.subList(0, Integer.parseInt(textQuestionQuantity)));
        adapter = new QuestionsRecViewAdapter(this,textChoiceQuantity);
        adapter.setQuestionsList(randomQuestionList);
        questionsRecView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };

        questionsRecView.setLayoutManager(linearLayoutManager);


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
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return questions;
    }

    public void readFromSharedPref(){
        SharedPreferences sharedPreferences = getSharedPreferences("settingsinfo", Context.MODE_PRIVATE);
        textQuestionQuantity = sharedPreferences.getString("textQuestionQuantity","10");
        textChoiceQuantity = sharedPreferences.getString("textChoiceQuantity","4");
    }




}