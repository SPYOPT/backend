package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.backend.entities.Postulaciones;

public interface PostulacionesRepository extends JpaRepository<Postulaciones, Long> {
}
