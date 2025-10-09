package pe.edu.upc.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoporteDTO {
    private Long idTiket;
    private String asunto;
    private String descripcion;
    private Boolean estado;           // true = abierto, false = cerrado
    private LocalDate fechaCreacion;
    private LocalDate fechaCierre;

    // FK plano
    private Long userId;
}
