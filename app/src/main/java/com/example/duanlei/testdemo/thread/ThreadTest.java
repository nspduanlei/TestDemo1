package com.example.duanlei.testdemo.thread;

public class ThreadTest {

    public static void main(String[] args) {
        test001();
    }

    /**
     * 5个线程内部打印hello和word， hello在前，要求提供一种方法使得5个线程先全部打印
     * 出hello后再打印5个word
     */
    private static int count;

    private static synchronized void setCount() {
        System.out.println(count++);
    }

    private static void test001() {
        final SyncLockTest lockTest = new SyncLockTest();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lockTest.hello();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);
        Thread thread5 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

}
