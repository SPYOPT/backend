package pe.edu.upc.backend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.backend.entities.Postulaciones;
import pe.edu.upc.backend.repositories.PostulacionesRepository;
import pe.edu.upc.backend.services.PostulacionesServices;

import java.util.List;
@Service
public class PostulacionesServiceImp implements PostulacionesServices {
    @Autowired
    PostulacionesRepository postulacionesRepository;

    @Override
    public Postulaciones add(Postulaciones postulaciones) {
        //validar si existe artista y anuncio
        if (postulaciones.getAnuncios()==null || postulaciones.getArtist() == null) {
            return null;
        }
        return postulacionesRepository.save(postulaciones);
    }

    @Override
    public void delete(Long id) {
        Postulaciones postulacionFound = findById(id);
        if (postulacionFound==null){
            return;
        }
        postulacionesRepository.deleteById(id);
    }

    @Override
    public Postulaciones findById(Long id) {
        return postulacionesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Postulaciones> listAll() {
        return postulacionesRepository.findAll();
    }
}
