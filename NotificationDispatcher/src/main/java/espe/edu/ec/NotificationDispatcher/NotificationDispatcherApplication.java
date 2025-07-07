package espe.edu.ec.NotificationDispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // Habilita el registro en Eureka Server
public class NotificationDispatcherApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationDispatcherApplication.class, args);
    }
}