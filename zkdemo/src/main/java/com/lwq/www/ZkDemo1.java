package com.lwq.www;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * @author: LWQ
 * @create: 2019/9/12
 * @description: ZkDemo1
 **/
public class ZkDemo1 {
    private static final String zkServers = "192.168.233.130:2181";

    public static void main(String[] args) throws IOException, InterruptedException {
        connect();
    }

    public static ZooKeeper connect() throws IOException, InterruptedException {
        int sessionTimeout = 30000;
        // 创建ZooKeeper对象时就会与zk服务器连接
        ZooKeeper zooKeeper = new ZooKeeper(zkServers, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
            }
        });
        Thread.sleep(2000);
        String msg = MessageFormat.format("Connection Status:{0}", zooKeeper.getState());
        System.out.println(msg);
        return zooKeeper;
    }
}
