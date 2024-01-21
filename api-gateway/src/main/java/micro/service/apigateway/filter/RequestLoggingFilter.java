package micro.service.apigateway.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class RequestLoggingFilter implements GlobalFilter, Ordered {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send("logger", msg);
    }


    // Only valid request logged
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        sendMessage(exchange.getRequest().getURI().toString());
        // Log request details synchronously
        log.info("Request to GLOBAL: {} {}", request.getMethod(), request.getURI());

        // Continue with the filter chain
        return chain.filter(exchange)
                .doOnTerminate(() -> {
                    // Log response details after the completion of the filter chain
                    log.info("Response status: {}", response.getStatusCode());
                });
    }

    @Override
    public int getOrder() {
        // Set the order to execute this filter
        return Ordered.HIGHEST_PRECEDENCE;

    }
}
