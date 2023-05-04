package com.example.prova230323;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CalendarMainActivity extends AppCompatActivity {
    private EditText data = findViewById(R.id.dataOggi);
    private Calendar endTime = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener dateSL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent i = getIntent();
        String username = i.getStringExtra("username");
        ((TextView)findViewById(R.id.msg)).setText("Ecco i tuoi appuntamenti, " + username);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CalendarMainActivity.this, dateSL, endTime.get(Calendar.YEAR), endTime.get(Calendar.MONTH), endTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
}