package pe.edu.upc.backend.security;

import org.springframework.security.core.GrantedAuthority;
import pe.edu.upc.backend.entities.Authority;

public class AuthoritySecurity implements GrantedAuthority {

    private Authority authority;

    /* Metodo a implementar de la interfaz GrantedAuthority */

    @Override
    public String getAuthority() {
        return authority.getName();
    }

    /* Metodos generados de acceso*/

    public AuthoritySecurity(Authority authority) {
        this.authority = authority;
    }

    public AuthoritySecurity() {
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthoritySecurity{" +
                "authority=" + authority +
                '}';
    }
}
