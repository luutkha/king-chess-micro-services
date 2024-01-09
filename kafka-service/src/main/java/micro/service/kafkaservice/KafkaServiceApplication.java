package micro.service.kafkaservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaServiceApplication.class, args);


	}
	@Bean
	NewTopic notification(){
		return new NewTopic("notification",2, (short) 1);
	}
	@Bean
	NewTopic log(){
		return new NewTopic("logger",2, (short) 1);
	}
}
