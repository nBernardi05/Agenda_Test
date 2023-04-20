package com.example.prova230323;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences _users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _users = this.getSharedPreferences("_users", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = _users.edit();
        editor.putString("admin", "Qwerty12");
        editor.putString("erizzolo", "PippoPlutoTopolino1");
        editor.apply();
    }
    public void login(View view){
        String usr = ((EditText)findViewById(R.id.username)).getText().toString();
        String psw = ((EditText)findViewById(R.id.password)).getText().toString();
        SharedPreferences sp = getSharedPreferences("_users", 0);
        try{
            if(sp.getString(usr, "").equals(psw)){
                Intent i=new Intent();
                i.putExtra("username", usr);
                i.setClass(this, SecretActivity.class);
                startActivity(i);
            }else{
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "Username/Password non validi", duration);
                toast.show();
            }
        }finally {

        }
    }
    public void register(View view){
        String usr = ((EditText)findViewById(R.id.username)).getText().toString();
        String psw = ((EditText)findViewById(R.id.password)).getText().toString();
        SharedPreferences.Editor editor = _users.edit();
        if(_users.getString(usr, "").equals("")) {
            editor.putString(usr, psw);
            editor.apply();
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "Registrato", duration);
            toast.show();
        }else{
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "L'utente esiste già", duration);
            toast.show();
        }
    }

    public void chiudiApp(View view){
        finish();
    }
}