package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.DTOAnuncio;
import pe.edu.upc.backend.entities.Anuncios;

import java.util.List;

public interface AnunciosServices {
    public Anuncios add(Anuncios anuncios);//add

    public void delete(Long id); //delete

    public Anuncios findById(Long id); //find by id

    public List<Anuncios> listAll(); //listar

}
