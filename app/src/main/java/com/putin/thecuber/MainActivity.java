package com.putin.thecuber;

import androidx.appcompat.app.AppCompatActivity;


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


import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    Button b;
    TextView t;
    PyObject python;
    Physicaloid mPhysicaloid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        b=(Button)findViewById(R.id.button);
        b.setVisibility(View.INVISIBLE);
        t=(TextView)findViewById(R.id.textView);

        Python py=Python.getInstance();
        python=py.getModule("Rubics_CFOP");
        Log.d("solve","Ready!");
        b.setVisibility(View.VISIBLE);

        mPhysicaloid=new Physicaloid(this);

        mPhysicaloid.setBaudrate(9600);

        if(mPhysicaloid.open()) { 	// tries to connect to device and if device was connected

            // read listener, When new data is received from Arduino add it to Text view
            mPhysicaloid.addReadListener(new ReadLisener() {
                @Override
                public void onRead(int size) {
                    byte[] buf = new byte[size];
                    mPhysicaloid.read(buf, size);
                    tvAppend(t, Html.fromHtml("<font color=blue>" + new String(buf) + "</fon                     t>")); 		// add data to text viiew
                }
            });

        } else {
            //Error while connecting
            Toast.makeText(this, "Cannot open", Toast.LENGTH_LONG).show();
        }

    }

    Handler mHandler = new Handler();
    private void tvAppend(TextView tv, CharSequence text) {
        final TextView ftv = tv;
        final CharSequence ftext = text;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                ftv.append(ftext); 	// add text to Text view
            }
        });
    }

    public void doo(View view){
        t.setText((String)"Computing");
        Log.d("solve","Computing...");
        Toast.makeText(this,"Computing...",LENGTH_SHORT).show();
        PyObject o=python.callAttr("ran_k","1");
        t.setText(o.toString());
        Log.d("solve",o.toString());
        Toast.makeText(this,o.toString(),LENGTH_SHORT).show();
        String str= o.toString()+"\r\n";
        if(str.length()>0) {
            byte[] buf = str.getBytes();	//convert string to byte array
            mPhysicaloid.write(buf, buf.length);	//write data to arduino
        }
    }

}