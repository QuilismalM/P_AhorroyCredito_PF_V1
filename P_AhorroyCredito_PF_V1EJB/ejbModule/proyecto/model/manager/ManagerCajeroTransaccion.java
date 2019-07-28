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
import sun.misc.CEStreamExhausted;
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
		return em.find(CuentaCliente.class, nroCuenta);
	}

	public List<Usuario> findAllUsuarios() {
		String consulta = "SELECT u FROM Usuario u";
		Query q = em.createQuery(consulta, Usuario.class);
		return q.getResultList();
	}

	public Usuario buscarUsuario(int idUsuario) {
		return em.find(Usuario.class, idUsuario);
	}

	public List<Transaccion> findAllTransaccion() {
		String consulta = "SELECT t FROM Transaccion t";
		Query q = em.createQuery(consulta, Transaccion.class);
		return q.getResultList();
	}

	public Transaccion buscarTransaccion(int idTransaccion) {
		return em.find(Transaccion.class, idTransaccion);
	}

	public List<TipoTransaccion> findAllTipoTransaccion() {
		String consulta = "SELECT t FROM TipoTransaccion t where id_tipo_transaccion=1 or id_tipo_transaccion=2";
		Query q = em.createQuery(consulta, TipoTransaccion.class);
		return q.getResultList();
	}

	public TipoTransaccion buscarTipoTransaccion(int idTipoTransaccion) {
		return em.find(TipoTransaccion.class, idTipoTransaccion);
	}

	public double ConsultarSaldo(int nro_cuenta) {
		double saldoactual = 0;
		String consulta = "select cl from  CuentaCliente cl where nro_cuenta_cl=" + nro_cuenta;
		Query q = em.createQuery(consulta, CuentaCliente.class);
		List<CuentaCliente> list = q.getResultList();
		for (CuentaCliente c : list) {
			saldoactual = c.getSaldoCuenta().doubleValue(); // convertimos getsaldocuenta de Bigdecimal a int c0n
															// intValue()
			// System.out.println("impresion de consulta de saldo="+ saldoactual);
		}

		return saldoactual;

	}

	public void insertarTransaccion(int nroCuenta, int idTipoTransaccion, double montoTransaccion,
			Date fechaTransaccion, double SaldoTransaccion) {
		double saldo_actual = ConsultarSaldo(nroCuenta);

		Transaccion transaccion = new Transaccion();
		CuentaCliente cuentaCliente = buscarCuentaCliente(nroCuenta);
		TipoTransaccion tipoTransaccion = buscarTipoTransaccion(idTipoTransaccion);

		transaccion.setCuentaCliente(cuentaCliente);
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setMontoTransaccion(new BigDecimal(montoTransaccion));
		transaccion.setFechaTransaccion(fechaTransaccion);
		if (idTipoTransaccion == 1) {
			transaccion.setSaldoTransaccion(new BigDecimal(saldo_actual + montoTransaccion));
		} else if (idTipoTransaccion == 2) {
			transaccion.setSaldoTransaccion(new BigDecimal(saldo_actual - montoTransaccion));
		}
		em.persist(transaccion);

		//////
		if (idTipoTransaccion == 1) {
			cuentaCliente = buscarCuentaCliente(nroCuenta);
			cuentaCliente.setSaldoCuenta(new BigDecimal(saldo_actual + montoTransaccion));

		}
		if(idTipoTransaccion == 2) {
		cuentaCliente = buscarCuentaCliente(nroCuenta);
		cuentaCliente.setSaldoCuenta(new BigDecimal(saldo_actual - montoTransaccion));
		
		}
		em.merge(cuentaCliente);
	}

	public void eliminarTransaccion(int idTransaccion) {
		
		Transaccion transaccion = buscarTransaccion(idTransaccion);
		if (transaccion != null)
			em.remove(transaccion);
		double saldoElim=ConsultarSaldo(transaccion.getCuentaCliente().getNroCuentaCl());
		
		
		
	}

	public void actuaizarTransferencia(Transaccion transaccion) throws Exception {
		Transaccion e = buscarTransaccion(transaccion.getIdTransaccion());
		if (e == null)
			throw new Exception("No existe la transaccion.");
		e.setFechaTransaccion(transaccion.getFechaTransaccion());
		e.setCuentaCliente(transaccion.getCuentaCliente());
		e.setTipoTransaccion(transaccion.getTipoTransaccion());
		e.setMontoTransaccion(transaccion.getMontoTransaccion());
		e.setSaldoTransaccion(transaccion.getSaldoTransaccion());
		em.merge(e);
	}

	public List<Transaccion> findTransaccionesByNrocuenta(int nroCuenta) throws Exception {
		List<Transaccion> listado = null;

		try {
			String consulta = "SELECT t FROM Transaccion t JOIN t.cuentaCliente c where c.nroCuentaCl= :nroCuenta";
			Query q = em.createQuery(consulta);
			q.setParameter("nroCuenta", nroCuenta);
			listado = q.getResultList();
			
		if(listado==null) {
			throw new Exception("No existe");
			
		}
		

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return listado;

	}

}
