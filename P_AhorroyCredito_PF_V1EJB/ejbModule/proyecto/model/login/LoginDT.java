package proyecto.model.login;

public class LoginDT {
	private String username;
	private int id_usuarios;
	private String contrasena;
	private String nombre_usuario;
	private String apellido_usuario;
	private String cedula;
	private int cuentaClienteCl;
	private int id_rol;
	private String rutaAcceso;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId_usuarios() {
		return id_usuarios;
	}
	public void setId_usuarios(int id_usuarios) {
		this.id_usuarios = id_usuarios;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public String getRutaAcceso() {
		return rutaAcceso;
	}
	public void setRutaAcceso(String rutaAcceso) {
		this.rutaAcceso = rutaAcceso;
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
	public int getCuentaClienteCl() {
		return cuentaClienteCl;
	}
	public void setCuentaClienteCl(int cuentaClienteCl) {
		this.cuentaClienteCl = cuentaClienteCl;
	}
	

}
