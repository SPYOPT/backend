package pe.edu.upc.backend.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.backend.dtos.DTORestaurante;
import pe.edu.upc.backend.entities.Restaurante;
import pe.edu.upc.backend.entities.User;
import pe.edu.upc.backend.exceptions.ResourceNotFoundException;
import pe.edu.upc.backend.repositories.RestauranteRepository;
import pe.edu.upc.backend.repositories.UserRepository;
import pe.edu.upc.backend.services.RestauranteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final UserRepository userRepository;

    public RestauranteServiceImpl(RestauranteRepository restauranteRepository,
                                  UserRepository userRepository) {
        this.restauranteRepository = restauranteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DTORestaurante create(DTORestaurante dto) {
        Restaurante entity = toEntity(dto, null);
        Restaurante saved = restauranteRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DTORestaurante findByIdDTO(Integer id) {
        Restaurante r = restauranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante no encontrado: " + id));
        return toDTO(r);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTORestaurante> findAllDTO() {
        return restauranteRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public DTORestaurante update(DTORestaurante dto) {
        Restaurante current = restauranteRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante no encontrado: " + dto.getId()));

        Restaurante updated = toEntity(dto, current);
        Restaurante saved = restauranteRepository.save(updated);
        return toDTO(saved);
    }

    @Override
    public void deleteById(Integer id) {
        if (!restauranteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Restaurante no encontrado: " + id);
        }
        restauranteRepository.deleteById(id);
    }

    /* ===== Mapping ===== */

    private DTORestaurante toDTO(Restaurante r) {
        DTORestaurante dto = new DTORestaurante();
        dto.setId(r.getId());
        dto.setNombre(r.getNombre());
        dto.setDireccion(r.getDireccion());
        dto.setCiudad(r.getCiudad());
        dto.setAforoMesas(r.getAforoMesas());
        dto.setTelefono(r.getTelefono());
        dto.setEmail(r.getEmail());
        dto.setFechaCreacion(r.getFechaCreacion() == null ? null : r.getFechaCreacion().toString());
        dto.setIdUsuario(r.getOwner() == null ? null : r.getOwner().getId());
        return dto;
    }

    /** Si current es null, se crea; si no, se “mergea” */
    private Restaurante toEntity(DTORestaurante dto, Restaurante current) {
        Restaurante r = (current == null) ? new Restaurante() : current;

        r.setNombre(dto.getNombre());
        r.setDireccion(dto.getDireccion());
        r.setCiudad(dto.getCiudad());
        r.setAforoMesas(dto.getAforoMesas());
        r.setTelefono(dto.getTelefono());
        r.setEmail(dto.getEmail());
        if (dto.getFechaCreacion() != null && !dto.getFechaCreacion().isBlank()) {
            r.setFechaCreacion(java.time.LocalDate.parse(dto.getFechaCreacion()));
        }

        if (dto.getIdUsuario() != null) {
            User owner = userRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario propietario no encontrado: " + dto.getIdUsuario()));
            r.setOwner(owner);
        } else {
            r.setOwner(null);
        }
        return r;
    }
}

