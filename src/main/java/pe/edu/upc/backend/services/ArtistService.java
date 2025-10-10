package pe.edu.upc.backend.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.backend.entities.Artist;

import java.util.List;
import java.util.Optional;


@Service
public interface ArtistService {
    List<Artist> list();
    Optional<Artist> findById(long id);
    Artist save(Artist artist);
    void delete(long id);

}
