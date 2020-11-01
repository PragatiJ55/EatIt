package com.example.eatit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText email;
    Button reset;
    TextInputLayout emailLayout;
    AlertDialog.Builder builder;
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email = findViewById(R.id.Email);
        reset=findViewById(R.id.reset);
        emailLayout=findViewById(R.id.emailLayout);
        builder = new AlertDialog.Builder(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email_string= email.getText().toString();
                if(email_string.isEmpty()){
                    emailLayout.setError("Email cannot be left blank. Please enter a valid email");
                }
                else if(!isEmailValid(email_string)){
                    emailLayout.setError("Enter a valid email address");
                }
                else{
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email_string)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("Reset password", "Email sent.");
                                        builder.setMessage("An email is sent to "+email_string+".Tap the link in the email to reset your password")
                                                .setTitle("Check your email")
                                                .setCancelable(false)
                                                .setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        finish();
                                                    }
                                                })  ;
                                        final AlertDialog alert = builder.create();
                                        alert.setOnShowListener( new DialogInterface.OnShowListener() {
                                            @Override
                                            public void onShow(DialogInterface arg0) {
                                                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#112947"));
                                            }
                                        });
                                        alert.show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}
