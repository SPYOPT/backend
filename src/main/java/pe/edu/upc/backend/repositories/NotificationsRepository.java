package pe.edu.upc.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.backend.entities.Notifications;


public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
}
