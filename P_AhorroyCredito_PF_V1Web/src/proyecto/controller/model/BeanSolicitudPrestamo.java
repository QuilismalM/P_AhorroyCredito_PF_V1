package proyecto.controller.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import proyecto.model.entities.CuentaCliente;
import proyecto.model.entities.SolicitudP;
import proyecto.model.manager.ManagerSolicitudPrestamo;

@Named
@SessionScoped
public class BeanSolicitudPrestamo implements Serializable {

	private static final long serialVersionUID = 1L;     
	@EJB
	private ManagerSolicitudPrestamo mangerSolicitud;
	private List<SolicitudP> listaSolicitud;
	private List<SolicitudP> listaSolicitud2;
	private List<CuentaCliente> listaCuentaCliente;
	private SolicitudP solicitudP;
	private SolicitudP solicitudPseleccionada;
	private BeanLogin beanLogin;

    
	private int id_solicitud;
	private BigDecimal valor_solicitud;
	private String estado_solicitud;
	private int nroCuentaCl;
	private int nroMeses;
	Date fecha_solicitud = new Date();
	private boolean panelColapsado;
	@PostConstruct
	public void inicializar() {


		CuentaCliente cl = (CuentaCliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cuentaCliente");
		if (cl != null) {
			nroCuentaCl=cl.getNroCuentaCl();
			listaCuentaCliente = mangerSolicitud.findAllcuentaCliente();
			listaSolicitud2 = mangerSolicitud.SolcitudesCliente(nroCuentaCl);
		}

		listaSolicitud = mangerSolicitud.findAllSolicitudP();
		panelColapsado=true;
		solicitudP = new SolicitudP();

	}
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}


	public void actionListenerInsertarSolicitud() {
		try {
			estado_solicitud="Pendiente";
			mangerSolicitud.insertarSolucitudP(valor_solicitud, estado_solicitud, fecha_solicitud,nroCuentaCl,nroMeses);
			listaSolicitud = mangerSolicitud.SolcitudesCliente(nroCuentaCl);
			solicitudP = new SolicitudP();
			JSFUtil.crearMensajeInfo("Datos insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarSolicitud(int id_solicitudp) {
		mangerSolicitud.eliminarSolicitudP(id_solicitudp);
		listaSolicitud = mangerSolicitud.SolcitudesCliente(nroCuentaCl);
		JSFUtil.crearMensajeInfo("Eliminado");

	}
	

	public void actionListenerSeleccionarSolicitud(SolicitudP solicitudP) {
		solicitudPseleccionada = solicitudP;
	}

	public void actionListenerActualizarSolicitudl() {
		try {
			mangerSolicitud.actualizarSolicitudP(solicitudPseleccionada);
			listaSolicitud = mangerSolicitud.SolcitudesCliente(nroCuentaCl);
			JSFUtil.crearMensajeInfo("Datos Actualizados");

		} catch (Exception e) {
			// TODO: handle exception
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	///////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////
	
	public List<SolicitudP> getListaSolicitudP() {
		return listaSolicitud;
	}
	
	public void actionListenerActualizarSolicitudPrestamo1() {
		try {
			mangerSolicitud.actualizarSolicitudPresatamo1(solicitudPseleccionada);
			listaSolicitud=mangerSolicitud.findAllSolicitudP();
		JSFUtil.crearMensajeInfo("Solicitud Revisada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		e.printStackTrace();
		}
	}
	
	
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	
	

	public List<CuentaCliente> getListaCuentaCliente() {
		return listaCuentaCliente;
	}

	public List<SolicitudP> getListaSolicitud2() {
		return listaSolicitud2;
	}
	public void setListaSolicitud2(List<SolicitudP> listaSolicitud2) {
		this.listaSolicitud2 = listaSolicitud2;
	}
	public void setListaCuentaCliente(List<CuentaCliente> listaCuentaCliente) {
		this.listaCuentaCliente = listaCuentaCliente;
	}

	public int getId_solicitud() {
		return id_solicitud;
	}
	
	public int getNroMeses() {
		return nroMeses;
	}

	public void setNroMeses(int nroMeses) {
		this.nroMeses = nroMeses;
	}

	public void setId_solicitud(int id_solicitud) {
		this.id_solicitud = id_solicitud;
	}

	public BigDecimal getValor_solicitud() {
		return valor_solicitud;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}


	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}


	public boolean isPanelColapsado() {
		return panelColapsado;
	}


	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}


	public void setValor_solicitud(BigDecimal valor_solicitud) {
		this.valor_solicitud = valor_solicitud;
	}

	public String getEstado_solicitud() {
		return estado_solicitud;
	}

	public void setEstado_solicitud(String estado_solicitud) {
		this.estado_solicitud = estado_solicitud;
	}


	public int getNroCuentaCl() {
		return nroCuentaCl;
	}

	public void setNroCuentaCl(int nroCuentaCl) {
		this.nroCuentaCl = nroCuentaCl;
	}

	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}

	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}

	public List<SolicitudP> getListaSolicitud() {
		return listaSolicitud;
	}

	public void setListaSolicitud(List<SolicitudP> listaSolicitud) {
		this.listaSolicitud = listaSolicitud;
	}

	public SolicitudP getSolicitudP() {
		return solicitudP;
	}

	public void setSolicitudP(SolicitudP solicitudP) {
		this.solicitudP = solicitudP;
	}

	public SolicitudP getSolicitudPseleccionada() {
		return solicitudPseleccionada;
	}

	public void setSolicitudPseleccionada(SolicitudP solicitudPseleccionada) {
		this.solicitudPseleccionada = solicitudPseleccionada;
	}

}
