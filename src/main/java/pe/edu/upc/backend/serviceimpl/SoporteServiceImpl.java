package pe.edu.upc.backend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.backend.dtos.SoporteDTO;
import pe.edu.upc.backend.entities.Soporte;
import pe.edu.upc.backend.entities.User;
import pe.edu.upc.backend.repositories.SoporteRepository;
import pe.edu.upc.backend.repositories.UserRepository;
import pe.edu.upc.backend.services.SoporteService;

import java.time.LocalDate;
import java.util.List;

@Service
public class SoporteServiceImpl implements SoporteService {
    @Autowired
    private SoporteRepository soporteRepository;

    @Autowired
    private UserRepository userRepository;

    // =========================
    // CRUD
    // =========================
    @Override
    public SoporteDTO add(SoporteDTO dto) {
        // 1) Validaciones mínimas
        if (dto == null) return null;
        if (dto.getUserId() == null) return null;
        if (dto.getAsunto() == null || dto.getAsunto().isBlank()) return null;

        User user = userRepository.findById(dto.getUserId()).orElse(null);
        if (user == null) return null;

        // 2) Mapear y setear defaults
        Soporte s = new Soporte();
        s.setIdTiket(null);
        s.setAsunto(dto.getAsunto());
        s.setDescripcion(dto.getDescripcion());
        s.setEstado(dto.getEstado() != null ? dto.getEstado() : Boolean.TRUE);
        s.setFechaCreacion(dto.getFechaCreacion() != null ? dto.getFechaCreacion() : LocalDate.now());
        s.setFechaCierre(dto.getFechaCierre());
        s.setUser(user);

        // 3) Guardar
        s = soporteRepository.save(s);

        // 4) Devolver DTO
        return toDTO(s);
    }

    @Override
    public Soporte edit(Soporte soporte) {
        if (soporte == null || soporte.getIdTiket() == null) return null;

        Soporte found = soporteRepository.findById(soporte.getIdTiket()).orElse(null);
        if (found == null) return null;

        // Actualiza campos editables (sin tocar user)
        if (soporte.getAsunto() != null) found.setAsunto(soporte.getAsunto());
        if (soporte.getDescripcion() != null) found.setDescripcion(soporte.getDescripcion());
        if (soporte.getEstado() != null) found.setEstado(soporte.getEstado());
        if (soporte.getFechaCreacion() != null) found.setFechaCreacion(soporte.getFechaCreacion());
        if (soporte.getFechaCierre() != null) found.setFechaCierre(soporte.getFechaCierre());

        return soporteRepository.save(found);
    }

    @Override
    public void delete(Long id) {
        Soporte s = findById(id);
        if (s == null) return; // no existe
        soporteRepository.deleteById(id);
    }

    @Override
    public Soporte findById(Long id) {
        return soporteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Soporte> listAll() {
        return soporteRepository.findAll();
    }


    // =========================
    // Métodos extra alineados al repositorio (4 queries)
    // =========================
    @Override
    public List<Soporte> listByUserId(Long userId) {
        return soporteRepository.findByUser_Id(userId);
    }

    @Override
    public List<Soporte> listOpenOrderByFechaCreacionDesc() {
        return soporteRepository.findByEstadoTrueOrderByFechaCreacionDesc();
    }

    @Override
    public int closeTicket(Long ticketId, LocalDate fechaCierre) {
        // Validar existencia (mismo estilo que delete/find)
        Soporte s = findById(ticketId);
        if (s == null) return 0;

        LocalDate cierre = (fechaCierre != null) ? fechaCierre : LocalDate.now();
        return soporteRepository.cerrarTicket(ticketId, cierre);
    }


    // =========================
    // Mapper entidad <-> DTO
    // =========================
    private SoporteDTO toDTO(Soporte s) {
        SoporteDTO dto = new SoporteDTO();
        dto.setIdTiket(s.getIdTiket());
        dto.setAsunto(s.getAsunto());
        dto.setDescripcion(s.getDescripcion());
        dto.setEstado(s.getEstado());
        dto.setFechaCreacion(s.getFechaCreacion());
        dto.setFechaCierre(s.getFechaCierre());
        dto.setUserId(s.getUser() != null ? s.getUser().getId() : null);
        return dto;
    }

}
