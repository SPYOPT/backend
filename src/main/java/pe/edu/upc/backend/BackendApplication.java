package pe.edu.upc.backend;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.edu.upc.backend.dtos.SoporteDTO;
import pe.edu.upc.backend.entities.Authority;
import pe.edu.upc.backend.entities.Soporte;
import pe.edu.upc.backend.entities.User;
import pe.edu.upc.backend.repositories.UserRepository;
import pe.edu.upc.backend.services.AuthorityService;
import pe.edu.upc.backend.services.SoporteService;
import pe.edu.upc.backend.services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner startConfiguration(
            UserService userService,
            AuthorityService authorityService,

            UserRepository userRepository, SoporteService soporteService){
        return args -> {

            Authority authority1 = authorityService.addAuthority(new Authority("ROLE_ADMIN"));
            Authority authority2 = authorityService.addAuthority(new Authority("ROLE_USER"));
            Authority authority3 = authorityService.addAuthority(new Authority("ROLE_ASSIST"));

            userService.addUser(new User(null, "gmorip","pass",true,
                    List.of(authority1, authority2)));

            userService.addUser(new User(null, "qwerty","pass",true,
                    List.of(authority2)));

            userService.addUser(new User(null, "asdfg","pass",true,
                    List.of(authority3)));

            Map<String, User> users = userRepository.findAll().stream()
                    .collect(Collectors.toMap(User::getUsername, u -> u));

            Long idGmorip = users.get("gmorip").getId();
            Long idQwerty = users.get("qwerty").getId();
            Long idAsdfg  = users.get("asdfg").getId();

            //----------------
            //DATOS PARA SOPORTE
            //----------------

            // Abierto (admin)
            soporteService.add(new SoporteDTO(
                    null,
                    "No puedo iniciar sesión",
                    "Error de credenciales al iniciar sesión",
                    true,
                    LocalDate.now().minusDays(3),
                    null,
                    idGmorip
            ));

            // Abierto (user)
            soporteService.add(new SoporteDTO(
                    null,
                    "La app se cierra al abrir",
                    "Se cierra inmediatamente después del splash",
                    true,
                    LocalDate.now().minusDays(2),
                    null,
                    idQwerty
            ));

            // Abierto (user)
            soporteService.add(new SoporteDTO(
                    null,
                    "Necesito permisos de lectura",
                    "Solicito acceso de solo lectura al módulo X",
                    true,
                    LocalDate.now().minusDays(1),
                    null,
                    idQwerty
            ));

            // Cerrado (assist)
            soporteService.add(new SoporteDTO(
                    null,
                    "Consulta de facturación",
                    "No recibí la factura de septiembre",
                    false,
                    LocalDate.now().minusDays(5),
                    LocalDate.now().minusDays(4),
                    idAsdfg
            ));

            // Otro abierto (assist)
            soporteService.add(new SoporteDTO(
                    null,
                    "Error en reporte",
                    "El PDF se genera en blanco para el período 09/2025",
                    true,
                    LocalDate.now().minusDays(6),
                    null,
                    idAsdfg
            ));

            // Otro cerrado (admin)
            soporteService.add(new SoporteDTO(
                    null,
                    "Recuperar contraseña",
                    "Olvidé mi contraseña, solicito restablecimiento",
                    false,
                    LocalDate.now().minusDays(10),
                    LocalDate.now().minusDays(9),
                    idGmorip
            ));


            //-------------------------

        };
    }
}







