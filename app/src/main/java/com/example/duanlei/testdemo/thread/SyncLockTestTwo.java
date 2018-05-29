package com.example.duanlei.testdemo.thread;

public class SyncLockTestTwo {

    boolean helloWillRun = true;
    boolean wordWillRun = false;

    public synchronized void hello() {
        /*
        如果hello()不是接下来将要运行的状态，即：!helloWillRun,那么保持等待wait()
        while用于防止线程假醒后，顺序往下执行输出功能，从而破坏交替顺序
         */
        while (!helloWillRun) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "say:Hello");
        helloWillRun = false;
        wordWillRun = true;
        this.notify();
    }

    public synchronized void world() {
        while (!wordWillRun) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "say:Word");
        helloWillRun = true;
        wordWillRun = false;
        this.notify();
    }

}
