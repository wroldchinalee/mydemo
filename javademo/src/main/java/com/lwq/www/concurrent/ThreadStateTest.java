package com.lwq.www.concurrent;

/**
 * @author: LWQ
 * @create: 2019/11/29
 * @description: ThreadStateTest
 **/
public class ThreadStateTest {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable(false);
        Thread thread = new Thread(myRunnable);
        System.out.println(thread.getState());
        myRunnable.setFlag(true);
        thread.start();
        System.out.println(thread.getState());
        myRunnable.setSleepTime(3000);
        Thread.sleep(1000);
        System.out.println(thread.getState());
        myRunnable.setFlag(false);
        Thread.sleep(1000);
        System.out.println(thread.getState());
    }


}

class MyRunnable implements Runnable {
    private boolean flag;
    private long sleepTime = 1;

    public MyRunnable(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }
}