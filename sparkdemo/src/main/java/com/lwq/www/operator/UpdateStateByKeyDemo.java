package com.lwq.www.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Seconds;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.dstream.DStream;
import scala.Function1;
import scala.Tuple2;
import scala.collection.TraversableOnce;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author: LWQ
 * @create: 2019/9/19
 * @description: UpdateStateByKeyDemo
 **/
public class UpdateStateByKeyDemo {
    public static final String HOST = "192.168.233.130";
    public static final int PORT = 9999;

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("UpdateStateByKeyDemo");

        JavaStreamingContext streamingContext = new JavaStreamingContext(sparkConf, Seconds.apply(1));

        JavaReceiverInputDStream<String> inputDStream = streamingContext.socketTextStream(HOST, PORT, StorageLevel.MEMORY_ONLY());

        JavaDStream<String> stringJavaDStream = inputDStream.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(",")).iterator();
            }
        });

        JavaPairDStream<String, Integer> word2Count1Dstream = stringJavaDStream.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<>(word, 1);
            }
        });


    }
}
