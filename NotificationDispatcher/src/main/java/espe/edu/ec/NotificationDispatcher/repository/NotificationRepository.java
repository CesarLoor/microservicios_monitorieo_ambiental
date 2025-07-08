package espe.edu.ec.NotificationDispatcher.repository;

import espe.edu.ec.NotificationDispatcher.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}