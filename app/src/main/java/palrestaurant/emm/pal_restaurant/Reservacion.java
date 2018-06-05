package palrestaurant.emm.pal_restaurant;

public class Reservacion {
    private int id;
    private String Hora;
    private String Fecha;
    private int Mesa;
    private int id_rest;
    private int id_comensal;

    public Reservacion(String Hora, String Fecha, int Mesa) {
        this.Hora = Hora;
        this.Fecha = Fecha;
        this.Mesa = Mesa;

    }
}
