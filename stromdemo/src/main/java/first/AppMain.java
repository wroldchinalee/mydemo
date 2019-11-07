package first;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * @author: LWQ
 * @create: 2019/11/5
 * @description: AppMain
 **/
public class AppMain {
    private static final String test_spout = "test_spout";
    private static final String test_bolt = "test_bolt";
    private static final String test2_bolt = "test2_bolt";

    public static void main(String[] args) {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout(test_spout, new TestSpout(), 1);
        topologyBuilder.setBolt(test_bolt, new TestBolt(), 1).setNumTasks(1).shuffleGrouping(test_spout);
        topologyBuilder.setBolt(test2_bolt, new Test2Bolt(), 1).setNumTasks(1).fieldsGrouping(test_bolt, new Fields("count"));
        Config config = new Config();
        config.setDebug(true);
        config.put("test", "test");
        try {
            if (args != null && args.length > 0) {
                System.out.println("远程运行模式");
                StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
            } else {
                System.out.println("运行本地模式");
                LocalCluster localCluster = new LocalCluster();
                localCluster.submitTopology("word-count-demo", config, topologyBuilder.createTopology());
                Thread.sleep(20000);
                localCluster.shutdown();
            }
        } catch (AlreadyAliveException e) {
            e.printStackTrace();
        } catch (InvalidTopologyException e) {
            e.printStackTrace();
        } catch (AuthorizationException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
