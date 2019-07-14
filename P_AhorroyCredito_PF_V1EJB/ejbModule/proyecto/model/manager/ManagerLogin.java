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
 * Session Bean implementation class ManagerLogin
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


	public List<Rol> findAllRoles() {
		String consulta = "SELECT r FROM Rol r";
		Query q = em.createQuery(consulta, Rol.class);
		return q.getResultList();
	}
	public Usuario getUser(String username, String contrasena) {
		try {
			Usuario user = (Usuario) em
					.createQuery("SELECT u from Usuario u where u.username =:name and u.contrasena =:password")
					.setParameter("name", username).setParameter("password", contrasena).getSingleResult();
			System.out.println("aqui el usuauri"+user);
			return user;
		} catch (Exception e) {
	
			return null;
			
		}
	}

	

}
