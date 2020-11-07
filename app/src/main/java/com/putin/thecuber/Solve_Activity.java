package com.putin.thecuber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
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
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static android.widget.Toast.LENGTH_SHORT;
import static java.lang.String.valueOf;

public class Solve_Activity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{

    PyObject python;
    JavaCameraView cam;
    TextView state;
    Button bu;
    Button sid;
    String s="";
    public static String colours[] ={"","","","","",""};
    String[] side={"top","left","front","right","back","bot"};
    int current =0;
    Mat a,b=new Mat(),g=new Mat(),r=new Mat(),o=new Mat(),y=new Mat(),copy=new Mat(),w=new Mat();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.solve_head, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sol:
                for(int i = 0; i <6;i++){
                    if(colours[i].equals("")){
                        Toast.makeText(this,"NOT YET FULL....",LENGTH_SHORT).show();
                        return true;
                    }
                }
                String loaded="";

                for(int a=0;a<6;a++){
                    loaded+=colours[a]+" ";
                }

                Intent intent= new Intent(this,solve_the_cube.class);
                intent.putExtra(EXTRA_MESSAGE,loaded);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
        sid=findViewById(R.id.res);
        state=findViewById(R.id.textView2);


//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//        Python py=Python.getInstance();
//        python=py.getModule("Rubics_CFOP");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

   public void sh(View view){

        String loaded="";

        if(current==5){
            colours[current++]=bu.getText().toString().substring(bu.getText().toString().indexOf(':')+1);
            for(int a=0;a<6;a++){
                loaded+=colours[a]+" ";
            }
            bu.setVisibility(View.GONE);
            state.setVisibility(View.VISIBLE);
            state.setText(loaded);
            Toast.makeText(this,loaded, Toast.LENGTH_LONG).show();
            return;
        }
        if(current>5) {
            for(int a=0;a<6;a++){
                loaded+=colours[a]+" ";
            }
            Toast.makeText(this, "DONE!!!\n"+loaded, Toast.LENGTH_SHORT).show();
            return;
        }
       colours[current++]=bu.getText().toString().substring(bu.getText().toString().indexOf(':')+1);
       for(int a=0;a<6;a++){
           loaded+=colours[a]+" ";
       }
       Toast.makeText(this,loaded, Toast.LENGTH_LONG).show();
    }

    public void reset(View view){
        for(int i=0;i<6;i++){
            colours[i]="";
        }
        current =0;
        bu.setVisibility(View.VISIBLE);
        state.setVisibility(View.GONE);
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
        copy.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        a=inputFrame.rgba();
        a.copyTo(copy);
        Mat h=new Mat();
        Imgproc.cvtColor(a,h,Imgproc.COLOR_RGB2HSV);
        Core.inRange(h,new Scalar(95,200,140), new Scalar(120,255,255),b);
        Core.inRange(h,new Scalar(44,175,150), new Scalar(73,255,255),g);
        Core.inRange(h,new Scalar(10,180,216), new Scalar(25,255,255),o);
        Core.inRange(h,new Scalar(0,170,160), new Scalar(10,255,255),y);
        Core.inRange(h,new Scalar(160,170,0), new Scalar(255,255,255),r);
        Core.add(r,y,r);
        Core.inRange(h,new Scalar(27,160,170), new Scalar(40,255,255),y);
        Core.inRange(h,new Scalar(0,12,200), new Scalar(255,50,255),w);
//        Core.copyTo(a, t);
        Core.add(b,g,a);
        Core.add(a,o,a);
        Core.add(a,y,a);
        Core.add(a,r,a);
        Core.add(a,w,a);
        int c1=a.rows()/2,c2=a.cols()/2;
        int u1=c1-100,u2=c2-100,u3=c1+100,u4=c2+100;
//        Imgproc.rectangle(copy,new Point(225,100),new Point(525,410),new Scalar(0,0,0),30);
//        Imgproc.rectangle(copy,new Point(u1+70,u2-155),new Point(u3+185,u4-65),new Scalar(255,255,255),3);
        int pnts[]={u1+75,u2-160,u3+190,u4-60};
        int flag;
//        for(int i=0;i<a.rows();i+=20){
//            for(int j=0;j<a.cols();j+=20){
//                if(a.get(i,j)[0]==255.0){
//                    u1=i;u2=j;
//                    flag=1;
//                    break;
//                }
//            }
//            if(flag==1){
//                break;
//            }
//        }
//
//        flag=0;
//        for(int i=a.rows()-1;i>0;i-=20){
//            for(int j=a.cols()-1;j>0;j-=20){
//                if(a.get(i,j)[0]==255.0){
//                    u3=i;u4=j;
//                    flag=1;
//                    break;
//                }
//            }
//            if(flag==1){
//                break;
//            }
//        }
        int s1=a.rows()/480,s2=a.cols()/960;
        int size= (int) ((Math.sqrt(Math.pow((u3-u1),2)+Math.pow((u4-u2),2)))/(Math.sqrt(2)));
        int size_new= (int) ((Math.sqrt(Math.pow((pnts[2]-pnts[0]),2)+Math.pow((pnts[3]-pnts[1]),2)))/(Math.sqrt(2)));
//        size*=s1;
//        u1*=s1;
//        u2*=s1;
        int x=u1+size/6;
        int x_new=pnts[0]+size_new/6;
        int z=u2+size/6;
        int z_new=pnts[1]+size_new/6;
        flag=0;
        for(int i=0;i<3;i++){
            z=u2+size/6;
            z_new=pnts[1]+size_new/6;
            for(int j=0;j<3;j++){
                if((x>a.rows()-1)||(z>a.cols()-1)){
                    flag=1;
                    break;
                }
                if(r.get(x,z)[0]==255.0) {
                    s += "r";
                    Imgproc.rectangle(copy,new Point(x_new,z_new),new Point(x_new,z_new),new Scalar(220,25,25),30);
                }
                else if(g.get(x,z)[0]==255.0) {
                    s+= "g";
                    Imgproc.rectangle(copy,new Point(x_new,z_new),new Point(x_new,z_new),new Scalar(46,220,25),30);
                }
                else if(b.get(x,z)[0]==255.0){
                    s+= "b";
                    Imgproc.rectangle(copy,new Point(x_new,z_new),new Point(x_new,z_new),new Scalar(25,150,220),30);
                }
                else if(o.get(x,z)[0]==255.0){
                    s+= "o";
                    Imgproc.rectangle(copy,new Point(x_new,z_new),new Point(x_new,z_new),new Scalar(220,135,25),30);
                }
                else if(y.get(x,z)[0]==255.0){
                    s+= "y";
                    Imgproc.rectangle(copy,new Point(x_new,z_new),new Point(x_new,z_new),new Scalar(235,240,55),30);
                }
                else if(w.get(x,z)[0]==255.0){
                    s+="w";
                    Imgproc.rectangle(copy,new Point(x_new,z_new),new Point(x_new,z_new),new Scalar(255,255,255),30);
                }
                else{
                    s+="w";
                    Imgproc.rectangle(copy,new Point(x_new,z_new),new Point(x_new,z_new),new Scalar(255,255,255),30);
                }
                Log.d("uniq"," "+x+" "+z+" "+size+" "+u1+" "+u2+" "+a.rows()+" "+a.cols());
                z+=(size/3);
                z_new+=(size_new/3);
            }
            if(flag==1){
                break;
            }
            s+=" ";
            x+=(size/3);
            x_new+=(size_new/3);
        }
        Log.d("uniq","================");
        if(current<6) {
            bu.setText(side[current] + ": " + s);
        }
        h.release();
        System.gc();
        s="";
        return copy;
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
