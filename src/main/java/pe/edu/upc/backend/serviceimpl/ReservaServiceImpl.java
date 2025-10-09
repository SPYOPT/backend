package pe.edu.upc.backend.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.backend.dtos.DTOReserva;
import pe.edu.upc.backend.entities.Reserva;
import pe.edu.upc.backend.entities.Restaurante;
import pe.edu.upc.backend.entities.User;
import pe.edu.upc.backend.exceptions.ResourceNotFoundException;
import pe.edu.upc.backend.repositories.ReservaRepository;
import pe.edu.upc.backend.repositories.RestauranteRepository;
import pe.edu.upc.backend.repositories.UserRepository;
import pe.edu.upc.backend.services.ReservaService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final RestauranteRepository restauranteRepository;
    private final UserRepository userRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository,
                              RestauranteRepository restauranteRepository,
                              UserRepository userRepository) {
        this.reservaRepository = reservaRepository;
        this.restauranteRepository = restauranteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DTOReserva create(DTOReserva dto) {
        Reserva entity = toEntity(dto, null);
        Reserva saved = reservaRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DTOReserva findByIdDTO(Integer id) {
        Reserva r = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada: " + id));
        return toDTO(r);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTOReserva> findAllDTO() {
        return reservaRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public DTOReserva update(DTOReserva dto) {
        Reserva current = reservaRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada: " + dto.getId()));
        Reserva merged = toEntity(dto, current);
        Reserva saved = reservaRepository.save(merged);
        return toDTO(saved);
    }

    @Override
    public void deleteById(Integer id) {
        if (!reservaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reserva no encontrada: " + id);
        }
        reservaRepository.deleteById(id);
    }

    /* ===== Mapping ===== */

    private DTOReserva toDTO(Reserva r) {
        DTOReserva dto = new DTOReserva();
        dto.setId(r.getId());
        dto.setIdUsuario(r.getUsuario() == null ? null : r.getUsuario().getId());
        dto.setIdRestaurante(r.getRestaurante() == null ? null : r.getRestaurante().getId());
        dto.setFechaReserva(r.getFechaReserva() == null ? null : r.getFechaReserva().toString());
        dto.setNumPersonas(r.getNumPersonas());
        dto.setEstado(r.getEstado());
        dto.setFechaCreacion(r.getFechaCreacion() == null ? null : r.getFechaCreacion().toString());
        return dto;
    }

    private Reserva toEntity(DTOReserva dto, Reserva current) {
        Reserva r = (current == null) ? new Reserva() : current;

        if (dto.getIdRestaurante() != null) {
            Restaurante restaurante = restauranteRepository.findById(dto.getIdRestaurante())
                    .orElseThrow(() -> new ResourceNotFoundException("Restaurante no encontrado: " + dto.getIdRestaurante()));
            r.setRestaurante(restaurante);
        } else {
            r.setRestaurante(null);
        }

        if (dto.getIdUsuario() != null) {
            User user = userRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + dto.getIdUsuario()));
            r.setUsuario(user);
        } else {
            r.setUsuario(null);
        }

        if (dto.getFechaReserva() != null && !dto.getFechaReserva().isBlank()) {
            r.setFechaReserva(LocalDate.parse(dto.getFechaReserva()));
        } else {
            r.setFechaReserva(null);
        }

        r.setNumPersonas(dto.getNumPersonas());
        r.setEstado(dto.getEstado());

        if (dto.getFechaCreacion() != null && !dto.getFechaCreacion().isBlank()) {
            r.setFechaCreacion(LocalDate.parse(dto.getFechaCreacion()));
        }
        return r;
    }
}

