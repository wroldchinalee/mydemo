package com.lwq.www.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: LWQ
 * @create: 2019/12/3
 * @description: CyclicBarrierDemo2
 * 字面意思循环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 循环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 * 叫做栅栏，是描述所有线程被栅栏拦住了，await方法相当于栅栏，一组线程执行快的会先调用await方法
 * 先进入等待状态，当都达到时，一起跳过栅栏执行（执行await方法之后），也算形象。我们可以把这个状态就叫做barrier。
 *
 **/
public class CyclicBarrierDemo2 {
    public static void main(String[] args) throws InterruptedException{

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println("汇总已分别计算出的两个员工的工资"));

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("计算出员工1的工资");
            try {
                cyclicBarrier.await();
                System.out.println("员工1的工资计算完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("计算出员工2的工资");
            try {
                cyclicBarrier.await();
                System.out.println("员工2的工资计算完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }, "thread2");


        thread1.start();
        thread2.start();

        System.out.println("====end====");
    }
}
