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
import proyecto.model.manager.ManagerCliente;
@Named
@SessionScoped
public class BeanCliente  implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerCliente managerCliente;
	private List<Usuario> listaUsuarios;
	private List<Rol> listaRoles;
	private List<Empresa> listaEmpresas;
	private List<CuentaCliente> listaCuentaCliente;
	private List<TipoCuenta> listaTipoCuenta;
	private List<EstadoCuenta> listaEstadoCuenta;
	private int idrol;
	private int idem;
	private int idTipoCuenta;
	private int idEstadoCuenta;
	private int idUsuarios;
	private BigDecimal saldoCuenta;
	private BigDecimal interesCuenta;
	private Usuario user;
	
	private Usuario usuario;
	private CuentaCliente cuentaCliente; 
	private TipoCuenta tipoCuenta;
	private EstadoCuenta estadoCuenta;
	private Empresa empresa;
	
	private boolean panelColapsado;
	@PostConstruct
	public void inicializar() {
		listaUsuarios = managerCliente.findAllUsuarios();
		listaEmpresas = managerCliente.findAllEmpresas();
		listaRoles =  managerCliente.findAllRoles();
		listaCuentaCliente=managerCliente.findAllCuentaCliente();
		listaTipoCuenta=managerCliente.findAllTipoCuenta();
		listaEstadoCuenta=managerCliente.findAllEstadoCuenta();
		
		usuario= new Usuario();
		cuentaCliente= new CuentaCliente();
	
		panelColapsado=true;
	}
	
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}

	
	public void actionListenerInsertarUsuario(){
		try {
			Rol r=new Rol();
			r.setIdRol(idrol);
			usuario.setRol(r);
			usuario.setEmpresa(new Empresa());
			usuario.getEmpresa().setIdEmpresa(idem);
			managerCliente.insertarUsuario(usuario);
			listaUsuarios= managerCliente.findAllUsuarios();
			usuario= new Usuario();
			JSFUtil.crearMensajeInfo("Datos de usuario insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
//	public void actionListenerInsertarCuenta(){
//		try {
//			TipoCuenta tc=new TipoCuenta();
//			tc.setIdTipoCuenta(idTipoCuenta);
//			cuentaCliente.setTipoCuenta(tc);
//			cuentaCliente.setEstadoCuenta(new EstadoCuenta());
//			cuentaCliente.getEstadoCuenta().setIdEstadoCuenta(idEstadoCuenta);
//			cuentaCliente.setUsuario(new Usuario());
//			cuentaCliente.getUsuario().setIdUsuarios(idUsuarios);
//			
//			managerCliente.insertarCuentaCliente(cuentaCliente);
//			listaCuentaCliente=managerCliente.findAllCuentaCliente();
//			cuentaCliente=new CuentaCliente();
//		
//			JSFUtil.crearMensajeInfo("Datosinsertados.");
//		} catch (Exception e) {
//			JSFUtil.crearMensajeError(e.getMessage());
//			e.printStackTrace();
//		}
//	}
	 public void actionListenerInsertarCuenta() {
		 try {
		 managerCliente.insertarCuentaCliente(saldoCuenta, interesCuenta, idUsuarios, idTipoCuenta, idEstadoCuenta);
	    listaCuentaCliente= managerCliente.findAllCuentaCliente();	
	    JSFUtil.crearMensajeInfo("CUENTA CREADA !!");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	    	
	    }
	
	
	
	
	
	
	public void actionListenerEliminarUsuario(int id) {
		managerCliente.eliminarUsuario(id);
		listaUsuarios=managerCliente.findAllUsuarios();
		JSFUtil.crearMensajeInfo("Usuario Eliminado.");
	}
	public void actionListenerSeleccionarUsuario(Usuario usuario) {
		user=usuario;
	}
	
	
	public void actionListenerActualizar() {
		try {
			Rol r=new Rol();
			r.setIdRol(idrol);
			user.setRol(r);
			user.setEmpresa(new Empresa());
			user.getEmpresa().setIdEmpresa(idem);
			managerCliente.actualizarUsuario(user);
			listaUsuarios=managerCliente.findAllUsuarios();
			JSFUtil.crearMensajeInfo("datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
 	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public int getIdrol() {
		return idrol;
	}

	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}

	public int getIdem() {
		return idem;
	}

	public void setIdem(int idem) {
		this.idem = idem;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public ManagerCliente getManagerCliente() {
		return managerCliente;
	}

	public void setManagerCliente(ManagerCliente managerCliente) {
		this.managerCliente = managerCliente;
	}

	public List<CuentaCliente> getListaCuentaCliente() {
		return listaCuentaCliente;
	}

	public void setListaCuentaCliente(List<CuentaCliente> listaCuentaCliente) {
		this.listaCuentaCliente = listaCuentaCliente;
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

	public int getIdTipoCuenta() {
		return idTipoCuenta;
	}

	public void setIdTipoCuenta(int idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	public int getIdEstadoCuenta() {
		return idEstadoCuenta;
	}

	public void setIdEstadoCuenta(int idEstadoCuenta) {
		this.idEstadoCuenta = idEstadoCuenta;
	}

	public CuentaCliente getCuentaCliente() {
		return cuentaCliente;
	}

	public void setCuentaCliente(CuentaCliente cuentaCliente) {
		this.cuentaCliente = cuentaCliente;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public EstadoCuenta getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public BigDecimal getInteresCuenta() {
		return interesCuenta;
	}

	public void setInteresCuenta(BigDecimal interesCuenta) {
		this.interesCuenta = interesCuenta;
	}
	
        
}
