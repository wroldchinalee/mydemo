package com.lwq.www.singleton;

/**
 * 枚举类方式
 * @author: LWQ
 * @create: 2019/9/18
 * @description: Singleton04
 **/
public enum  Singleton04 {
    // 单例对象
    INSTANCE;

    public void doSomething(){
        System.out.println("doSomething");
    }

    public static void main(String[] args) {
        Singleton04.INSTANCE.doSomething();
    }
}
