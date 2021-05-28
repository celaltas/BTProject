package com.example.btproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private MaterialCardView cardViewQuiz, cardViewMail, cardViewSettings;
    private static final String FILE_NAME = "QuestionList.bin";
    ArrayList<Question> questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        cardViewQuiz = findViewById(R.id.cardViewQuiz);
        cardViewMail = findViewById(R.id.cardViewMail);
        cardViewSettings = findViewById(R.id.cardViewSettings);

        cardViewMail.setOnClickListener(this);
        cardViewQuiz.setOnClickListener(this);
        cardViewSettings.setOnClickListener(this);

        questions = Question.getQuestionArrayList();
        writeToFile(questions);



    }

    public void writeToFile(ArrayList<Question> questions) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            int i = 0;
            while (i < questions.size()) {
                oos.writeObject(questions.get(i));
                i++;
            }

            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.cardViewQuiz:
                intent = new Intent(this, QuizMainActivity.class);
                startActivity(intent);
                break;
            case R.id.cardViewMail:
                intent = new Intent(this, MailActivity.class);
                startActivity(intent);
                break;
            case R.id.cardViewSettings:
                intent = new Intent(this, SettingsActivity.class);
                intent.putExtra("questionQuantity", String.valueOf(questions.size()));
                startActivity(intent);
                break;
        }


    }


}