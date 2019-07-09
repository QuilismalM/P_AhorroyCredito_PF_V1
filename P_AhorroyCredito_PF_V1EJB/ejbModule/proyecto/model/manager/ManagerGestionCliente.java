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
 * Session Bean implementation class ManagerGestionCliente
 */
@Stateless
@LocalBean
public class ManagerGestionCliente {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerGestionCliente() {
        // TODO Auto-generated constructor stub
    }
  
    public List<CuentaCliente> listarCuentaCliente(){
		String sentencia ="SELECT c FROM CuentaCliente c";
		Query q = em.createQuery(sentencia,CuentaCliente.class);
		return q.getResultList();
	}
  
    public List<Rol> listarRol(){
    	String sentencia = "SELECT r FROM Rol r";
		Query q = em.createQuery(sentencia, Rol.class);
		return q.getResultList();
  	}
    
    public List<Empresa> listarEmpresa(){
    	String sentencia = "SELECT e FROM Empresa e";
		Query q = em.createQuery(sentencia, Empresa.class);
		return q.getResultList();
  	}
    public List<TipoCuenta> listarTipoCuenta(){
    	String sentencia = "SELECT t FROM TipoCuenta t";
		Query q = em.createQuery(sentencia, TipoCuenta.class);
		return q.getResultList();
  	}
    public List<EstadoCuenta> listarEstadoCuenta(){
    	String sentencia="SELECT e FROM EstadoCuenta e";
		Query q = em.createQuery(sentencia, EstadoCuenta.class);
		return q.getResultList();
  	}
    public CuentaCliente buscarporCodigo(int nro_cuenta_cliente){
        return em.find(CuentaCliente.class, nro_cuenta_cliente);
    }
	
	   public Usuario buscarPorCodigoUsuario(int idUsuarios) {
		   Usuario usuario= em.find(Usuario.class, idUsuarios);
  		   return usuario;
   }
   
//   public void insertarCuentaCliente(String cedulaUsuario, int idUsuarios, BigDecimal saldoCuenta,int nroCuentaCl, char direccionUsuario, int telefonoUsuario, char username) {
// 
//  CuentaCliente cuentaCliente=new CuentaCliente();
//  Usuario usuario= new Usuario();
//  //Usuario usuario=buscarPorCodigoUsuario(idUsuarios);
//  cuentaCliente.setUsuario(usuario);
//  cuentaCliente.setSaldoCuenta(saldoCuenta);
// 
//
//  cuentaCliente.setNroCuentaCl(nroCuentaCl);
// // cuentaCliente.setTipoCuenta(tipoCuenta);
//  
//  
//    	em.persist(cuentaCliente);
//    }
	   
	   public void insertaCuentaCliente(int idUsuarios, BigDecimal saldoCuenta,int nroCuentaCl) {
		   CuentaCliente cuentaCliente = new CuentaCliente();
		   Usuario usuario=buscarPorCodigoUsuario(idUsuarios);
		   cuentaCliente.setNroCuentaCl(nroCuentaCl);
		   cuentaCliente.setSaldoCuenta(saldoCuenta);
		   cuentaCliente.setUsuario(usuario);
	    	
	    	em.persist(cuentaCliente);
	    }
	   
	   
		public List<CuentaCliente> findCuentaClientenroCuentaCl(int nroCuentaCl) {
			String consulta ="SELECT c FROM CuentaCliente c where c.nroCuentaCl='"+nroCuentaCl+"'";
			Query q = em.createQuery(consulta, CuentaCliente.class);
			return q.getResultList();

		}
//		public void insertarCuentaCliente(CuentaCliente cuentaCliente) throws Exception {
//			if (findCuentaClientenroCuentaCl(cuentaCliente.getNroCuentaCl()).size() >0) 
//				throw new Exception("Ya existe la cedula indicada.");
//				em.persist(cuentaCliente);	
//			}
			
   
  
    
    public void eliminarCuentaCliente(int nroCuentaCl) {
    	CuentaCliente cuentaCliente= buscarporCodigo(nroCuentaCl);
    	
    	if(cuentaCliente!=null)
    		em.remove(cuentaCliente);
    }
    
    public void actualizarCuentaCliente(CuentaCliente cuentaCliente) throws Exception {
    	CuentaCliente e = buscarporCodigo(cuentaCliente.getNroCuentaCl());
    	if(cuentaCliente==null)
    		throw new Exception("No existe el tratamiento especificada.");
    	//e.setIdUsuarios(cedulaUsuario);
    	e.setNroCuentaCl(cuentaCliente.getNroCuentaCl());
    	e.setUsuario(cuentaCliente.getUsuario());
    	e.setSaldoCuenta(cuentaCliente.getSaldoCuenta());
    	e.setInteresCuenta(cuentaCliente.getInteresCuenta());
    	em.merge(e);
    	
    }
    
}
