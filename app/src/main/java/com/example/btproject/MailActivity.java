package com.example.btproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;

public class MailActivity extends AppCompatActivity {

    private Button addAttachment,sendMail;
    private EditText mailTo,subjectText,messageText;
    private static final int PICK_FROM_INTERNAL_STORAGE=101;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);


        addAttachment = findViewById(R.id.addAttachment);
        sendMail = findViewById(R.id.sendMail);
        mailTo = findViewById(R.id.mailTo);
        subjectText = findViewById(R.id.subjectText);
        messageText = findViewById(R.id.messageText);

        addAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent();
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Dosya Seç"), PICK_FROM_INTERNAL_STORAGE);

            }
        });
        sendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailTo.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, subjectText.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, messageText.getText().toString());

                if(uri!=null){
                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                }
                if (intent.resolveActivity(getPackageManager())!=null){
                    startActivity(Intent.createChooser(intent, "Send E-mail"));
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_FROM_INTERNAL_STORAGE && resultCode==RESULT_OK){
            uri = data.getData();
            Toast.makeText(MailActivity.this, "Ek oluşturuldu", Toast.LENGTH_SHORT).show();
        }

    }
}