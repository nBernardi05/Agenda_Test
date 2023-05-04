package com.example.prova230323;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class SecretActivity extends AppCompatActivity {
    private EditText data;
    private EditText oraInizio;
    private EditText oraFine;
    private EditText titolo;
    private Calendar endTime = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener dateSL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);

        data = (EditText)findViewById(R.id.data);
        oraInizio = (EditText)findViewById(R.id.oraInizio);
        oraFine = (EditText)findViewById(R.id.oraFine);
        titolo = (EditText)findViewById(R.id.titolo);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SecretActivity.this, dateSL, endTime.get(Calendar.YEAR), endTime.get(Calendar.MONTH), endTime.get(Calendar.DAY_OF_MONTH)).show();
                //new StringBuilder().append(DatePickerDialog.).append("/").append(+1).append("/").append(mYear)
            }

        });

    }
    public void back(View view){
        finish();
    }



    public void add(View view)
    {

        //DatePickerDialog dp = new DatePickerDialog(this, 0);
        Intent i=new Intent();
        //DatePicker d = findViewById(R.id.datai).date;
        //i.putExtra("data", dp);
        i.setClass(this, CalendarMainActivity.class);
        startActivity(i);
    }
}