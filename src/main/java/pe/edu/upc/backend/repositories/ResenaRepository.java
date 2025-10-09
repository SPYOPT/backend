package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.backend.entities.Resena;

import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Integer> {

    // Reseñas por restaurante
    List<Resena> findByRestaurante_IdOrderByFechaResenaDesc(Integer idRestaurante);

    // (Opcional) por evento o artista si luego mapeas esas entidades
    List<Resena> findByIdEvento(Integer idEvento);
    List<Resena> findByIdArtista(Integer idArtista);

    long countByRestaurante_Id(Integer idRestaurante);
}