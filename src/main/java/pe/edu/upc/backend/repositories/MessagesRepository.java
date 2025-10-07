package pe.edu.upc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.backend.entities.Messages;
import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long> {

}
