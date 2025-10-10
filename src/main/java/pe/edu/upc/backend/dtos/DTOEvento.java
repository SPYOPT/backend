package pe.edu.upc.backend.dtos;

public class DTOEvento {
    private Long id;             // id_eventos
    private Long anuncioId;      // FK -> id_anuncios
    private Long artistId;       // FK -> artists.id_artist
    private String fecha;        // "yyyy-MM-dd" (columna: fecha)
    private String cachet;       // hasta 10 chars
    private Boolean realizado;   // columna: realizado
    private String fechaCreacion;// "yyyy-MM-dd" (columna: fecha_creacion)

    public DTOEvento() { }

    public DTOEvento(Long id, Long anuncioId, Long artistId,
                     String fecha, String cachet, Boolean realizado,
                     String fechaCreacion) {
        this.id = id;
        this.anuncioId = anuncioId;
        this.artistId = artistId;
        this.fecha = fecha;
        this.cachet = cachet;
        this.realizado = realizado;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAnuncioId() { return anuncioId; }
    public void setAnuncioId(Long anuncioId) { this.anuncioId = anuncioId; }

    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getCachet() { return cachet; }
    public void setCachet(String cachet) { this.cachet = cachet; }

    public Boolean getRealizado() { return realizado; }
    public void setRealizado(Boolean realizado) { this.realizado = realizado; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}

