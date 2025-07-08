package espe.edu.ec.EnvironmentalAnalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // Habilita el registro en Eureka Server
public class EnvironmentalAnalyzerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnvironmentalAnalyzerApplication.class, args);
    }
}
