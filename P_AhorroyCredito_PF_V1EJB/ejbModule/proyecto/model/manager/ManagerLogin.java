package proyecto.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import proyecto.model.entities.Rol;
import proyecto.model.entities.Usuario;
import proyecto.model.login.LoginDT;


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
//	public Usuario getUser(String username, String contrasena) {
//		try {
//			Usuario user = (Usuario) em
//					.createQuery("SELECT u from Usuario u where u.username =:name and u.contrasena =:password")
//					.setParameter("name", username).setParameter("password", contrasena).getSingleResult();
//			System.out.println("aqui el usuauri"+user);
//			return user;
//		} catch (Exception e) {
//	
//			return null;
//			
//		}
//	}
	
	public LoginDT accederSistema(String username,String contrasena,int id_rol) throws Exception{
		Usuario usuario = (Usuario) em
				.createQuery("SELECT u from Usuario u where u.username =:name and u.contrasena =:password")
				.setParameter("name", username).setParameter("password", contrasena).getSingleResult();
		
		
		if(usuario==null)
			throw new Exception("Error en usuario y/o clave.");
		if(!usuario.getContrasena().equals(contrasena))
			throw new Exception("Error en usuario y/o clave.");
		if(!usuario.getRol().getIdRol().equals(id_rol))
			throw new Exception("Error en usuario y/o clave.");
		
		LoginDT loginDTO=new LoginDT();
		
		loginDTO.setUsername(usuario.getUsername());
		loginDTO.setId_rol(usuario.getRol().getIdRol());
		loginDTO.setContrasena(usuario.getContrasena());
		
		//dependiendo del tipo de usuario, configuramos la ruta de acceso a las pags web:
		if(usuario.getRol().getIdRol().equals(1))
			loginDTO.setRutaAcceso("indexAdministrador/indexAdministrador.xhtml");
		else if(usuario.getRol().getIdRol().equals(2))
			loginDTO.setRutaAcceso("indexClientes/indexClientes.xhtml");
		else if (usuario.getRol().getIdRol().equals(3))
			loginDTO.setRutaAcceso("indexCajero/IndexCajero.xhtml");
		else
			loginDTO.setRutaAcceso("indexAtencionCliente/IndexAtencionCliente.xhtml");
		return loginDTO;
	}

	

}
