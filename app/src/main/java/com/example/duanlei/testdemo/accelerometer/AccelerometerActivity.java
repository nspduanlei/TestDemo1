package com.example.duanlei.testdemo.accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.duanlei.testdemo.R;

public class AccelerometerActivity extends AppCompatActivity {

    SensorManager sensorManager;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        textView = (TextView)findViewById(R.id.accelerometer_show);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener
                ,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                ,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(sensorEventListener);
    }

    SensorEventListener sensorEventListener = new SensorEventListener() {
        // 当传感器的值发生变化时回调该方法
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            StringBuilder sb = new StringBuilder();
            sb.append("X:");
            sb.append(values[0]);
            sb.append("\nY:");
            sb.append(values[1]);
            sb.append("\nZ:");
            sb.append(values[2]);
            textView.setText(sb.toString());
        }

        // 传感器精度改变时回调此方法
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
