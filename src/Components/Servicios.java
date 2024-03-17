package Components;

public class Servicios extends Registros {

    private String domicilio;
    private String datoExtra1;
    private String datoExtra2;

    public Servicios(String tipoCuenta, String cuenta, String usuario, String correo, String password, String url, String domicilio, String datoExtra1, String datoExtra2) {
        super(tipoCuenta, cuenta, usuario, correo, password, url);
        this.domicilio = domicilio;
        this.datoExtra1 = datoExtra1;
        this.datoExtra2 = datoExtra2;
    }

    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Domicilio: " + this.domicilio);
        System.out.println("Dato Extra 1: " + this.datoExtra1);
        System.out.println("Dato Extra 2: " + this.datoExtra2);
        System.out.println("-----------------------------");
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDatoExtra1() {
        return datoExtra1;
    }

    public void setDatoExtra1(String datoExtra1) {
        this.datoExtra1 = datoExtra1;
    }

    public String getDatoExtra2() {
        return datoExtra2;
    }

    public void setDatoExtra2(String datoExtra2) {
        this.datoExtra2 = datoExtra2;
    }
}
