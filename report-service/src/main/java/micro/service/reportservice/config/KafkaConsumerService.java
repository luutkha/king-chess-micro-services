package micro.service.reportservice.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "logger", groupId = "logger-consumer")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}
