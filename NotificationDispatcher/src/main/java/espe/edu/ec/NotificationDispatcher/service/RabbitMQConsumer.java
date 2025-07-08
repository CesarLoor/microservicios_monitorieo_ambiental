package espe.edu.ec.NotificationDispatcher.service;


import espe.edu.ec.NotificationDispatcher.dto.AlertEventDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = "alert-events.queue")
    public void handleAlertEvent(AlertEventDTO event) {
        // Enviar notificaciones basadas en el evento recibido
        notificationService.sendNotification(event);
    }
}