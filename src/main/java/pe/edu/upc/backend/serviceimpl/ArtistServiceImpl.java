package pe.edu.upc.backend.serviceimpl;

import pe.edu.upc.backend.entities.Artist;
import pe.edu.upc.backend.services.ArtistService;

import java.util.List;
import java.util.Optional;

public class ArtistServiceImpl implements ArtistService {

    @Override
    public List<Artist> list() {
        return List.of();
    }

    @Override
    public Optional<Artist> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Artist save(Artist artist) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
