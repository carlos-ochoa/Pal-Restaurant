package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Perfil_Comensal extends AppCompatActivity {

    private static final String IP_BORRCOM = "http://pruebagamash.esy.es/archPHP/Borrar_Comensal.php";

    Button btnBuscar, btnCambiar, btnEliminar;
    TextView TVNombre_Usuario, TVNombre;
    private VolleyRP volley;
    private RequestQueue mRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__comensal);

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();
        btnBuscar = findViewById(R.id.btnBuscar);
        btnCambiar = findViewById(R.id.btnCambiar);
        btnEliminar = findViewById(R.id.btnEliminar);
        TVNombre = findViewById(R.id.TVNombre);
        TVNombre_Usuario = findViewById(R.id.TVNombre_Usuario);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(Perfil_Comensal.this, Buscar_Platillo.class);
                Perfil_Comensal.this.startActivity(intentReg);

            }
        });

        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(Perfil_Comensal.this, ActualizarComensal.class); //"Llamamos" al registro desde el main
                Perfil_Comensal.this.startActivity(intentReg);  //Comenzar la actividad del registro
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BorrarWebService(TVNombre.getText().toString(),TVNombre_Usuario.getText().toString());
            }

            private void BorrarWebService(String TVNombre, String TVNombre_Usuario) {
                HashMap<String,String> hashMapToken = new HashMap<>();
                hashMapToken.put("Nombre_Usuario", TVNombre_Usuario);
                hashMapToken.put("Nombre", TVNombre);

                JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_BORRCOM, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject datos) {
                        try {
                            String estado = datos.getString("resultado");
                            if (estado.equalsIgnoreCase("El usuario se borró correctamente")) {
                                Toast.makeText(Perfil_Comensal.this, estado, Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(Perfil_Comensal.this, estado, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(Perfil_Comensal.this, "No se pudo borrar al usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Perfil_Comensal.this, "No se pudo borrar al usuario", Toast.LENGTH_SHORT).show();
                    }
                });
                VolleyRP.addToQueue(solicitud, mRequest, Perfil_Comensal.this, volley);

            }
        });

    }

}
