package proyecto.controller.model;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto.model.entities.CuentaCliente;
import proyecto.model.entities.TipoTransaccion;
import proyecto.model.entities.Transaccion;
import proyecto.model.entities.Usuario;
import proyecto.model.manager.ManagerCajeroTransaccion;
import proyecto.controller.model.JSFUtil;


@Named
@SessionScoped
public class BeanCajeroTransaccion implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerCajeroTransaccion managerCajeroTransaccion;
	private List<CuentaCliente> listaCuentaCliente;
	private List<Usuario> listaUsuario;
	private List<Transaccion> listaTransaccion;
	private List<TipoTransaccion> listaTipoTransaccion;

	private int nroCuenta;
	private int idTipoTransaccion;
	private double montoTransaccion;
    private double SaldoTransaccion;
    
    private Transaccion transaccion;
    private TipoTransaccion tipoTransaccion;
    private CuentaCliente cuentaCliente;
    private boolean panelColapsado;
    private Transaccion transaccionSeleccionado;
    
   Date fechaTransaccion = new Date();
  
	@PostConstruct
	public void inicializar() {
		listaCuentaCliente = managerCajeroTransaccion.findAllcuentaCliente();
		listaUsuario = managerCajeroTransaccion.findAllUsuarios();
		listaTransaccion = managerCajeroTransaccion.findAllTransaccion();
		listaTipoTransaccion = managerCajeroTransaccion.findAllTipoTransaccion();
		transaccion = new Transaccion();
		tipoTransaccion=new TipoTransaccion();
		cuentaCliente=new CuentaCliente();
		panelColapsado=true;
		

	}
	
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	

	public void actionListenerInsertarTransaccion() {
		try {

			managerCajeroTransaccion.insertarTransaccion(nroCuenta, idTipoTransaccion, montoTransaccion, fechaTransaccion, SaldoTransaccion);
			listaTransaccion = managerCajeroTransaccion.findAllTransaccion();
			transaccion = new Transaccion();
			JSFUtil.crearMensajeInfo("Transacción realizada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarTranasaccion(int idTransaccion) {
		managerCajeroTransaccion.eliminarTransaccion(idTransaccion);
		listaTransaccion=managerCajeroTransaccion.findAllTransaccion();
				JSFUtil.crearMensajeInfo("Transacción Eliminado");
	}
	public void actionListenerSeleccionarTransaccion(Transaccion transaccion) {
		transaccionSeleccionado=transaccion;
	}
	public void actionListenerActualizarTransaccion() {
	try {
		managerCajeroTransaccion.actuaizarTransferencia(transaccionSeleccionado);
		listaTransaccion=managerCajeroTransaccion.findAllTransaccion();
		JSFUtil.crearMensajeInfo("Datos Actualizados");
		
	}catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		e.printStackTrace();
	}
	}
	

public void actionBuscarTransaccion() {
	try {
		listaTransaccion= managerCajeroTransaccion.findTransaccionesByNrocuenta(nroCuenta);
		//listaTransaccion=managerCajeroTransaccion.findAllTransaccion();
		
        
	}catch (Exception e) {
		JSFUtil.crearMensajeError(e.getMessage());
		//JSFUtil.crearMensajeError(e.getMessage());
		//e.printStackTrace();
	}
}




	

	public List<CuentaCliente> getListaCuentaCliente() {
		return listaCuentaCliente;
	}

	public void setListaCuentaCliente(List<CuentaCliente> listaCuentaCliente) {
		this.listaCuentaCliente = listaCuentaCliente;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<Transaccion> getListaTransaccion() {
		return listaTransaccion;
	}

	public void setListaTransaccion(List<Transaccion> listaTransaccion) {
		this.listaTransaccion = listaTransaccion;
	}

	public List<TipoTransaccion> getListaTipoTransaccion() {
		return listaTipoTransaccion;
	}

	public void setListaTipoTransaccion(List<TipoTransaccion> listaTipoTransaccion) {
		this.listaTipoTransaccion = listaTipoTransaccion;
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public CuentaCliente getCuentaCliente() {
		return cuentaCliente;
	}

	public void setCuentaCliente(CuentaCliente cuentaCliente) {
		this.cuentaCliente = cuentaCliente;
	}

	public int getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(int nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public int getIdTipoTransaccion() {
		return idTipoTransaccion;
	}

	public void setIdTipoTransaccion(int idTipoTransaccion) {
		this.idTipoTransaccion = idTipoTransaccion;
	}

	
	
	
	
	public double getMontoTransaccion() {
		return montoTransaccion;
	}

	public void setMontoTransaccion(double montoTransaccion) {
		this.montoTransaccion = montoTransaccion;
	}

	public double getSaldoTransaccion() {
		return SaldoTransaccion;
	}

	public void setSaldoTransaccion(double saldoTransaccion) {
		SaldoTransaccion = saldoTransaccion;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	public Transaccion getTransaccionSeleccionado() {
		return transaccionSeleccionado;
	}
	public void setTransaccionSeleccionado(Transaccion transaccionSeleccionado) {
		this.transaccionSeleccionado = transaccionSeleccionado;
	}
	////////////////////////////
	public String actionListenerTransacciones() {
		return "indexTransacciones";
	}
	
	public String actionListenerHistorialEstadocuenta() {
		return "indexCajeroEstadoCuenta";
	}

	public String actionListenerHome() {
		return "IndexCajero";
}
}
