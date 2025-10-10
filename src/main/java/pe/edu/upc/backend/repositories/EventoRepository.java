package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.backend.entities.Evento;
import pe.edu.upc.backend.entities.Anuncios;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    // --- Por ARTISTA (usamos JPQL por el campo id_artist en la entidad Artist) ---
    @Query("select e from Evento e where e.artista.id_artist = :artistId")
    List<Evento> findByArtistId(@Param("artistId") Long artistId);

    // --- Por ANUNCIO ---
    // Opción 1 (más segura): pasas la entidad anuncio
    List<Evento> findByAnuncio(Anuncios anuncio);

    // Opción 2 (si conoces el nombre exacto del PK en Anuncios; descomenta y ajusta si es necesario)
    // @Query("select e from Evento e where e.anuncio.id = :announcementId")
    // List<Evento> findByAnnouncementId(@Param("announcementId") Long announcementId);

    // --- Rango de fechas (usa 'fecha' de eventos) ---
    List<Evento> findByFechaBetween(LocalDate inicio, LocalDate fin);

    // --- Realizados / no realizados ---
    List<Evento> findByRealizadoTrueOrderByFechaDesc();
    List<Evento> findByRealizadoFalseOrderByFechaAsc();

    // --- Chequeo de conflicto (ej.: ¿ya hay un evento activo para este anuncio en esa fecha?) ---
    @Query("select (count(e) > 0) from Evento e where e.anuncio = :anuncio and e.fecha = :fecha and e.realizado = true")
    boolean existsActivoByAnuncioAndFecha(@Param("anuncio") Anuncios anuncio,
                                          @Param("fecha") LocalDate fecha);

    // Variante por artista + fecha (por si necesitas validar agenda del artista ese día)
    @Query("select (count(e) > 0) from Evento e where e.artista.id_artist = :artistId and e.fecha = :fecha and e.realizado = true")
    boolean existsByArtistIdAndFecha(@Param("artistId") Long artistId,
                                     @Param("fecha") LocalDate fecha);
}


