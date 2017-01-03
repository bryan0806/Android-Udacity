package com.example.android.homecamera;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

import com.example.android.homecamera.R;
import com.jcraft.jsch.*;
//import java.awt.*;
//import javax.swing.*;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

import static android.R.attr.host;
import static android.R.attr.password;
import static android.R.id.message;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //final String[] message = new String[1];
        Button btn = (Button) findViewById(R.id.button);
        Button exit = (Button) findViewById(R.id.button2);
        Button live = (Button) findViewById(R.id.buttonforlive);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AsyncTask<Integer,Void,Void>(){
                    @Override
                    protected Void doInBackground(Integer... params){
                        try {
                            executeRemoteCommand("osmc", "2jdilgxl", "1.34.74.162", 22);
                           // Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                           // Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_LONG).show();

                        }
                        return null;
                    }
                }.execute(1);
       // Toast.makeText(MainActivity.this, message[0],Toast.LENGTH_LONG).show();
            }
        });

       exit.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        live.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String url = "http://1.34.74.162:8081";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });


    }

        public static String executeRemoteCommand(
                String username,
                String password,
                String hostname,
                int port
                ) throws Exception {
                JSch jsch=new JSch();
                Session session = jsch.getSession(username,hostname,port);
                session.setPassword(password);

        Properties prop = new Properties();
        prop.put("StrictHostKeyChecking","no");


        session.setConfig(prop);

        session.connect();

        ChannelExec channelssh=(ChannelExec)session.openChannel("exec");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        channelssh.setOutputStream(baos);

        channelssh.setCommand("df > /home/osmc/test3.txt");
        channelssh.connect();



        channelssh.disconnect();

        return baos.toString();
    }



        }











