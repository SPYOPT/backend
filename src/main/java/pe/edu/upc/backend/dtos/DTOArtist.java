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
public class DTOArtist {
    private Long id_artist;
    private Long user_id;
    private String main_gender;
    private String artis_name;
    private String bio;
    private String City;
    private String phone;
    private String email;
    private LocalDate creation_date;
}
