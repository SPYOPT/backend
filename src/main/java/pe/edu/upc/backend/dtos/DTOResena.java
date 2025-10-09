package pe.edu.upc.backend.dtos;

public class DTOResena {
    private Integer id;
    private Integer idEvento;
    private Integer idRestaurante;
    private Integer idArtista;
    private String puntuacion;
    private String comentario;
    private String fechaResena;

    public DTOResena() {
    }

    public DTOResena(Integer id, Integer idEvento, Integer idRestaurante, Integer idArtista,
                     String puntuacion, String comentario, String fechaResena) {
        this.id = id;
        this.idEvento = idEvento;
        this.idRestaurante = idRestaurante;
        this.idArtista = idArtista;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
        this.fechaResena = fechaResena;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdEvento() { return idEvento; }
    public void setIdEvento(Integer idEvento) { this.idEvento = idEvento; }

    public Integer getIdRestaurante() { return idRestaurante; }
    public void setIdRestaurante(Integer idRestaurante) { this.idRestaurante = idRestaurante; }

    public Integer getIdArtista() { return idArtista; }
    public void setIdArtista(Integer idArtista) { this.idArtista = idArtista; }

    public String getPuntuacion() { return puntuacion; }
    public void setPuntuacion(String puntuacion) { this.puntuacion = puntuacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public String getFechaResena() { return fechaResena; }
    public void setFechaResena(String fechaResena) { this.fechaResena = fechaResena; }
}

