package micro.app.gateway.filter;

import com.auth.utils.jwt.JWTUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

//    private final Function<String, Jws<Claims>> jwtUtilFunctionValidator;

    public CustomFilter(JWTUtil jwtUtil) {
        super(Config.class);
//        this.jwtUtilFunctionValidator = jwtUtil.buildJWTFunctionValiadtor();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();

            try {
//                Jws<Claims> claims = jwtUtilFunctionValidator.apply("");
            } catch (Exception e) {
                return this.onError(exchange);
            }

//            ServerHttpRequest newRequest = request.mutate().header(AUTHORIZATION, BEARER + decryptedAccessToken).build();
//            ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
//            return chain.filter(newExchange);
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}
