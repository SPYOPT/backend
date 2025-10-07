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
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mensajes;
    @ManyToOne
    @JoinColumn(name = "id_usuarios")
    private User user;
    private String texto;
    private LocalDate fecha_envio;
}
