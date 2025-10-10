package pe.edu.upc.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "eventos")
public class Evento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_eventos")
        private Long id;

        // FK: eventos.id_anuncios -> anuncios.id_anuncios
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "id_anuncios", nullable = false)
        private Anuncios anuncio;

        // FK: eventos.id_artistas -> artists.id_artist
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "id_artistas", nullable = false, referencedColumnName = "id_artist")
        private Artist artista;

        @Column(name = "fecha")
        private LocalDate fecha;

        @Column(name = "cachet", length = 10)
        private String cachet;

        @Column(name = "realizado")
        private Boolean realizado;

        @Column(name = "fecha_creacion")
        private LocalDate fechaCreacion;

        public Evento() {}

        // Getters y Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Anuncios getAnuncio() { return anuncio; }
        public void setAnuncio(Anuncios anuncio) { this.anuncio = anuncio; }

        public Artist getArtista() { return artista; }
        public void setArtista(Artist artista) { this.artista = artista; }

        public LocalDate getFecha() { return fecha; }
        public void setFecha(LocalDate fecha) { this.fecha = fecha; }

        public String getCachet() { return cachet; }
        public void setCachet(String cachet) { this.cachet = cachet; }

        public Boolean getRealizado() { return realizado; }
        public void setRealizado(Boolean realizado) { this.realizado = realizado; }

        public LocalDate getFechaCreacion() { return fechaCreacion; }
        public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
