package com.putin.thecuber;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;


import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {

    Button b;
    TextView t;
    PyObject python;

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