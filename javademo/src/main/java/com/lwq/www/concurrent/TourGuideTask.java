package com.lwq.www.concurrent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: LWQ
 * @create: 2019/12/3
 * @description: TourGuideTask
 * 导游线程，都到达目的地时，发放护照和签证
 */
public class TourGuideTask implements Runnable {
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    @Override
    public void run() {
        System.out.println("****导游" + timeFormatter.format(LocalDateTime.now()) + "分发护照签证****");
        try {
            //模拟发护照签证需要2秒
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
