package com.hgc.learnandroid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Sign_Up extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private SignInButton googlebut;
    private CardView button_sign_up;
    private ProgressDialog pdLoading;
    private TextView text_name,text_username,text_password,text_orsignin,text_forgot_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent i = new Intent(Sign_Up.this, MainTopics.class);
                    finish();
                    startActivity(i);
                }
            }
        };
//        pdLoading.setMessage("Signing up...");
        button_sign_up= (CardView) findViewById(R.id.bsignup);
        text_name = (TextView) findViewById(R.id.sfield_name);
        text_password = (TextView) findViewById(R.id.sfield_password);
        text_username = (TextView) findViewById(R.id.sfield_username);
        text_orsignin=(TextView)findViewById(R.id.orsignin);
        text_forgot_password=(TextView)findViewById(R.id.sfield_forgpass);

        button_sign_up.setOnClickListener(this);
        text_forgot_password.setOnClickListener(this);
        text_orsignin.setOnClickListener(this);



    }
    private void Signup()
    {
        final String name,email,password;
        name=text_name.getText().toString();
        email=text_username.getText().toString().trim();
        password=text_password.getText().toString().trim();
            if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(name)) {

            Toast.makeText(Sign_Up.this, "Empty Fields", Toast.LENGTH_SHORT).show();
        }
        else{
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user=mAuth.getCurrentUser();
                    UserProfileChangeRequest profileChangeRequest=new UserProfileChangeRequest.Builder()
                            .setDisplayName(name).build();
                    user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                            Toast.makeText(Sign_Up.this, "Account Updated", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i=new Intent(Sign_Up.this,MainTopics.class);
                                startActivity(i);
                            }
                            else {

                                Toast.makeText(Sign_Up.this, "error in updating name", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Sign_Up.this, "An error occurred..", Toast.LENGTH_SHORT).show();
                }
            }
        });}
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
      if(v==button_sign_up)
      {
        Signup();
      }
      else if(v==text_forgot_password)
      {
          AlertDialog.Builder builder=new AlertDialog.Builder(this);
          builder.setTitle("Enter your email");
          final EditText editmail=new EditText(this);
          builder.setView(editmail);
          builder.setPositiveButton("Send Mail", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                  String mailid=editmail.getText().toString().trim();
                  mAuth.sendPasswordResetEmail(mailid).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          if(task.isSuccessful())
                          {
                              Toast.makeText(Sign_Up.this, "Email Sent...", Toast.LENGTH_SHORT).show();
                          }
                          else
                          {
                              Toast.makeText(Sign_Up.this, "An error occurred...", Toast.LENGTH_SHORT).show();
                          }
                      }
                  });
              }
          });
          builder.show();
      }
      else if(v==text_orsignin)
        {

            Intent i=new Intent(Sign_Up.this,authActivity.class);
            finish();
            startActivity(i);
        }
    }
}
