package com.example.enterpriseprogression;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;


    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() !=null){

            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

        }

        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        buttonSignin = (Button)findViewById(R.id.buttonSignin);
        textViewSignup = (TextView)findViewById(R.id.textViewSignup);


        progressDialog = new ProgressDialog(this);


        buttonSignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    private void userLogin(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please Enter email", Toast.LENGTH_SHORT).show();
            //stopping the function executing further
            return;

        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please Enter password", Toast.LENGTH_SHORT).show();
            //stopping the function executing further
            return;
        }

        //if validation are okay
        //we will first show a progress bar

        progressDialog.setMessage("Registering User.....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()){

                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {

        if (view == buttonSignin) {

            userLogin();
        }

        if (view == textViewSignup) {
            finish();
            startActivity(new Intent(this, RegistrationActivity.class));

        }

    }
}