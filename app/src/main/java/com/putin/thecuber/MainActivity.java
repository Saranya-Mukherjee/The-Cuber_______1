package com.putin.thecuber;

import androidx.appcompat.app.AppCompatActivity;


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
import com.physicaloid.lib.Physicaloid;
import com.physicaloid.lib.usb.driver.uart.ReadLisener;


import java.util.Arrays;

import me.aflak.arduino.Arduino;
import me.aflak.arduino.ArduinoListener;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity implements ArduinoListener {

    Button b;
    TextView t;
    PyObject python;
    Physicaloid mPhysicaloid;
    Arduino arduino;

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
        arduino.send("1".getBytes());
        Toast.makeText(this,"sending"+ Arrays.toString("1".getBytes()),LENGTH_SHORT).show();
    }

    public void doo(View view){
        t.setText((String)"Computing");
        Log.d("solve","Computing...");
        Toast.makeText(this,"Computing...",LENGTH_SHORT).show();
        PyObject o=python.callAttr("ran_k","1");
        t.setText(o.toString());
        Log.d("solve",o.toString());
        Toast.makeText(this,o.toString(),LENGTH_SHORT).show();
        arduino.send(o.toString().getBytes());
    }
}
