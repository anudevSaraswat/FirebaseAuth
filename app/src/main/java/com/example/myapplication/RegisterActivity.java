package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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
        Button button = findViewById(R.id.verify);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
//                MapFragment fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
//                fragment.getMapAsync(new OnMapReadyCallback() {
//                    @Override
//                    public void onMapReady(GoogleMap googleMap) {
//                        googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)));
//                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                        googleMap.getUiSettings().setZoomGesturesEnabled(true);
//                    }
//                });

//        Button join = findViewById(R.id.join);
//        auth = FirebaseAuth.getInstance();
//        mail = findViewById(R.id.mailed);
//        pwd = findViewById(R.id.pwded);
//        join.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signInUser();
//            }
//        });
//    }
//
//    void signInUser(){
//        auth.createUserWithEmailAndPassword(mail.getText().toString(), pwd.getText().toString())
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            Intent intent = new Intent(RegisterActivity.this, Main2Activity.class);
//                            startActivity(intent);
//                        }
//                        else
//                            new AlertDialog.Builder(RegisterActivity.this)
//                                    .setMessage(task.getException()+"").setTitle("Error registering user!")
//                                    .setPositiveButton("ok", null).show();
//                    }
//                });
//        <com.google.android.material.textfield.TextInputLayout
//        android:layout_width="match_parent"
//        android:layout_height="wrap_content"
//        android:layout_marginBottom="10dp"
//        android:id="@+id/addressEdParent">
//
//                <com.google.android.material.textfield.TextInputEditText
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:textSize="25sp"
//        android:hint="@string/address_hint"
//        android:textColor="#000"
//        android:inputType="textPassword"
//        android:id="@+id/addressEd"/>
//
//        </com.google.android.material.textfield.TextInputLayout>

    }
}
