package com.example.firoz.remotepc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText etip,etmessage;
    Button shutdown, restart;
    Socket client;
    String message;
    String ip;
    int port;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etip=(EditText)findViewById(R.id.editText);
        //etmessage=(EditText)findViewById(R.id.editText3);
        shutdown=(Button)findViewById(R.id.button);
        restart=(Button)findViewById(R.id.button2);

        shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //message=etmessage.getText().toString();
                //etmessage.setText("");
                message="shutdown";
                ip=etip.getText().toString();
                port=4444;

                if(!ip.trim().equals(""))
                {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try
                            {
                                client=new Socket(ip,port);
                                DataOutputStream dout=new DataOutputStream(client.getOutputStream());
                                dout.writeUTF(message);
                                dout.flush();
                                dout.close();
                                client.close();
                            } catch (Exception e)
                            {
                                connectionFailed();
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Enter IP Address",Toast.LENGTH_SHORT).show();
                }



            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message="restart";
                ip=etip.getText().toString();
                port=4444;

                if(!ip.trim().equals(""))
                {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try
                            {
                                client=new Socket(ip,port);
                                DataOutputStream dout=new DataOutputStream(client.getOutputStream());
                                dout.writeUTF(message);
                                dout.flush();
                                dout.close();
                                client.close();
                            } catch (Exception e)
                            {
                                connectionFailed();
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Enter IP Address",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public void connectionFailed()
    {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Server Not Found!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
