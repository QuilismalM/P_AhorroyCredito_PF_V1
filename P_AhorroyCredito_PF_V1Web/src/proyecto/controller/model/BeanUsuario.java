package proyecto.controller.model;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto.model.entities.Empresa;
import proyecto.model.entities.Rol;
import proyecto.model.entities.Usuario;
import proyecto.model.manager.ManagerUsuario;


@Named
@SessionScoped
public class BeanUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerUsuario managerUsuario;
	private List<Usuario> listaUsuarios;
	private List<Rol> listaRoles;
	private List<Empresa> listaEmpresas;
	private int idrol;
	private int idem;
	private Usuario user;
	
	private Usuario usuario;
	private Empresa empresa;
	
	private boolean panelColapsado;
	@PostConstruct
	public void inicializar() {
		listaUsuarios = managerUsuario.findAllUsuarios();
		listaEmpresas = managerUsuario.findAllEmpresas();
		listaRoles =  managerUsuario.findAllRoles();
		usuario= new Usuario();
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
			managerUsuario.insertarUsuario(usuario);
			listaUsuarios= managerUsuario.findAllUsuarios();
			usuario= new Usuario();
			JSFUtil.crearMensajeInfo("Datos de usuario insertados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarUsuario(int id) {
		managerUsuario.eliminarUsuario(id);
		listaUsuarios=managerUsuario.findAllUsuarios();
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
			managerUsuario.actualizarUsuario(user);
			listaUsuarios=managerUsuario.findAllUsuarios();
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
	
        
}
