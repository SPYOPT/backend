package pe.edu.upc.backend.serviceimpl;

import pe.edu.upc.backend.entities.Portfolio;
import pe.edu.upc.backend.services.PortfolioService;

import java.util.List;
import java.util.Optional;

public class PortfolioServiceImpl implements PortfolioService {

    @Override
    public List<Portfolio> list() {
        return List.of();
    }

    @Override
    public Optional<Portfolio> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Portfolio save(Portfolio portfolio) {
        return null;
    }
}
