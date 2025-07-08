package espe.edu.ec.NotificationDispatcher.scheduler;

import espe.edu.ec.NotificationDispatcher.service.NotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationScheduler {

    private final NotificationService notificationService;

    public NotificationScheduler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "0 */30 * * * *") // Cada 30 minutos
    public void sendLowPriorityNotifications() {
        // Lógica para agrupar y enviar notificaciones de baja prioridad
        System.out.println("Enviando notificaciones de baja prioridad...");
    }
}