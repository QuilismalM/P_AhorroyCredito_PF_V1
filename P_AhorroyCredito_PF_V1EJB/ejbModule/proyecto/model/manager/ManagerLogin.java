package proyecto.model.manager;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import proyecto.model.entities.Rol;
import proyecto.model.entities.Usuario;






/**
 * Session 	Bean implementation class ManagerLogin
 */
@Stateless
@LocalBean
public class ManagerLogin {
   @PersistenceContext
   private EntityManager em;
    /**
     * Default constructor. 
     */

    public ManagerLogin() {
        // TODO Auto-generated constructor stub
    }
    
    public List <Rol> findAllRoles() {
		String consulta = "SELECT r FROM Rol r";
		Query q = em.createQuery(consulta, Rol.class);
		return q.getResultList();
	}
    public Usuario findUsuarioByUsername(String username) {
		return em.find(Usuario.class,username);
	}
    @SuppressWarnings("unlikely-arg-type")
	public boolean comprobarUsuario(String username, String contrasena,String id_rol) throws Exception {
		Usuario u = findUsuarioByUsername(username);
		if (u == null)
			throw new Exception("No existe el usuario "+username);
//    	if(!u.getActivo())
//    	throw new Exception("El usuario no está activo.");
		if (u.getContrasena().equals(contrasena) && u.getRol().equals(id_rol))
			return true;
		throw new Exception("Contraseña no válida.");
	}
    
    
    

}
