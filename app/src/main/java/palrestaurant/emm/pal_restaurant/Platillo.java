package palrestaurant.emm.pal_restaurant;

public class Platillo {
    private int id;
    private int photoId;
    private String nombre;
    private String tipo;
    private int precio;
    private String descripcion;
    private int id_restaurante;

    public Platillo(int id, String nombre, String tipo, int precio, String descripcion, int id_restaurante){
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio= precio;
        this.descripcion = descripcion;
        this.id_restaurante = id_restaurante;
    }

    public void actualizarPlatillo(int id, String nombre, String tipo, int precio, String descripcion, int id_restaurante){
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio= precio;
        this.descripcion = descripcion;
        this.id_restaurante = id_restaurante;
    }

    public void eliminarPlatillo(int id){
        //TODO here
    }

    public String getNombre(){
        return nombre;
    }

    public int getId(){
        return id;
    }

    public int getPhotoId(){
        return photoId;
    }

    public int getPrecio(){
        return precio;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getTipo(){
        return tipo;
    }
}


