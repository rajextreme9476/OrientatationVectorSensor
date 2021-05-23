package com.rx.rxorientatationvector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import android.os.Bundle;
import android.widget.TextView;
import com.rx.orientationaidllibrary.RxOrientation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvSensorData = findViewById(R.id.tvSensorData);

        RxOrientation rxOrientation = RxOrientation.getInstance(this);
        rxOrientation.getOrientationData().observe(this, new Observer<float[]>() {
            @Override
            public void onChanged(float[] floats) {

                tvSensorData.setText("X = " + floats[0]+"\n\n\n"+"Y = " + floats[1]+"\n\n\n"+"Z = " + floats[2]);

            }
        });
    }

}