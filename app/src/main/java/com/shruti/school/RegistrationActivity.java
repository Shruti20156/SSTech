package com.shruti.school;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

public class RegistrationActivity extends AppCompatActivity {
    EditText name, mobileNo, emailID, username, password;
    Button btn;
    SharedPreferences preferences;//temporary data store
    SharedPreferences.Editor editor;//edit and put data

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        preferences = PreferenceManager.getDefaultSharedPreferences(RegistrationActivity.this);
        editor = preferences.edit();

        name = findViewById(R.id.etRegisterName);
        mobileNo = findViewById(R.id.etMobileNo);
        emailID = findViewById(R.id.etEmailId);
        username = findViewById(R.id.etUserName);
        password = findViewById(R.id.etPassword);
        btn = findViewById(R.id.btnRegister);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty()) {
                    name.setError("Please Enter Your Name");
                } else if (mobileNo.getText().toString().isEmpty()) {
                    mobileNo.setError("Please Enter Your Mobile No");

                } else if (mobileNo.getText().toString().length() != 10) {
                    mobileNo.setError("InValid Mobile No");

                } else if (emailID.getText().toString().isEmpty()) {
                    emailID.setError("Please Enter Email ID");

                } else if (!emailID.getText().toString().contains("@") || !emailID.getText().toString().contains(".com")) {
                    emailID.setError("Enter Valid Email ID");
                } else if (username.getText().toString().isEmpty()) {
                    username.setError("Please Enter Your Username");
                } else if (username.getText().toString().length() < 8) {
                    username.setError("Please Enter 8 Character Username");
                } else if (!username.getText().toString().matches(".*[A-Z].*")) {
                    username.setError("Please Use 1 Uppercase Letter");

                } else if (!username.getText().toString().matches(".*[a-z].*")) {
                    username.setError("Please Use 1 LowerCase Letter");
                } else if (!username.getText().toString().matches(".*[0-9].*")) {
                    username.setError("Please Use 1 Number");
                } else if (!username.getText().toString().matches(".*[@,#,$,!,&].*")) {
                    username.setError("Please Use 1 Special Symbol");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Please Enter Your Password");
                } else if (password.getText().toString().length() < 8) {
                    password.setError("Please Enter 8 Character Password");
                } else {

                    progressDialog = new ProgressDialog(RegistrationActivity.this);
                    progressDialog.setTitle("Please Wait");
                    progressDialog.setMessage("Registration is in process");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + mobileNo.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            RegistrationActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressDialog.dismiss();
                                    Toast.makeText(RegistrationActivity.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(RegistrationActivity.this, "Verification Fail", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationcode, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    Intent intent = new Intent(RegistrationActivity.this, OtpVerificationActivity.class);
                                    intent.putExtra("verificationcode", verificationcode);
                                    intent.putExtra("name", name.getText().toString());
                                    intent.putExtra("mob", mobileNo.getText().toString());
                                    intent.putExtra("email", emailID.getText().toString());
                                    intent.putExtra("username", username.getText().toString());
                                    intent.putExtra("password", password.getText().toString());
                                    startActivity(intent);
                                }
                            }

                    );


                }
            }

        });

    }

}

