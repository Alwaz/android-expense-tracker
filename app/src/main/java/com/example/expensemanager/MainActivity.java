package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPass;
    private TextView mForgetPassword, mSignupHere;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       loginDetails();
    }

    private void loginDetails(){
        mEmail=(EditText)findViewById(R.id.email_login);
        mPass=(EditText)findViewById(R.id.password_login);
        btnLogin=(Button)findViewById(R.id.btn_login);
        mForgetPassword=(TextView)findViewById(R.id.forget_password);
        mSignupHere=(TextView)findViewById(R.id.signup_reg);


        btnLogin.setOnClickListener(v -> {
            String email=mEmail.getText().toString().trim();
            String password=mPass.getText().toString().trim();

            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email Required...");
            }
            if(TextUtils.isEmpty(password)){
                mPass.setError("Password Required...");

            }

        });

//        when user will click on signup it will take them to new activity (Regitration Activity)
        mSignupHere.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RegistrationActivity.class)));

//        on clicking this will take to reset password activity
        mForgetPassword.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),ResetActivity.class)));



    }
}