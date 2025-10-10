package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.backend.entities.Portfolio;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,Long> {
    // 1. Buscar portafolios por artista
    List<Portfolio> findByArtist_IdArtist(Long artistId);
    // 2. Buscar por título (contiene texto)
    List<Portfolio> findByTitleContainingIgnoreCase(String title);
    // 3. Contar cuántos portafolios tiene un artista
    @Query("SELECT COUNT(p) FROM Portfolio p WHERE p.artist.id_artist = :artistId")
    Long countByArtistId(Long artistId);
    // 4. Obtener los más recientes (ordenados por fecha)
    List<Portfolio> findAllByOrderByCreationDateDesc();
}
