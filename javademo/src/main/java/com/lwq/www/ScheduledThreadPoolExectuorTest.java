package com.lwq.www;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: LWQ
 * @create: 2019/10/16
 * @description: ScheduledThreadPoolExectuorTest
 **/
public class ScheduledThreadPoolExectuorTest {
    public static int count = 0;

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("scheduled-pool-%d").build());

        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (count > 2) {
                    invokeA(null);
                } else {
                    invokeA(count + "");
                }
                count = count + 1;
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    public static void invokeA(String str) {
        System.out.println(Integer.valueOf(str));
    }
}
