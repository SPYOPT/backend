package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.backend.entities.Messages;

import java.time.LocalDate;
import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    //buscar un mensaje de un usuario en especifco
    @Query("SELECT m FROM Messages m WHERE m.user.id = :userId")
    List<Messages> findMessagesByUserId(@Param("userId") Long userId);

    @Query("SELECT m FROM Messages m WHERE m.fecha_envio = :fecha")
    List<Messages> findByFechaEnvio(@Param("fecha") LocalDate fecha);

    @Query("SELECT m FROM Messages m WHERE m.user.id = :userId AND m.fecha_envio BETWEEN :startDate AND :endDate")
    List<Messages> findMessagesByUserAndDateRange(@Param("userId") Long userId,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);

    @Query("SELECT m FROM Messages m WHERE m.user.id = :userId ORDER BY m.fecha_envio DESC LIMIT :limit")
    List<Messages> findRecentMessagesByUser(@Param("userId") Long userId,
                                            @Param("limit") int limit);
}
