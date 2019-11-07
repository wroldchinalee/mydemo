package com.lwq.www;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author: LWQ
 * @create: 2019/10/14
 * @description: SchedulerManager
 **/
public class SchedulerManager {
    private int coreSize;
    private int a;
    private int b;
    private int c;
    private ScheduledExecutorService service;
    private ScheduledExecutorService service2;
    private ScheduledExecutorService service3;

    public SchedulerManager(int coreSize) {
        this.coreSize = coreSize;
        this.a = coreSize;
        this.b = coreSize;
        this.c = coreSize;
        service = new ScheduledThreadPoolExecutor(getCorePoolSize(), new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(false).build());
        service2 = new ScheduledThreadPoolExecutor(getCorePoolSize(), new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(false).build());
        service3 = new ScheduledThreadPoolExecutor(getCorePoolSize(), new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(false).build());
    }

    private int getCorePoolSize() {
        System.out.println(coreSize);
        return coreSize;
    }
}
