package com.example.enterpriseprogression;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int ENTERPRISE_EVOLUTION = 4000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(homeIntent);
                finish();


            }
        },ENTERPRISE_EVOLUTION);
    }
}
