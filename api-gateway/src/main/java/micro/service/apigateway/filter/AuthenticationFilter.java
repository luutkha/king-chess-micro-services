package micro.service.apigateway.filter;


import lombok.extern.log4j.Log4j2;
import micro.service.apigateway.config.WebClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
@Log4j2
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    public static final String LB_AUTH_SERVICE_VALIDATE_TOKEN = "lb://AUTH-SERVICE//auth/validate?token=";
    @Autowired
    private RouteValidator validator;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClientConfig webClient;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send("logger", msg);
    }

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.error(exchange.getRequest().getURI());
            log.error(exchange.getRequest().getMethod());
            sendMessage(exchange.getRequest().getURI().toString());

            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                    ServerHttpResponse response = exchange.getResponse();
//                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//
//                    String errorMessage = "Missing authorization header";
//                    byte[] bytes = errorMessage.getBytes();
//                    DataBuffer buffer = response.bufferFactory().wrap(bytes);
//                    return response.writeWith(Flux.just(buffer));
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    //REST call to AUTH service
                    log.info("VALIDATE TOKEN");
                    String url = LB_AUTH_SERVICE_VALIDATE_TOKEN + authHeader;
                    log.info(url);

                    return webClient.webClientBuilder().build().get()
                            .uri(url)
                            .exchangeToMono(response -> {
                                HttpStatusCode statusCode = response.statusCode();
                                if (statusCode.is2xxSuccessful()) {
                                    return chain.filter(exchange);
                                } else {
                                    log.error("REQUEST ERROR ON TOKEN");
                                    exchange.getResponse().setStatusCode(statusCode);
                                    return exchange.getResponse().setComplete();
                                }
                            });

                } catch (Exception e) {
                    log.error("invalid access...!");
                    throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT);
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}