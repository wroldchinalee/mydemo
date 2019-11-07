package first;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @author: LWQ
 * @create: 2019/11/5
 * @description: TestSpout
 **/
public class TestSpout extends BaseRichSpout {
    private SpoutOutputCollector spoutOutputCollector;
    private static final String field = "word";
    private int count;
    private String[] message = {
            "My nickname is xuwujing",
            "My blog address is http://www.panchengming.com/",
            "My interest is playing games"
    };


    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        System.out.println("TestSpout open:" + map.get("test"));
        this.spoutOutputCollector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        if (count < message.length) {
            System.out.println("第" + count + "次开始发送数据...");
            this.spoutOutputCollector.emit(new Values(message[count]));
        }
        count++;
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        System.out.println("定义格式...");
        outputFieldsDeclarer.declare(new Fields(field));
    }

    @Override
    public void close() {
        System.out.println("TestSpout 关闭...");
    }

    @Override
    public void ack(Object msgId) {
        System.out.println("TestSpout 确认:" + msgId);
    }

    @Override
    public void fail(Object msgId) {
        super.fail("TestSpout 失败:" + msgId);
    }
}
