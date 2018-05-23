package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
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

public class ActualizarComensal extends AppCompatActivity {

    private static final String IP_ACTCOM = "http://pruebagamash.esy.es/archPHP/Actualizar_Comensal.php";

    EditText Nombre, Nombre_usuario, ContActual, contNueva;
    Button btnCambiarFoto, btnAct;
    private VolleyRP volley;
    private RequestQueue mRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_comensal);
        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        btnCambiarFoto = findViewById(R.id.btnCambiar_foto);
        btnAct = findViewById(R.id.btnAct);
        Nombre = findViewById(R.id.EditT_Nombre);
        Nombre_usuario = findViewById(R.id.EditT_Nombre_Usuario);
        ContActual = findViewById(R.id.EditT_Cont);
        contNueva = findViewById(R.id.EditT_NuevaCont);

        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarWebService(Nombre.getText().toString(),Nombre_usuario.getText().toString(),ContActual.getText().toString(), contNueva.getText().toString());
            }
        });

        btnCambiarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void ActualizarWebService(String Nombre_usuario, String Nombre, String Contraseña, String ContraseñaNueva) {
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("Nombre_Usuario", Nombre_usuario);
        hashMapToken.put("Nombre", Nombre);
        hashMapToken.put("Contrasena", Contraseña);
        hashMapToken.put("ContraseñaNueva", ContraseñaNueva);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_ACTCOM, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("El usuario se actualizó correctamente")) {
                        Toast.makeText(ActualizarComensal.this, estado, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ActualizarComensal.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(ActualizarComensal.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActualizarComensal.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);

    }
}
