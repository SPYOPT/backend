package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.SoporteDTO;
import pe.edu.upc.backend.entities.Soporte;

import java.time.LocalDate;
import java.util.List;

public interface SoporteService {
    // CRUD base (misma idea que MajorService)
    SoporteDTO add(SoporteDTO soporteDTO);

    Soporte edit(Soporte soporte);

    void delete(Long id);

    Soporte findById(Long id);

    List<Soporte> listAll();

    // Extras alineados a tus queries y necesidades del backend
    List<Soporte> listByUserId(Long userId);

    List<Soporte> listOpenOrderByFechaCreacionDesc();

    int closeTicket(Long ticketId, LocalDate fechaCierre);

}
