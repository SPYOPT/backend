package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.DTOMessages;
import pe.edu.upc.backend.services.MessagesServices;
import org.springframework.http.HttpStatus;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/mensajes")

public class MessagesController {
    @Autowired
    private MessagesServices messagesServices;

    @PostMapping
    public ResponseEntity<DTOMessages> crearMessages(@RequestBody DTOMessages dtoMessages) {
        DTOMessages nuevoMessage = messagesServices.crearMessages(dtoMessages);
        return new ResponseEntity<>(nuevoMessage, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DTOMessages>> listarMessages() {
        List<DTOMessages> messages = messagesServices.listarMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOMessages> buscarMessagesPorId(@PathVariable Long id) {
        DTOMessages message = messagesServices.buscarMessagesPorId(id);
        if (message != null) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMessages(@PathVariable Long id) {
        messagesServices.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DTOMessages>> findMessagesByUserId(@PathVariable Long userId) {
        List<DTOMessages> messages = messagesServices.findMessagesByUserId(userId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/fecha/{fechaEnvio}")
    public ResponseEntity<List<DTOMessages>> findByFechaEnvio(@PathVariable LocalDate fechaEnvio) {
        List<DTOMessages> messages = messagesServices.findByFechaEnvio(fechaEnvio);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/rango-fechas/{startDate}/{endDate}")
    public ResponseEntity<List<DTOMessages>> findMessagesByUserAndDateRange(
            @PathVariable Long userId,
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate) {
        List<DTOMessages> messages = messagesServices.findMessagesByUserAndDateRange(userId, startDate, endDate);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/recientes/{limit}")
    public ResponseEntity<List<DTOMessages>> findRecentMessagesByUser(
            @PathVariable Long userId,
            @PathVariable int limit) {
        List<DTOMessages> messages = messagesServices.findRecentMessagesByUser(userId, limit);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }


}
