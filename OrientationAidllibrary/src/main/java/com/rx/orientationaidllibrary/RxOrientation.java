package com.rx.orientationaidllibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class RxOrientation {

    Context context;
    private Intent orientationServiceIntent;
    private static RxOrientation rxOrientation = null;
    public static MutableLiveData<float[]> sensorData = new MutableLiveData<float[]>();
    private OrientationSensorInterface orientationInterface;


    private RxOrientation(Context context){
        this.context = context;
       // addSensorDataObserver();
        bindOrientationService(context);
    }

    public static RxOrientation getInstance(Context context){
        if(rxOrientation == null){
            rxOrientation = new RxOrientation(context);
        }
        return rxOrientation;
    }

    public MutableLiveData<float[]> getOrientationData()
    {
        return OrientationSensorData.sensorData ;
    }

    private void addSensorDataObserver()
    {
        OrientationSensorData.sensorData.observe((LifecycleOwner) context.getApplicationContext(), new Observer<float[]>() {
            @Override
            public void onChanged(float[] floats) {
                sensorData.setValue(floats);
                Log.d("","X = " + floats[0]+"\n\n"+"Y = " + floats[1]+"\n\n"+"Z = " + floats[2]);
            }
        });
    }

    private void bindOrientationService(Context context) {

        orientationServiceIntent = new Intent(context, OrientationSensorData.class);
        ServiceConnection mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName className, IBinder binder) {
                orientationInterface = OrientationSensorInterface.Stub.asInterface(binder);
                try {
                    getDataFromAIDL();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onServiceDisconnected(ComponentName arg0) {

            }
        };
        context.bindService(orientationServiceIntent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private void getDataFromAIDL() throws Exception {
        Log.d("00",orientationInterface.orientation().toString());
    }
}
