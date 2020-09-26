package com.putin.thecuber;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    Button b;
    TextView t;
    PyObject python;
    static final UUID mUUID = UUID.fromString("0000ffe2-0000-1000-8000-00805f9b34fb");
    BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
    BluetoothDevice bt = btAdapter.getRemoteDevice("3C:A3:08:91:AF:D8");
    BluetoothSocket btSocket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        b=(Button)findViewById(R.id.button);
        b.setVisibility(View.INVISIBLE);
        t=(TextView)findViewById(R.id.textView);


//        System.out.println(btAdapter.getBondedDevices());
//        Log.d("solve", String.valueOf(btAdapter.getBondedDevices()));


//        System.out.println(bt.getName());
        Log.d("solve", bt.getName());

        int counter = 0;
        do {
            try {
                btSocket = bt.createRfcommSocketToServiceRecord(mUUID);
                System.out.println(btSocket);
                btSocket.connect();
                System.out.println(btSocket.isConnected());
                Log.d("soL", String.valueOf(btSocket.isConnected()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            counter++;
        } while (!btSocket.isConnected() && counter < 3);
//        try {
//            OutputStream outputStream = btSocket.getOutputStream();
//            outputStream.write(48);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        InputStream inputStream = null;
//        try {
//            inputStream = btSocket.getInputStream();
//            inputStream.skip(inputStream.available());
//
//            for (int i = 0; i<26; i++) {
//
//                byte b = (byte) inputStream.read();
//                System.out.println((char) b);
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            btSocket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(btSocket.isConnected());

        Python py=Python.getInstance();
        python=py.getModule("Rubics_CFOP");
        Log.d("solve","Ready!");
        b.setVisibility(View.VISIBLE);
    }

    public void doo(View view){
        t.setText((String)"Computing");
        Log.d("solve","Computing...");
        Toast.makeText(this,"Computing...",LENGTH_SHORT).show();
        PyObject o=python.callAttr("ran_k","1");
        t.setText(o.toString());
        Log.d("solve",o.toString());
        Toast.makeText(this,o.toString(),LENGTH_SHORT).show();
    }

}
