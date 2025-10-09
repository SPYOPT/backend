package pe.edu.upc.backend.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.backend.dtos.DTONotifications;
import pe.edu.upc.backend.entities.Notifications;
import pe.edu.upc.backend.repositories.NotificationsRepository;
import pe.edu.upc.backend.services.NotificationsServices;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class NotificationsServiceImpl implements NotificationsServices {

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DTONotifications crearNotifications(DTONotifications dtoNotifications) {
        Notifications notifications = modelMapper.map(dtoNotifications, Notifications.class);
        return modelMapper.map(notificationsRepository.save(notifications), DTONotifications.class);
    }

    @Override
    public List<DTONotifications> listarNotifications() {
        return notificationsRepository.findAll().stream()
                .map(n -> modelMapper.map(n, DTONotifications.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public DTONotifications buscarNotificationsPorId(Long id) {
        return modelMapper.map(notificationsRepository.findById(id).get(), DTONotifications.class);
    }

    @Override
    public void eliminar(Long id) {
        notificationsRepository.deleteById(id);
    }

    @Override
    public List<DTONotifications> findNotificationsByUserId(Long userId) {
        return notificationsRepository.findByUserId(userId).stream()
                .map(n -> modelMapper.map(n, DTONotifications.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTONotifications> findByLeido(Boolean leido) {
        return notificationsRepository.findByLeido(leido).stream()
                .map(n -> modelMapper.map(n, DTONotifications.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTONotifications> findUnreadNotificationsFromLastWeek(LocalDate fechaInicio) {
        return notificationsRepository.findUnreadNotificationsFromLastWeek(fechaInicio).stream()
                .map(n -> modelMapper.map(n, DTONotifications.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Object[]> countNotificationsByTypeAndStatus(LocalDate startDate, LocalDate endDate) {
        return notificationsRepository.countNotificationsByTypeAndStatus(startDate, endDate);
    }
}
