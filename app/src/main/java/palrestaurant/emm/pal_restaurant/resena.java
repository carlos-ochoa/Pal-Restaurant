package palrestaurant.emm.pal_restaurant;

public class resena {
    private String nombre_usuario;
    private String id_restaurante;
    private String descripcion;

    public resena(String nombre_usuario, String descripcion){
        this.nombre_usuario = nombre_usuario;
        this.descripcion = descripcion;
    }

    public void actualizar(String nombre_usuario, String descripcion ){
        this.nombre_usuario= nombre_usuario;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre_usuario;
    }
    public String getDescripcion() {
        return descripcion;
    }
}


