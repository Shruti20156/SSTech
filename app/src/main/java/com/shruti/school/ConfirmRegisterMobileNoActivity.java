package com.shruti.school;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ConfirmRegisterMobileNoActivity extends AppCompatActivity {
EditText etConfirmRegisterMobileNo;
AppCompatButton btnVerify;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_register_mobile_no);
        etConfirmRegisterMobileNo=findViewById(R.id.etConfirmRegisterMobileMobileNo);
        btnVerify = findViewById(R.id.btnConfirmRegisterMobileNoVerify);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etConfirmRegisterMobileNo.getText().toString().isEmpty())
                {
                    etConfirmRegisterMobileNo.setError("Please Enter Mobile No");
                }
                else if(etConfirmRegisterMobileNo.getText().toString().length()!=10)
                {
                   etConfirmRegisterMobileNo.setError(" Please Enter Valid Mobile No");
                }
                else{
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" +etConfirmRegisterMobileNo.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            ConfirmRegisterMobileNoActivity.this,new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressDialog.dismiss();
                                    Toast.makeText(ConfirmRegisterMobileNoActivity.this, "Verification Completed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(ConfirmRegisterMobileNoActivity.this, "Verification Fail", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationcode, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    Intent intent = new Intent(ConfirmRegisterMobileNoActivity.this,ForgetPasswordVerifyOTPActivity.class);
                                    intent.putExtra("verificationcode", verificationcode);
                                    intent.putExtra("mobileno", etConfirmRegisterMobileNo.getText().toString());

                                    startActivity(intent);
                                }

                            }

                    );


                }
            }
        });

    }
}
