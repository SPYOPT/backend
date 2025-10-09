package pe.edu.upc.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reseñas") // Cambia a "resenas" si tu tabla no usa tilde
public class Resena {

    @Id
    @Column(name = "id_reseñas") // Cambia a "id_resenas" si aplica
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Si ya tienes entidades Evento/Artista, cambia estos IDs por @ManyToOne
    @Column(name = "id_eventos")
    private Integer idEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurantes", nullable = false)
    private Restaurante restaurante;

    @Column(name = "id_artistas")
    private Integer idArtista;

    @Column(name = "puntuacion", length = 1)
    private String puntuacion;

    @Column(name = "comentario", length = 500)
    private String comentario;

    @Column(name = "fecha_resena")
    private LocalDate fechaResena;

    public Resena() {}

    /* Getters/Setters */
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdEvento() { return idEvento; }
    public void setIdEvento(Integer idEvento) { this.idEvento = idEvento; }

    public Restaurante getRestaurante() { return restaurante; }
    public void setRestaurante(Restaurante restaurante) { this.restaurante = restaurante; }

    public Integer getIdArtista() { return idArtista; }
    public void setIdArtista(Integer idArtista) { this.idArtista = idArtista; }

    public String getPuntuacion() { return puntuacion; }
    public void setPuntuacion(String puntuacion) { this.puntuacion = puntuacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDate getFechaResena() { return fechaResena; }
    public void setFechaResena(LocalDate fechaResena) { this.fechaResena = fechaResena; }
}
