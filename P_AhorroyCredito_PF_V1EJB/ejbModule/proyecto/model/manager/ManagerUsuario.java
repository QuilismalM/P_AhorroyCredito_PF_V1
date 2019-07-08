package proyecto.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto.model.entities.Usuario;
import proyecto.model.entities.Rol;
import proyecto.model.entities.Empresa;

/**
 * Session Bean implementation class ManagerUsuario
 */
@Stateless
@LocalBean
public class ManagerUsuario {

	@PersistenceContext
	private EntityManager em;

	public ManagerUsuario() {

	}

	public List<Usuario> findAllUsuarios() {
		String consulta = "SELECT u FROM Usuario u";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();
	}

	public List<Rol> findAllRoles() {
		String consulta = "SELECT r FROM Rol r";
		Query q = em.createQuery(consulta, Rol.class);
		return q.getResultList();
	}

	public List<Empresa> findAllEmpresas() {
		String consulta = "SELECT e FROM Empresa e";
		Query q = em.createQuery(consulta, Empresa.class);
		return q.getResultList();
	}

	public List<Usuario> findUsuarioByCedula(String cedula) {
		String consulta = "SELECT u FROM Usuario u where u.cedulaUsuario='" + cedula + "'";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();

	}

	public Usuario findUsuarioById(int id) {
		return em.find(Usuario.class, id);
	}

	public void insertarUsuario(Usuario usuario) throws Exception {
		if (findUsuarioByCedula(usuario.getCedulaUsuario()).size() > 0)
			throw new Exception("Ya existe la cedula indicada.");
		em.persist(usuario);
	}

	public void eliminarUsuario(int id) {
		Usuario usuario = findUsuarioById(id);
		if (usuario != null)
			em.remove(usuario);
	}

	public void actualizarUsuario(Usuario usuario) throws Exception  {
		Usuario user = findUsuarioById(usuario.getIdUsuarios());
		if (usuario == null)
			throw new Exception("no existe.");
		user.setCedulaUsuario(usuario.getCedulaUsuario());
		user.setNombreUsuario(usuario.getNombreUsuario());
		user.setApellidoUsuario(usuario.getApellidoUsuario());
		user.setDireccionUsuario(usuario.getDireccionUsuario());
		user.setCorreoUsario(usuario.getCorreoUsario());
		user.setTelefonoUsuario(usuario.getTelefonoUsuario());
		user.setUsername(usuario.getUsername());
		user.setContrasena(usuario.getContrasena());
		user.setRol(usuario.getRol());
		user.setEmpresa(usuario.getEmpresa());
		em.merge(user);
	}

}
