package com.shruti.school;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyProfileActivity extends AppCompatActivity {
//TextView tvToken;
SharedPreferences preferences;
    GoogleSignInOptions googleSignInOptions;

    GoogleSignInClient googleSignInClient;
ImageView ivProfilePhoto;
Button btnChangeProfile;
TextView tvName,tvMob,tvEmailId,tvUsername;
String strUsername;
ProgressDialog progressDialog;
Button btnsignout;
//Uri imagePath;
//Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_profile);

        preferences = PreferenceManager.getDefaultSharedPreferences(MyProfileActivity.this);
        strUsername=preferences.getString("username","");

        ivProfilePhoto = findViewById(R.id.ivmyprofilepic);
        btnChangeProfile = findViewById(R.id.btnMyProfileChangepic);
        tvName = findViewById(R.id.tvmyprofilename);
        tvMob = findViewById(R.id.tvmyprofileMob);
        tvEmailId = findViewById(R.id.tvmyprofileEmailId);
        tvUsername = findViewById(R.id.tvmyprofileUsername);
        tvName=findViewById(R.id.tvmyprofilename);
        tvEmailId=findViewById(R.id.tvmyprofileEmailId);

        btnsignout=findViewById(R.id.btnSignOut);
        googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient= GoogleSignIn.getClient(MyProfileActivity.this,googleSignInOptions);

        GoogleSignInAccount googleSignInAccount=GoogleSignIn.getLastSignedInAccount(this);
        if (googleSignInAccount!=null) {
            String name = googleSignInAccount.getDisplayName();
            String email = googleSignInAccount.getEmail();
            tvName.setText(name);
            tvEmailId.setText(email);
            btnsignout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> istask) {
                            Intent intent = new Intent(MyProfileActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            });
        }

//        tvPassword=findViewById(R.id.tvmyprofilePassword);
//        tvToken=findViewById(R.id.tvmyprofileToken);

//        String strName = preferences.getString("Name", "");
//        String strMob=preferences.getString("MobileNo","");
//        String strEmail=preferences.getString("EmailId","");
//        String  strUserName=preferences.getString("Username","");
//        String strPassword=preferences.getString("Password","");

//        tvName.setText(strName);
//        tvMob.setText(strMob);
//        tvEmailId.setText(strEmail);
//        tvUsername.setText(strUserName);
//        tvPassword.setText(strPassword);


//btnChangeProfile.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//        openImageChooser();
//    }
//});
//        Toast.makeText(MyProfileActivity.this, "My Profile Open", Toast.LENGTH_SHORT).show();
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Toast.makeText(MyProfileActivity.this, "FCM Token not Recieved", Toast.LENGTH_SHORT).show();
//
//                            return;
//                        }
//
//                        // Get new FCM registration token
//                        String token = task.getResult();
//                        tvToken.setText(token);
//                        Toast.makeText(MyProfileActivity.this, token, Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

//    private void openImageChooser() {
//        Intent intent=new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,"Select Profile Photo"),999);
//    }

//        @Override
//        public void onBackPressed() {
//
//            super.onBackPressed();
//            Intent intent = new Intent(MyProfileActivity.this, LoginActivity.class);
//            startActivity(intent);
//        }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode==999 && resultCode==RESULT_OK&& data!=null)
//        {
//            imagePath=data.getData();
//            try {
//
//
//                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
//                ivProfilePhoto.setImageBitmap(bitmap);
//                File imageFile = new File(getFilesDir(), "profile.jpeg");
//                FileOutputStream outputStream = openFileOutput(imageFile.getName(), Context.MODE_PRIVATE);
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//                outputStream.close();
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog=new ProgressDialog(MyProfileActivity.this);
        progressDialog.setTitle("My Profile");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();

        getMyDetails();
    }

    private void getMyDetails() {

    }
}

