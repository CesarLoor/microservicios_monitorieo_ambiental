package espe.edu.ec.SensorDataColletion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("espe.edu.ec.SensorDataColletion.model")
@EnableJpaRepositories("espe.edu.ec.SensorDataColletion.repository")
public class SensorDataColletionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorDataColletionApplication.class, args);
    }
}
