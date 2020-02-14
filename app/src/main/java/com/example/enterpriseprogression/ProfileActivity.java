package com.example.enterpriseprogression;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button button2;
    private Object FirebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toast.makeText(ProfileActivity.this, "Firebase connection success", Toast.LENGTH_SHORT).show();

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));

        }

        FirebaseUser = firebaseAuth.getCurrentUser();


        textViewUserEmail = (TextView) findViewById(R.id.textViewuserEmail);


        textViewUserEmail.setText("Welcome "+ textViewUserEmail.getEditableText());

        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == button2){

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));

        }

    }
}
