package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.DTORestaurante;
import pe.edu.upc.backend.services.RestauranteService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @PostMapping
    public ResponseEntity<DTORestaurante> create(@RequestBody DTORestaurante dto) {
        DTORestaurante created = restauranteService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTORestaurante> findById(@PathVariable Integer id) {
        DTORestaurante dto = restauranteService.findByIdDTO(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DTORestaurante>> findAll() {
        List<DTORestaurante> list = restauranteService.findAllDTO();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTORestaurante> update(@PathVariable Integer id, @RequestBody DTORestaurante dto) {
        dto.setId(id);
        DTORestaurante updated = restauranteService.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        restauranteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
