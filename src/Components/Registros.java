package Components;

import java.io.Serializable;

public class Registros implements Serializable{
	private String tipoCuenta;
	private String cuenta;
	private String usuario;
	private String password;
	private String correo;
	private String url;

	public Registros(String tipoCuenta, String cuenta, String usuario, String correo, String password, String url) {
		this.tipoCuenta = tipoCuenta;
		this.cuenta = cuenta;
		this.usuario = usuario;
		this.correo = correo;
		this.password = password;
		this.url = url;
	}

	public void mostrarInfo() {
	System.out.println("Tipo Cuenta: " + this.tipoCuenta);
	System.out.println("Cuenta: " + this.cuenta);
	System.out.println("Usuario: " + this.usuario);
	System.out.println("Contraseña: " + this.password);
	System.out.println("Correo: " + this.correo);
	System.out.println("URL: " + this.url);
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String contraseña) {
		this.password = contraseña;
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

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipo) {
		this.tipoCuenta = tipo;
	}

}
