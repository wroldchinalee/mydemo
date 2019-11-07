package first;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: LWQ
 * @create: 2019/11/5
 * @description: Test2Bolt
 **/
public class Test2Bolt extends BaseRichBolt {
    private HashMap<String, Integer> counts = null;
    private long count = 1;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        System.out.println("Test2Bolt prepare:" + map.get("test"));
        this.counts = new HashMap(16);
    }

    @Override
    public void execute(Tuple tuple) {
        String msg = tuple.getStringByField("count");
        System.out.println("第" + count + "次统计单词出现的次数");
        if (!counts.containsKey(msg)) {
            counts.put(msg, 1);
        } else {
            counts.put(msg, counts.get(msg) + 1);
        }
        count++;
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    }

    @Override
    public void cleanup() {
        System.out.println("===============开始显示单词数量===============");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("===============结束显示单词数量===============");
        System.out.println("Test2Bolt的资源释放");
    }
}
