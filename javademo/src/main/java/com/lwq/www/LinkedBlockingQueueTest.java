package com.lwq.www;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: LWQ
 * @create: 2019/11/11
 * @description: LinkedBlockingQueueTest
 **/
public class LinkedBlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(3);
        queue.put("1");
        queue.put("2");
        queue.put("3");
        System.out.println("full");
        queue.put("4");
        System.out.println("are you ok?");
    }
}
