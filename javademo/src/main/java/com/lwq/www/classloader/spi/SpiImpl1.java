package com.lwq.www.classloader.spi;

/**
 * @author: LWQ
 * @create: 2019/11/21
 * @description: SpiImpl1
 **/
public class SpiImpl1 implements SPIService{
    @Override
    public void execute() {
        System.out.println("SpiImpl1.execute()");
    }
}
