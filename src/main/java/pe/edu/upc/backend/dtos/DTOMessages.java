package pe.edu.upc.backend.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.backend.entities.User;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DTOMessages {
    private Long id_mensajes;
    private User user;
    private String texto;
    private LocalDate fecha_envio;
}
