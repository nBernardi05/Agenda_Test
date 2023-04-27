package com.example.prova230323;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Intent i = getIntent();
        String username = i.getStringExtra("username");
        ((TextView)findViewById(R.id.msg)).setText("Ecco i tuoi appuntamenti, " + username);
    }
}