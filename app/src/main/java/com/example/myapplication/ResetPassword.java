package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private EditText mail;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mail = findViewById(R.id.resetEmail);
        auth = FirebaseAuth.getInstance();
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResetLink();
            }
        });
    }

    void sendResetLink(){
        auth.sendPasswordResetEmail(mail.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            new AlertDialog.Builder(ResetPassword.this)
                                    .setTitle("Message").setMessage("Mail sent!")
                                    .setPositiveButton("ok", null).show();
                        else
                            new AlertDialog.Builder(ResetPassword.this)
                                    .setTitle("Error").setMessage("Error while sending mail!")
                                    .setPositiveButton("ok", null).show();

                    }
                });
    }
}
