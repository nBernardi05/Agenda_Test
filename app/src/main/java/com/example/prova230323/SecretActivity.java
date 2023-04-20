package com.example.prova230323;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecretActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);
        Intent i = getIntent();
        String username = i.getStringExtra("username");
        ((TextView)findViewById(R.id.msg)).setText("Hi " + username);
    }
    public void back(View view){
        finish();
    }
}