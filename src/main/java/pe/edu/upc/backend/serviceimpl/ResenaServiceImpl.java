package pe.edu.upc.backend.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.backend.dtos.DTOResena;
import pe.edu.upc.backend.entities.Resena;
import pe.edu.upc.backend.entities.Restaurante;
import pe.edu.upc.backend.exceptions.ResourceNotFoundException;
import pe.edu.upc.backend.repositories.ResenaRepository;
import pe.edu.upc.backend.repositories.RestauranteRepository;
import pe.edu.upc.backend.services.ResenaService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResenaServiceImpl implements ResenaService {

    private final ResenaRepository resenaRepository;
    private final RestauranteRepository restauranteRepository;

    public ResenaServiceImpl(ResenaRepository resenaRepository,
                             RestauranteRepository restauranteRepository) {
        this.resenaRepository = resenaRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public DTOResena create(DTOResena dto) {
        Resena entity = toEntity(dto, null);
        Resena saved = resenaRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DTOResena findByIdDTO(Integer id) {
        Resena r = resenaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reseña no encontrada: " + id));
        return toDTO(r);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTOResena> findAllDTO() {
        return resenaRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public DTOResena update(DTOResena dto) {
        Resena current = resenaRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Reseña no encontrada: " + dto.getId()));
        Resena merged = toEntity(dto, current);
        Resena saved = resenaRepository.save(merged);
        return toDTO(saved);
    }

    @Override
    public void deleteById(Integer id) {
        if (!resenaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reseña no encontrada: " + id);
        }
        resenaRepository.deleteById(id);
    }

    /* ===== Mapping ===== */

    private DTOResena toDTO(Resena r) {
        DTOResena dto = new DTOResena();
        dto.setId(r.getId());
        dto.setIdEvento(r.getIdEvento());
        dto.setIdArtista(r.getIdArtista());
        dto.setIdRestaurante(r.getRestaurante() == null ? null : r.getRestaurante().getId());
        dto.setPuntuacion(r.getPuntuacion());
        dto.setComentario(r.getComentario());
        dto.setFechaResena(r.getFechaResena() == null ? null : r.getFechaResena().toString());
        return dto;
    }

    private Resena toEntity(DTOResena dto, Resena current) {
        Resena r = (current == null) ? new Resena() : current;

        if (dto.getIdRestaurante() != null) {
            Restaurante restaurante = restauranteRepository.findById(dto.getIdRestaurante())
                    .orElseThrow(() -> new ResourceNotFoundException("Restaurante no encontrado: " + dto.getIdRestaurante()));
            r.setRestaurante(restaurante);
        } else {
            r.setRestaurante(null);
        }

        r.setIdEvento(dto.getIdEvento());
        r.setIdArtista(dto.getIdArtista());
        r.setPuntuacion(dto.getPuntuacion());
        r.setComentario(dto.getComentario());

        if (dto.getFechaResena() != null && !dto.getFechaResena().isBlank()) {
            r.setFechaResena(LocalDate.parse(dto.getFechaResena()));
        } else {
            r.setFechaResena(null);
        }
        return r;
    }
}
