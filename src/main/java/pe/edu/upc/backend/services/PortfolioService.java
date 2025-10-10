package pe.edu.upc.backend.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.backend.entities.Portfolio;

import java.util.List;
import java.util.Optional;

@Service
public interface PortfolioService {
    List<Portfolio> list();
    Optional<Portfolio> findById(long id);
    void  delete(long id);
    Portfolio save(Portfolio portfolio);
}
