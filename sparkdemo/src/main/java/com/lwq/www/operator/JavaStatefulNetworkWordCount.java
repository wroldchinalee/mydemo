package com.lwq.www.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.*;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.Optional;
import org.apache.spark.streaming.Seconds;
import org.apache.spark.streaming.State;
import org.apache.spark.streaming.StateSpec;
import org.apache.spark.streaming.api.java.*;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * @author: LWQ
 * @create: 2019/9/19
 * @description: JavaStatefulNetworkWordCount
 **/
public class JavaStatefulNetworkWordCount {
    public static final String HOST = "192.168.233.130";
    public static final int PORT = 9999;

    public static void main(String[] args) throws InterruptedException {
        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("JavaStatefulNetworkWordCount");
        JavaStreamingContext javaStreamingContext = new JavaStreamingContext(sparkConf, Seconds.apply(3));
        javaStreamingContext.checkpoint(".//checkpoint");
        JavaReceiverInputDStream<String> inputDStream = javaStreamingContext.socketTextStream(HOST, PORT);
        List<Tuple2<String, Integer>> list = Arrays.asList(new Tuple2<>("hello", 2), new Tuple2<>("world", 3));
        JavaPairRDD<String, Integer> initialRdd = javaStreamingContext.sparkContext().parallelizePairs(list);

        Function3<String, Optional<Integer>, State<Integer>, Tuple2<String, Integer>> mappingFunc = (word, one, state) -> {
            int sum = one.orElse(0) + (state.exists() ? state.get() : 0);
            Tuple2<String, Integer> output = new Tuple2<>(word, sum);
            state.update(sum);
            return output;
        };
        JavaDStream<String> wordDstream = inputDStream.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
        JavaPairDStream<String, Integer> word2Count1Dstream = wordDstream.mapToPair(word -> new Tuple2<>(word, 1));

        JavaMapWithStateDStream<String, Integer, Integer, Tuple2<String, Integer>> stateDstream = word2Count1Dstream.mapWithState(StateSpec.function(mappingFunc).initialState(initialRdd));
        stateDstream.print();
        javaStreamingContext.start();
        javaStreamingContext.awaitTermination();
        javaStreamingContext.stop();
    }
}
