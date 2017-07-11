package com.example.artursamirovs.amigos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText regUsername, regPassword, regFirstName, regLocation;
    Button createNewAcc;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regUsername = (EditText) findViewById(R.id.registration_username);
        regPassword = (EditText) findViewById(R.id.registration_password);
        regFirstName = (EditText) findViewById(R.id.registration_first_name);
        regLocation = (EditText) findViewById(R.id.registration_location);
        createNewAcc = (Button) findViewById(R.id.buttonRegistration);
        createNewAcc.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == createNewAcc.getId()){
            createUser();
        }
    }

    public void createUser(){
        mAuth.createUserWithEmailAndPassword(regUsername.getText().toString(), regPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Registration: ", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Not succesful",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(RegistrationActivity.this, HomeActivity.class));
                            finish();
                        }

                        // ...
                    }
                });
    }

}
