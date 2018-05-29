package com.example.duanlei.testdemo.handler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

public class TestActivity extends Activity {

    Handler handler1;
    Handler handler2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler1 = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler2 = new Handler();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = 1;
                Bundle bundle = new Bundle();
                bundle.putString("data", "data");
                message.setData(bundle);
                handler1.sendMessage(message);
            }
        });

        //几种在子线程进行ui操作的方法
        new Thread(new Runnable() {
            @Override
            public void run() {

                //1
                handler1.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                //2
                View view = new View(TestActivity.this);
                view.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

                //3
                TestActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

            }
        });
    }

    //标准的异步消息处理线程写法
    class LooperThread extends Thread {
        public Handler mHandler;

        @SuppressLint("HandlerLeak")
        public void run() {
            Looper.prepare();

            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    //
                }
            };

            Looper.loop();
        }
    }
}
