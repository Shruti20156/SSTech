package com.shruti.school.comman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.shruti.school.R;

public class NetworkChangeListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      if(!NetworkDetails.isConnectedToInternet(context))
      {
          AlertDialog.Builder builder=new AlertDialog.Builder(context);//dialog build
          View layoutdialog= LayoutInflater.from(context).inflate(R.layout.wifi_connection,null);
          builder.setView(layoutdialog);
          AppCompatButton btnretry=layoutdialog.findViewById(R.id.btninternetretry);
          AlertDialog alertDialog=builder.create();
          alertDialog.show();
          alertDialog.setCanceledOnTouchOutside(false);
          btnretry.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  alertDialog.dismiss();
                  onReceive(context,intent);
              }
          });
      }
      else
      {
          Toast.makeText(context, "Your Internet is Connected", Toast.LENGTH_SHORT).show();
      }
    }
}


//android Application Componenet
//Activity=>
//Services
//BroadcastReceiver=>commucate system and app communication alarm,battery,call
//Content Provider=>data store,pass