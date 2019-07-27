package proyecto.controller.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import proyecto.model.entities.Rol;
import proyecto.model.login.LoginDT;
import proyecto.model.manager.ManagerLogin;
import proyecto.model.utl.ModelUtil;
import proyecto.controller.model.JSFUtil;

@Named
@SessionScoped
public class BeanLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_rol;
	private int id_usuario;
	private String username;
	private String contrasena;
	private String nombre_usuario;
	private String apellido_usuario;
	private String cedula;
	private boolean acceso;

	@EJB
	private ManagerLogin managerLogin;
	private LoginDT loginDT;
	private List<Rol> listaRoles;
	@PostConstruct
	public void inicializar() {
		loginDT = new LoginDT();
		listaRoles = managerLogin.findAllRoles();
	}
	public String accederSistema() {
		acceso = false;
		try {
			loginDT = managerLogin.accederSistema(username, contrasena, id_rol);
			id_rol = loginDT.getId_rol();
			nombre_usuario = loginDT.getNombre_usuario();
			apellido_usuario= loginDT.getApellido_usuario();
			id_usuario = loginDT.getId_usuarios();
			cedula= loginDT.getCedula();
			return loginDT.getRutaAcceso() + "?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError("Login Incorrecto");
		}
		return "";
	}

	public String salirSistema() {
		System.out.println("salirSistema");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/IndexPrincipal/IndexPrincipal.xhtml?faces-redirect=true";
	}

	public void actionVerificarLogin() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath = ec.getRequestPathInfo();
		try {
			// si no paso por login:
			if (loginDT == null || ModelUtil.isEmpty(loginDT.getRutaAcceso())){
				ec.redirect(ec.getRequestContextPath() + "/faces/IndexPrincipal/IndexPrincipal.xhtml");
			} else {
				// validar las rutas de acceso:
				if (requestPath.contains("/indexAdministrador") && loginDT.getRutaAcceso().startsWith("/indexAdministrador"))
					return;
				if (requestPath.contains("/indexCajero") && loginDT.getRutaAcceso().startsWith("/indexCajero"))				 
					return;
				if (requestPath.contains("/indexClientes") && loginDT.getRutaAcceso().startsWith("/indexClientes"))				 
					return;
				if (requestPath.contains("/indexAtencionCliente") && loginDT.getRutaAcceso().startsWith("/indexAtencionCliente"))				 
					return;
				// caso contrario significa que hizo login pero intenta acceder a ruta no permitida:
				ec.redirect(ec.getRequestContextPath() + "/faces/IndexPrincipal/IndexPrincipal.xhtml");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	public String indexAdmin_Personal() {
		return "indexAdmin_Personal";
	}
	public String indexAdmin_Historial() {
		return "indexAdmin_Historial";
	}



	public String tipocuenta() {
		return "indexTipoCuenta";
	}

	public String roles() {
		return "indexRol";
	}

	public String tipotransaccion() {
		return "indexTipoTransaccion";
	}

	public String estadocuenta() {
		return "indexEstadoCuenta";
	}

	public String indexAdminstrador() {
		return "indexAdministrador";
	}


	public boolean isAcceso() {
		return acceso;
	}

	public void setAcceso(boolean acceso) {
		this.acceso = acceso;
	}

	public LoginDT getLoginDT() {
		return loginDT;
	}

	public void setLoginDT(LoginDT loginDT) {
		this.loginDT = loginDT;
	}

	public List<Rol> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
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

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getApellido_usuario() {
		return apellido_usuario;
	}

	public void setApellido_usuario(String apellido_usuario) {
		this.apellido_usuario = apellido_usuario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	

}
