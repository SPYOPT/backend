package pe.edu.upc.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_portfolio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artist")
    private Artist artist;
    private String type;
    private String url;
    private String title;
    private LocalDate CreationDate;

    public Portfolio() {
    }

    public Portfolio(Long id_portfolio, Artist artist, String type, String url, String title, LocalDate creationDate) {
        this.id_portfolio = id_portfolio;
        this.artist = artist;
        this.type = type;
        this.url = url;
        this.title = title;
        CreationDate = creationDate;
    }

    public Long getId_portfolio() {
        return id_portfolio;
    }

    public void setId_portfolio(Long id_portfolio) {
        this.id_portfolio = id_portfolio;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        CreationDate = creationDate;
    }
}
