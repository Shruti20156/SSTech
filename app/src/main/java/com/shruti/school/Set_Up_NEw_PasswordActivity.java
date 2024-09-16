package com.shruti.school;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.shruti.school.comman.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Set_Up_NEw_PasswordActivity extends AppCompatActivity {
    EditText etNewPassword,etConfirmPassword;
    AppCompatButton btnSetUpPassword;
    ProgressDialog progressDialog;
String strMobileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_new_password);
        etNewPassword=findViewById(R.id.etVerifyNewPassword);
        etConfirmPassword=findViewById(R.id.etVerifyConfirmPassword);
        btnSetUpPassword=findViewById(R.id.btnConfirmPassword);
strMobileNo=getIntent().getStringExtra("mobile");
btnSetUpPassword.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(etNewPassword.getText().toString().isEmpty()||etConfirmPassword.getText().toString().isEmpty())
        {
            Toast.makeText(Set_Up_NEw_PasswordActivity.this, "Please Enter New or Confirm Password", Toast.LENGTH_SHORT).show();
        }
        else if(!etNewPassword.getText().toString().equals(etConfirmPassword.getText().toString()))
        {
            etConfirmPassword.setError("Password did not match");
        }
        else {
            progressDialog=new ProgressDialog(Set_Up_NEw_PasswordActivity.this);
            progressDialog.setTitle("Updating Password");
            progressDialog.setMessage("Please Wait..");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            forgetPassword();
        }
    }
});

    }

    private void forgetPassword() {
        AsyncHttpClient client=new AsyncHttpClient();//Client server Communication
        RequestParams params=new RequestParams();//put the data
        params.put("mobileno",strMobileNo);
        params.put("password",etNewPassword.getText().toString());
        client.post(Urls.forgetPasswordWebservice,
                params,
                new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            String status=response.getString("success");
                            if (status.equals("1"))
                            {
                                Intent intent=new Intent(Set_Up_NEw_PasswordActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Set_Up_NEw_PasswordActivity.this, "Password Not Found", Toast.LENGTH_SHORT).show();
                            }
                        } catch ( JSONException e ) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });
    }
}