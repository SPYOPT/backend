package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.backend.entities.Usuarios;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {

    public Usuarios findByEmail(String email);

    public List<Usuarios> findByTelefono(String telefono);

    @Query("SELECT u FROM Usuarios u WHERE u.email LIKE %:dominio%")
    List<Usuarios> findUsuariosPorDominioEmail(String dominio);

    @Query("SELECT u FROM Usuarios u WHERE u.date BETWEEN :fechaInicio AND :fechaFin")
    List<Usuarios> findUsuariosPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
}
