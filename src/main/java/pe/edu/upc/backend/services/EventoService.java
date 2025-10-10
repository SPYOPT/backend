package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.DTOEvento;

import java.time.LocalDate;
import java.util.List;

public interface EventoService {

    // ---------- CRUD principal (DTO in/out) ----------
    DTOEvento create(DTOEvento dto);
    DTOEvento findByIdDTO(Long id);
    List<DTOEvento> findAllDTO();
    DTOEvento update(DTOEvento dto);
    void deleteById(Long id);

    // ---------- Consultas adicionales ----------
    // Por artista (FK: id_artistas -> Artist.id_artist)
    List<DTOEvento> findByArtist(Long artistId);

    // Por anuncio (FK: id_anuncios)
    List<DTOEvento> findByAnuncio(Long anuncioId);

    // Rango de fechas (columna: fecha)
    List<DTOEvento> findByFechaBetween(LocalDate inicio, LocalDate fin);

    // Realizados / no realizados
    List<DTOEvento> findRealizadosOrderByFechaDesc();
    List<DTOEvento> findNoRealizadosOrderByFechaAsc();

    // Chequeos de conflicto/agenda
    boolean existsActivoPorAnuncioEnFecha(Long anuncioId, LocalDate fecha);
    boolean existsActivoPorArtistaEnFecha(Long artistId, LocalDate fecha);
}

