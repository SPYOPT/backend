package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.backend.entities.Artist;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
    // 1. Buscar artistas por ciudad
    List<Artist> findByCity(String city);
    // 2. Buscar artistas por género principal
    List<Artist> findByMainGender(String mainGender);
    // 3. Buscar artistas por nombre (contiene texto)
    List<Artist> findByArtistNameContainingIgnoreCase(String name);
    // 4. Buscar artistas creados después de una fecha específica
    @Query("SELECT a FROM Artist a WHERE a.creation_date > :date")
    List<Artist> findArtistsCreatedAfter(java.time.LocalDate date);
}
