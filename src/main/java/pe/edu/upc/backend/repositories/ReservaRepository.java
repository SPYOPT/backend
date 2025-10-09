package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.backend.entities.Reserva;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    // Por restaurante / usuario
    List<Reserva> findByRestaurante_Id(Integer idRestaurante);
    List<Reserva> findByUsuario_Id(Long idUsuario);

    // Rango de fechas
    List<Reserva> findByFechaReservaBetween(LocalDate inicio, LocalDate fin);

    // Chequeos de disponibilidad (ejemplo)
    boolean existsByRestaurante_IdAndFechaReservaAndEstadoTrue(Integer idRestaurante, LocalDate fecha);
}

