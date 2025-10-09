package pe.edu.upc.backend.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.backend.dtos.DTOMessages;
import pe.edu.upc.backend.entities.Messages;
import pe.edu.upc.backend.repositories.MessagesRepository;
import pe.edu.upc.backend.services.MessagesServices;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class MessagesServiceImpl implements MessagesServices {

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DTOMessages crearMessages(DTOMessages dtoMessages) {
        Messages messages = modelMapper.map(dtoMessages, Messages.class);
        return modelMapper.map(messagesRepository.save(messages), DTOMessages.class);
    }

    @Override
    public List<DTOMessages> listarMessages() {
        return messagesRepository.findAll().stream()
                .map(m -> modelMapper.map(m, DTOMessages.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public DTOMessages buscarMessagesPorId(Long id) {
        return modelMapper.map(messagesRepository.findById(id).get(), DTOMessages.class);
    }

    @Override
    public void eliminar(Long id) {
        messagesRepository.deleteById(id);
    }

    @Override
    public List<DTOMessages> findMessagesByUserId(Long userId) {
        return messagesRepository.findMessagesByUserId(userId).stream()
                .map(m -> modelMapper.map(m, DTOMessages.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTOMessages> findByFechaEnvio(LocalDate fechaEnvio) {
        return messagesRepository.findByFechaEnvio(fechaEnvio).stream()
                .map(m -> modelMapper.map(m, DTOMessages.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTOMessages> findMessagesByUserAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        return messagesRepository.findMessagesByUserAndDateRange(userId, startDate, endDate).stream()
                .map(m -> modelMapper.map(m, DTOMessages.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTOMessages> findRecentMessagesByUser(Long userId, int limit) {
        return messagesRepository.findRecentMessagesByUser(userId,limit).stream()
                .map(m -> modelMapper.map(m, DTOMessages.class))
                .collect(Collectors.toList());
    }
}
