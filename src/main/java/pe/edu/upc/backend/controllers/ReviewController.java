package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.entities.Review;
import pe.edu.upc.backend.services.ReviewService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/reviews")
@CrossOrigin()
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> list() {
        return reviewService.list();
    }

    @GetMapping("/{id}")
    public Optional<Review> getById(@PathVariable("id") Long id) {
        return reviewService.findById(id);
    }

    @PostMapping
    public Review create(@RequestBody Review review) {
        return reviewService.save(review);
    }

    @PutMapping("/{id}")
    public Review update(@PathVariable("id") Long id, @RequestBody Review review) {
        review.setId_review(id);
        return reviewService.save(review);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        reviewService.delete(id);
    }
}