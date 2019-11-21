package com.lwq.www.classloader.spi;

/**
 * @author: LWQ
 * @create: 2019/11/21
 * @description: SpiImpl2
 **/
public class SpiImpl2 implements SPIService{
    @Override
    public void execute() {
        System.out.println("SpiImpl2 execute()");
    }
}
