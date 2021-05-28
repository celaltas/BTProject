package com.example.btproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView textUsername, textPoint;
    private Button goMenuBtn;
    private int score;
    private String username;
    private TextView toolbarText;
    private ImageView leftIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        goMenuBtn = findViewById(R.id.goMenuBtn);
        toolbarText = findViewById(R.id.toolbarText);
        toolbarText.setText("Sonuç");
        leftIcon = findViewById(R.id.leftIcon);




        goMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, QuizMainActivity.class);
                startActivity(intent);
            }
        });

        getUserScore();
        setUserScore();
    }

    private void setUserScore() {
        textUsername=findViewById(R.id.textUsername);
        textPoint=findViewById(R.id.textPoint);
        textPoint.setText(Integer.toString(score));
        textUsername.setText(username);
    }

    private void getUserScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("settingsinfo", Context.MODE_PRIVATE);
        int trueChoice = sharedPreferences.getInt("score",0);
        username = sharedPreferences.getString("username","");
        int questionPoint = Integer.parseInt(sharedPreferences.getString("questionScore","0"));
        score = trueChoice*questionPoint;
    }
}