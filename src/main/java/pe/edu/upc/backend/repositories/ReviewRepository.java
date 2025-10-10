package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.backend.entities.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
    // 1. Buscar reseñas por artista
    List<Review> findByArtist_IdArtist(Long artistId);

    // 2. Buscar reseñas con una puntuación específica
    List<Review> findByPunctuation(String punctuation);

    // 3. Buscar reseñas con texto que contenga cierta palabra
    List<Review> findByCommentContainingIgnoreCase(String keyword);

    // 4. Promedio de puntuaciones por artista
    @Query("SELECT AVG(CAST(r.punctuation AS double)) FROM Review r WHERE r.artist.id_artist = :artistId")
    Double averageScoreByArtist(Long artistId);

}
