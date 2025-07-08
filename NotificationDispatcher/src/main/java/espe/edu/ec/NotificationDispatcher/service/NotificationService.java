package espe.edu.ec.NotificationDispatcher.service;

import espe.edu.ec.NotificationDispatcher.dto.AlertEventDTO;
import espe.edu.ec.NotificationDispatcher.model.Notification;
import espe.edu.ec.NotificationDispatcher.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(AlertEventDTO event) {
        // Simular envío de notificación por correo/SMS
        System.out.println("Enviando notificación para evento: " + event.getType());

        // Registrar la notificación en CockroachDB
        Notification notification = new Notification();
        notification.setNotificationId(UUID.randomUUID().toString());
        notification.setEventType(event.getType());
        notification.setRecipient("admin@example.com"); // Destinatario simulado
        notification.setStatus("SENT");
        notification.setTimestamp(LocalDateTime.now());

        notificationRepository.save(notification);
    }
}