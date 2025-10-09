package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.DTOMessages;

import java.time.LocalDate;
import java.util.List;

public interface MessagesServices {
    public DTOMessages crearMessages(DTOMessages dtoMessages);
    public List<DTOMessages> listarMessages();
    public DTOMessages buscarMessagesPorId(Long id);
    public void eliminar(Long id);
    List<DTOMessages> findMessagesByUserId(Long userId);
    List<DTOMessages> findByFechaEnvio(LocalDate fechaEnvio);
    List<DTOMessages> findMessagesByUserAndDateRange(Long userId, LocalDate startDate, LocalDate endDate);
    List<DTOMessages> findRecentMessagesByUser(Long userId, int limit);



}
