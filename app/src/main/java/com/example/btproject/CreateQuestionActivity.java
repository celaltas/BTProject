package com.example.btproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateQuestionActivity extends AppCompatActivity {
    private EditText getQuestionContent,getChoiceA,getChoiceB,getChoiceC,getChoiceD,getChoiceTrue;
    private Button createSingleQuestionButton;
    private TextView toolbarText;
    private ImageView leftIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        toolbarText = findViewById(R.id.toolbarText);
        toolbarText.setText("Soru Oluştur");

        leftIcon = findViewById(R.id.leftIcon);
        createSingleQuestionButton = findViewById(R.id.createSingleQuestionButton);
        getQuestionContent = findViewById(R.id.getQuestionContent);
        getChoiceA = findViewById(R.id.getChoiceA);
        getChoiceB = findViewById(R.id.getChoiceB);
        getChoiceC = findViewById(R.id.getChoiceC);
        getChoiceD = findViewById(R.id.getChoiceD);
        getChoiceTrue = findViewById(R.id.getChoiceTrue);




        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateQuestionActivity.this, QuizMainActivity.class);
                startActivity(intent);
            }
        });
        createSingleQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save() {
        FileOutputStream fos = null;
        Question question;
        String FILE_NAME = getIntent().getStringExtra("FILE_NAME");
        String content = getQuestionContent.getText().toString();
        String choiceA = getChoiceA.getText().toString();
        String choiceB = getChoiceB.getText().toString();
        String choiceC = getChoiceC.getText().toString();
        String choiceD = getChoiceD.getText().toString();
        String choiceTrue = getChoiceTrue.getText().toString();
        question = new Question(content,choiceA,choiceB,choiceC,choiceD,choiceTrue);

        try {

            fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
            AppendObjectOutputStream oos = new AppendObjectOutputStream(fos);
            oos.writeObject(question);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }



}