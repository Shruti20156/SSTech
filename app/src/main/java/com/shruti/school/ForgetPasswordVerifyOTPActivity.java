package com.shruti.school;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

public class ForgetPasswordVerifyOTPActivity extends AppCompatActivity {
    TextView tvMobileno, tvResendOtp;
    EditText etInputCode1, etInputCode2, etInputCode3, etInputCode4, etInputCode5, etInputCode6;
    AppCompatButton btnVerify;
    private String strVerificationCode, strMobileNo;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp_verification);
        tvMobileno = findViewById(R.id.tvOtpverifyMob);
        tvResendOtp = findViewById(R.id.tvOtpverifyResend);
        etInputCode1 = findViewById(R.id.etOtpverifyCode1);
        etInputCode2 = findViewById(R.id.etOtpverifyCode2);
        etInputCode3 = findViewById(R.id.etOtpverifyCode3);
        etInputCode4 = findViewById(R.id.etOtpverifyCode4);
        etInputCode5 = findViewById(R.id.etOtpverifyCode5);
        etInputCode6 = findViewById(R.id.etOtpverifyCode6);
        btnVerify = findViewById(R.id.btnOtpVerify);
        strVerificationCode = getIntent().getStringExtra("verificationcode");

        strMobileNo = getIntent().getStringExtra("mobileno");


        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etInputCode1.getText().toString().trim().isEmpty() || etInputCode2.getText().toString().trim().isEmpty() ||
                        etInputCode3.getText().toString().trim().isEmpty() || etInputCode4.getText().toString().trim().isEmpty()
                        || etInputCode5.getText().toString().trim().isEmpty() || etInputCode6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(ForgetPasswordVerifyOTPActivity.this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
                } else {
                    String otpcode = etInputCode1.getText().toString() + etInputCode2.getText().toString() +
                            etInputCode3.getText().toString() + etInputCode4.getText().toString() +
                            etInputCode5.getText().toString() + etInputCode6.getText().toString();

                    if (strVerificationCode != null) {
                        progressDialog = new ProgressDialog(ForgetPasswordVerifyOTPActivity.this);
                        progressDialog.setTitle("Verifying OTP");
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                strVerificationCode, otpcode
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                           progressDialog.dismiss();
                                           Intent intent=new Intent(ForgetPasswordVerifyOTPActivity.this, Set_Up_NEw_PasswordActivity.class);
                                          intent.putExtra("mobile",strMobileNo);
                                           startActivity(intent);


                                        } else {
                                            Toast.makeText(ForgetPasswordVerifyOTPActivity.this, "OTP Verification fail", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }

                }
            }
        });
        tvResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" +strMobileNo,
                        60,
                        TimeUnit.SECONDS,
                        ForgetPasswordVerifyOTPActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressDialog.dismiss();
                                Toast.makeText(ForgetPasswordVerifyOTPActivity.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressDialog.dismiss();
                                Toast.makeText(ForgetPasswordVerifyOTPActivity.this, "Verification Fail", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newverificationcode, @NonNull PhoneAuthProvider
                                    .ForceResendingToken forceResendingToken) {
                                strVerificationCode=newverificationcode;

                            }
                        }

                );

            }
        });
        setUpInputOTP();

    }

    private void setUpInputOTP() {

        etInputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override//CharSequence=collection chars
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {//onTextchanged is used when type just 1st then focus on next
                if (!charSequence.toString().trim().isEmpty()) {
                    etInputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etInputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    etInputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etInputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    etInputCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etInputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    etInputCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etInputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    etInputCode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });







    }

    }
