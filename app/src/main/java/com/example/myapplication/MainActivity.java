package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText mail;
    private EditText pwd;
    private Intent intent;
    private AuthCredential credential;
    private FirebaseAuth.AuthStateListener stateListener;

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(stateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail = findViewById(R.id.mailed);
        pwd = findViewById(R.id.pwded);
        TextView register = findViewById(R.id.newUser);
        TextView forgot = findViewById(R.id.forgot);
        Button signUp = findViewById(R.id.signup);
        SignInButton google = findViewById(R.id.googleButton);
        auth = FirebaseAuth.getInstance();
        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (auth.getCurrentUser() != null)
                    startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        };
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUserWithMail();
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail().requestProfile()
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .build();
                Log.e("id->>", getString(R.string.default_web_client_id));
                GoogleSignInClient client = GoogleSignIn.getClient(MainActivity.this, gso);
                Intent in = client.getSignInIntent();
                startActivityForResult(in, 2);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ResetPassword.class);
                startActivity(intent);
            }
        });
    }

    void signInUserWithMail(){
        auth.signInWithEmailAndPassword(mail.getText().toString(), pwd.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            intent = new Intent(MainActivity.this, Main2Activity.class);
                            startActivity(intent);
                        }
                        else
                            new AlertDialog.Builder(MainActivity.this)
                                    .setMessage(task.getException()+"").setTitle("Error logging in!")
                            .setPositiveButton("ok", null).show();
                    }
                });
    }

    void signInUserWithGoogle(){
        Log.e("credential->>", credential+"");
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                            startActivity(new Intent(MainActivity.this, Main2Activity.class));
                        else
                            new AlertDialog.Builder(MainActivity.this)
                                    .setMessage(task.getException()+"").setTitle("Error logging in!")
                                    .setPositiveButton("ok", null).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount account = task.getResult();
            Log.e("account->>", account.getIdToken());
            credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            signInUserWithGoogle();
        }
    }
}
