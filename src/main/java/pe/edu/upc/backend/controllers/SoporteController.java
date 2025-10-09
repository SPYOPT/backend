package pe.edu.upc.backend.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.SoporteDTO;
import pe.edu.upc.backend.entities.Soporte;
import pe.edu.upc.backend.services.SoporteService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc") // http://localhost:8080/upc
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    // ---------- LISTAR ----------
    @GetMapping("/soportes")
    public ResponseEntity<List<Soporte>> listAll() {
        return new ResponseEntity<>(soporteService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/soportes/user/{userId}")
    public ResponseEntity<List<Soporte>> listByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(soporteService.listByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/soportes/open")
    public ResponseEntity<List<Soporte>> listOpen() {
        return new ResponseEntity<>(soporteService.listOpenOrderByFechaCreacionDesc(), HttpStatus.OK);
    }


    // ---------- CREAR ----------
    @PostMapping("/soportes")
    public ResponseEntity<SoporteDTO> add(@RequestBody SoporteDTO dto) {
        SoporteDTO saved = soporteService.add(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ---------- ACTUALIZAR ----------
    @PutMapping("/soportes/{id}")
    public ResponseEntity<Soporte> edit(@PathVariable Long id, @RequestBody Soporte body) {
        body.setIdTiket(id);
        Soporte updated = soporteService.edit(body);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // ---------- CERRAR TICKET (PATCH) ----------
    @PatchMapping("/soportes/{id}/cerrar")
    public ResponseEntity<Integer> close(@PathVariable Long id,
                                         @RequestParam(required = false) String fecha) {
        LocalDate cierre = (fecha != null) ? LocalDate.parse(fecha) : null;
        int rows = soporteService.closeTicket(id, cierre);
        return new ResponseEntity<>(rows, rows > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    // ---------- ELIMINAR ----------
    @DeleteMapping("/soportes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        soporteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
