package pe.edu.upc.backend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.backend.entities.Anuncios;
import pe.edu.upc.backend.repositories.AnunciosRepository;
import pe.edu.upc.backend.services.AnunciosServices;

import java.util.List;

@Service
public class AnunciosServiceImp implements AnunciosServices {

    @Autowired
    AnunciosRepository anunciosRepository;

    @Override
    public Anuncios add(Anuncios anuncios) {
        if (anuncios.getTitulo()==null || anuncios.getTitulo().isBlank()) {
            return null;
        }
        //Validar si el nombre ya esta registrado
        if (!anunciosRepository.findByTitulo(anuncios.getTitulo()).isEmpty()) {
            System.out.println(anunciosRepository.findByTitulo(anuncios.getTitulo()));
            return null;

        }
        return anunciosRepository.save(anuncios);
    }

    @Override
    public void delete(Long id) {
        Anuncios anucioFound = findById(id);
        if (anucioFound==null){
            return;
        }
        anunciosRepository.deleteById(id);

    }

    @Override
    public Anuncios findById(Long id) {
        return anunciosRepository.findById(id).orElse(null);
    }

    @Override
    public List<Anuncios> listAll() {
        return anunciosRepository.findAll();
    }
}
