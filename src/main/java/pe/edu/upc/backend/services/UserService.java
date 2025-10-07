package pe.edu.upc.backend.services;

import pe.edu.upc.backend.dtos.DTOUser;
import pe.edu.upc.backend.entities.User;

public interface UserService {

    public User findById (Long id);
    public DTOUser findByIdDTO (Long id);
    public User findByUsername(String username);

    public User addUser(DTOUser dtoUser);
    public User addUser(User user);
    //aqui se va a usar un DTO



}
