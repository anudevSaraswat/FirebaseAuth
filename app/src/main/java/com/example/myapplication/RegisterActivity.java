package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText mail;
    private EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button join = findViewById(R.id.join);
        auth = FirebaseAuth.getInstance();
        mail = findViewById(R.id.mailed);
        pwd = findViewById(R.id.pwded);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });
    }

    void signInUser(){
        auth.createUserWithEmailAndPassword(mail.getText().toString(), pwd.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(RegisterActivity.this, Main2Activity.class);
                            startActivity(intent);
                        }
                        else
                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setMessage(task.getException()+"").setTitle("Error registering user!")
                                    .setPositiveButton("ok", null).show();
                    }
                });
    }
}
