package pe.edu.upc.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "soporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Soporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTiket;

    private String asunto;
    private String descripcion;
    private Boolean estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaCierre;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuarios")
    private User user;

    @Override
    public String toString() {
        return "Soporte{" +
                "idTiket=" + idTiket +
                ", asunto='" + asunto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaCierre=" + fechaCierre +
                '}';
    }
}
