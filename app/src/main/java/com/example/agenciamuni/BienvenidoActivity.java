package com.example.agenciamuni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BienvenidoActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView mCorreo, mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        mCorreo = findViewById(R.id.correo);
        mLogout = findViewById(R.id.logout);

        mAuth = FirebaseAuth.getInstance();

    }

    protected void onStar (){
        super.onStart();

        FirebaseUser mFirebaseUser = mAuth.getCurrentUser();

        if (mFirebaseUser!=null){
            mCorreo.setText(mFirebaseUser.getEmail());

        }else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    public void logout (View view){
        FirebaseAuth.getInstance().signOut();

    }
}