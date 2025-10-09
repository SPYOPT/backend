package pe.edu.upc.backend.dtos;

public class DTOReserva {
    private Integer id;
    private Long idUsuario;
    private Integer idRestaurante;
    private String fechaReserva;
    private Integer numPersonas;
    private Boolean estado;
    private String fechaCreacion;

    public DTOReserva() {
    }

    public DTOReserva(Integer id, Long idUsuario, Integer idRestaurante, String fechaReserva,
                      Integer numPersonas, Boolean estado, String fechaCreacion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idRestaurante = idRestaurante;
        this.fechaReserva = fechaReserva;
        this.numPersonas = numPersonas;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public Integer getIdRestaurante() { return idRestaurante; }
    public void setIdRestaurante(Integer idRestaurante) { this.idRestaurante = idRestaurante; }

    public String getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(String fechaReserva) { this.fechaReserva = fechaReserva; }

    public Integer getNumPersonas() { return numPersonas; }
    public void setNumPersonas(Integer numPersonas) { this.numPersonas = numPersonas; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
