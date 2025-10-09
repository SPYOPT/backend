package pe.edu.upc.backend.dtos;

public class DTORestaurante {
    private Integer id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private Integer aforoMesas;
    private String telefono;
    private String email;
    private String fechaCreacion;
    private Long idUsuario;

    public DTORestaurante() {
    }

    public DTORestaurante(Integer id, String nombre, String direccion, String ciudad, Integer aforoMesas,
                          String telefono, String email, String fechaCreacion, Long idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.aforoMesas = aforoMesas;
        this.telefono = telefono;
        this.email = email;
        this.fechaCreacion = fechaCreacion;
        this.idUsuario = idUsuario;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public Integer getAforoMesas() { return aforoMesas; }
    public void setAforoMesas(Integer aforoMesas) { this.aforoMesas = aforoMesas; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
}
