package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.DTOReserva;
import pe.edu.upc.backend.services.ReservaService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<DTOReserva> create(@RequestBody DTOReserva dto) {
        DTOReserva created = reservaService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOReserva> findById(@PathVariable Integer id) {
        DTOReserva dto = reservaService.findByIdDTO(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DTOReserva>> findAll() {
        List<DTOReserva> list = reservaService.findAllDTO();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOReserva> update(@PathVariable Integer id, @RequestBody DTOReserva dto) {
        dto.setId(id);
        DTOReserva updated = reservaService.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        reservaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
