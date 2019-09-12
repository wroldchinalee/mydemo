package com.lwq.www;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: LWQ
 * @create: 2019/8/31
 * @description: TestCode
 **/
public class TestCode {


    static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(0);

    public static void main(String[] args) throws IOException {
        executor.schedule(new Runnable() {

            @Override
            public void run() {
                System.out.println("END");
            }

        }, 30, TimeUnit.SECONDS);
    }
}
