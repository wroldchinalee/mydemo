package com.lwq.www.singleton;

/**
 * 静态内部类
 *
 * @author: LWQ
 * @create: 2019/9/18
 * @description: Singleton05
 **/
public class Singleton05 {
    private Singleton05() {
    }

    private static class SingletonHolder {
        private static Singleton05 INSTANCE = new Singleton05();
    }

    public static Singleton05 getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
