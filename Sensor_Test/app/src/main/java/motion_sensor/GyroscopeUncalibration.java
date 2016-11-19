package motion_sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by user on 2016/11/16.
 */

public class GyroscopeUncalibration implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor gyroscopeUncalibration;
    /*
    Rate of rotation (without drift compensation) around the x axis.
    Rate of rotation (without drift compensation) around the y axis.
    Rate of rotation (without drift compensation) around the z axis.
    Estimated drift around the x axis.
    Estimated drift around the y axis.
    Estimated drift around the z axis.
    */

    private float r_r_x_axis, r_r_y_axis, r_r_z_axis;
    private float e_d_x_axis, e_d_y_axis, e_d_z_axis;
    private float[] array = new float[6];

    public GyroscopeUncalibration(Context context) {
        //Sensor Manager
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //register
        gyroscopeUncalibration = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        /*
        r_r_x_axis=event.values[0];
        r_r_y_axis=event.values[1];
        r_r_z_axis=event.values[2];
        e_d_x_axis=event.values[3];
        e_d_y_axis=event.values[4];
        e_d_z_axis=event.values[5];
        */
        array = event.values;
        Log.d(TAG,"Gyroscope_Cal="+"rx:"+event.values[0]+"ry:"+event.values[1]+"rz:"+event.values[2]);
        Log.d(TAG,"Gyroscope_Cal="+"ex:"+event.values[3]+"ey:"+event.values[4]+"ez:"+event.values[5]);
    }


    public boolean sensorCheck(){
        if(gyroscopeUncalibration != null)
            return true;
        else
            return false;
    }

    public void start() {
        mSensorManager.registerListener(this, gyroscopeUncalibration, SensorManager.SENSOR_DELAY_GAME);
    }
    //Get Sensor Data Method
    public float[] getsensorvalue(){
        return array;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onResume() {
        mSensorManager.registerListener(this, gyroscopeUncalibration, SensorManager.SENSOR_DELAY_NORMAL);
    }


    public void onPause() {
        //unregister sensor
        mSensorManager.unregisterListener(this);
    }
}
