package com.example.user.sensor_test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import motion_sensor.Accelerometer;
import motion_sensor.Gyroscope;
import motion_sensor.GyroscopeUncalibration;
import position_sensor.Compass;

public class MainActivity extends AppCompatActivity {

    private Accelerometer accelerometer;
    private Gyroscope gyroscope;
    private GyroscopeUncalibration gyroscopeUncalibration;
    private Compass compass;

    private float[] ac_array = new float[3];
    private float[] gy_array = new float[3];
    private float[] gyc_array = new float[6];


    TextView acceleor_w_txt,gyroscope_w_txt,compass_w_txt,cgyroscope_w_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutinit();
        sensor_star();

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {   //確定按下退出鍵
            //Close Sensor
            accelerometer.onPause();
            gyroscope.onPause();
            gyroscopeUncalibration.onPause();
            compass.onPause();

            MainActivity.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void layoutinit(){
        acceleor_w_txt = (TextView)findViewById(R.id.accelerometer_work_txt);
        gyroscope_w_txt = (TextView)findViewById(R.id.gyroscope_work_txt);
        compass_w_txt = (TextView)findViewById(R.id.compass_work_txt);
        cgyroscope_w_txt = (TextView)findViewById(R.id.cgyroscope_work_txt);
    }


    private void sensor_star(){
        //New
        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);
        gyroscopeUncalibration = new GyroscopeUncalibration(this);
        compass = new Compass(this);

        //Accelerometer Sensor Star
        accelerometer.start();
        if(accelerometer.sensorCheck()==true){
            acceleor_w_txt.setText("WORK");
            acceleor_w_txt.setTextColor(getResources().getColor(R.color.green));
        }
        else{
            acceleor_w_txt.setText("NO SENSOR");
            acceleor_w_txt.setTextColor(getResources().getColor(R.color.red));
        }
        //Gyroscope Sensor Star
        gyroscope.start();
        if(gyroscope.sensorCheck()==true){
            gyroscope_w_txt.setText("WORK");
            gyroscope_w_txt.setTextColor(getResources().getColor(R.color.green));
        }
        else{
            gyroscope_w_txt.setText("NO SENSOR");
            gyroscope_w_txt.setTextColor(getResources().getColor(R.color.red));
        }
        //E-Compass Sensor Star
        compass.start();
        if(compass.sensorCheck()==true){
            compass_w_txt.setText("WORK");
            compass_w_txt.setTextColor(getResources().getColor(R.color.green));
        }
        else{
            compass_w_txt.setText("NO SENSOR");
            compass_w_txt.setTextColor(getResources().getColor(R.color.red));
        }

        gyroscopeUncalibration.start();
        if(gyroscopeUncalibration.sensorCheck()==true){
            cgyroscope_w_txt.setText("WORK");
            cgyroscope_w_txt.setTextColor(getResources().getColor(R.color.green));
        }
        else{
            cgyroscope_w_txt.setText("NO SENSOR");
            cgyroscope_w_txt.setTextColor(getResources().getColor(R.color.red));
        }

    }

}
