package espe.edu.ec.EnvironmentalAnalyzer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue newReadingQueue() {
        return new Queue("new.reading.queue", true);
    }
}
