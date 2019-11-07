package com.lwq.www;

/**
 * @author: LWQ
 * @create: 2019/10/28
 * @description: StringDemo3
 **/
public class StringDemo3 {
    public static void main(String[] args) {
        String str1 = new String("1") + new String("1");
        str1.intern();
        String str2 = "11";
        System.out.println(str1 == str2);

//        String s1 = new String("zxy");    // 结论3
//        s1.intern(); // 常量池非空，返回值是常量池里的内容
//        String s2 = "zxy"; // 常量池非空，返回值是常量池里的内容
//        System.out.println(s1 == s2); //false
//        System.out.println(s1.intern() == s2); // true

    }
}
