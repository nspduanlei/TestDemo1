package com.example.duanlei.testdemo.accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.duanlei.testdemo.R;

public class MoreSensorActivity extends AppCompatActivity {

    private SensorManager sensorManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_sensor);

        // 获取传感器管理服务
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 程序退出时 注销传感器监听器
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 程序暂停时 注销传感器监听器
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 方向传感器注册监听
        sensorManager.registerListener(sensorEventListener
                ,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)
                ,SensorManager.SENSOR_DELAY_GAME);
        // 陀螺仪传感器注册监听
        sensorManager.registerListener(sensorEventListener
                ,sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
                ,SensorManager.SENSOR_DELAY_GAME);
        // 磁场传感器注册监听
        sensorManager.registerListener(sensorEventListener
                ,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
                ,SensorManager.SENSOR_DELAY_GAME);
        // 重力传感器注册监听
        sensorManager.registerListener(sensorEventListener
                ,sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
                ,SensorManager.SENSOR_DELAY_GAME);
        // 线性加速度传感器注册监听
        sensorManager.registerListener(sensorEventListener
                ,sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
                ,SensorManager.SENSOR_DELAY_GAME);
        // 温度传感器注册监听
        sensorManager.registerListener(sensorEventListener
                ,sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
                ,SensorManager.SENSOR_DELAY_GAME);
        // 环境光传感器注册监听
        sensorManager.registerListener(sensorEventListener
                ,sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
                ,SensorManager.SENSOR_DELAY_GAME);
        // 压力传感器注册监听
        sensorManager.registerListener(sensorEventListener
                ,sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
                ,SensorManager.SENSOR_DELAY_GAME);
        // 加速度传感器注册监听
        sensorManager.registerListener(sensorEventListener
                ,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                ,SensorManager.SENSOR_DELAY_GAME);
    }

    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            int sensorType = event.sensor.getType();
            StringBuilder sb = null;
            switch (sensorType) {
                case Sensor.TYPE_ORIENTATION:
                    sb = new StringBuilder();
                    sb.append("Z:"+ values[0]);
                    sb.append("\nX:"+ values[1]);
                    sb.append("\nY:"+ values[2]);
                    break;

                case Sensor.TYPE_GYROSCOPE:
                    sb = new StringBuilder();
                    sb.append("X:"+ values[0]);
                    sb.append("\nY:" + values[1]);
                    sb.append("\nZ:" + values[2]);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    sb = new StringBuilder();
                    sb.append("X:"+ values[0]);
                    sb.append("\nY:" + values[1]);
                    sb.append("\nZ:" + values[2]);
                    break;
                case Sensor.TYPE_GRAVITY:
                    sb = new StringBuilder();
                    sb.append("Z:"+values[0]);
                    sb.append("\nY:"+values[1]);
                    sb.append("\nZ:"+values[2]);
                    break;
                case Sensor.TYPE_LINEAR_ACCELERATION:
                    sb = new StringBuilder();
                    sb.append("X:" + values[0]);
                    sb.append("\nY:" + values[1]);
                    sb.append("\nZ:" + values[2]);
                    break;
                case Sensor.TYPE_TEMPERATURE:
                    sb = new StringBuilder();
                    sb.append("Temp:" + values[0]);
                    break;
                case Sensor.TYPE_LIGHT:
                    sb = new StringBuilder();
                    sb.append("Light:" + values[0]);
                    break;
                case Sensor.TYPE_PRESSURE:
                    sb = new StringBuilder();
                    sb.append("Press:"+values[0]);
                    break;
                default:
                    break;
            }

            Log.e("test_dl", sb.toString());
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
