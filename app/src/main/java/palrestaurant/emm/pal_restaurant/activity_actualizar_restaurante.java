package palrestaurant.emm.pal_restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class activity_actualizar_restaurante extends AppCompatActivity {

    private static final String IP_ACTREST = "http://pruebagamash.esy.es/archPHP/Actualizar_Restaurante.php";

    Button btnAct;
    EditText ET_Nombre_Usuario, ET_Nombre, ET_Tipo, ET_Direccion, ET_Telef, ET_Sitio_W, ET_Contraseña, ET_ContraseñaNueva, ET_Descripcion;
    private VolleyRP volley;
    private RequestQueue mRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_restaurante);
        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        btnAct= findViewById(R.id.btnAct);
        ET_Nombre_Usuario= findViewById(R.id.ET_Nombre_Usuario);
        ET_Nombre= findViewById(R.id.ET_Nombre_Usuario);
        ET_Tipo=findViewById(R.id.ET_Tipo);
        ET_Direccion= findViewById(R.id.ET_Direccion);
        ET_Telef=findViewById(R.id.ET_Telef);
        ET_Sitio_W= findViewById(R.id.ET_Sitio_W);
        ET_Contraseña=findViewById(R.id.ET_Contraseña);
        ET_ContraseñaNueva= findViewById(R.id.ET_ContraseñaNueva);
        ET_Descripcion= findViewById(R.id.ET_Descripcion);

        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarWebService(ET_Nombre_Usuario.getText().toString(), ET_ContraseñaNueva.getText().toString(), "12", ET_Contraseña.getText().toString(), ET_Nombre.getText().toString(),  ET_Tipo.getText().toString(),ET_Direccion.getText().toString(), ET_Telef.getText().toString(), ET_Sitio_W.getText().toString(), ET_Descripcion.getText().toString());
            }
        });
    }

    private void ActualizarWebService(String Nombre_Usuario, String ContraseñaNueva, String ID_Usuario, String Contraseña, String Nombre_Restaurante, String Tipo_Rest, String Direccion, String Telefono, String Sitio_Web, String Descripcion) {
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("Nombre_Usuario", Nombre_Usuario);
        hashMapToken.put("ContraseñaNueva", ContraseñaNueva);
        hashMapToken.put("ID_Usuario", ID_Usuario);
        hashMapToken.put("Contrasena", Contraseña);
        hashMapToken.put("Nombre_Restaurante", Nombre_Restaurante);
        hashMapToken.put("Tipo_Rest", Tipo_Rest);
        hashMapToken.put("Direccion", Direccion);
        hashMapToken.put("Telefono", Telefono);
        hashMapToken.put("Sitio_Web", Sitio_Web);
        hashMapToken.put("Descripcion", Descripcion);
        //Generar la solicitud del JSON
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_ACTREST, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("El usuario se actualizó correctamente")) {
                        Toast.makeText(activity_actualizar_restaurante.this, estado, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(activity_actualizar_restaurante.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(activity_actualizar_restaurante.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_actualizar_restaurante.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);

    }

}
