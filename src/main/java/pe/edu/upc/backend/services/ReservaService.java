package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.DTOReserva;
import java.time.LocalDate;
import java.util.List;

public interface ReservaService {

    // CRUD principal (DTO in/out)
    DTOReserva create(DTOReserva dto);
    DTOReserva findByIdDTO(Integer id);
    List<DTOReserva> findAllDTO();
    DTOReserva update(DTOReserva dto);
    void deleteById(Integer id);

    // Consultas adicionales (opcionales)
    List<DTOReserva> findByRestaurante(Integer idRestaurante);
    List<DTOReserva> findByUsuario(Long idUsuario);
    List<DTOReserva> findByFechaReservaBetween(LocalDate inicio, LocalDate fin);
    boolean existsReservaActivaEnFecha(Integer idRestaurante, LocalDate fecha);
}
