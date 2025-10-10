package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.DTOResena;
import java.util.List;

public interface ResenaService {

    // CRUD principal (DTO in/out)
    DTOResena create(DTOResena dto);
    DTOResena findByIdDTO(Integer id);
    List<DTOResena> findAllDTO();
    DTOResena update(DTOResena dto);
    void deleteById(Integer id);

    // Consultas adicionales (opcionales)
    List<DTOResena> findByRestaurante(Integer idRestaurante);
    List<DTOResena> findByEvento(Integer idEvento);
    List<DTOResena> findByArtista(Integer idArtista);
    long countByRestaurante(Integer idRestaurante);
}

