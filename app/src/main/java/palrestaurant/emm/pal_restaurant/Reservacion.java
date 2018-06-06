package palrestaurant.emm.pal_restaurant;

public class Reservacion {
    private int id;
    private String Hora;
    private String Fecha;
    private String Mesa;
    private int id_rest;
    private int id_comensal;

    public Reservacion(String Hora, String Fecha, String Mesa) {
        this.Hora = Hora;
        this.Fecha = Fecha;
        this.Mesa = Mesa;

    }


    public String getHora() {
        return Hora;
    }

    public String getFecha() {
        return Fecha;
    }

    public String getMesa() {
        return Mesa;
    }
}
