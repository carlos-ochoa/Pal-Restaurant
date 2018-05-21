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

public class MainActivity extends AppCompatActivity {

    private static final String IP_REGISTRAR = ".http://pruebagamash.esy.es/archPHP/Iniciar_Sesion_GETALL.php";

    EditText TEnombre_usuario, TEPass;
    Button btnReg, btnIni;
    private VolleyRP volley;
    private RequestQueue mRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        btnIni = findViewById(R.id.btnIni);
        btnReg = findViewById(R.id.btnReg);  //Creamos el botòn que nos llevará a registrar usuario.

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(MainActivity.this, Registro.class); //"Llamamos" al registro desde el main
                MainActivity.this.startActivity(intentReg);  //Comenzar la actividad del registro

            }
        });

        btnIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrarWebService(TEnombre_usuario.getText().toString(), TEPass.getText().toString());
            }
        });
    }

    private void registrarWebService(String nombre_usuario, String pass){
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("Nombre", nombre_usuario);
        hashMapToken.put("Contraseña", pass);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_REGISTRAR, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("El usuario existe")) {
                        Toast.makeText(MainActivity.this, estado, Toast.LENGTH_SHORT).show();
                        finish();
                                Intent intentReg = new Intent(MainActivity.this, Perfil_Comensal.class); //"Llamamos" al registro desde el main
                                MainActivity.this.startActivity(intentReg);  //Comenzar la actividad del registro
                    } else {
                        Toast.makeText(MainActivity.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Usuario inexistente", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Usuario inexistente", Toast.LENGTH_SHORT).show();
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

