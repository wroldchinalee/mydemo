package first;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * @author: LWQ
 * @create: 2019/11/5
 * @description: TestBolt
 **/
public class TestBolt extends BaseRichBolt {
    private OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        System.out.println("TestBolt prepare:" + map.get("test"));
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String msg = tuple.getStringByField("word");
        System.out.println("开始分割单词:" + msg);
        String[] words = msg.toLowerCase().split(" ");
        for (String word : words) {
            this.outputCollector.emit(new Values(word));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("count"));
    }

    @Override
    public void cleanup() {
        System.out.println("TestBolt的资源释放");
    }
}
