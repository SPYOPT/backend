package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.DTORestaurante;
import java.util.List;

public interface RestauranteService {

    // CRUD principal (DTO in/out)
    DTORestaurante create(DTORestaurante dto);
    DTORestaurante findByIdDTO(Integer id);
    List<DTORestaurante> findAllDTO();
    DTORestaurante update(DTORestaurante dto);
    void deleteById(Integer id);

    // Consultas adicionales (opcionales)
    List<DTORestaurante> findByOwner(Long idUsuario);
    List<DTORestaurante> searchByNombre(String nombre);
    List<DTORestaurante> findByCiudad(String ciudad);
}
