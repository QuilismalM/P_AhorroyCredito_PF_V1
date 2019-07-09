package proyecto.controller.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto.model.entities.CuentaCliente;
import proyecto.model.entities.Empresa;
import proyecto.model.entities.EstadoCuenta;
import proyecto.model.entities.Rol;
import proyecto.model.entities.TipoCuenta;
import proyecto.model.entities.Usuario;
import proyecto.model.manager.ManagerGestionCliente;

@Named
@SessionScoped
public class BeanGestionCliente implements Serializable{
	private static final  long serialVersionUID = 1L;
	
	@EJB
	private ManagerGestionCliente managerGestionCliente;
	private List<CuentaCliente> listaCuentaCliente;
	private List<Rol> listaRol;
	private List<Empresa> listaEmpresa;
	private List<TipoCuenta> listaTipoCuenta;
	private List<EstadoCuenta> listaEstadoCuenta;
	private Usuario usuario;
	private CuentaCliente cuentaCliente;
	private boolean panelColapso;
	private CuentaCliente cuentaClienteSeleccionado;
	
	private int idUsuarios;
	private String cedulaUsuario;
		    private BigDecimal saldoCuenta;
		    private int nroCuentaCl;
		    private String direccionUsuario;
		    private String telefonoUsuario;
		    private String correoUsario;
		    private String username;
		    private String contrasena;
		    private BigDecimal interesCuenta;
		    private String nombreUsuario;
		    private String apellidoUsuario;
	   
	  	
	 @PostConstruct
	    public void inicializar() {
		 listaCuentaCliente=managerGestionCliente.listarCuentaCliente();
//
//		 		listaEmpresa=managerGestionCliente.listarEmpresa();
	 		listaRol=managerGestionCliente.listarRol();
//		 		listaTipoCuenta=managerGestionCliente.listarTipoCuenta();
		 		listaEstadoCuenta=managerGestionCliente.listarEstadoCuenta();
		 
	    		 cuentaCliente =new CuentaCliente();
	  	    
	    }
	 
	 
	 
	 //////////////
	 public void actionListenerColapsarPanel() {
			panelColapso=!panelColapso;
		}
	    public void actionListenerInsertarCuentaCliente() {
	    //managerGestionCliente.insertarCuentaCliente(cedulaUsuario, saldoCuenta, nroCuentaCl, direccionUsuario, telefonoUsuario, username);
	    managerGestionCliente.insertaCuentaCliente(idUsuarios, saldoCuenta, nroCuentaCl);
	    listaCuentaCliente=managerGestionCliente.listarCuentaCliente();	
	    }
	 
//		public void actionListenerInsertarCuentaCliente(){
//			try {
//				Usuario u=new Usuario();
//				u.setIdUsuarios(idUsuarios);
//				cuentaCliente.setUsuario(u);
//			//	cuentaCliente.setUsuario(new Usuario());
//				cuentaCliente.getUsuario().setCedulaUsuario(cedulaUsuario);
//				cuentaCliente.getUsuario().setNombreUsuario(nombreUsuario);
//				cuentaCliente.getUsuario().setApellidoUsuario(apellidoUsuario);
//				cuentaCliente.getUsuario().setDireccionUsuario(direccionUsuario);
//				cuentaCliente.getUsuario().setCorreoUsario(correoUsario);
//				cuentaCliente.getUsuario().setTelefonoUsuario(telefonoUsuario);
//				cuentaCliente.getUsuario().setUsername(username);
//				cuentaCliente.getUsuario().setContrasena(contrasena);
//				cuentaCliente.setNroCuentaCl(nroCuentaCl);
//				cuentaCliente.setSaldoCuenta(saldoCuenta);
//				cuentaCliente.setInteresCuenta(interesCuenta);
//				
//				listaCuentaCliente=managerGestionCliente.listarCuentaCliente();
//				cuentaCliente=new CuentaCliente();
//				JSFUtil.crearMensajeInfo("Datos insertados.");
//			} catch (Exception e) {
//				JSFUtil.crearMensajeError(e.getMessage());
//				e.printStackTrace();
//			}
//		}
	     
		
		public void actionListenerEliminarCuentaCliente(int nro_cuenta_cliente) {
			managerGestionCliente.eliminarCuentaCliente(nro_cuenta_cliente);
			listaCuentaCliente=managerGestionCliente.listarCuentaCliente();
		
			
			JSFUtil.crearMensajeInfo(" Eliminado");
		}
		
		public void actionListenerSeleccionarCuentaCliente(CuentaCliente cuentaCliente) {
			cuentaClienteSeleccionado=cuentaCliente;

		}
		
		public void actionListenerActualizarCuentaCliente() {
			try {
				managerGestionCliente.actualizarCuentaCliente(cuentaClienteSeleccionado);
				listaCuentaCliente=managerGestionCliente.listarCuentaCliente();
				
				JSFUtil.crearMensajeInfo("Datos Actualizados");
			} catch (Exception e) {
				JSFUtil.crearMensajeError(e.getMessage());
				e.printStackTrace();
			}
		}



		public ManagerGestionCliente getMangerGestionCliente() {
			return managerGestionCliente;
		}



		public void setMangerGestionCliente(ManagerGestionCliente mangerGestionCliente) {
			this.managerGestionCliente = mangerGestionCliente;
		}



		public List<CuentaCliente> getListaCuentaCliente() {
			return listaCuentaCliente;
		}



		public void setListaCuentaCliente(List<CuentaCliente> listaCuentaCliente) {
			this.listaCuentaCliente = listaCuentaCliente;
		}



		public Usuario getUsuario() {
			return usuario;
		}



		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}



		public CuentaCliente getCuentaCliente() {
			return cuentaCliente;
		}



		public void setCuentaCliente(CuentaCliente cuentaCliente) {
			this.cuentaCliente = cuentaCliente;
		}



		public boolean isPanelColapso() {
			return panelColapso;
		}



		public void setPanelColapso(boolean panelColapso) {
			this.panelColapso = panelColapso;
		}



		public CuentaCliente getCuentaClienteSeleccionado() {
			return cuentaClienteSeleccionado;
		}



		public void setCuentaClienteSeleccionado(CuentaCliente cuentaClienteSeleccionado) {
			this.cuentaClienteSeleccionado = cuentaClienteSeleccionado;
		}



	


		public ManagerGestionCliente getManagerGestionCliente() {
			return managerGestionCliente;
		}



		public void setManagerGestionCliente(ManagerGestionCliente managerGestionCliente) {
			this.managerGestionCliente = managerGestionCliente;
		}



		public int getIdUsuarios() {
			return idUsuarios;
		}



		public void setIdUsuarios(int idUsuarios) {
			this.idUsuarios = idUsuarios;
		}



		public BigDecimal getSaldoCuenta() {
			return saldoCuenta;
		}



		public void setSaldoCuenta(BigDecimal saldoCuenta) {
			this.saldoCuenta = saldoCuenta;
		}



		public int getNroCuentaCl() {
			return nroCuentaCl;
		}



		public void setNroCuentaCl(int nroCuentaCl) {
			this.nroCuentaCl = nroCuentaCl;
		}



		public String getCedulaUsuario() {
			return cedulaUsuario;
		}



		public void setCedulaUsuario(String cedulaUsuario) {
			this.cedulaUsuario = cedulaUsuario;
		}



		public String getDireccionUsuario() {
			return direccionUsuario;
		}



		public void setDireccionUsuario(String direccionUsuario) {
			this.direccionUsuario = direccionUsuario;
		}



		public String getTelefonoUsuario() {
			return telefonoUsuario;
		}



		public void setTelefonoUsuario(String telefonoUsuario) {
			this.telefonoUsuario = telefonoUsuario;
		}



		public String getUsername() {
			return username;
		}



		public void setUsername(String username) {
			this.username = username;
		}



		public String getContrasena() {
			return contrasena;
		}



		public void setContrasena(String contrasena) {
			this.contrasena = contrasena;
		}



		public String getCorreoUsario() {
			return correoUsario;
		}



		public void setCorreoUsario(String correoUsario) {
			this.correoUsario = correoUsario;
		}



		public BigDecimal getInteresCuenta() {
			return interesCuenta;
		}



		public void setInteresCuenta(BigDecimal interesCuenta) {
			this.interesCuenta = interesCuenta;
		}



		public String getNombreUsuario() {
			return nombreUsuario;
		}



		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}



		public String getApellidoUsuario() {
			return apellidoUsuario;
		}



		public void setApellidoUsuario(String apellidoUsuario) {
			this.apellidoUsuario = apellidoUsuario;
		}



		public List<Rol> getListaRol() {
			return listaRol;
		}



		public void setListaRol(List<Rol> listaRol) {
			this.listaRol = listaRol;
		}



		public List<Empresa> getListaEmpresa() {
			return listaEmpresa;
		}



		public void setListaEmpresa(List<Empresa> listaEmpresa) {
			this.listaEmpresa = listaEmpresa;
		}



		public List<TipoCuenta> getListaTipoCuenta() {
			return listaTipoCuenta;
		}



		public void setListaTipoCuenta(List<TipoCuenta> listaTipoCuenta) {
			this.listaTipoCuenta = listaTipoCuenta;
		}



		public List<EstadoCuenta> getListaEstadoCuenta() {
			return listaEstadoCuenta;
		}



		public void setListaEstadoCuenta(List<EstadoCuenta> listaEstadoCuenta) {
			this.listaEstadoCuenta = listaEstadoCuenta;
		}



	
	 
	 ///////////
	 


}
