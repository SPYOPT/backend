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

public class DTONotifications {

    private Long id_notificaciones;
    private User user;
    private String mensaje;
    private String tipo_notificacion;
    private Boolean leido;
    private LocalDate fecha_notificacion;

}
