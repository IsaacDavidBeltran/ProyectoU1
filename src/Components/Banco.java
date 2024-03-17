package Components;

public class Banco extends Registros {

    private String tarjeta;
    private String tipo;
    private String nip;
    private String cve;
    private String vencimiento;

    public Banco(String cuenta, String usuario, String contraseña, String correo, String url, String tipo, String tarjeta, String nip, String cve, String vencimiento) {
        super(cuenta, usuario, contraseña, correo, url, tipo);
        this.tarjeta = tarjeta;
        this.tipo = tipo;
        this.nip = nip;
        this.cve = cve;
        this.vencimiento = vencimiento;
    }

    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Tarjeta: " + this.tarjeta);
        System.out.println("Tipo: " + this.tipo);
        System.out.println("NIP: " + this.nip);
        System.out.println("CVE: " + this.cve);
        System.out.println("Vencimiento: " + this.vencimiento);
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getCve() {
        return cve;
    }

    public void setCve(String cve) {
        this.cve = cve;
    }
}

