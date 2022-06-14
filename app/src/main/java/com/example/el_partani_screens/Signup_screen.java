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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

public class Signup_screen extends AppCompatActivity{
    Button sign_up_btn;
    EditText inputName,inputEmail,inputPassword,inputConfirmPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    int count = 0;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference teachersRef;
    boolean teacher = false;

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
        teachersRef = FirebaseDatabase.getInstance().getReference("csv");

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreForAuth();
            }
        });

    }

    private void isTeacher() {
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("csv");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String t_name=snapshot.child("name").getValue().toString();
                    String t_email=snapshot.child("email").getValue().toString();
                    String t_phone=snapshot.child("phone").getValue().toString();
                    if(t_name.equals(inputName.getText().toString()) &&
                            t_email.equals(inputEmail.getText().toString()) &&
                            t_phone.equals(inputPassword.getText().toString()))
                        teacher = true;
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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
                            Toast.makeText(Signup_screen.this, "sign up successful",
                                    Toast.LENGTH_SHORT).show();
                            //check if is in data base of teachers, if not creates a new teacher
                            isTeacher();    //if is in list moves to schedule screen
                            if(teacher){
                                Intent intent = new Intent(Signup_screen.this,
                                        Teacher_schedule.class);
                                intent.putExtra("name",inputName.getText().toString());
                                startActivity(intent);

                            }
                            else {
                                //add student to db
                                Student st = new Student(inputName.getText().toString(),
                                        inputPassword.getText().toString(),
                                        inputEmail.getText().toString(),"tbd");

                                DatabaseReference studentRef = FirebaseDatabase.getInstance().
                                        getReference("Studentss").push();
                                //p.setPrbm_id(problemRef.getKey());  change key to phone
                                studentRef.setValue(st);
                                sendUserToNextActivity();   //if gets to here he isnt in list moves to schedule screen
                            }
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Signup_screen.this,
                                    "" + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    private void sendUserToNextActivity () {
        Intent intent = new Intent(Signup_screen.this, studentSchedule.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}