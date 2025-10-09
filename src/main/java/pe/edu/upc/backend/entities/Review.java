package pe.edu.upc.backend.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_review;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_artist")
    private Artist artist;
    private String punctuation;
    private String comment;
    private LocalDate reviewDate;

    public Review() {
    }

    public Review(Long id_review, Artist artist, String punctuation, String comment, LocalDate reviewDate) {
        this.id_review = id_review;
        this.artist = artist;
        this.punctuation = punctuation;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public Long getId_review() {
        return id_review;
    }

    public void setId_review(Long id_review) {
        this.id_review = id_review;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(String punctuation) {
        this.punctuation = punctuation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
