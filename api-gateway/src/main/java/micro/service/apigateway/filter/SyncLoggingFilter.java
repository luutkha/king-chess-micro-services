package micro.service.apigateway.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
@Log4j2
public class SyncLoggingFilter extends AbstractGatewayFilterFactory<SyncLoggingFilter.Config> {

    public SyncLoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            logRequest(exchange);
            return chain.filter(exchange);
        };
    }

    private void logRequest(ServerWebExchange exchange) {
        log.info("Request URL: {}", exchange.getRequest().getURI());
        log.info("HTTP Method: {}", exchange.getRequest().getMethod());
    }

    public static class Config {
        // You can define configuration properties here if needed
    }
}







