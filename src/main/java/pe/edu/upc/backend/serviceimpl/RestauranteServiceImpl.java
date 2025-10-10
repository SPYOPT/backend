package pe.edu.upc.backend.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.backend.dtos.DTORestaurante;
import pe.edu.upc.backend.entities.Restaurante;
import pe.edu.upc.backend.entities.User;
import pe.edu.upc.backend.exceptions.ResourceNotFoundException;
import pe.edu.upc.backend.repositories.RestauranteRepository;
import pe.edu.upc.backend.repositories.UserRepository;
import pe.edu.upc.backend.services.RestauranteService;

import java.time.LocalDate;
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

    // ---------------- CRUD ----------------

    @Override
    public DTORestaurante create(DTORestaurante dto) {
        // owner es obligatorio (FK NOT NULL)
        if (dto.getIdUsuario() == null) {
            throw new IllegalArgumentException("idUsuario es obligatorio");
        }
        Restaurante entity = toEntity(dto, null);
        Restaurante saved = restauranteRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DTORestaurante findByIdDTO(Long id) {
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
        if (dto.getId() == null) {
            throw new IllegalArgumentException("id es obligatorio para actualizar");
        }
        Restaurante current = restauranteRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante no encontrado: " + dto.getId()));

        Restaurante updated = toEntity(dto, current);
        Restaurante saved = restauranteRepository.save(updated);
        return toDTO(saved);
    }

    @Override
    public void deleteById(Long id) {
        if (!restauranteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Restaurante no encontrado: " + id);
        }
        restauranteRepository.deleteById(id);
    }

    // ------------- Consultas adicionales -------------

    @Override
    @Transactional(readOnly = true)
    public List<DTORestaurante> findByOwner(Long idUsuario) {
        return restauranteRepository.findByOwner_Id(idUsuario)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTORestaurante> searchByNombre(String nombre) {
        return restauranteRepository.findByNombreContainingIgnoreCase(nombre == null ? "" : nombre)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTORestaurante> findByCiudad(String ciudad) {
        return restauranteRepository.findByCiudadIgnoreCase(ciudad == null ? "" : ciudad)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    /* ============== Mapping ============== */

    private DTORestaurante toDTO(Restaurante r) {
        DTORestaurante dto = new DTORestaurante();
        dto.setId(r.getId()); // Long
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

    /**
     * Si current es null, crea; si no, mergea manteniendo owner cuando no se envía idUsuario.
     */
    private Restaurante toEntity(DTORestaurante dto, Restaurante current) {
        Restaurante r = (current == null) ? new Restaurante() : current;

        // Campos básicos (solo sobreescribe si vienen no nulos para evitar borrar accidentalmente)
        if (dto.getNombre() != null) r.setNombre(dto.getNombre());
        if (dto.getDireccion() != null) r.setDireccion(dto.getDireccion());
        if (dto.getCiudad() != null) r.setCiudad(dto.getCiudad());
        if (dto.getAforoMesas() != null) r.setAforoMesas(dto.getAforoMesas());
        if (dto.getTelefono() != null) r.setTelefono(dto.getTelefono());
        if (dto.getEmail() != null) r.setEmail(dto.getEmail());

        if (dto.getFechaCreacion() != null && !dto.getFechaCreacion().isBlank()) {
            r.setFechaCreacion(LocalDate.parse(dto.getFechaCreacion()));
        } else if (current == null && r.getFechaCreacion() == null) {
            // opcional: setear hoy si estás creando y no te mandan fecha
            r.setFechaCreacion(LocalDate.now());
        }

        // Owner (FK NOT NULL): si mandas idUsuario, se actualiza; si no mandas, se preserva en update
        if (dto.getIdUsuario() != null) {
            User owner = userRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario propietario no encontrado: " + dto.getIdUsuario()));
            r.setOwner(owner);
        } else if (current == null) {
            // creando y no mandaron owner
            throw new IllegalArgumentException("idUsuario es obligatorio al crear");
        }
        return r;
    }
}


