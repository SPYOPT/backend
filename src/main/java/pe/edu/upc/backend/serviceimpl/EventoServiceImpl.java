package pe.edu.upc.backend.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.backend.dtos.DTOEvento;
import pe.edu.upc.backend.entities.Anuncios;
import pe.edu.upc.backend.entities.Artist;
import pe.edu.upc.backend.entities.Evento;
import pe.edu.upc.backend.exceptions.ResourceNotFoundException;
import pe.edu.upc.backend.repositories.EventoRepository;
import pe.edu.upc.backend.repositories.AnunciosRepository; // <-- asegúrate de tener este repo
import pe.edu.upc.backend.repositories.ArtistRepository;   // <-- y este también
import pe.edu.upc.backend.services.EventoService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;
    private final AnunciosRepository anunciosRepository;
    private final ArtistRepository artistRepository;

    public EventoServiceImpl(EventoRepository eventoRepository,
                             AnunciosRepository anunciosRepository,
                             ArtistRepository artistRepository) {
        this.eventoRepository = eventoRepository;
        this.anunciosRepository = anunciosRepository;
        this.artistRepository = artistRepository;
    }

    // -------------------- CRUD --------------------

    @Override
    public DTOEvento create(DTOEvento dto) {
        if (dto.getAnuncioId() == null) {
            throw new IllegalArgumentException("anuncioId es obligatorio");
        }
        if (dto.getArtistId() == null) {
            throw new IllegalArgumentException("artistId es obligatorio");
        }
        Evento entity = toEntity(dto, null);
        Evento saved = eventoRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DTOEvento findByIdDTO(Long id) {
        Evento e = eventoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado: " + id));
        return toDTO(e);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTOEvento> findAllDTO() {
        return eventoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public DTOEvento update(DTOEvento dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("id es obligatorio para actualizar");
        }
        Evento current = eventoRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado: " + dto.getId()));
        Evento merged = toEntity(dto, current);
        Evento saved = eventoRepository.save(merged);
        return toDTO(saved);
    }

    @Override
    public void deleteById(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento no encontrado: " + id);
        }
        eventoRepository.deleteById(id);
    }

    // ---------------- Consultas adicionales ----------------

    @Override
    @Transactional(readOnly = true)
    public List<DTOEvento> findByArtist(Long artistId) {
        return eventoRepository.findByArtistId(artistId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTOEvento> findByAnuncio(Long anuncioId) {
        Anuncios anuncio = anunciosRepository.findById(anuncioId)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio no encontrado: " + anuncioId));
        return eventoRepository.findByAnuncio(anuncio).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTOEvento> findByFechaBetween(LocalDate inicio, LocalDate fin) {
        return eventoRepository.findByFechaBetween(inicio, fin).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTOEvento> findRealizadosOrderByFechaDesc() {
        return eventoRepository.findByRealizadoTrueOrderByFechaDesc().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTOEvento> findNoRealizadosOrderByFechaAsc() {
        return eventoRepository.findByRealizadoFalseOrderByFechaAsc().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsActivoPorAnuncioEnFecha(Long anuncioId, LocalDate fecha) {
        Anuncios anuncio = anunciosRepository.findById(anuncioId)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio no encontrado: " + anuncioId));
        return eventoRepository.existsActivoByAnuncioAndFecha(anuncio, fecha);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsActivoPorArtistaEnFecha(Long artistId, LocalDate fecha) {
        return eventoRepository.existsByArtistIdAndFecha(artistId, fecha);
    }

    // -------------------- Mapping --------------------

    private DTOEvento toDTO(Evento e) {
        DTOEvento dto = new DTOEvento();
        dto.setId(e.getId());
        // Ajusta el getter de la PK de Anuncios según tu entidad (getId / getIdAnuncios / etc.)
        dto.setAnuncioId(e.getAnuncio() != null ? getAnuncioIdSafe(e.getAnuncio()) : null);
        dto.setArtistId(e.getArtista() != null ? e.getArtista().getId_artist() : null);
        dto.setFecha(e.getFecha() != null ? e.getFecha().toString() : null);
        dto.setCachet(e.getCachet());
        dto.setRealizado(e.getRealizado());
        dto.setFechaCreacion(e.getFechaCreacion() != null ? e.getFechaCreacion().toString() : null);
        return dto;
    }

    private Evento toEntity(DTOEvento dto, Evento current) {
        Evento e = (current == null) ? new Evento() : current;

        // anuncio (obligatorio en create; en update si viene se cambia, si no se preserva)
        if (dto.getAnuncioId() != null) {
            Anuncios anuncio = anunciosRepository.findById(dto.getAnuncioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Anuncio no encontrado: " + dto.getAnuncioId()));
            e.setAnuncio(anuncio);
        } else if (current == null) {
            throw new IllegalArgumentException("anuncioId es obligatorio al crear");
        }

        // artista (obligatorio en create; en update si viene se cambia, si no se preserva)
        if (dto.getArtistId() != null) {
            Artist artist = artistRepository.findById(dto.getArtistId())
                    .orElseThrow(() -> new ResourceNotFoundException("Artista no encontrado: " + dto.getArtistId()));
            e.setArtista(artist);
        } else if (current == null) {
            throw new IllegalArgumentException("artistId es obligatorio al crear");
        }

        if (dto.getFecha() != null && !dto.getFecha().isBlank()) {
            e.setFecha(LocalDate.parse(dto.getFecha()));
        }
        e.setCachet(dto.getCachet());
        e.setRealizado(dto.getRealizado());
        if (dto.getFechaCreacion() != null && !dto.getFechaCreacion().isBlank()) {
            e.setFechaCreacion(LocalDate.parse(dto.getFechaCreacion()));
        } else if (current == null && e.getFechaCreacion() == null) {
            e.setFechaCreacion(LocalDate.now());
        }
        return e;
    }

    /**
     * Helper para evitar romper la compilación si la PK de Anuncios no se llama exactamente "id".
     * Cambia la implementación si tu entidad `Anuncios` usa otro nombre (p.ej., getIdAnuncios()).
     */
    private Long getAnuncioIdSafe(Anuncios a) {
        try {
            // Intenta getId()
            return (Long) a.getClass().getMethod("getId").invoke(a);
        } catch (Exception ignore) {
            try {
                // Intenta getIdAnuncios()
                return (Long) a.getClass().getMethod("getIdAnuncios").invoke(a);
            } catch (Exception e) {
                // Último recurso: lanza excepción clara para que ajustes el getter
                throw new IllegalStateException("Ajusta getAnuncioIdSafe(): no se pudo obtener la PK de Anuncios. " +
                        "Define el getter correcto (getId / getIdAnuncios) en esta clase.");
            }
        }
    }
}
