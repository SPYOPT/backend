package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.backend.entities.Restaurante;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    // Restaurantes del propietario (usuario)
    List<Restaurante> findByOwner_Id(Long idUsuario);

    // Búsquedas simples
    List<Restaurante> findByNombreContainingIgnoreCase(String nombre);
    List<Restaurante> findByCiudadIgnoreCase(String ciudad);
}
