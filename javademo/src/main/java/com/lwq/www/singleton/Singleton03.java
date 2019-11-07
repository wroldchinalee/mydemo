package com.lwq.www.singleton;

/**
 * 双重检测方式
 * @author: LWQ
 * @create: 2019/9/18
 * @description: Singleton03
 **/
public class Singleton03 {
    private static Singleton03 instance;

    private Singleton03() {
    }

    public static Singleton03 getInstance() {
        if (instance == null) {
            synchronized (Singleton03.class) {
                if (instance == null) {
                    instance = new Singleton03();
                }
            }
        }
        return instance;
    }
}
