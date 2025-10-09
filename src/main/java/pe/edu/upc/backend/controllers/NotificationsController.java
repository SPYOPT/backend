package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.DTONotifications;
import pe.edu.upc.backend.services.NotificationsServices;
import org.springframework.http.HttpStatus;
import java.time.LocalDate;
import java.util.List;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")

public class NotificationsController {

    @Autowired
    private NotificationsServices notificationsServices;

    @PostMapping
    public ResponseEntity<DTONotifications> crearNotification(@RequestBody DTONotifications dtoNotifications) {
        DTONotifications nuevaNotificacion = notificationsServices.crearNotifications(dtoNotifications);
        return new ResponseEntity<>(nuevaNotificacion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DTONotifications>> listarNotifications() {
        List<DTONotifications> notifications = notificationsServices.listarNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTONotifications> buscarNotificationsPorId(@PathVariable Long id) {
        DTONotifications notification = notificationsServices.buscarNotificationsPorId(id);
        if (notification != null) {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotifications(@PathVariable Long id) {
        notificationsServices.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DTONotifications>> findNotificationsByUserId(@PathVariable Long userId) {
        List<DTONotifications> notifications = notificationsServices.findNotificationsByUserId(userId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/leido/{leido}")
    public ResponseEntity<List<DTONotifications>> findByLeido(@PathVariable Boolean leido) {
        List<DTONotifications> notifications = notificationsServices.findByLeido(leido);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/no-leidas/desde/{fechaInicio}")
    public ResponseEntity<List<DTONotifications>> findUnreadNotificationsFromLastWeek(@PathVariable LocalDate fechaInicio) {
        List<DTONotifications> notifications = notificationsServices.findUnreadNotificationsFromLastWeek(fechaInicio);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/estadisticas/{startDate}/{endDate}")
    public ResponseEntity<List<Object[]>> countNotificationsByTypeAndStatus(
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate) {
        List<Object[]> estadisticas = notificationsServices.countNotificationsByTypeAndStatus(startDate, endDate);
        return new ResponseEntity<>(estadisticas, HttpStatus.OK);
    }

}
