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
public class DTOReview {
    private Long id_review;
    private Long artist_id;
    private String punctuation;
    private String comment;
    private LocalDate reviewDate;

}
