package com.example.el_partani_screens;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class login_screen extends AppCompatActivity {
    Button login_btn, toSignUp_btn;
    EditText inputName, inputEmail, inputPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    boolean isNewUser = true;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FloatingActionButton fb_admin;
    TextView tv_forgot_pass;
    Teacher t;
    DatabaseReference teacherRef;
    boolean teacher = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tv_forgot_pass = findViewById(R.id.tv_forgot_pass);
        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                if (checkEmail(email))
                    resetPassword(email);
            }
        });
        fb_admin = findViewById(R.id.fb_admin);
        registerForContextMenu(fb_admin);

        inputName = findViewById(R.id.inputNameLIS);
        inputEmail = findViewById(R.id.inputEmailLIS);
        inputPassword = findViewById(R.id.inputPasswordLIS);
        login_btn = findViewById(R.id.loginbtnLIS);
        toSignUp_btn = findViewById(R.id.toSignUp_btnLIS);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        toSignUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((v == toSignUp_btn)) {
                    Intent intent = new Intent(login_screen.this, Signup_screen.class);
                    startActivity(intent);
                }
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perForLogin();
            }
        });


    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // you can set menu header with title icon etc
        menu.setHeaderTitle("Choose a screen to go to");
        // add menu items
        menu.add(0, v.getId(), 0, "add a new teacher");
        menu.add(0, v.getId(), 0, "add a new profession");
        menu.add(0, v.getId(), 0, "add a new privet hour ");
        menu.add(0, v.getId(), 0, "go to admin main screen ");

    }

    // menu item select listener
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "add a new admin") {
            Intent intent = new Intent(login_screen.this, addTecherForAdmin.class);
            startActivity(intent);
        } else if (item.getTitle() == "add a new profession") {
            Intent intent = new Intent(login_screen.this, addNewProfession.class);
            startActivity(intent);
        } else if (item.getTitle() == "add a new private hour") {
            Intent intent = new Intent(login_screen.this, addPrivateHourForAdmin.class);
            startActivity(intent);
        } else if (item.getTitle() == "go to admin main screen") {
            Intent intent = new Intent(login_screen.this, adminMainScreen.class);
            startActivity(intent);
        }
        return true;
    }

    private void isAdmin() {
        //is the admin checking iscorrect?
        String name = inputName.getText().toString();
        String phone = inputPassword.getText().toString();
        String email = inputEmail.getText().toString();

        teacherRef = FirebaseDatabase.getInstance().getReference("Admin/");

        teacherRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    String adminName = data.child("name").getValue().toString();
                    String adminPhone = data.child("phone").getValue().toString();
                    String adminEmail = data.child("email").getValue().toString();
                    if (adminName.equals(name) && adminPhone.equals(phone) && adminEmail.equals(email))
                        fb_admin.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * todo - add documentation
     */
    private void resetPassword(String email) {
        Log.d("tag", email);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "Email sent.");
                            Log.d("tag", "Check Your Email");
                            Toast.makeText(getApplicationContext(), "An Email was sent to you for resetting the password", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * todo - add docomentation
     *
     * @param email
     * @return
     */
    private boolean checkEmail(String email) {
        if (email.trim().length() == 0) {
            Toast.makeText(login_screen.this, "please enter an email address",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!email.matches(emailPattern)) {
            inputEmail.setError("ENTER CORRECT EMAIL");
            return false;
        }
        isEmailExists(email, new OnEmailCheckListener() {
            @Override
            public void onSuccess(boolean isRegistered) {
                if (isRegistered) {
                    //The email was registered before
                } else {
                    //The email not registered before
                    isNewUser = false;
                }
            }
        });
        return isNewUser;
    }

    private void isEmailExists(final String email, final OnEmailCheckListener listener) {
        //check email already exist or not.
        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        int task_result = task.getResult().getSignInMethods().size();
                        boolean check = task_result == 1 ? true : false;
                        if (!check)
                            Toast.makeText(login_screen.this, "email not existed, please sign up", Toast.LENGTH_SHORT).show();

                        listener.onSuccess(check);
                        /*
                        if (task.getResult().getSignInMethods().size() == 0) {  //1 if exists , 0 if not
                            isNewUser = false;   //email not existed
                            Log.d("tag", "email not existed, please sign up");
                            Toast.makeText(login_screen.this, "email not existed, please sign up", Toast.LENGTH_SHORT).show();
                        } else {
                            isNewUser = true;
                            Log.d("tag", "isNewUser2:" + isNewUser);
                        }*/
                    }
                });
    }

    private void perForLogin() {
        if (inputName.getText().toString().trim().length() > 0 &&
                inputPassword.getText().toString().trim().length() > 0 &&
                inputEmail.getText().toString().trim().length() == 0) {
            isAdmin();
            return;
        }
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();


        if (!email.matches(emailPattern)) {
            inputEmail.setError("ENTER CORECT EMIAL");
            inputEmail.requestFocus();
        } else if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError("ENTER PROPER PASSWORD");
            inputPassword.requestFocus();

        } else {
            progressDialog.setMessage("Please wait while Login...");
            progressDialog.setTitle("LOGIN");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                sendUserToNextActivity();
                                Toast.makeText(login_screen.this,
                                        "Login succesful",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(login_screen.this,
                                        "" + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
    private void isTeacher() {
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("csv");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String t_name = snapshot.child("name").getValue().toString();
                    String t_email = snapshot.child("email").getValue().toString();
//                    String t_phone = snapshot.child("phone").getValue().toString();
                    if(t_name.equals(inputName.getText().toString()) &&
                            t_email.equals(inputEmail.getText().toString()) /* &&
                           t_phone.equals(inputPassword.getText().toString())*/) {
                        teacher = true;
                        Intent intent = new Intent(login_screen.this,
                                Teacher_schedule.class);
                        intent.putExtra("name", t_name);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else{//is student


                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    private void sendUserToNextActivity() {
        isTeacher();

        /*if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals("nBY12GPlOyQ21WRJHCdKwBMKb7E2"))
            startActivity(new Intent(getApplicationContext(), WeTrip.class));*/
        Log.d("tag", "techer:"+teacher);
        Intent intent = new Intent(login_screen.this,
                studentSchedule.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name", inputName.getText().toString());
        startActivity(intent);





    }


}


