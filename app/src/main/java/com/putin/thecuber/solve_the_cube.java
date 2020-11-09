package com.putin.thecuber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static android.widget.Toast.LENGTH_SHORT;

public class solve_the_cube extends AppCompatActivity {

    TextView t1,t2;
    Button b;
    PyObject python;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_the_cube);

        Intent intent =getIntent();
        String scramble=intent.getStringExtra(EXTRA_MESSAGE);

        t1=findViewById(R.id.scram);
        t1.setText(scramble);

        t2=findViewById(R.id.solve);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        Python py= Python.getInstance();
        python=py.getModule("Rubics_CFOP");

    }

    public void sol(){
        String scr=t1.getText().toString();
        PyObject o=python.callAttr("sol_k",scr);
//        Toast.makeText(this,o.toString(),LENGTH_SHORT).show();
        t2.setText(o.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.final_head, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.solve:
                sol();
                return true;
            case R.id.back:
                Intent intent = new Intent(this, Solve_Activity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
