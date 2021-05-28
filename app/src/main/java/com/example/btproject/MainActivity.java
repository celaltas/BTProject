package com.example.btproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button SignInButton;
    Button SignUpButton;
    EditText username;
    EditText password;
    int attempt;
    ArrayList<Person> persons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignInButton = findViewById(R.id.SignInButton);
        SignUpButton = findViewById(R.id.SignUpButton);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        attempt = 0;
        persons = fetchPersonsList();
        loadUser();


        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPerson()) {
                    SharedPreferences sharedPreferences = getSharedPreferences("settingsinfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String user = username.getText().toString();
                    editor.putString("username", user);
                    editor.commit();
                    System.out.println("user yazıldı: " +user);
                    Toast.makeText(MainActivity.this, "Giriş başarılı", Toast.LENGTH_SHORT).show();
                    cleanTextBoxes();
                    goToMenu();

                } else {
                    attempt += 1;
                    Toast.makeText(MainActivity.this, "Hatalı kullanıcı adı/parola", Toast.LENGTH_SHORT).show();
                    cleanTextBoxes();
                    if (attempt >= 3) {
                        Toast.makeText(MainActivity.this, "Üç hatalı giriş yapıldı, 15 dakika sonra tekrar deneyiniz", Toast.LENGTH_SHORT).show();
                        SignInButton.setEnabled(false);
                    }

                }

            }
        });

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpScreen.class));
            }
        });


    }

    private void cleanTextBoxes() {
        username.setText("");
        password.setText("");
    }

    private boolean checkPerson() {
        for (Person person : persons) {
            if (username.getText().toString().equals(person.getUserName()) && password.getText().toString().equals(person.getPassword())) {

                return true;
            }
        }
        return false;
    }

    private ArrayList<Person> fetchPersonsList() {
        if (getIntent().getExtras() != null) {
            persons = (ArrayList<Person>) getIntent().getSerializableExtra("personList");
        } else {
            persons = Person.getPersonsList();
        }
        return persons;
    }

    public void loadUser() {
        if (getIntent().getExtras() != null) {
            String sendedUsername = getIntent().getStringExtra("username");
            String sendedPassword = getIntent().getStringExtra("password");
            username.setText(sendedUsername);
            password.setText(sendedPassword);

        }
    }

    private void goToMenu() {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }


}