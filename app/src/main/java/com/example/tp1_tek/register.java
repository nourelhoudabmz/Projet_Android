package com.example.tp1_tek;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {

        EditText editTextEmail, editTextPassword;
        Button buttonReg;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            mAuth=FirebaseAuth.getInstance();
            editTextEmail = findViewById(R.id.email);
            editTextPassword = findViewById(R.id.password);
            buttonReg = findViewById(R.id.registerButton);
            ProgressBar = findViewById(R.id.progressBar);
            TextView= findViewById(R.id.signupText);



           TextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view ){
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                finish(); }
           });

            buttonReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    String email, password;
                    email = String.valueOf(editTextEmail.getText());
                    password = String.valueOf(editTextPassword.getText());

                    if (TextUtils.isEmpty(email)){
                        Toast.makeText(Register.this,"Enter email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(password)){
                        Toast.makeText(Register.this,"Enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                            new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task){
                                progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this,"Account created", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Register.this,"Authentication failed", Toast.LENGTH_SHORT).show();
                            }
                            }
                            });
                }

            });
        }}

