package proyecto.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto.model.entities.CuentaCliente;
import proyecto.model.entities.Transaccion;

/**
 * Session Bean implementation class ManagerClientes
 */
@Stateless
@LocalBean
public class ManagerClientes {
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public ManagerClientes() {    	
    	
    }
    
     public List<CuentaCliente> findAllcuentaclientes(){
    	 System.out.println("-------hola enotro..al manager de cuentas-----");
    	String consulta = "select u from CuentaCliente u";
    	Query q = em.createQuery(consulta, CuentaCliente.class);    	
    	return q.getResultList();
    }
     
     public List<Transaccion> findAllTransaccion(){
    	 String consulta = "select t from Transaccion t";
    	 Query q = em.createQuery(consulta,Transaccion.class);
    	 return q.getResultList();
     }
     
     public List<Transaccion> SearchMovimientos(String fecchainicial, String tipomovimiento){
    	 String consulta = "select * from Transaccion where fecha_transaccion>='"+ fecchainicial
    	 +"' and id_tipo_transaccion="+ tipomovimiento+";";
    	 Query q = em.createQuery(consulta, Transaccion.class);
    	 return q.getResultList();
     }


}
