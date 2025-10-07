package pe.edu.upc.backend.services;

import pe.edu.upc.backend.entities.Authority;

public interface AuthorityService {

    public Authority addAuthority(Authority authority);

    public Authority findByName(String authorityName);

}
