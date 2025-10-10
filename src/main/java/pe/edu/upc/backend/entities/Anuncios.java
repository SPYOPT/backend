package pe.edu.upc.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Anuncios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anuncios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String Descripcion;
    private String genero_buscado;
    private LocalDate fecha_evento;
    private String ubicacion;
    private float presupuesto;
    private boolean activo;
    private LocalDate fecha_creacion;

    @ManyToOne
    @JoinColumn(name="id_restaurantes")
    private Restaurante restaurante;

    @Column(name = "id_eventos")
    private Long idEvento;

    @JsonIgnore
    @OneToMany(mappedBy = "anuncios", fetch = FetchType.EAGER)
    private List<Postulaciones> postulaciones;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getDescripcion() {return Descripcion;}
    public void setDescripcion(String descripcion) {Descripcion = descripcion;}

    public String getGenero_buscado() {return genero_buscado;}
    public void setGenero_buscado(String genero_buscado) {this.genero_buscado = genero_buscado;}

    public LocalDate getFecha_evento() {return fecha_evento;}
    public void setFecha_evento(LocalDate fecha_evento) {this.fecha_evento = fecha_evento;}

    public String getUbicacion() {return ubicacion;}
    public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}

    public float getPresupuesto() {return presupuesto;}
    public void setPresupuesto(float presupuesto) {this.presupuesto = presupuesto;}

    public boolean isActivo() {return activo;}
    public void setActivo(boolean activo) {this.activo = activo;}

    public LocalDate getFecha_creacion() {return fecha_creacion;}
    public void setFecha_creacion(LocalDate fecha_creacion) {this.fecha_creacion = fecha_creacion;}

    public Restaurante getRestaurante() {return restaurante;}
    public void setRestaurante(Restaurante restaurante) {this.restaurante = restaurante;}

    public List<Postulaciones> getPostulaciones() {return postulaciones;}
    public void setPostulaciones(List<Postulaciones> postulaciones) {this.postulaciones = postulaciones;}

    public Long getIdEvento() {return idEvento;}
    public void setIdEvento(Long idEvento) {this.idEvento = idEvento;}
}
