package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.dtos.UsuariosDTO;
import pe.edu.upc.backend.entities.Usuarios;
import pe.edu.upc.backend.serviceimpl.UsuariosServicesImpl;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosServicesImpl usuariosServicesImpl;

    @PostMapping
    public UsuariosDTO createUsuario(@RequestBody UsuariosDTO usuarioDTO) {
        return usuariosServicesImpl.createUsuarios(usuarioDTO);
    }

    @GetMapping
    public List<UsuariosDTO> getAllUsuarios() {
        return usuariosServicesImpl.findAllUsuarios();
    }


    @GetMapping("/{id}")
    public UsuariosDTO getUsuarioById(@PathVariable Long id) {
        return usuariosServicesImpl.findUsuarioById(id);
    }

    @DeleteMapping("/{idUsuario}")
    public void deleteUsuarioById(@PathVariable  Long idUsuario){
        usuariosServicesImpl.deleteUsuarioById(idUsuario);

    }

    @GetMapping("/email/{email}")
    public Usuarios getUsuarioByEmail(@PathVariable String email) {
        return usuariosServicesImpl.findByEmail(email);
    }

    @GetMapping("/telefono/{telefono}")
    public List<Usuarios> getUsuariosByTelefono(@PathVariable String telefono) {
        return usuariosServicesImpl.findByTelefono(telefono);
    }

    @GetMapping("/dominio/{dominio}")
    public List<Usuarios> getUsuariosPorDominioEmail(@PathVariable String dominio) {
        return usuariosServicesImpl.findUsuariosPorDominioEmail(dominio);
    }

    @GetMapping("/fechas")
    public List<Usuarios> getUsuariosPorRangoFechas(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {
        return usuariosServicesImpl.findUsuariosPorRangoFechas(fechaInicio, fechaFin);
    }
}