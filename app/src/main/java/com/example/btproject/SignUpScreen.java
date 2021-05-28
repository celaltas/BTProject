package com.example.btproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class SignUpScreen extends AppCompatActivity {
    ArrayList<Person> persons;
    private EditText eMail, password, passwordConfirm, birthDate, userName, userSurname, phone;
    private Button SignUpButton;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        eMail = findViewById(R.id.eMail);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.passwordConfirm);
        birthDate = findViewById(R.id.birthDate);
        userName = findViewById(R.id.userName);
        userSurname = findViewById(R.id.userSurname);
        phone = findViewById(R.id.phone);
        SignUpButton = findViewById(R.id.SignUpButton);
        persons = Person.getPersonsList();

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createUser()) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    goToLogin();
                }


            }
        });
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        SignUpScreen.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener, year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                birthDate.setText(date);
            }
        };


    }


    private boolean validateEmail() {
        String emailInput = eMail.getText().toString().trim();
        if (emailInput.isEmpty()) {
            eMail.setError("Bu alan boş bırakılamaz!");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            eMail.setError("Geçerli bir e-mail adresi giriniz!");
            return false;
        } else {
            eMail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        String passwordConfirmInput = passwordConfirm.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (passwordInput.length() < 6) {
            password.setError("Şifre uzunluğu 6 karakterden az olamaz!");
            return false;
        } else if (!(passwordInput.equals(passwordConfirmInput))) {
            passwordConfirm.setError("Parola ile eşleşmiyor!");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private boolean validateDate() {
        try {
            Date dateInput = new SimpleDateFormat("dd/MM/yyyy").parse(birthDate.getText().toString());
            Date today = Calendar.getInstance().getTime();
            if (today.compareTo(dateInput) < 0) {
                birthDate.setError("Doğum tarihi bugünden büyük olamaz");
                return false;

            } else {
                return true;

            }
        } catch (ParseException e) {
            e.printStackTrace();
            birthDate.setError("Bir hata oluştu");
            return false;
        }


    }

    private boolean validateUserName() {
        String emailInput = eMail.getText().toString().trim();
        String username = emailInput.split("@")[0];
        for (Person person : persons) {
            if (person.getUserName().equals(username)) {
                Toast.makeText(SignUpScreen.this, "Bu isimde bir kullanıcı mevcut", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private boolean emptyFieldControl() {
        if (userName.getText().toString().trim().equals("")) {
            userName.setError("İsim gerekli");
            userName.setHint("Lütfen bir isim giriniz");
            return false;
        } else if (userSurname.getText().toString().trim().equals("")) {
            userSurname.setError("Soyisim gerekli");
            userSurname.setHint("Lütfen bir soyisim giriniz");
            return false;
        } else if (phone.getText().toString().trim().equals("")) {
            phone.setError("Telefon numarası  gerekli");
            phone.setHint("Lütfen bir telefon numarası giriniz");
            return false;
        } else {
            return true;
        }
    }

    private void cleanBoxes() {
        userName.setText("");
        userSurname.setText("");
        eMail.setText("");
        phone.setText("");
        birthDate.setText("");
        password.setText("");
        passwordConfirm.setText("");
    }

    private boolean createUser() {
        if (validateEmail() && validatePassword() && validateDate() && validateUserName() && emptyFieldControl()) {
            Toast toastMessage;
            String username = userName.getText().toString();
            String pass = password.getText().toString();
            persons.add(new Person(username, pass));
            toastMessage = Toast.makeText(SignUpScreen.this, "Kayıt başarılı giriş sayfasına yönlendiriliyorsunuz", Toast.LENGTH_SHORT);
            toastMessage.show();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            toastMessage.cancel();
            return true;
        } else {
            Toast.makeText(SignUpScreen.this, "Lütfen eksik veya hatalı alanları doldurunuz", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void goToLogin() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        String username = userName.getText().toString();
        String pass = password.getText().toString();
        intent.putExtra("personList", persons);
        intent.putExtra("username", username);
        intent.putExtra("password", pass);
        cleanBoxes();
        startActivity(intent);
    }


}