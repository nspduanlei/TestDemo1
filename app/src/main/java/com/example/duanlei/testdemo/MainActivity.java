package com.example.duanlei.testdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.ndklib.NdkTest;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tv_test);

        textView.setText(NdkTest.sayHelloWorld());

        int[] testData = new int[]{1,2,3,4,5};
        NdkTest.operationArray2(testData);

        for (int i = 0; i < 5; i++) {
            Log.e("testDl", "----------" + testData[i]);
        }
    }

}
