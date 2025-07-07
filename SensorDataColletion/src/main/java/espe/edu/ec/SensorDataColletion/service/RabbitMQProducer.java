package espe.edu.ec.SensorDataColletion.service;


import espe.edu.ec.SensorDataColletion.model.SensorReading;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNewSensorReadingEvent(SensorReading sensorReading) {
        rabbitTemplate.convertAndSend("sensor-events", "new.reading", sensorReading);
    }
}
