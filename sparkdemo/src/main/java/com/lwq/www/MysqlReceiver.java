package com.lwq.www;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;

import java.math.BigDecimal;
import java.sql.*;
import java.util.concurrent.*;

/**
 * @author: LWQ
 * @create: 2019/9/3
 * @description: MysqlReceiver
 **/
public class MysqlReceiver extends Receiver<String> {
    private ExecutorService executorService;
    private String url = "jdbc:mysql://192.168.233.130/canal";
    private String username = "root";
    private String password = "root";
    private Connection connection;

    public MysqlReceiver(StorageLevel storageLevel) {
        super(storageLevel);
    }

    @Override
    public void onStart() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("mysql-receiver-pool-%d").build();
        executorService = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                threadFactory);
        executorService.execute(this::receive);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void receive() {
        while (!isStopped()) {
            try {
                if (connection == null) {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection(url, username, password);
                }
                String sql = "select * from userinfo";
                Statement stmt = connection.createStatement();    //创建一个statement对象
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String info = rs.getString("info");
                    BigDecimal cash = rs.getBigDecimal("cash");
                    UserInfo userInfo = new UserInfo(id, name, info, cash);
                    String json = JSON.toJSONString(userInfo);
                    store(json);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStop() {
        if (executorService != null) {
            executorService.shutdownNow();
        }
    }
}
