package espe.edu.ec.NotificationDispatcher.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue alertEventsQueue() {
        return new Queue("alert-events.queue", true);
    }
}
