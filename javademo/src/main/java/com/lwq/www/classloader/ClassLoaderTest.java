package com.lwq.www.classloader;


/**
 * @author: LWQ
 * @create: 2019/11/20
 * @description: ClassLoaderTest
 **/
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println("ClassLoaderTest.getClass().getClassLoader():"
        + ClassLoaderTest.class.getClassLoader());

        try {
            Class.forName("com.lwq.www.classloader.ClassLoaderTest", true,
                    ClassLoaderTest.class.getClassLoader().getParent());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
