package com.lwq.www;

/**
 * @author: LWQ
 * @create: 2019/10/29
 * @description: StringDemo4
 **/
public class StringDemo4 {
    public static void main(String[] args) {
        String s1 = "ab" + "cd";
        String s2 = "abcd";
        System.out.println(s1 == s2);    // true

        s1 = new String("1");    // 同时会生成堆中的对象 以及常量池中1的对象，但是此时s1是指向堆中的对象的
        String s3 = s1.intern();            // 常量池中的已经存在
        s2 = "1";
        System.out.println(s1 == s2);    // false
        System.out.println(s3 == s2);
    }
}
