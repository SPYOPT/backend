package pe.edu.upc.backend.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.backend.dtos.UsuariosDTO;
import pe.edu.upc.backend.entities.Usuarios;
import pe.edu.upc.backend.repositories.UsuarioRepository;
import pe.edu.upc.backend.services.UsuariosServices;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuariosServicesImpl implements UsuariosServices {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UsuariosDTO createUsuarios(UsuariosDTO usuarios) {
        Usuarios usuario = modelMapper.map(usuarios, Usuarios.class);
        return modelMapper.map(usuarioRepository.save(usuario), UsuariosDTO.class);
    }

    @Override
    public List<UsuariosDTO> findAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(u -> modelMapper.map(u,UsuariosDTO.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public UsuariosDTO findUsuarioById(Long idUsuario) {
        return modelMapper.map(usuarioRepository.findById(idUsuario).orElse(null), UsuariosDTO.class);
    }

    @Override
    public void deleteUsuarioById(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    @Override
    public Usuarios findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }


    @Override
    public List<Usuarios> findByTelefono(String telefono) {
        return usuarioRepository.findByTelefono(telefono);
    }


    @Override
    public List<Usuarios> findUsuariosPorDominioEmail(String dominio) {
        return usuarioRepository.findUsuariosPorDominioEmail(dominio);
    }

    @Override
    public List<Usuarios> findUsuariosPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return usuarioRepository.findUsuariosPorRangoFechas(fechaInicio, fechaFin);
    }

}
