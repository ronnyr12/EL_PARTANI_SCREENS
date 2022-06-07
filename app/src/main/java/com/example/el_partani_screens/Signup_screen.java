package com.example.el_partani_screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;

public class Signup_screen extends AppCompatActivity{
    Button sign_up_btn;
    EditText inputName,inputEmail,inputPassword,inputConfirmPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    int count = 0;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputConfirmPassword = findViewById(R.id.InputConfirmPassword);
        sign_up_btn = findViewById(R.id.signupbtn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreForAuth();
            }
        });

    }

        private void PreForAuth () {
            String email = inputEmail.getText().toString();
            String password = inputPassword.getText().toString();
            String confirmPassword = inputConfirmPassword.getText().toString();


            if (!email.matches(emailPattern)) {
                inputEmail.setError("ENTER CORRECT EMAIL");
                inputEmail.requestFocus();
            } else if (password.isEmpty() || password.length() < 6) {
                inputPassword.setError("ENTER PROPER PASSWORD");
            } else if (!password.equals(confirmPassword)) {
                inputConfirmPassword.setError("PASSWORD NOT MATCH BOTH FIELD");
            } else {
                progressDialog.setMessage("Please wait while sign up...");
                progressDialog.setTitle("SIGN UP");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();

                            Toast.makeText(Signup_screen.this, "sign up succesful",
                                    Toast.LENGTH_SHORT).show();

                            sendUserToNextActivity();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Signup_screen.this,
                                    "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    private void sendUserToNextActivity () {
        Intent intent = new Intent(Signup_screen.this, addNewProfession.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}