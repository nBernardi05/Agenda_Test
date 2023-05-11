package com.example.prova230323;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SecretActivity extends AppCompatActivity {
    private EditText data;
    private EditText oraInizio;
    private EditText oraFine;
    private EditText titolo;
    private Calendar endTime = Calendar.getInstance();
    private SharedPreferences _events;

    DatePickerDialog.OnDateSetListener dateSL;
    TimePickerDialog.OnTimeSetListener timeSL;
    TimePickerDialog.OnTimeSetListener timeSLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);

        Intent i = getIntent();
        String username = i.getStringExtra("username");

        _events = this.getSharedPreferences(username, Context.MODE_PRIVATE);

        data = (EditText)findViewById(R.id.data);
        oraInizio = (EditText)findViewById(R.id.oraInizio);
        oraFine = (EditText)findViewById(R.id.oraFine);
        titolo = (EditText)findViewById(R.id.titolo);

        dateSL = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                endTime.set(year, month, day);
                updateLabel();
            }
        };
        timeSL = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m) {
                endTime.set(Calendar.HOUR_OF_DAY, h);
                endTime.set(Calendar.MINUTE, m);
                updateLabel();
            }
        };
        timeSLE = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m) {
                endTime.set(Calendar.HOUR_OF_DAY, h);
                endTime.set(Calendar.MINUTE, m);
                updateLabel();
            }
        };

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SecretActivity.this, dateSL, endTime.get(Calendar.YEAR), endTime.get(Calendar.MONTH), endTime.get(Calendar.DAY_OF_MONTH)).show();
                //new StringBuilder().append(DatePickerDialog.).append("/").append(+1).append("/").append(mYear)
            }

        });

        oraInizio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(SecretActivity.this, timeSL, endTime.get(Calendar.HOUR_OF_DAY), endTime.get(Calendar.MINUTE), true).show();
            }
        });
        oraFine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(SecretActivity.this, timeSLE, endTime.get(Calendar.HOUR_OF_DAY), endTime.get(Calendar.MINUTE), true).show();
            }
        });

    }
    public void back(View view){
        finish();
    }



    public void add(View view)
    {
        String dateh = ((EditText)findViewById(R.id.data)).getText();       // TODO: fixare
        SharedPreferences.Editor editor = _events.edit();
        editor.putString("admin", "Qwerty12");
        editor.putString("erizzolo", "PippoPlutoTopolino1");
        editor.apply();
        //DatePickerDialog dp = new DatePickerDialog(this, 0);
        Intent i=new Intent();
        //DatePicker d = findViewById(R.id.datai).date;
        //i.putExtra("data", dp);
        i.setClass(this, CalendarMainActivity.class);
        startActivity(i);
    }
    private void updateLabel() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ITALIAN);
        data.setText(dateFormat.format(endTime.getTime()));
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ITALIAN);
        oraInizio.setText(timeFormat.format(endTime.getTime()));
        oraFine.setText(timeFormat.format(endTime.getTime()));
    }
}