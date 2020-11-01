package com.example.eatit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

public class SignUpActivity extends AppCompatActivity {
    EditText email, password;
    Button SignUp;
    TextInputLayout passwordLayout, emailLayout;
    TextView LogIn;
    String email_string, password_string;
    private FirebaseAuth mAuth;
    ProgressBar spinner;
    CountryCodePicker ccp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailLayout=findViewById(R.id.emailLayout);
        passwordLayout=findViewById(R.id.passowordLayout);
        spinner=findViewById(R.id.progressBar1);
        email = findViewById(R.id.Email);
        password=findViewById(R.id.password);
        SignUp=findViewById(R.id.SignUp);
        mAuth = FirebaseAuth.getInstance();
        LogIn=findViewById(R.id.logIn);
        Log.e("I'm","here");
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 email_string=email.getText().toString();
                password_string=password.getText().toString();
                spinner.setVisibility(View.VISIBLE);
                if(!(email_string.isEmpty() && password_string.isEmpty())){
                    mAuth.createUserWithEmailAndPassword(email_string, password_string)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    spinner.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("User created!", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        try
                                        {
                                            throw task.getException();
                                        }
                                        // if user enters wrong email.
                                        catch (FirebaseAuthWeakPasswordException weakPassword)
                                        {
                                            Log.d("SignUpError", "onComplete: weak_password");
                                            if(password_string.length()<6){
                                                passwordLayout.setError("Password should be atleast 6 characters");

                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(),weakPassword.getReason(),Toast.LENGTH_LONG).show();
                                            }

                                            // TODO: take your actions!
                                        }
                                        // if user enters wrong password.
                                        catch (FirebaseAuthInvalidCredentialsException malformedEmail)
                                        {
                                            Log.d("SignUpError", "onComplete: malformed_email");
                                            Toast.makeText(getApplicationContext(),malformedEmail.getMessage(),Toast.LENGTH_LONG).show();
                                            // TODO: Take your action
                                        }
                                        catch (FirebaseAuthUserCollisionException existEmail)
                                        {
                                            Log.d("SignUpError", "onComplete: exist_email");
                                            emailLayout.setError("Email already exists");

                                            // TODO: Take your action
                                        }
                                        catch (Exception e)
                                        {
                                            Log.d("SignUpError", "onComplete: " + e.getMessage());
                                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                                        }
                                        email.addTextChangedListener(new TextWatcher() {
                                            @Override
                                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                            }

                                            @Override
                                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                emailLayout.setError(null);
                                            }

                                            @Override
                                            public void afterTextChanged(Editable editable) {

                                            }
                                        });
                                        password.addTextChangedListener(new TextWatcher() {
                                            @Override
                                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                            }

                                            @Override
                                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                passwordLayout.setError(null);
                                            }

                                            @Override
                                            public void afterTextChanged(Editable editable) {

                                            }
                                        });
                                    }

                                    // ...
                                }
                            });
                }
            }
        });

    }
}
