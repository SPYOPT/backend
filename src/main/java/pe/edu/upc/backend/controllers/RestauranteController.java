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

    // CREATE
    @PostMapping
    public ResponseEntity<DTORestaurante> create(@RequestBody DTORestaurante dto) {
        DTORestaurante created = restauranteService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<DTORestaurante> findById(@PathVariable Long id) { // <-- Long
        DTORestaurante dto = restauranteService.findByIdDTO(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<DTORestaurante>> findAll() {
        List<DTORestaurante> list = restauranteService.findAllDTO();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<DTORestaurante> update(@PathVariable Long id,      // <-- Long
                                                 @RequestBody DTORestaurante dto) {
        dto.setId(id);
        DTORestaurante updated = restauranteService.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {              // <-- Long
        restauranteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ---------------- Opcionales (si quieres exponer las búsquedas del service) ----------------

    // Restaurantes por propietario
    @GetMapping("/owner/{userId}")
    public ResponseEntity<List<DTORestaurante>> findByOwner(@PathVariable Long userId) {
        return new ResponseEntity<>(restauranteService.findByOwner(userId), HttpStatus.OK);
    }

    // Buscar por nombre (contains, ignore case)
    @GetMapping("/search")
    public ResponseEntity<List<DTORestaurante>> searchByNombre(@RequestParam String nombre) {
        return new ResponseEntity<>(restauranteService.searchByNombre(nombre), HttpStatus.OK);
    }

    // Filtrar por ciudad (ignore case)
    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<DTORestaurante>> findByCiudad(@PathVariable String ciudad) {
        return new ResponseEntity<>(restauranteService.findByCiudad(ciudad), HttpStatus.OK);
    }
}
