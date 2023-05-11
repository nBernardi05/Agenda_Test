package com.example.prova230323;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarMainActivity extends AppCompatActivity {
    private EditText data;
    public Calendar endTime = Calendar.getInstance();
    private TaskAdapter adapter;
    RecyclerView rv;
    DatePickerDialog.OnDateSetListener dateSL;
    private String username;
    SharedPreferences _events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        rv = findViewById(R.id.rview);


        data = (EditText)findViewById(R.id.dataOggi);

        Intent i = getIntent();
        username = i.getStringExtra("username");

        _events = this.getSharedPreferences(username, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = _events.edit();
        /*((TextView)findViewById(R.id.rview)).setText("Ecco i tuoi appuntamenti, " + username);*/

        dateSL  = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                endTime.set(year, month, day);
                updateLabel();
                updateList(username);
            }
        };

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(CalendarMainActivity.this, dateSL, endTime.get(Calendar.YEAR), endTime.get(Calendar.MONTH), endTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    public void updateList(String user) {
        adapter = new TaskAdapter(CalendarMainActivity.this, user);
        rv.setAdapter(adapter);
    }

    private void updateLabel() {
        String myFormat = "dd MMMM yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.ITALIAN);
        data.setText(dateFormat.format(endTime.getTime()));
    }

    public void newEvent(View view) {
        Intent i=new Intent();
        i.putExtra("username", username);
        i.setClass(this, SecretActivity.class);
        startActivity(i);
    }
}