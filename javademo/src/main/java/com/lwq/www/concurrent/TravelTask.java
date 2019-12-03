package com.lwq.www.concurrent;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: LWQ
 * @create: 2019/12/3
 * @description: TravelTask
 * 旅行线程
 **/
public class TravelTask implements Runnable {

    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    private CyclicBarrier cyclicBarrier;
    private String name;
    private int arriveTime;//赶到的时间

    public TravelTask(CyclicBarrier cyclicBarrier, String name, int arriveTime) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
        this.arriveTime = arriveTime;
    }

    @Override
    public void run() {
        try {
            //模拟达到需要花的时间
            Thread.sleep(arriveTime * 1000);
            System.out.println(name + timeFormatter.format(LocalDateTime.now()) + "到达集合点");
            // 相当于栅栏，等待所有线程都达到后，继续执行后面
            // 如果cyclicBarrier传入了Runnable参数，将先执行Runnable对象的run方法
            // 执行之后在继续执行每个线程await方法之后的代码
            cyclicBarrier.await();
            System.out.println(name + "开始旅行啦～～");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}