package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.DTOMessages;

import java.util.List;

public interface MessagesServices {
    public DTOMessages crearMessages(DTOMessages dtoMessages);
    public List<DTOMessages> listarMessages();
    public DTOMessages buscarMessagesPorId(Long id);
    public List<DTOMessages> findMessagesByTexto(String texto);
    public List<DTOMessages> findMessagesByUsuarioEmail(String email);
    public List<Object[]> countMessagesPorUsuario();



}
