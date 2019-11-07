package com.lwq.www.singleton;

/**
 * 饿汉式
 *
 * @author: LWQ
 * @create: 2019/9/18
 * @description: Singleton01
 **/
public class Singleton01 {
    private static final Singleton01 INSTANCE = new Singleton01();

    private Singleton01() {
    }

    public static Singleton01 getInstance() {
        return INSTANCE;
    }
}
