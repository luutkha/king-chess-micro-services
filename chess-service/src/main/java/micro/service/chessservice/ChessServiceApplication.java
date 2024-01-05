package micro.service.chessservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("micro") // Add the package to scan here
public class ChessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChessServiceApplication.class, args);
    }

}
