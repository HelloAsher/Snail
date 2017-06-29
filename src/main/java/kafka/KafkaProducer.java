package kafka;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

public class KafkaProducer {
    public static void main(String[] args) {
        final String topic = "mytopic";
        Properties properties = new Properties();
        properties.put("metadata.broker.list", "MNode01:9092,WNode01:9092,WNode02:9092");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("key.serializer.class", "kafka.serializer.StringEncoder");
        properties.put("request.required.acks", "-1");
        kafka.javaapi.producer.Producer<String, String> producer =
                new kafka.javaapi.producer.Producer<String, String>(new ProducerConfig(properties));

        for (int i = 1; i <= 10; i++) {
            String key = String.valueOf(i);
            String value = "this is from producer3: " + "message" + i;
            System.out.println(key + "----" + value);
            producer.send(new KeyedMessage<String, String>(topic, key, value));
        }
    }
}
