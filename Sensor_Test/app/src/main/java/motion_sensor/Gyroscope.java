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

public class Gyroscope implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor gyroscope;

    //Rate of rotation around the x axis.
    //Rate of rotation around the y axis.
    //Rate of rotation around the z axis.
    private float r_r_x_axis, r_r_y_axis, r_r_z_axis;
    private float[] array = new float[3];

    public Gyroscope (Context context) {
        //Sensor Manager
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //register
        gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        /*
        r_r_x_axis=event.values[0];
        r_r_y_axis=event.values[1];
        r_r_z_axis=event.values[2];
        */
        array = event.values;
        Log.d(TAG,"Gyroscope="+"x:"+event.values[0]+"y:"+event.values[1]+"z:"+event.values[2]);
    }


    public boolean sensorCheck(){
        if(gyroscope != null)
            return true;
        else
            return false;
    }
    public void start() {
        mSensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_GAME);
    }
    //Get Sensor Data Method
    public float[] getsensorvalue(){
        return array;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onResume() {
        mSensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }


    public void onPause() {
        //unregister sensor
        mSensorManager.unregisterListener(this);
    }
}
