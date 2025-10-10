package pe.edu.upc.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOPostulacion {
    private String mensaje;
    private boolean aceptada;
    private LocalDate fecha_postulacion;
}
