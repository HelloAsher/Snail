package kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Asher on 2017/1/16.
 */
public class KafkaConsumer {
    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "MNode01:2181,WNode01:2181,WNode02:2181");
        properties.put("group.id", "jd-group");
        properties.put("zookeeper.session.timeout.ms", "4000");
        properties.put("zookeeper.sync.time.ms", "200");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "smallest");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");

        kafka.javaapi.consumer.ConsumerConnector consumer =
                kafka.consumer.Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));


        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("mytopic", new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap =
                consumer.createMessageStreams(topicCountMap,keyDecoder,valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get("mytopic").get(0);
        ConsumerIterator<String, String> it = stream.iterator();

        while (it.hasNext())
            System.out.println(it.next().message());

    }
}
