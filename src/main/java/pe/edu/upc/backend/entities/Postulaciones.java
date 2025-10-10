package pe.edu.upc.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name="Postulaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postulaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    private boolean aceptada;
    private LocalDate fecha_postulacion;


    @ManyToOne
    @JoinColumn(name="anuncios_id")
    private Anuncios anuncios;

    @ManyToOne
    @JoinColumn(name="artists_id")
    private Artist artist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public void setAceptada(boolean aceptada) {
        this.aceptada = aceptada;
    }

    public LocalDate getFecha_postulacion() {
        return fecha_postulacion;
    }

    public void setFecha_postulacion(LocalDate fecha_postulacion) {
        this.fecha_postulacion = fecha_postulacion;
    }

    public Anuncios getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(Anuncios anuncios) {
        this.anuncios = anuncios;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
