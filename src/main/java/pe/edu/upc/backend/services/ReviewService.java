package pe.edu.upc.backend.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.backend.entities.Review;

import java.util.List;
import java.util.Optional;

@Service
public interface ReviewService {
    List<Review> list();
    Optional<Review> findById(long id);
    Review save(Review review);
    void delete(Long id);
}
