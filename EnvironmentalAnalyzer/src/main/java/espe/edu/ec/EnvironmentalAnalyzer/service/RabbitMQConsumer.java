package espe.edu.ec.EnvironmentalAnalyzer.service;

import espe.edu.ec.EnvironmentalAnalyzer.dto.NewSensorReadingEventDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private AlertService alertService;

    @RabbitListener(queues = "new.reading.queue")
    public void handleNewSensorReadingEvent(NewSensorReadingEventDTO event) {
        // Analizar el evento y generar alertas si es necesario
        alertService.analyzeAndGenerateAlert(event);
    }
}