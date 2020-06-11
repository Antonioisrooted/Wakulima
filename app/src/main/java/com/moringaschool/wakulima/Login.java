package com.moringaschool.wakulima;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.wakulima.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {

public static final String TAG = Login.class.getSimpleName();
    @BindView(R.id.password_l)EditText mPassword;
    @BindView(R.id.Email_l)EditText mEmail;
    @BindView(R.id.loginBtn_l)Button mLoginBtn;
    @BindView(R.id.createText_l)TextView mCreateBtn;
    @BindView(R.id.progressBar2)ProgressBar mProgressbar;
    Context context;
    FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
        mLoginBtn.setOnClickListener(this);
        mCreateBtn.setOnClickListener(this);
        createAuthProgressDialog();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent intent =new Intent(Login.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }

            }
        };
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating");
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        if (v==mCreateBtn){
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        }
        if (v==mLoginBtn){
            Intent intent = new Intent(Login.this, MainActivity.class);
//            loginWithPassword();
            startActivity(intent);
        }

    }

    private void loginWithPassword() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            mEmail.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(password)){
            mPassword.setError("Password is required.");
            return;
        }

        if (password.length()<6){
            mPassword.setError("Password must be >=6 Character");
            return;
        }

        mAuthProgressDialog.show();
        fAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mAuthProgressDialog.dismiss();
                        if (!task.isSuccessful()){
                            Toast.makeText(Login.this, "Authentication Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            fAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
