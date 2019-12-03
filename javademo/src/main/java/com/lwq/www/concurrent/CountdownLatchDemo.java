package com.lwq.www.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author: LWQ
 * @create: 2019/12/3
 * @description: CountdownLatchDemo
 * 中文直译为倒计时门栓，创建对象时传入一个倒计时数，通过倒计时为0，线程继续运行
 * 比如有
 * 一个任务 A，它要等待其他 2个任务执行完毕之后才能执行，此时就可以利用 CountDownLatch
 * 来实现这种功能了。
 **/
public class CountdownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println("子线程:" + Thread.currentThread().getName() + "正在执行");
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程:" + Thread.currentThread().getName() + "执行完毕");
                countDownLatch.countDown();
            }).start();
        }
        System.out.println("等待两个子线程执行完毕");
        countDownLatch.await();
        System.out.println("两个子线程执行完毕");
        System.out.println("继续执行主线程");
    }

}
