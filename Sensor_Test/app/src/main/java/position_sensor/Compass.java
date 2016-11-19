package position_sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


/**
 * Created by spyua on 2016/11/15.
 */

public class Compass implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor ecompass;
    private float x_axis, y_axis, z_axis;

    public Compass(Context context) {
        //Sensor Manager

        /*
        SensorManager lets you access the device's sensors. Get an instance of this class
        by calling Context.getSystemService() with the argument SENSOR_SERVICE.
         */
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        //register
        /*
        TYPE_MAGNETIC_FIELD:A constant describing a magnetic field sensor type.
        */
        ecompass = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void start(){
        /*
        SENSOR_DELAY_GAME:rate suitable for games
        SENSOR_DELAY_UI:rate suitable for the user interface
        SENSOR_DELAY_FASTEST:get sensor data as fast as possible
         */
        mSensorManager.registerListener(this, ecompass, SensorManager.SENSOR_DELAY_GAME);

    }


    public boolean sensorCheck(){
        if(ecompass != null)
            return true;
        else
            return false;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        /*
        Units of measure:μT
        SensorEvent.values[0]	Geomagnetic field strength along the x axis.
        SensorEvent.values[1]	Geomagnetic field strength along the y axis.
        SensorEvent.values[2]	Geomagnetic field strength along the z axis.
        */
        x_axis=event.values[0];
        y_axis=event.values[1];
        z_axis=event.values[2];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes
    }


    public void onResume() {
        mSensorManager.registerListener(this, ecompass, SensorManager.SENSOR_DELAY_NORMAL);
    }


    public void onPause() {
        //unregister sensor
        mSensorManager.unregisterListener(this);
    }

}
