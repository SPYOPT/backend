package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.entities.Postulaciones;
import pe.edu.upc.backend.services.PostulacionesServices;


import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/postulacion")
public class PostulacionController {
    @Autowired
    PostulacionesServices postulacionesServices;

    @PostMapping
    public ResponseEntity<Postulaciones> add(@RequestBody Postulaciones postulaciones) {
        if (postulaciones.getAnuncios()==null || postulaciones.getArtist() == null) {
            return null;
        }

        Postulaciones newPostulacion = postulacionesServices.add(postulaciones);
        return new ResponseEntity<>(newPostulacion, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Postulaciones>> listAll(){
        List<Postulaciones> exhibitions = postulacionesServices.listAll();
        return new ResponseEntity<>(exhibitions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postulaciones> findById(@PathVariable("id") Long id){
        Postulaciones foundPostulacion = postulacionesServices.findById(id);
        if(foundPostulacion == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundPostulacion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        postulacionesServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
