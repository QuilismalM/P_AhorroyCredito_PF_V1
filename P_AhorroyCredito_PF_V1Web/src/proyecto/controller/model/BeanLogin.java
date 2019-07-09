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
import proyecto.model.manager.ManagerLogin;


@Named
@SessionScoped
public class BeanLogin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id_rol;
	private String username ;
	private String contrasena;

	  

	@EJB
	private ManagerLogin managerLogin;
	private List<Rol> listaRoles;
	private Usuario usuario;
   
    
	
	@PostConstruct
	public void inicializar() {
		listaRoles =  managerLogin.findAllRoles();
	}
	
	public String actionLogin() {
		try {
			if (id_rol.equals("1") ) {
				System.out.println("Aqui esta el mensaje"+id_rol);
				///JSFUtil.crearMensajeInfo("Login correcto");
				//return "/admin/menu.xhtml";
				return "indexAdministrador/indexAdmin_Personal.xhtml?faces-redirect=true";
				
		/*	}else if(idUsua.equals("medico")) {
				return "medico/index";
			}
			return "clientes/index";*/
			} 
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}
	public String salirSistema(){
		System.out.println("salirSistema");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}


	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Rol> getListaRoles() {
		return listaRoles;
	}
	public void setListaRoles(List<Rol> listaRoles) {
		this.listaRoles = listaRoles;
	}
	public String getId_rol() {
		return id_rol;
	}
	public void setId_rol(String id_rol) {
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
