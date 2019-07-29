package proyecto.model.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto.model.entities.CuentaCliente;
import proyecto.model.entities.Empresa;
import proyecto.model.entities.EstadoCuenta;
import proyecto.model.entities.Rol;
import proyecto.model.entities.TipoCuenta;
import proyecto.model.entities.Usuario;


/**
 * Session Bean implementation class ManagerUsuario
 */
@Stateless
@LocalBean
public class ManagerCliente {
	@PersistenceContext
	private EntityManager em;
    public ManagerCliente() {
        // TODO Auto-generated constructor stub
    }

	public List<Usuario> findAllUsuarios() {
		String consulta = "SELECT u FROM Usuario u where id_rol=2";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();
//		Usuario user = (Usuario) em
//				.createQuery("SELECT u from Usuario u where u.id_rol=:rol")
//				.setParameter("rol", '1').getResultList();
//		return (List<Usuario>) user;
	}
	//Query q = em.createQuery("SELECT u from Usuario u where u.id_rol=:rol".setParameter("rol", 1), Usuario.class);

	public List<Usuario> findAllUsuarios2() {
		String consulta = "SELECT u FROM Usuario u where id_rol=2";
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
	   public List<CuentaCliente> findAllCuentaCliente(){
			String sentencia ="SELECT c FROM CuentaCliente c";
			Query q = em.createQuery(sentencia,CuentaCliente.class);
			return q.getResultList();
		}

	   public List<TipoCuenta> findAllTipoCuenta(){
			String sentencia ="SELECT t FROM TipoCuenta t";
			Query q = em.createQuery(sentencia,TipoCuenta.class);
			return q.getResultList();
		}
	   
	   public List<EstadoCuenta> findAllEstadoCuenta(){
			String sentencia ="SELECT e FROM EstadoCuenta e";
			Query q = em.createQuery(sentencia,EstadoCuenta.class);
			return q.getResultList();
		}
	   
	public List<Usuario> findUsuarioByCedula(String cedula) {
		String consulta = "SELECT u FROM Usuario u where u.cedulaUsuario='" + cedula + "'";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();

	}
	 public List<CuentaCliente> buscarporCodigo(int nroCuentaCliente){
		 String consulta = "SELECT c FROM CuentaCliente c where c.nroCuentaCliente='"+nroCuentaCliente+"'";
		 Query q = em.createQuery(consulta, CuentaCliente.class);  
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
	
//	public void insertarCuentaCliente(CuentaCliente cuentaCliente) throws Exception {
//		if (buscarporCodigo(cuentaCliente.getNroCuentaCl()).size() > 0)
//			throw new Exception("Ya existe la cedula indicada.");
//		em.persist(cuentaCliente);
//	}
//	
	
	 public Usuario buscarporCodigoUsuario(int idUsuarios){
		 Usuario usuario = em.find(Usuario.class, idUsuarios);
		 return usuario;
	    }
	 public EstadoCuenta buscarporCodigoEstadoCuenta(int idEstadoCuenta){
		 EstadoCuenta estadoCuenta= em.find(EstadoCuenta.class, idEstadoCuenta);
		 return estadoCuenta;
	    }
	 public TipoCuenta buscarporCodigoTipoCuenta(int idTipoCuenta){
		 TipoCuenta tipoCuenta = em.find(TipoCuenta.class, idTipoCuenta);
		 return tipoCuenta;
	    }
	
//	public void insertarCuentaCliente(BigDecimal saldoCuenta,BigDecimal interesCuenta, int idUsuarios, int idTipoCuenta, int idEstadoCuenta) {
//	    	CuentaCliente cuentaCliente= new CuentaCliente();
//	    	Usuario usuario= buscarporCodigoUsuario(idUsuarios);
//	    	TipoCuenta tipoCuenta=buscarporCodigoTipoCuenta(idTipoCuenta);
//	    	EstadoCuenta estadoCuenta=buscarporCodigoEstadoCuenta(idEstadoCuenta);
//	    	
//	    	cuentaCliente.setSaldoCuenta(saldoCuenta);
//	    	cuentaCliente.setInteresCuenta(interesCuenta);
//	    			cuentaCliente.setUsuario(usuario);
//	    	cuentaCliente.setTipoCuenta(tipoCuenta);
//	    	cuentaCliente.setEstadoCuenta(estadoCuenta);
//	    em.persist(cuentaCliente);
//	    }
		public void insertarCuentaCliente(BigDecimal saldoCuenta,BigDecimal interesCuenta, int idUsuarios, int idTipoCuenta, int idEstadoCuenta) {
	    	CuentaCliente cuentaCliente= new CuentaCliente();
	    	Usuario user= buscarporCodigoUsuario(idUsuarios);
	    	TipoCuenta tipoCuenta=buscarporCodigoTipoCuenta(idTipoCuenta);
	    	EstadoCuenta estadoCuenta=buscarporCodigoEstadoCuenta(idEstadoCuenta);
	    	
	    	cuentaCliente.setSaldoCuenta(saldoCuenta);
	    	cuentaCliente.setInteresCuenta(interesCuenta);
	    			cuentaCliente.setUsuario(user);
	    	cuentaCliente.setTipoCuenta(tipoCuenta);
	    	cuentaCliente.setEstadoCuenta(estadoCuenta);
	    em.persist(cuentaCliente);
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