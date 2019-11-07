package com.lwq.www.jmockit;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: LWQ
 * @create: 2019/11/6
 * @description: ConstructorAndBlockMockingTest
 **/

//Mock构造函数&初始代码块
public class ConstructorAndBlockMockingTest {
    // AnOrdinaryClassWithBlock的MockUp类，继承MockUp即可
    public /*static*/ class AnOrdinaryClassWithBlockMockUp extends MockUp<AnOrdinaryClassWithBlock> {
        // Mock构造函数和初始代码块, 函数名$init就代表类的构造函数
        @Mock
        public void $init(int i) {
            System.out.println("abc");
        }

        // Mock静态初始代码块,, 函数名$init就代表类的静态代码块
        @Mock
        public void $clinit() {
        }
    }

    @Test
    public void testClassMockingByMockUp() {
        new AnOrdinaryClassWithBlockMockUp();
//        AnOrdinaryClassWithBlock instance = new AnOrdinaryClassWithBlock(10);
        AnOrdinaryClassWithBlock instance = AnOrdinaryClassWithBlock.getInstance(10);
        // 静态初始代码块被mock了
        Assert.assertTrue(AnOrdinaryClassWithBlock.j == 0);
        // 构造函数和初始代码块被mock
        Assert.assertTrue(instance.getI() == 0);
    }

}
