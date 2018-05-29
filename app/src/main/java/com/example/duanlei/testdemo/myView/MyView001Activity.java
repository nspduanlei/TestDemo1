package com.example.duanlei.testdemo.myView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.duanlei.testdemo.R;

public class MyView001Activity extends Activity {

    View testView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_view_001);

        //Invalidate
        //postInvalidate
        //requestLayout

        testView = findViewById(R.id.myView);

        testView.invalidate();
        testView.postInvalidate();
        testView.requestLayout();


    }
}
