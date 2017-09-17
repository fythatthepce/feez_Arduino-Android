package com.example.feez.feeopencv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class vision1 extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    JavaCameraView javaCameraView;
    private static final String TAG="vision1";
    Mat mRgba,mRgbaF,mRgbaT;
    Mat mGray,mGrayF,mGrayT;

    static {
        System.loadLibrary("MyOpencvLibs");
    }


    BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch(status){
                case BaseLoaderCallback.SUCCESS:{
                    javaCameraView.enableView();
                    break;
                }
                default:{
                    super.onManagerConnected(status);
                    break;
                }
            }
            super.onManagerConnected(status);
        }
    };


    /*
    static {
        if(OpenCVLoader.initDebug()){
            Log.d(TAG,"Opencv Install !!!");
        }
        else{
            Log.d(TAG,"Opencv Not Install");
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision1);

        javaCameraView = (JavaCameraView)findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
    } //MAIN


    @Override
    protected void onPause(){
        super.onPause();
        if(javaCameraView!=null){
            javaCameraView.disableView();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(javaCameraView!=null){
            javaCameraView.disableView();
        }
    }


    @Override
    protected void onResume(){
        super.onResume();

        if(OpenCVLoader.initDebug()){
            Log.d(TAG,"Opencv Install !!!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        else{
            Log.d(TAG,"Opencv Not Install");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_3_0,this,mLoaderCallback);
        }

    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height,width, CvType.CV_8UC4);
        mRgbaF = new Mat(height, width, CvType.CV_8UC4);
        mRgbaT = new Mat(width, width, CvType.CV_8UC4);

        mGray = new Mat(width, width, CvType.CV_8UC1);
        mGrayF = new Mat(height, width, CvType.CV_8UC1);
        mGrayT = new Mat(width, width, CvType.CV_8UC1);

    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();

        /*
        // Rotate mRgba 90 degrees
        Core.transpose(mRgba, mRgbaT);
        Imgproc.resize(mRgbaT, mRgbaF, mRgbaF.size(), 0,0, 0);
        Core.flip(mRgbaF, mRgba, 1 );
        return mRgba; // This function must return
        */

        OpencvNativeClass.convertGray(mRgba.getNativeObjAddr(),mGray.getNativeObjAddr());

        // Rotate mRgba 90 degrees
        Core.transpose(mGray, mGrayT);
        Imgproc.resize(mGrayT, mGrayF, mGrayF.size(), 0,0, 0);
        Core.flip(mGrayF, mGray, 1 );
        return  mGray;

    }

}//SCOPE
