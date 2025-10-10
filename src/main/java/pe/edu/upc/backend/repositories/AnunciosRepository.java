package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.backend.entities.Anuncios;

import java.util.List;

public interface AnunciosRepository extends JpaRepository<Anuncios, Long> {
    List<Anuncios> findByTitulo(String titulo);
}
