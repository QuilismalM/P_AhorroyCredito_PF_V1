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
	 public CuentaCliente buscarCuentaCliente(int nroCuentaCl) {
	   	return em.find(CuentaCliente.class,nroCuentaCl);
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
	public void insertarSolucitudP(BigDecimal valor_solicitud,String estado_solicitud,Date fecha_solicitud,int nroCuentaCl,int nro_meses) {
		SolicitudP solicitudP = new SolicitudP();
		CuentaCliente cuentaCliente= buscarCuentaCliente(nroCuentaCl);
		solicitudP.setValorSolicitudp(valor_solicitud);
		solicitudP.setEstadoSolicitud(estado_solicitud);
		solicitudP.setFechaSolicitud(fecha_solicitud);
		solicitudP.setCuentaCliente(cuentaCliente);
		solicitudP.setNroMesesSolicitud(nro_meses);
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
	public SolicitudP buscarSolicitudP(int idsolicitud) {
		return em.find(SolicitudP.class, idsolicitud);
	}
	public void actualizarSolicitudP(SolicitudP solicitudP) throws Exception {
		SolicitudP s = buscarSolicitudP(solicitudP.getIdSolicitud());
		if (s == null)
			throw new Exception("No existe la solicitud.");
		s.setValorSolicitudp(solicitudP.getValorSolicitudp());
		s.setNroMesesSolicitud(solicitudP.getNroMesesSolicitud());
		em.merge(s);
	}

//	public void actualizarSolicitudP(SolicitudP solicitudP) throws Exception {
//		SolicitudP t = findSolicitudPByIdSolicitud(solicitudP.getIdSolicitud());
//		if (t == null)
//			throw new Exception("No  existe solicitud");
//		em.merge(t);
//	}

}
