package com.putin.thecuber;

import androidx.appcompat.app.AppCompatActivity;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;


import java.util.UUID;


import me.aflak.arduino.Arduino;
import me.aflak.arduino.ArduinoListener;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity implements ArduinoListener{

    Button b;
    TextView t;
    PyObject python;
    Arduino arduino;
    String macAddress = "3C:A3:08:91:AF:D8";
    private static final UUID UUID_DEVICE = UUID.fromString("00002a00-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_SERVICE = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_CHARACTERISTIC = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        b=findViewById(R.id.button);
        b.setVisibility(View.INVISIBLE);
        t=findViewById(R.id.textView);

        Python py=Python.getInstance();
        python=py.getModule("Rubics_CFOP");
        Log.d("solve","Ready!");
        b.setVisibility(View.VISIBLE);


        arduino=new Arduino(this);
        Button b1=findViewById(R.id.b1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        arduino.setArduinoListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        arduino.unsetArduinoListener();
        arduino.close();
    }

    @Override
    public void onArduinoAttached(UsbDevice device) {
        Toast.makeText(this, "Arduino attached", Toast.LENGTH_SHORT).show();
        arduino.open(device);
    }

    @Override
    public void onArduinoDetached() {
        Toast.makeText(this,"Arduino detached",LENGTH_SHORT).show();
    }

    @Override
    public void onArduinoMessage(byte[] bytes) {
        Toast.makeText(this,new String(bytes),LENGTH_SHORT).show();
    }

    @Override
    public void onArduinoOpened() {
        String str = "Arduino opened...";
        arduino.send(str.getBytes());
        Toast.makeText(this,"Arduino opened.",LENGTH_SHORT).show();
    }

    public void se(View view){
        arduino.send("HI".getBytes());
        Toast.makeText(this,"sending:  "+"HI",LENGTH_SHORT).show();
    }


    public void doo(View view){
        // ToDo: opens the scanning activity
        Intent intent = new Intent(this, Solve_Activity.class);
        startActivity(intent);
    }
}
