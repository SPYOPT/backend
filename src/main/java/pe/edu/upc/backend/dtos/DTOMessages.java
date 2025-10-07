package pe.edu.upc.backend.dtos;


import pe.edu.upc.backend.entities.User;


import java.time.LocalDate;



public class DTOMessages {
    private Long id_mensajes;
    private User user;
    private String texto;
    private LocalDate fecha_envio;
}
