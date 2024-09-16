package com.shruti.school;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class HomeActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener
{
boolean doubletap=false;
BottomNavigationView  bottomNavigationView;
SharedPreferences preferences;
SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);


        preferences= PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        editor=preferences.edit();
boolean firsttime=preferences.getBoolean("isFirstTime",true);
if (firsttime)
{
    welcome();
}
bottomNavigationView=findViewById(R.id.homeBottomNav);
bottomNavigationView.setOnNavigationItemSelectedListener(this);
bottomNavigationView.setSelectedItemId(R.id.menuBottomNavHome);
//        setTitle("Home Activity");
    }
private void welcome(){
        AlertDialog.Builder ad=new AlertDialog.Builder(HomeActivity.this);
        ad.setTitle("Smart School");
 ad.setMessage("Welcome to Smart School");
 ad.setPositiveButton("Thank You", new DialogInterface.OnClickListener() {
     @Override
     public void onClick(DialogInterface dialogInterface, int i) {

         dialogInterface.cancel();
     }
 }).create().show();
 editor.putBoolean("isFirstTime",false).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menuHomeQRCode)
        {
            Intent intent=new Intent(HomeActivity.this, QrCodeActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId()==R.id.menuHomeMyLocation){
            Intent i=new Intent(HomeActivity.this,GoogleMapActivity.class);
            startActivity(i);
        }
        else if (item.getItemId()==R.id.menuhomemyprofile){
            Intent i=new Intent(HomeActivity.this, MyProfileActivity.class);
            startActivity(i);
        } else if (item.getItemId()==R.id.menuhomesetting) {
            Intent i=new Intent(HomeActivity.this, SettingActivity.class);
            startActivity(i);
        } else if (item.getItemId()==R.id.menuhomecontactus) {
            Intent i=new Intent(HomeActivity.this, ContactUsActivity.class);
            startActivity(i);
        } else if (item.getItemId()==R.id.menuhomeaboutus) {
            Intent i=new Intent(HomeActivity.this, AboutUsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.menuhomelogout) {
logout();
        }
        return true;
    }
    private void logout(){
        AlertDialog.Builder ad=new AlertDialog.Builder(HomeActivity.this);
        ad.setTitle("Smart School");
        ad.setMessage("Are you sure want to Logout?");
       ad.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int i) {
               dialog.cancel();
           }
       });
        ad.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(HomeActivity.this,LoginActivity.class);
                editor.putBoolean("isLogin",false).commit();
                startActivity(intent);
            }
        }).create().show();
    }

    @Override
    public void onBackPressed() {
        if (doubletap) {
            finishAffinity();
            super.onBackPressed();
        } else {
            Toast.makeText(HomeActivity.this, "Press Again to Exit App", Toast.LENGTH_SHORT).show();
            doubletap = true;
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubletap = false;
                }
            }, 2000);

        }
    }
HomeFragment homeFragment=new HomeFragment();
    ExploreFragment exploreFragment=new ExploreFragment();
    MaterialFragment materialFragment=new MaterialFragment();
    NotificationFragment notificationFragment=new NotificationFragment();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menuBottomNavHome){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.homeFramelayout,homeFragment).commit();
        } else if (item.getItemId()==R.id.menuBottomNavExplore ){
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.homeFramelayout,exploreFragment).commit();
        } else if (item.getItemId()==R.id.menuBottomNavMaterial) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.homeFramelayout,materialFragment).commit();
        } else if (item.getItemId()==R.id.menuBottomNavNotification) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.homeFramelayout,notificationFragment).commit();
        }
        return true;
    }
}
