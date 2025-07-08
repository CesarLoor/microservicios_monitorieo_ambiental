package espe.edu.ec.SensorDataColletion.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    

    // Nombre del exchange
    public static final String SENSOR_EVENTS_EXCHANGE = "sensor-events";

    // Nombre de la cola para eventos de lecturas de sensores
    public static final String NEW_READING_QUEUE = "new.reading.queue";

    // Clave de enrutamiento para eventos de lecturas de sensores
    public static final String NEW_READING_ROUTING_KEY = "new.reading";

    @Bean
    public Queue newReadingQueue() {
        
        return new Queue(NEW_READING_QUEUE, true); // durable = true

    }

    @Bean
    public TopicExchange sensorEventsExchange() {
        return new TopicExchange(SENSOR_EVENTS_EXCHANGE);
    }

    @Bean
    public Binding bindNewReadingQueue(Queue newReadingQueue, TopicExchange sensorEventsExchange) {
        return BindingBuilder.bind(newReadingQueue)
                .to(sensorEventsExchange)
                .with(NEW_READING_ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
