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
import proyecto.model.entities.SolicitudP;

/**
 * Session Bean implementation class ManagerSolicitudPrestamo
 */
@Stateless
@LocalBean
public class ManagerSolicitudPrestamo {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerSolicitudPrestamo() {
		// TODO Auto-generated constructor stub
	}
	 public CuentaCliente buscarCuentaCliente(int nroCuenta) {
	   	return em.find(CuentaCliente.class,nroCuenta);
	 }

	public SolicitudP findSolicitudPByIdSolicitud(int id_solicitudp) {
		return em.find(SolicitudP.class, id_solicitudp);
	}

	public List<SolicitudP> findAllSolicitudP() {
		String consulta = "SELECT s FROM SolicitudP s";
		Query q = em.createQuery(consulta, SolicitudP.class);
		return q.getResultList();
	}
	  public List<CuentaCliente> findAllcuentaCliente() {
			String consulta = "SELECT c FROM CuentaCliente c";
			Query q = em.createQuery(consulta, CuentaCliente.class);
			return q.getResultList();
		}
	public void insertarSolucitudP(int id_solicitud, BigDecimal valor_solicitud,String estado_solicitud,Date fecha_solicitud,int nro_cuenta_cliente) {
		SolicitudP solicitudP = new SolicitudP();
		CuentaCliente cuentaCliente= buscarCuentaCliente(nro_cuenta_cliente);
		solicitudP.setIdSolicitud(id_solicitud);
		solicitudP.setValorSolicitudp(valor_solicitud);
		solicitudP.setEstadoSolicitud(estado_solicitud);
		solicitudP.setFechaSolicitud(fecha_solicitud);
		solicitudP.setCuentaCliente(cuentaCliente);
		
		em.persist(solicitudP);

	}
	
//	   public void insertarTransaccion(int nroCuenta, int idTipoTransaccion , BigDecimal montoTransaccion ,Date fechaTransaccion,BigDecimal saldoTransaccion)  {
//	    	Transaccion transaccion=new Transaccion();
//	     	CuentaCliente cuentaCliente=buscarCuentaCliente(nroCuenta);
//	     	
//	        TipoTransaccion tipoTransaccion=buscarTipoTransaccion(idTipoTransaccion);
//	     	
//	     	transaccion.setCuentaCliente(cuentaCliente);
//	     	transaccion.setTipoTransaccion(tipoTransaccion);
//	     	transaccion.setMontoTransaccion(montoTransaccion);
//	     	transaccion.setFechaTransaccion(fechaTransaccion); 
//	     	transaccion.setSaldoTransaccion(saldoTransaccion);
//	     	em.persist(transaccion);
//	     
//	    }

	public void eliminarSolicitudP(int id_solicitudp) {
		SolicitudP solicitudP = findSolicitudPByIdSolicitud(id_solicitudp);
		if (solicitudP != null)
			em.remove(solicitudP);
	}

	public void actualizarSolicitudP(SolicitudP solicitudP) throws Exception {
		SolicitudP t = findSolicitudPByIdSolicitud(solicitudP.getIdSolicitud());
		if (t == null)
			throw new Exception("No  existe solicitud");
		em.merge(t);
	}

}
