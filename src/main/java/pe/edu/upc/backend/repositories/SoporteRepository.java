package pe.edu.upc.backend.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.backend.entities.Soporte;

import java.time.LocalDate;
import java.util.List;

public interface SoporteRepository extends JpaRepository<Soporte, Long> {
    // 1) QUERY METHOD — listar por usuario (FK: user.id)
    List<Soporte> findByUser_Id(Long userId);

    // 2) QUERY METHOD — tickets abiertos ordenados por fecha creación DESC
    List<Soporte> findByEstadoTrueOrderByFechaCreacionDesc();

    // 3) JPQL — cerrar ticket (update + set fecha_cierre)
    @Modifying
    @Transactional
    @Query("""
           UPDATE Soporte s
           SET s.estado = false,
               s.fechaCierre = :fechaCierre
           WHERE s.idTiket = :idTicket
           """)
    int cerrarTicket(@Param("idTicket") Long idTicket,
                     @Param("fechaCierre") LocalDate fechaCierre);



}
