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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private SeekBar seekBarQuestionQuantity,seekBarChoiceQuantity;
    private EditText questionScore,examTime;
    private Button btnSettings;
    private ImageView leftIcon;
    private TextView toolbarText,textQuestionQuantity,textChoiceQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        toolbarText = findViewById(R.id.toolbarText);
        toolbarText.setText("Settings");
        leftIcon = findViewById(R.id.leftIcon);
        seekBarQuestionQuantity = findViewById(R.id.seekBarQuestionQuantity);
        seekBarChoiceQuantity = findViewById(R.id.seekBarChoiceQuantity);
        questionScore = findViewById(R.id.questionScore);
        examTime = findViewById(R.id.examTime);
        btnSettings = findViewById(R.id.btnSettings);
        textQuestionQuantity = findViewById(R.id.textQuestionQuantity);
        textChoiceQuantity = findViewById(R.id.textChoiceQuantity);

        if(getIntent().hasExtra("questionQuantity")){
            String size = getIntent().getStringExtra("questionQuantity");
            seekBarQuestionQuantity.setMax(Integer.parseInt(size));
        }


        leftIcon.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        seekBarChoiceQuantity.setOnSeekBarChangeListener(this);
        seekBarQuestionQuantity.setOnSeekBarChangeListener(this);

       readFromSharedPref();

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.leftIcon:
                intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSettings:
                saveInfo();
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (seekBar==seekBarQuestionQuantity){
                textQuestionQuantity.setText(String.valueOf(progress));
            }
            if (seekBar==seekBarChoiceQuantity){
                textChoiceQuantity.setText(String.valueOf(progress));
            }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void saveInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("settingsinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String questionNumber = textQuestionQuantity.getText().toString();
        String choiceNumber = textChoiceQuantity.getText().toString();
        String examTimeTotal = examTime.getText().toString();
        String questScore = questionScore.getText().toString();

        editor.putString("textQuestionQuantity", questionNumber);
        editor.putString("textChoiceQuantity", choiceNumber);
        editor.putString("examTime", examTimeTotal);
        editor.putString("questionScore", questScore);
        editor.commit();


    }
    public void readFromSharedPref(){
        SharedPreferences sharedPreferences = getSharedPreferences("settingsinfo", Context.MODE_PRIVATE);
        String questionNumber = sharedPreferences.getString("textQuestionQuantity","10");
        String choiceNumber =sharedPreferences.getString("textChoiceQuantity","4");
        String examTimeTotal =sharedPreferences.getString("examTime","30");
        String questScore =sharedPreferences.getString("questionScore","10");


        textQuestionQuantity.setText(questionNumber);
        textChoiceQuantity.setText(choiceNumber);
        examTime.setText(examTimeTotal);
        questionScore.setText(questScore);


    }
}