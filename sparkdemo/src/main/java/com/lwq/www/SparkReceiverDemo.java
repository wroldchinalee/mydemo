package com.lwq.www;

import org.apache.spark.SparkConf;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

/**
 * @author: LWQ
 * @create: 2019/9/3
 * @description: SparkReceiverDemo
 **/
public class SparkReceiverDemo {
    public static void main(String[] args) throws InterruptedException {
        SparkConf sparkConf = new SparkConf().setAppName("receiver-job").setMaster("local[2]");
        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Duration.apply(5000));
        MysqlReceiver mysqlReceiver = new MysqlReceiver(StorageLevel.MEMORY_ONLY());
        JavaReceiverInputDStream<String> stringDStream = jssc.receiverStream(mysqlReceiver);
        stringDStream.print();
        jssc.start();
        jssc.awaitTermination();
        jssc.stop();
    }
}
