package palrestaurant.emm.pal_restaurant;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL="http://192.168.1.66/Registro_Comensal.php";
    private Map<String,String> params;
    public RegisterRequest(String Nombre_Usuario, String Contraseña, String Nombre, Response.Listener<String> listener)
    {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("Nombre_Usuario", Nombre_Usuario);
        params.put("Contraseña", Contraseña);
        params.put("Nombre", Nombre);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
