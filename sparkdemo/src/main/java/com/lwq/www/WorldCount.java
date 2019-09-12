package com.lwq.www;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Seconds;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author: LWQ
 * @create: 2019/8/30
 * @description: WorldCount
 **/
public class WorldCount {
    public static void main(String[] args) throws InterruptedException {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[2]").setAppName("WorldCount");

        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Seconds.apply(1));

        JavaReceiverInputDStream<String> dStream = jssc.socketTextStream("192.168.233.130", 9999);

        JavaDStream<String> wordsDStream = dStream.flatMap((FlatMapFunction<String, String>) s -> Arrays.asList(s.split(",")).iterator());

        JavaPairDStream<String, Integer> wordPairDStream = wordsDStream.mapToPair((PairFunction<String, String, Integer>) word -> new Tuple2<>(word, 1));
        JavaPairDStream<String, Integer> wordCountDStream = wordPairDStream.reduceByKey((Function2<Integer, Integer, Integer>) (count1, count2) -> count1 + count2);

        wordCountDStream.print();
        jssc.start();
        jssc.awaitTermination();
        jssc.stop();
    }
}
