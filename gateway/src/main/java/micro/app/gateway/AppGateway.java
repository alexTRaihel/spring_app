package micro.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.auth"})
public class AppGateway {
    public static void main(String[] args) {
        SpringApplication.run(AppGateway.class, args);
    }
}
