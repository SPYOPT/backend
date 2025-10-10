package pe.edu.upc.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.entities.Artist;
import pe.edu.upc.backend.services.ArtistService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/artist")
@CrossOrigin(origins = "*")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping
    public List<Artist> getArtists(){
        return artistService.list();
    }
    @GetMapping("/{id}")
    public Optional<Artist> getById(@PathVariable("id") Long id) {
        return artistService.findById(id);
    }

    @PostMapping
    public Artist create(@RequestBody Artist artist) {
        return artistService.save(artist);
    }

    @PutMapping("/{id}")
    public Artist update(@PathVariable("id") Long id, @RequestBody Artist artist) {
        artist.setId_artist(id);
        return artistService.save(artist);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        artistService.delete(id);
    }


}
