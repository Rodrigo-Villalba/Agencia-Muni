package com.example.agenciamuni;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarseActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mEmailField,mPasswordField;
    private Button mLoginBtn;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText correo;
    private EditText contrasena;
    private EditText contrasenaConfirmacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        mAuth = FirebaseAuth.getInstance();
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        contrasenaConfirmacion = findViewById(R.id.contrasenaConfirmacion);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    public void registrarUsuario(View view){

        if (contrasena.getText().toString().equals(contrasenaConfirmacion.getText().toString())){

            mAuth.createUserWithEmailAndPassword(correo.getText().toString().trim(), contrasena.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(), "Usuario Creado.",Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);

                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Error de Logeo.",Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });

        } else {
            Toast.makeText(this, "Las Contrase??as no Coinciden", Toast.LENGTH_SHORT).show();
        }


    }


    //mEmailField = (EditText) findViewById(R.id.correo);
    //mPasswordField = (EditText) findViewById(R.id.contrasena);

    //mLoginBtn = (Button) findViewById(R.id.IniciarSesionBtn);

    //mLoginBtn.setOnClickListener(new OnClickListener() {


    //}
}