package pe.edu.upc.backend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.backend.entities.Notifications;

import java.time.LocalDate;
import java.util.List;


public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

    List<Notifications> findByUserId(Long userId);

    List<Notifications> findByLeido(Boolean leido);

    @Query("SELECT n FROM Notifications n WHERE n.leido = false AND n.fecha_notificacion >= :fechaInicio")
    List<Notifications> findUnreadNotificationsFromLastWeek(@Param("fechaInicio") LocalDate fechaInicio);

    @Query("SELECT n.tipo_notificacion, COUNT(n), SUM(CASE WHEN n.leido = true THEN 1 ELSE 0 END) " +
            "FROM Notifications n " +
            "WHERE n.fecha_notificacion BETWEEN :startDate AND :endDate " +
            "GROUP BY n.tipo_notificacion " +
            "ORDER BY COUNT(n) DESC")
    List<Object[]> countNotificationsByTypeAndStatus(@Param("startDate") LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate);

}
