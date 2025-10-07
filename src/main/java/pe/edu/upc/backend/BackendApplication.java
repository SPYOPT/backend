package pe.edu.upc.backend;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.edu.upc.backend.entities.Authority;
import pe.edu.upc.backend.entities.User;
import pe.edu.upc.backend.services.AuthorityService;
import pe.edu.upc.backend.services.UserService;

import java.util.List;


@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner startConfiguration(
            UserService userService,
            AuthorityService authorityService

    ){
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



        };
    }
}







