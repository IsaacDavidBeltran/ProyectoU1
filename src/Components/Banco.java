package Components;

public class Banco extends Registros {
    private String cuentaBancaria;
    private String tarjeta;
    private String tipoTarjeta;
    private String nip;
    private String cve;
    private String vencimiento;

    public Banco(String tipoCuenta, String cuenta, String usuario, String correo,
            String password, String url, String cuentaBancaria, String tarjeta, String tipoTarjeta, String nip,
            String cve, String vencimiento) {
        super(tipoCuenta, cuenta, usuario, correo, password, url);
        this.cuentaBancaria = cuentaBancaria;
        this.tarjeta = tarjeta;
        this.tipoTarjeta = tipoTarjeta;
        this.nip = nip;
        this.cve = cve;
        this.vencimiento = vencimiento;
    }

    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Cuenta Bancaria " + this.cuentaBancaria);
        System.out.println("Tarjeta: " + this.tarjeta);
        System.out.println("Tipo Tarjeta: " + this.tipoTarjeta);
        System.out.println("NIP: " + this.nip);
        System.out.println("CVE: " + this.cve);
        System.out.println("Vencimiento: " + this.vencimiento);
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipo) {
        this.tipoTarjeta = tipo;
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

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }
}
