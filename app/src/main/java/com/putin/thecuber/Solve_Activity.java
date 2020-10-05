package com.putin.thecuber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import static android.widget.Toast.LENGTH_SHORT;
import static java.lang.String.valueOf;

public class Solve_Activity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{

    PyObject python;
    JavaCameraView cam;
    Button bu;
    CheckBox c;
    String s="";
    Mat a,b=new Mat(),g=new Mat(),r=new Mat(),o=new Mat(),y=new Mat(),w=new Mat();
    BaseLoaderCallback baseLoaderCallback=new BaseLoaderCallback(Solve_Activity.this){
        @Override
        public void onManagerConnected(int status){
            switch(status){
                case BaseLoaderCallback.SUCCESS: {
                    cam.enableView();
                    break;
                }
                default: {
                    super.onManagerConnected(status);
                }
            }
        }
    };

    static{

        if(OpenCVLoader.initDebug()){

        }
        else{

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve);

        cam=(JavaCameraView)findViewById(R.id.camera);
        cam.setVisibility(SurfaceView.VISIBLE);
        cam.setCvCameraViewListener(this);
        bu=findViewById(R.id.button2);
        c=findViewById(R.id.side);

        Python py=Python.getInstance();
        python=py.getModule("Rubics_CFOP");


    }



    public void sh(View view){
        Toast.makeText(this,bu.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void sol(View view){
        PyObject o=python.callAttr("ran_k","1");
        Toast.makeText(this,o.toString(),LENGTH_SHORT).show();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        a=new Mat(height,width, CvType.CV_8UC4);

    }

    @Override
    public void onCameraViewStopped() {
        a.release();
        b.release();
        g.release();
        r.release();
        o.release();
        y.release();
        w.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        a=inputFrame.rgba();
        Mat h=new Mat();
        Imgproc.cvtColor(a,h,Imgproc.COLOR_RGB2HSV);
        Core.inRange(h,new Scalar(95,220,170), new Scalar(103,255,255),b);
        Core.inRange(h,new Scalar(44,175,150), new Scalar(73,255,255),g);
        Core.inRange(h,new Scalar(12,180,216), new Scalar(21,255,255),o);
        Core.inRange(h,new Scalar(0,175,200), new Scalar(10,255,255),y);
        Core.inRange(h,new Scalar(170,170,0), new Scalar(255,255,255),r);
        Core.add(r,y,r);
        Core.inRange(h,new Scalar(27,160,170), new Scalar(34,255,255),y);
//        Core.copyTo(a, t);
        Core.add(b,g,a);
        Core.add(a,o,a);
        Core.add(a,y,a);
        Core.add(a,r,a);

        double u1=0.0,u2=0.0,u3=0.0,u4=0.0;
        int flag=0;
        for(int i=0;i<a.rows();i+=20){
            for(int j=0;j<a.cols();j+=20){
                if(a.get(i,j)[0]==255.0){
                    u1=i;u2=j;
                    flag=1;
                    break;
                }
            }
            if(flag==1){
                break;
            }
        }

        flag=0;
        for(int i=a.rows()-1;i>0;i-=20){
            for(int j=a.cols()-1;j>0;j-=20){
                if(a.get(i,j)[0]==255.0){
                    u3=i;u4=j;
                    flag=1;
                    break;
                }
            }
            if(flag==1){
                break;
            }
        }

        int size= (int) ((Math.sqrt(Math.pow((u3-u1),2)+Math.pow((u4-u2),2)))/(Math.sqrt(2)));
        int x=(int)u1+(size/6);
        int z=(int)u2+(size/6);
        flag=0;
        for(int i=0;i<3;i++){
            z=(int)u2+(size/6);
            for(int j=0;j<3;j++){
                if((x>a.rows()-1)||(z>a.cols()-1)){
                    flag=1;
                    break;
                }
                if(r.get(x,z)[0]==255.0) {
                    s += "r";
                }
                else if(g.get(x,z)[0]==255.0) {
                    s+= "g";
                }
                else if(b.get(x,z)[0]==255.0){
                    s+= "b";
                }
                else if(o.get(x,z)[0]==255.0){
                    s+= "o";
                }
                else if(y.get(x,z)[0]==255.0){
                    s+= "y";
                }
                else{
                    s+="w";
                }
                Log.d("uniq"," "+x+" "+z+" "+size+" "+u1+" "+u2+" "+a.rows()+" "+a.cols());
                z+=(size/3);
            }
            if(flag==1){
                break;
            }
            s+=" ";
            x+=(size/3);
        }
        Log.d("uniq","================");
        bu.setText(s);
        h.release();
        System.gc();
        s="";
        return a;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(cam!=null){
            cam.disableView();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
//        if(cam!=null){
//            cam.disableView();
//        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(OpenCVLoader.initDebug()){
            baseLoaderCallback.onManagerConnected(BaseLoaderCallback.SUCCESS);
        }
        else{
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION,this,baseLoaderCallback);
        }
    }

}
