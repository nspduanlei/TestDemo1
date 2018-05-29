package com.example.duanlei.testdemo.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyView001 extends View {
    public MyView001(Context context) {
        this(context, null, 0);
    }

    public MyView001(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView001(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;

    private final String alphabet = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int height = getHeight();
        int width = getWidth();
        int aSize = alphabet.length();

        for (int i = 0; i < aSize; i++) {
            canvas.drawText(String.valueOf(alphabet.charAt(i)), width/2, (height/aSize) * (i+1),
                    mPaint);
        }

    }
}
