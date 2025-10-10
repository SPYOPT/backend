package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.DTOPostulacion;
import pe.edu.upc.backend.entities.Anuncios;
import pe.edu.upc.backend.entities.Postulaciones;

import java.util.List;

public interface PostulacionesServices {
    public Postulaciones add(Postulaciones postulaciones);//add

    public void delete(Long id); //delete

    public Postulaciones findById(Long id); //find by id

    public List<Postulaciones> listAll(); //listar
}
