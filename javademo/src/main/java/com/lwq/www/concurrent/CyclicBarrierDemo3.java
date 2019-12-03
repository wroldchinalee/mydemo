package com.lwq.www.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: LWQ
 * @create: 2019/12/3
 * @description: CyclicBarrierDemo3
 **/
public class CyclicBarrierDemo3 {
    public static void main(String[] args) {
        final int N = 4;
        CalcTask[] calcTasks = new CalcTask[N];
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new MergeTask(calcTasks));
        for (int i = 0; i < N; i++) {
            int[] numbers = {i + 1};
            calcTasks[i] = new CalcTask(cyclicBarrier, numbers);
            new Thread(calcTasks[i]).start();
        }


    }

    static class CalcTask implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private int[] numbers;
        private int total;

        public CalcTask(CyclicBarrier cyclicBarrier, int[] numbers) {
            this.cyclicBarrier = cyclicBarrier;
            this.numbers = numbers;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "开始计算数组");
            if (numbers == null || numbers.length == 0) {
                total = 0;
            } else {
                for (int i = 0; i < numbers.length; i++) {
                    total += numbers[i];
                }
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "计算数组完毕");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public int getTotal() {
            return total;
        }
    }

    static class MergeTask implements Runnable {
        private CalcTask[] tasks;

        public MergeTask(CalcTask[] tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            System.out.println("开始合并计算数据");
            int total = 0;
            if (tasks != null) {
                for (int i = 0; i < tasks.length; i++) {
                    total += tasks[i].getTotal();
                }
            }
            System.out.println("合并后结果：" + total);
        }
    }
}
