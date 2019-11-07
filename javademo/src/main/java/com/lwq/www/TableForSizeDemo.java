package com.lwq.www;

/**
 * @author: LWQ
 * @create: 2019/9/19
 * @description: TableForSizeDemo
 **/
public class TableForSizeDemo {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        System.out.println(tableSizeFor(0));
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
