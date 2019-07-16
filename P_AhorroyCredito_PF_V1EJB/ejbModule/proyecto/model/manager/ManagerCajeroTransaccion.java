package proyecto.model.manager;

import java.math.BigDecimal;
import java.util.Date;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto.model.entities.CuentaCliente;
import proyecto.model.entities.Transaccion;
import proyecto.model.entities.Usuario;
import proyecto.model.entities.TipoTransaccion;



/**
 * Session Bean implementation class ManagerCajeroTransaccion
 */
@Stateless
@LocalBean
public class ManagerCajeroTransaccion {
	 @PersistenceContext
	 private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerCajeroTransaccion() {
       
    }
    public List<CuentaCliente> findAllcuentaCliente() {
		String consulta = "SELECT c FROM CuentaCliente c";
		Query q = em.createQuery(consulta, CuentaCliente.class);
		return q.getResultList();
	}
    public CuentaCliente buscarCuentaCliente(int nroCuenta) {
    	return em.find(CuentaCliente.class,nroCuenta);
    }
    
    public List<Usuario> findAllUsuarios() {
  		String consulta= "SELECT u FROM Usuario u";
  		Query q = em.createQuery(consulta,Usuario.class);
  		return q.getResultList();
  	}
    public Usuario buscarUsuario(int idUsuario) {
    	return em.find(Usuario.class,idUsuario);
    }
    public List<Transaccion> findAllTransaccion() {
  		String consulta= "SELECT t FROM Transaccion t";
  		Query q = em.createQuery(consulta,Transaccion.class);
  		return q.getResultList();
  	}
    public Transaccion buscarTransaccion(int idTransaccion) {
    	return em.find(Transaccion.class,idTransaccion);
    }
    public List<TipoTransaccion> findAllTipoTransaccion() {
  		String consulta= "SELECT t FROM TipoTransaccion t";
  		Query q = em.createQuery(consulta,TipoTransaccion.class);
  		return q.getResultList();
  	}
    public TipoTransaccion buscarTipoTransaccion(int idTipoTransaccion) {
    	return em.find(TipoTransaccion.class,idTipoTransaccion);
    }
    public void insertarTransaccion(int nroCuenta, int idTipoTransaccion , BigDecimal montoTransaccion ,Date fechaTransaccion,BigDecimal saldoTransaccion)  {
    	Transaccion transaccion=new Transaccion();
     	CuentaCliente cuentaCliente=buscarCuentaCliente(nroCuenta);
     	
        TipoTransaccion tipoTransaccion=buscarTipoTransaccion(idTipoTransaccion);
     	
     	transaccion.setCuentaCliente(cuentaCliente);
     	transaccion.setTipoTransaccion(tipoTransaccion);
     	transaccion.setMontoTransaccion(montoTransaccion);
     	transaccion.setFechaTransaccion(fechaTransaccion); 
     	transaccion.setSaldoTransaccion(saldoTransaccion);
     	em.persist(transaccion);
     
    }
    public void eliminarTransaccion(int idTransaccion ) {
    	Transaccion transaccion =buscarTransaccion(idTransaccion);
    	if(transaccion!=null)
    		em.remove(transaccion);
    }
   
    public void actuaizarTransferencia(Transaccion transaccion)  throws Exception{
    	Transaccion e=buscarTransaccion(transaccion.getIdTransaccion());
    	if(e==null)
    		throw new Exception("No existe la transaccion.");
    	e.setCuentaCliente(transaccion.getCuentaCliente());
    	e.setTipoTransaccion(transaccion.getTipoTransaccion());
    	e.setMontoTransaccion(transaccion.getMontoTransaccion());
    	e.setSaldoTransaccion(transaccion.getSaldoTransaccion());
    	em.merge(e);
    }
  
    
}
	
