package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            TextView tv = findViewById(R.id.tv1);
            Button signOut = findViewById(R.id.out);
            tv.setText(user.getEmail());
            signOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    auth.signOut();
                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }
    }
}
