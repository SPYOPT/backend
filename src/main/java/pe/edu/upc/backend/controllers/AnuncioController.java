package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.entities.Anuncios;
import pe.edu.upc.backend.services.AnunciosServices;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/anuncios")
public class AnuncioController {
    @Autowired
    AnunciosServices anunciosServices;

    @PostMapping
    public ResponseEntity<Anuncios> add(@RequestBody Anuncios anuncios) {
        if (anuncios.getTitulo()==null || anuncios.getTitulo().isBlank()) {
            return null;
        }

        Anuncios newAnuncio = anunciosServices.add(anuncios);
        return new ResponseEntity<>(newAnuncio, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Anuncios>> listAll(){
        List<Anuncios> exhibitions = anunciosServices.listAll();
        return new ResponseEntity<>(exhibitions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anuncios> findById(@PathVariable("id") Long id){
        Anuncios foundAnuncio = anunciosServices.findById(id);
        if(foundAnuncio == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundAnuncio, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        anunciosServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
