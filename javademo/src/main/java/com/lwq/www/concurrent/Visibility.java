package com.lwq.www.concurrent;

/**
 * @author: LWQ
 * @create: 2019/12/3
 * @description: Visibility
 **/
public class Visibility {
    private int i;

    public Visibility(int i) {
        this.i = i;
    }

    public void add10K() {
        for (int j = 0; j < 10000; j++) {
            i++;
        }
    }

    public int getI() {
        return i;
    }

    public static void main(String[] args) throws InterruptedException {
        Visibility visibility = new Visibility(0);
        Thread thread = new Thread(visibility::add10K);
        System.out.println(visibility.getI());
        thread.start();
        Thread.sleep(1L);
        System.out.println(visibility.getI());
        Thread.sleep(1000L);
        System.out.println(visibility.getI());
    }
}
