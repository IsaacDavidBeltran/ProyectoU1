package Components;

public class Registros {

	private String cuenta;
	private String usuario;
	private String contraseña;
	private String correo;
	private String url;
	private String tipo;

	public Registros(String cuenta, String usuario, String contraseña, String correo, String url, String tipo) {
		this.cuenta = cuenta;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.correo = correo;
		this.url = url;
		this.tipo = tipo;
	}

	public void mostrarInfo() {
		System.out.println("Cuenta: " + this.cuenta);
		System.out.println("Usuario: " + this.usuario);
		System.out.println("Contraseña: " + this.contraseña);
		System.out.println("Correo: " + this.correo);
		System.out.println("URL: " + this.url);
		System.out.println("Tipo: " + this.tipo);
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
