package com.rx.orientationaidllibrary;

import androidx.lifecycle.LifecycleService;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.lifecycle.MutableLiveData;

public class OrientationSensorData extends LifecycleService implements SensorEventListener {

    private static int SENSOR_DELAY_MICROS = 8 * 1000;// 8ms
    private SensorManager sensorManager;
    private Sensor rotationSensor;
    public static MutableLiveData<float[]> sensorData = new MutableLiveData<float[]>();

    @Override
    public IBinder onBind(Intent intent) {
         super.onBind(intent);
         return binder;
    }

    private final OrientationSensorInterface.Stub binder = new OrientationSensorInterface.Stub() {
        @Override
        public String orientation() throws RemoteException {
            createSensorManager();
            return String.valueOf(sensorData.getValue());
        }

    };


    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
            sensorData.setValue(event.values);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void createSensorManager() {
        if (sensorManager == null) {
            //
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            sensorManager.registerListener(this, rotationSensor,
                    SENSOR_DELAY_MICROS);
        }
    }


}
