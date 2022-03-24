package com.nayron.kafkaecommerce;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        try(var dispatcher = new KafkaDispatcher()) {
            for (var i = 0; i < 1; i++) {
                var key = UUID.randomUUID().toString();

                var value = "1234,56767,23141";
                dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);

                String email = "Thank you for your order! We are processing your order";
                dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
            }
        }
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
