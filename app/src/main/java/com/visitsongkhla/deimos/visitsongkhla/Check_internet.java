package com.visitsongkhla.deimos.visitsongkhla;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public class Check_internet extends AsyncTask<String,Void,Integer>{
    Context context;
    String txt1,txt2,txt3;
    public Check_internet(Context context) {
        this.context=context;
    }



    public  boolean isConnected()
    {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Service.CONNECTIVITY_SERVICE);

        if (connectivityManager!=null)
        {
            NetworkInfo info=connectivityManager.getActiveNetworkInfo();
            if (info!=null)
            {
                if (info.getState()==NetworkInfo.State.CONNECTED)
                {

                    return true;
                }
            }
        }

        return false;
    }



    @Override
    protected Integer doInBackground(String... params) {
        Integer result=0;
        try {
            Socket socket=new Socket();
            SocketAddress socketAddress=new InetSocketAddress("8.8.8.8",53);
            socket.connect(socketAddress,1500);
            socket.close();
            result=1;
        } catch (IOException e) {
            e.printStackTrace();
            result=0;
        }

        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (isConnected())
        {
            if (result==1)
            {
                InternetCheckState.setTrue();
               // Toast.makeText(context, "  internet available ", Toast.LENGTH_SHORT).show();
            }

            if(result==0)
            {
                InternetCheckState.setFalse();
                buildDialog(context).show();
               // Toast.makeText(context, " No internet available ", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            InternetCheckState.setFalse();
            buildDialog(context).show();
            //Toast.makeText(context, " No internet available ", Toast.LENGTH_SHORT).show();
        }
        super.onPostExecute(result);
    }
    public AlertDialog.Builder buildDialog(Context c){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(c.getString(R.string.IC1));
        builder.setMessage(c.getString(R.string.IC2));
        builder.setPositiveButton(c.getString(R.string.IC3), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    return builder;

    }




}
