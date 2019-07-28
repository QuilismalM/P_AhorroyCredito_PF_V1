package proyecto.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import proyecto.model.entities.CuentaCliente;
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
	private int cuenta;
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
	

	
	public LoginDT accederSistema(String username,String contrasena,int id_rol) throws Exception{
		Usuario usuario = (Usuario) em
				.createQuery("SELECT u from Usuario u where u.username =:name and u.contrasena =:password")
				.setParameter("name", username).setParameter("password", contrasena).getSingleResult();	
		if(usuario==null)
			throw new Exception("Error en usuario y/o clave.");
		if(!usuario.getContrasena().equals(contrasena))
			throw new Exception("Error en contraseña");
		if(!usuario.getRol().getIdRol().equals(id_rol))
			throw new Exception("Error en rol");
		
		LoginDT loginDTO=new LoginDT();
		
		loginDTO.setUsername(usuario.getUsername());
		loginDTO.setId_rol(usuario.getRol().getIdRol());
		loginDTO.setContrasena(usuario.getContrasena());
		loginDTO.setNombre_usuario(usuario.getNombreUsuario());
		loginDTO.setApellido_usuario(usuario.getApellidoUsuario());
		loginDTO.setCedula(usuario.getCedulaUsuario());
		loginDTO.setId_usuarios(usuario.getIdUsuarios());
		
		int a = usuario.getIdUsuarios();
		 String consulta = "select cl from  CuentaCliente cl where id_usuarios=" + a;
    	 Query q = em.createQuery(consulta, CuentaCliente.class);
    	 List<CuentaCliente> list = q.getResultList();
    	 for (CuentaCliente c : list) {
    		 cuenta= c.getNroCuentaCl();
			 //System.out.println("impresion de consulta de saldo="+ saldoactual);
    	 }
		
		//dependiendo del tipo de usuario, configuramos la ruta de acceso a las pags web:
		if(usuario.getRol().getIdRol().equals(1))
			loginDTO.setRutaAcceso("/indexAdministrador/indexAdministrador.xhtml");
		else if(usuario.getRol().getIdRol().equals(2))
			loginDTO.setRutaAcceso("/indexClientes/indexClientes.xhtml");
		else if (usuario.getRol().getIdRol().equals(3))
			loginDTO.setRutaAcceso("/indexCajero/IndexCajero.xhtml");
		else
			loginDTO.setRutaAcceso("/indexAtencionCliente/IndexAtencionCliente.xhtml");
		return loginDTO;
	}
	
	public CuentaCliente CuentaByIdUsuario(int idUsuario) {
		CuentaCliente cuentaCliente = (CuentaCliente) em.
				createQuery("SELECT c from CuentaCliente c JOIN c.usuario u where u.idUsuarios = :idUsuario")
				.setParameter("idUsuario", idUsuario).getSingleResult();
		return cuentaCliente;
	}


	public int getCuenta() {
		return cuenta;
	}


//	public void setCuenta(int cuenta) {
//		this.cuenta = cuenta;
//	}
	
//	String consulta = "Select s from SolicitudP s JOIN s.cuentaCliente c where c.nroCuentaCl = :nro_cuenta_cl";
//	Query query=em.createQuery(consulta);
//	query.setParameter("nro_cuenta_cl", nro_cuenta_cl);
//	return query.getResultList();
//	
}
