package pe.edu.upc.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UsuariosDTO {
    private Long id_usuarios;
    private String email;
    private String telefono;
    private String password;
    private LocalDate date;
}
