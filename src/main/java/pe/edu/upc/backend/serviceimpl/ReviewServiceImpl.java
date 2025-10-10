package pe.edu.upc.backend.serviceimpl;

import pe.edu.upc.backend.entities.Review;
import pe.edu.upc.backend.services.ReviewService;

import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    @Override
    public List<Review> list() {
        return List.of();
    }

    @Override
    public Optional<Review> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Review save(Review review) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
