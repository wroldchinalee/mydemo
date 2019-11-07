package com.lwq.www.singleton;

/**
 * 懒汉式
 *
 * @author: LWQ
 * @create: 2019/9/18
 * @description: Singleton02
 **/
public class Singleton02 {
    private static Singleton02 instance;

    private Singleton02() {
    }

    public static Singleton02 getInstance() {
        if (instance == null) {
            instance = new Singleton02();
        }
        return instance;
    }
}
