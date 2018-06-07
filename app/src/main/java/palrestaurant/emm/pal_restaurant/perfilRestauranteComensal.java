package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class perfilRestauranteComensal extends AppCompatActivity {

    Button reserva, encuesta, resena, verResena;
    TextView restaurante, direccion, descripcion;
    String rest;
    private static final String IP = "http://pruebagamash.esy.es/archPHP/getRestPlat.php?Nombre_Platillo=";
    private static final String IP2 = "http://pruebagamash.esy.es/archPHP/getRestaurante.php?Nombre_Restaurante=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_restaurante_comensal);

        final String nombreRestaurante = getIntent().getStringExtra("nombrePlat");


        reserva = (Button) findViewById(R.id.btnReservar);
        encuesta = (Button) findViewById(R.id.btnEncuesta);
        resena = (Button) findViewById(R.id.btnResena);
        restaurante = (TextView) findViewById(R.id.TVNombreRest);
        direccion = (TextView) findViewById(R.id.textView4);
        descripcion = (TextView) findViewById(R.id.textView3);
        verResena= (Button) findViewById(R.id.button3);
        restaurante.setText("Compaches");
        direccion.setText("Av. Siempre viva, No.34 , Col. Iztapalapa");
        restaurante.setText("Restaurante de 1 estrella");

        obtenerDatos(IP+nombreRestaurante);

        reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(perfilRestauranteComensal.this, Reservar_lugar.class);
                intentReg.putExtra("nRes",nombreRestaurante);
                perfilRestauranteComensal.this.startActivity(intentReg);
            }
        });

        encuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(perfilRestauranteComensal.this, activity_responder_encuesta.class);
                intentReg.putExtra("nRes",nombreRestaurante);
                perfilRestauranteComensal.this.startActivity(intentReg);
            }
        });

        resena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(perfilRestauranteComensal.this, activity_resenas.class);
                intentReg.putExtra("nRes",nombreRestaurante);
                perfilRestauranteComensal.this.startActivity(intentReg);
            }
        });
        verResena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(perfilRestauranteComensal.this, presentarResultados_resenas.class);
                perfilRestauranteComensal.this.startActivity(intentReg);
            }
        });
    }

    private void obtenerDatos(String URL){
        JsonObjectRequest stringRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject Jsondatos = new JSONObject(response.getString("datos"));
                    restaurante.setText(Jsondatos.getString("Nombre_Restaurante"));
                    cargarDatos(IP2+restaurante.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(perfilRestauranteComensal.this, "Error de conexion en el perfil", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void cargarDatos(String URL){
        JsonObjectRequest stringRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject Jsondatos = new JSONObject(response.getString("datos"));
                    //TVNombreRest.setText(Jsondatos.getString("Nombre_Restaurante"));
                    descripcion.setText(Jsondatos.getString("Descripcion"));
                    direccion.setText(Jsondatos.getString("Direccion"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(perfilRestauranteComensal.this, "Error de conexion en el perfil", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
