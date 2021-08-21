package com.example.expensemanager;

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

public class RegistrationActivity extends AppCompatActivity {

    private EditText mEmail,getmPassword;
    private Button btnReg;
    private TextView mSignin;

//    Firebase
    private FirebaseAuth mAuth;

//    progress dailoge
    private ProgressDialog mDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitration);

        mAuth=FirebaseAuth.getInstance();
        mDialogue=new ProgressDialog(this);


        registration();
    }

    private void registration(){
        mEmail=findViewById(R.id.email_reg);
        getmPassword=findViewById(R.id.password_reg);
        btnReg=findViewById(R.id.btn_reg);
        mSignin=findViewById(R.id.signup_reg);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String password=getmPassword.getText().toString().trim();


//                if user did not enter email
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email Required...");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    getmPassword.setError("Password Required...");
                }
                mDialogue.setMessage("Proccessing...");
                mDialogue.show();

                //                    if reg is successfull show a toast and redirect to home activity
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
//                            if registration complete dissmiss the dialogue
                        mDialogue.dismiss();
                        Toast.makeText(getApplicationContext(),"Registration Successfull",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    }else{
//                            if not then also dissmissed
                        mDialogue.dismiss();
                       Toast.makeText(getApplicationContext(),"Registration Failed...",Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });

        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }
}