package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.DTOEvento;
import pe.edu.upc.backend.services.EventoService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/upc/eventos") // http://localhost:8080/upc/eventos
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // ---------- CRUD ----------
    @PostMapping
    public ResponseEntity<DTOEvento> create(@RequestBody DTOEvento dto) {
        DTOEvento created = eventoService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOEvento> findById(@PathVariable Long id) {
        DTOEvento dto = eventoService.findByIdDTO(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DTOEvento>> findAll() {
        return new ResponseEntity<>(eventoService.findAllDTO(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOEvento> update(@PathVariable Long id, @RequestBody DTOEvento dto) {
        dto.setId(id);
        DTOEvento updated = eventoService.update(dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ---------- Consultas adicionales ----------
    // Por artista
    @GetMapping("/artista/{artistId}")
    public ResponseEntity<List<DTOEvento>> findByArtist(@PathVariable Long artistId) {
        return new ResponseEntity<>(eventoService.findByArtist(artistId), HttpStatus.OK);
    }

    // Por anuncio
    @GetMapping("/anuncio/{anuncioId}")
    public ResponseEntity<List<DTOEvento>> findByAnuncio(@PathVariable Long anuncioId) {
        return new ResponseEntity<>(eventoService.findByAnuncio(anuncioId), HttpStatus.OK);
    }

    // Rango de fechas: ?inicio=YYYY-MM-DD&fin=YYYY-MM-DD
    @GetMapping("/fecha")
    public ResponseEntity<List<DTOEvento>> findByFechaBetween(@RequestParam String inicio,
                                                              @RequestParam String fin) {
        LocalDate start = LocalDate.parse(inicio);
        LocalDate end   = LocalDate.parse(fin);
        return new ResponseEntity<>(eventoService.findByFechaBetween(start, end), HttpStatus.OK);
    }

    // Realizados / No realizados
    @GetMapping("/realizados")
    public ResponseEntity<List<DTOEvento>> findRealizados() {
        return new ResponseEntity<>(eventoService.findRealizadosOrderByFechaDesc(), HttpStatus.OK);
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<DTOEvento>> findNoRealizados() {
        return new ResponseEntity<>(eventoService.findNoRealizadosOrderByFechaAsc(), HttpStatus.OK);
    }

    // Chequeos de agenda / conflicto
    @GetMapping("/existe/anuncio/{anuncioId}")
    public ResponseEntity<Boolean> existsActivoPorAnuncioEnFecha(@PathVariable Long anuncioId,
                                                                 @RequestParam String fecha) {
        LocalDate f = LocalDate.parse(fecha);
        return new ResponseEntity<>(eventoService.existsActivoPorAnuncioEnFecha(anuncioId, f), HttpStatus.OK);
    }

    @GetMapping("/existe/artista/{artistId}")
    public ResponseEntity<Boolean> existsActivoPorArtistaEnFecha(@PathVariable Long artistId,
                                                                 @RequestParam String fecha) {
        LocalDate f = LocalDate.parse(fecha);
        return new ResponseEntity<>(eventoService.existsActivoPorArtistaEnFecha(artistId, f), HttpStatus.OK);
    }
}
