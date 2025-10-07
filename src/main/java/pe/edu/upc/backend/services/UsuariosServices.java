package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.UsuariosDTO;
import pe.edu.upc.backend.entities.Usuarios;

import java.time.LocalDate;
import java.util.List;

public interface UsuariosServices {
    public UsuariosDTO createUsuarios(UsuariosDTO usuarios);
    public List<UsuariosDTO> findAllUsuarios();
    public UsuariosDTO findUsuarioById(Long idUsuario);
    public void deleteUsuarioById(Long idUsuario);
    public Usuarios findByEmail(String email);
    public List<Usuarios> findByTelefono(String telefono);
    public List<Usuarios> findUsuariosPorDominioEmail(String dominio);
    public List<Usuarios> findUsuariosPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
}
