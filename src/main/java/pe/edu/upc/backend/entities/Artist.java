package pe.edu.upc.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_artist;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios")
    private User user;

    private String artist_name;
    private String main_gender;
    private String bio;
    private String city;
    private String phone;
    private LocalDate creation_date;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private List<Postulaciones> postulaciones;//para las postulaciones

    public Artist() {
    }

    public Artist(Long id_artist, User user, String artist_name, String main_gender, String bio, String city, String phone, LocalDate creation_date, String email) {
        this.id_artist = id_artist;
        this.user = user;
        this.artist_name = artist_name;
        this.main_gender = main_gender;
        this.bio = bio;
        this.city = city;
        this.phone = phone;
        this.creation_date = creation_date;
        this.email = email;
    }

    public Long getId_artist() {
        return id_artist;
    }

    public void setId_artist(Long id_artist) {
        this.id_artist = id_artist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getMain_gender() {
        return main_gender;
    }

    public void setMain_gender(String main_gender) {
        this.main_gender = main_gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Postulaciones> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(List<Postulaciones> postulaciones) {
        this.postulaciones = postulaciones;
    }
}

