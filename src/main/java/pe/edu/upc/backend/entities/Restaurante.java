package pe.edu.upc.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @Column(name = "id_restaurantes")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // recomendado Long

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false, referencedColumnName = "id")
    private User owner; // FK correcto a users.id

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @Column(length = 100)
    private String ciudad;

    @Column(name = "aforo_mesas")
    private Integer aforoMesas;

    @Column(length = 9)
    private String telefono;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(length = 255)
    private String email;

    // Relaciones (asegúrate que en las otras entidades el campo se llame 'restaurante')
    @JsonIgnore
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resena> resenas;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> reservas;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anuncios> anuncios;

    public Restaurante() {}

    /* Getters/Setters */
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public Integer getAforoMesas() { return aforoMesas; }
    public void setAforoMesas(Integer aforoMesas) { this.aforoMesas = aforoMesas; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Resena> getResenas() { return resenas; }
    public void setResenas(List<Resena> resenas) { this.resenas = resenas; }

    public List<Evento> getReservas() { return reservas; }
    public void setReservas(List<Evento> reservas) { this.reservas = reservas; }

    public List<Anuncios> getAnuncios() { return anuncios; }
    public void setAnuncios(List<Anuncios> anuncios) { this.anuncios = anuncios; }
}
