package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.DTONotifications;

import java.time.LocalDate;
import java.util.List;

public interface NotificationsServices {

    public DTONotifications crearNotifications(DTONotifications dtoNotifications);
    public List<DTONotifications> listarNotifications();
    public DTONotifications buscarNotificationsPorId(Long id);
    public void eliminar(Long id);

    public List<DTONotifications> findNotificationsByUserId(Long userId);
    public List<DTONotifications> findByLeido(Boolean leido);
    public List<DTONotifications> findUnreadNotificationsFromLastWeek(LocalDate fechaInicio);
    public List<Object[]> countNotificationsByTypeAndStatus(LocalDate startDate, LocalDate endDate);

}
