package com.lwq.www.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: LWQ
 * @create: 2019/12/3
 * @description: CyclicBarrierDemo1
 *  * 字面意思循环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 *  * 循环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 *  * 叫做栅栏，是描述所有线程被栅栏拦住了，await方法相当于栅栏，一组线程执行快的会先调用await方法
 *  * 先进入等待状态，当都达到时，一起跳过栅栏执行（执行await方法之后），也算形象。我们可以把这个状态就叫做barrier。
 **/
public class CyclicBarrierDemo1 {

    public static void main(String[] args) throws Exception {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new TourGuideTask());
        Executor executor = Executors.newFixedThreadPool(3);
        //登哥最大牌，到的最晚
        executor.execute(new TravelTask(cyclicBarrier, "哈登", 5));
        executor.execute(new TravelTask(cyclicBarrier, "保罗", 3));
        executor.execute(new TravelTask(cyclicBarrier, "戈登", 1));
    }
}
