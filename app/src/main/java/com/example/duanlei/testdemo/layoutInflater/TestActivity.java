package com.example.duanlei.testdemo.layoutInflater;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.duanlei.testdemo.R;

public class TestActivity extends Activity {

    private LinearLayout mainLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test002);

        mainLayout = findViewById(R.id.main_layout);

        //获取 LayoutInflater 实例
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        LayoutInflater layoutInflater1 = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View buttonLayout = layoutInflater.inflate(R.layout.button_layout, mainLayout, false);

        mainLayout.addView(buttonLayout);

        /**
        1. 如果root为null，attachToRoot将失去作用，设置任何值都没有意义。
        2. 如果root不为null，attachToRoot设为true，则会给加载的布局文件的指定一个父布局，即root。
        3. 如果root不为null，attachToRoot设为false，则会将布局文件最外层的所有layout属性进行设置，
         当该view被添加到父view当中时，这些layout属性会自动生效。
        4. 在不设置attachToRoot参数的情况下，如果root不为null，attachToRoot参数默认为true。
         **/

        View view = new View(this);
    }
}
