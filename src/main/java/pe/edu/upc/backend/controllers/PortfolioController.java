package pe.edu.upc.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.backend.entities.Portfolio;
import pe.edu.upc.backend.services.PortfolioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/porfolios")
@CrossOrigin(origins = "*")
public class PortfolioController{
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping
    public List<Portfolio> list() {
        return portfolioService.list();
    }

    @GetMapping("/{id}")
    public Optional<Portfolio> getById(@PathVariable("id") Long id) {
        return portfolioService.findById(id);
    }

    @PostMapping
    public Portfolio create(@RequestBody Portfolio portfolio) {
        return portfolioService.save(portfolio);
    }

    @PutMapping("/{id}")
    public Portfolio update(@PathVariable("id") Long id, @RequestBody Portfolio portfolio) {
        portfolio.setId_portfolio(id);
        return portfolioService.save(portfolio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        portfolioService.delete(id);
    }
}
