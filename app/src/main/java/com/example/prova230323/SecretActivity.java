package com.example.prova230323;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class SecretActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);

    }
    public void back(View view){
        finish();
    }

    public void add(View view)
    {
        DatePickerDialog dp = new DatePickerDialog(this, 0);
        Intent i=new Intent();
        i.putExtra("data", dp);
        i.setClass(this, CalendarActivity.class);
        startActivity(i);
    }
}