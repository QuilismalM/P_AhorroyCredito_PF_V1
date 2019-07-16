package proyecto.controller.model;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import proyecto.model.entities.Rol;
import proyecto.model.entities.Usuario;
import proyecto.model.login.LoginDT;
import proyecto.model.manager.ManagerLogin;

@Named
@SessionScoped
public class BeanLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_rol;
	private String username;
	private String contrasena;
	 private boolean acceso;
	

	@EJB
	private ManagerLogin managerLogin;
	private List<Rol> listaRoles;
	private Usuario usuario;
	private LoginDT loginDT;

	@PostConstruct
	public void inicializar() {
		loginDT=new LoginDT();
		listaRoles = managerLogin.findAllRoles();
	}

//	public String send() {
//		usuario = managerLogin.getUser(username, contrasena);
//		if (usuario == null) {
//			usuario = new Usuario();
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "User not found!", " Login Error!"));
//			return null;
//		} else if (id_rol == 1) {
//			return "indexAdministrador/indexAdministrador.xhtml?faces-redirect=true";
//		}
//		else {
//			return "";
//		}
//	}
	
	public String accederSistema(){
		acceso=false;
		try {
			loginDT=managerLogin.accederSistema(username, contrasena);
			//verificamos el acceso del usuario:
			id_rol=loginDT.getId_rol();
			//redireccion dependiendo del tipo de usuario:
			return loginDT.getRutaAcceso()+"?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}
	
	public String salirSistema(){
		System.out.println("salirSistema");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}

	/*
	 * public String actionLogin() { try { if (id_rol == 1 ) {
	 * System.out.println("Aqui esta el mensaje"+id_rol); usuarioSeleccionado =
	 * managerLogin.extraerUsuario(username, contrasena, id_rol);
	 * System.out.println(usuarioSeleccionado.getCedulaUsuario());
	 * ///JSFUtil.crearMensajeInfo("Login correcto"); //return "/admin/menu.xhtml";
	 * return "indexAdministrador/indexAdmin_Personal.xhtml?faces-redirect=true";
	 */

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

}
