package pe.edu.upc.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity

public class Notifications {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id_notificaciones;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuarios")
    private User user;
    private String mensaje;
    private String tipo_notificacion;
    private Boolean leido;
    private LocalDate fecha_notificacion;
}
