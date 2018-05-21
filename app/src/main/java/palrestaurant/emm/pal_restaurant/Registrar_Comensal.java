package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Registrar_Comensal extends AppCompatActivity {

    private static final String IP_REGISTRAR = "http://pruebagamash.esy.es/archPHP/Registrar_Comensal_INSERT.php";

    EditText nombre, nombre_usuario, pass;
    Button registrar;
    private VolleyRP volley;
    private RequestQueue mRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__comensal);

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        nombre = (EditText) findViewById(R.id.EditT_Nombre);
        nombre_usuario=  (EditText) findViewById(R.id.EditT_Nombre_Usuario);
        pass = (EditText) findViewById(R.id.EditT_Contraseña);
        registrar = (Button) findViewById(R.id.btnCrear);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrarWebService(nombre.getText().toString(),nombre_usuario.getText().toString(),pass.getText().toString());
            }
        });
    }

    private void registrarWebService(String nombre, String nombre_usuario, String pass){
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("Nombre_Usuario", nombre_usuario);
        hashMapToken.put("Contraseña", pass);
        hashMapToken.put("Nombre", nombre);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_REGISTRAR, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("El usuario se registró correctamente")) {
                        Toast.makeText(Registrar_Comensal.this, estado, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Registrar_Comensal.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Registrar_Comensal.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registrar_Comensal.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);
    }

    /*private void SubirToken(String id,String token){
        SolicitudesJson s = new SolicitudesJson() {
            @Override
            public void solicitudCompletada(JSONObject j) {
                Toast.makeText(Registro.this, "Se registro correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void solicitudErronea() {
                Toast.makeText(Registro.this, "No se pudo subir el token", Toast.LENGTH_SHORT).show();
                finish();
            }
        };

        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("id",id);
        hashMapToken.put("token",token);

        s.solicitudJsonPOST(this,SolicitudesJson.IP_TOKEN_UPLOAD,hashMapToken);
    }*/
}