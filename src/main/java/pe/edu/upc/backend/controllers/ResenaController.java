package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.DTOResena;
import pe.edu.upc.backend.services.ResenaService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @PostMapping
    public ResponseEntity<DTOResena> create(@RequestBody DTOResena dto) {
        DTOResena created = resenaService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOResena> findById(@PathVariable Integer id) {
        DTOResena dto = resenaService.findByIdDTO(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DTOResena>> findAll() {
        List<DTOResena> list = resenaService.findAllDTO();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOResena> update(@PathVariable Integer id, @RequestBody DTOResena dto) {
        dto.setId(id);
        DTOResena updated = resenaService.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        resenaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

