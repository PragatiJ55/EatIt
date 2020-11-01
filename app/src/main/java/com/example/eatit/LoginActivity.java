package com.example.eatit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button logIn;
    TextView signUp, forgot_pwd;
    EditText email, password;
    private FirebaseAuth mAuth;
    TextInputLayout emailLayout, passwordLayout;
    ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logIn=findViewById(R.id.logIn);
        signUp=findViewById(R.id.signUp);
        email=findViewById(R.id.Email);
        password=findViewById(R.id.password);
        forgot_pwd=findViewById(R.id.forgot_pwd);
        spinner=findViewById(R.id.progressBar1);
        mAuth = FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("what","happened?");
                Intent intent=new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setVisibility(View.VISIBLE);

                String emailString=email.getText().toString();
                String passwordString=password.getText().toString();


                if(!(emailString.isEmpty() && passwordString.isEmpty())){
                    mAuth.signInWithEmailAndPassword(emailString, passwordString)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    spinner.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("Yes", "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Log.w("No", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });

                }
                else{

                    passwordLayout=findViewById(R.id.passowordLayout);
                    if(emailString.isEmpty()){
                        emailLayout=findViewById(R.id.emailLayout);
                        emailLayout.setError("Email cannot be blank. Please enter a valid email");
                    }
                    if(passwordString.isEmpty()){
                        passwordLayout=findViewById(R.id.passowordLayout);
                        passwordLayout.setError("Password cannot be blank. Please enter a passoword");
                    }
                }
            }});

    }
}
