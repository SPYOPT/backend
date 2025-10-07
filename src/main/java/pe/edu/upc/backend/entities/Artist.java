package pe.edu.upc.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_artist;
    private int id_users;
    private String artist_name;
    private String main_gender;
    private String bio;
    private String city;
    private String phone;
    private LocalDateTime creation_date;
    private String email;

    public Artist(int id_artist, int id_users, String artist_name, String main_gender, String bio, String city, String phone, LocalDateTime creation_date, String email) {
        this.id_artist = id_artist;
        this.id_users = id_users;
        this.artist_name = artist_name;
        this.main_gender = main_gender;
        this.bio = bio;
        this.city = city;
        this.phone = phone;
        this.creation_date = creation_date;
        this.email = email;
    }

    public int getId_artist() {
        return id_artist;
    }

    public void setId_artist(int id_artist) {
        this.id_artist = id_artist;
    }

    public int getId_users() {
        return id_users;
    }

    public void setId_users(int id_users) {
        this.id_users = id_users;
    }

    public String getMain_gender() {
        return main_gender;
    }

    public void setMain_gender(String main_gender) {
        this.main_gender = main_gender;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
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

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id_artist=" + id_artist +
                ", id_users=" + id_users +
                ", artist_name='" + artist_name + '\'' +
                ", main_gender='" + main_gender + '\'' +
                ", bio='" + bio + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", creation_date=" + creation_date +
                ", email='" + email + '\'' +
                '}';
    }
}
