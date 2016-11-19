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

public class Accelerometer implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor accelerometer;
    //Acceleration force along the x axis (including gravity).
    //Acceleration force along the y axis (including gravity)
    //Acceleration force along the z axis (including gravity).
    private float a_f_x_axis, a_f_y_axis, a_f_z_axis;
    private float[] array = new float[3];

    public Accelerometer(Context context) {
        //Sensor Manager
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //register
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        /*
        a_f_x_axis=event.values[0];
        a_f_y_axis=event.values[1];
        a_f_z_axis=event.values[2];
        */
        array = event.values;
        Log.d(TAG,"Accelerometer="+"x:"+event.values[0]+"y:"+event.values[1]+"z:"+event.values[2]);
    }


    public boolean sensorCheck(){
        if(accelerometer != null)
            return true;
        else
            return false;
    }

    public void start() {
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    //Get Sensor Data Method
    public float[] getsensorvalue(){
        return array;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onResume() {
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    public void onPause() {
        //unregister sensor
        mSensorManager.unregisterListener(this);
    }

}
