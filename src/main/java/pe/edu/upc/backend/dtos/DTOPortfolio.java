package pe.edu.upc.backend.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOPortfolio {
    private Long id_portfolio;
    private Long artist_id;
    private String type;
    private String url;
    private String title;
    private LocalDate CreationDate;
}
