package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;

public class perfilRestaurante extends AppCompatActivity {

    private static final String IP_BORRCOM = "http://pruebagamash.esy.es/archPHP/Eliminar_Rest_2.php";
    private static final String IP = "http://pruebagamash.esy.es/archPHP/getRestaurante.php?Nombre_Restaurante=";

    Button btnEliminar, btnActualizar, btnCrear, btnLeer, btnEst;
    private VolleyRP volley;
    private RequestQueue mRequest;
    TextView TVNombreRest, TVNombre_Usuario, desc, dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_restaurante2);

        final String nombreRestaurante = getIntent().getStringExtra("nombreUsuario");

        btnLeer = findViewById(R.id.btnQR);
        btnEst = findViewById(R.id.button);
        btnActualizar = findViewById(R.id.btnActu);
        btnEliminar = findViewById(R.id.btnEliminar);
        TVNombreRest = findViewById(R.id.TVNombreRest);
        desc = findViewById(R.id.textView3);
        dir = findViewById(R.id.textView4);
        TVNombre_Usuario = findViewById(R.id.TVNombreRest);

        TVNombreRest.setText(nombreRestaurante);
        TVNombre_Usuario.setText(nombreRestaurante);

        obtenerDatos(IP+nombreRestaurante);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);*/

       /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabRes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                       //.setAction("Action", null).show();
                        Intent i = new Intent(perfilRestaurante.this,Subir_Menu.class);
                        //i.putExtra("nombreRest",nombreRestaurante);
                        startActivity(i);
            }
        });*/

        FloatingActionButton fab = findViewById(R.id.fabRes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent i = new Intent(perfilRestaurante.this,Subir_Menu.class);
                        //i.putExtra("nombreRest",nombreRestaurante);
                        i.putExtra("nombreUsuario", nombreRestaurante);
                        startActivity(i);
            }
        });

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        /*btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(perfilRestaurante.this, Subir_Menu.class);
                perfilRestaurante.this.startActivity(intentReg);

            }
        });*/

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(perfilRestaurante.this,activity_actualizar_restaurante.class);
                //i.putExtra("nombreRest",nombreRestaurante);
                startActivity(i);

            }
        });

        btnEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(perfilRestaurante.this,estadisticas.class);
                i.putExtra("nombreRest",nombreRestaurante);
                startActivity(i);

            }
        });

        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(perfilRestaurante.this,leerQR.class);
                //i.putExtra("nombreRest",nombreRestaurante);
                startActivity(i);

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BorrarWebService( TVNombreRest.getText().toString(), TVNombre_Usuario.getText().toString());

            }
        });



    }

    private void obtenerDatos(String URL){
        JsonObjectRequest stringRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject Jsondatos = new JSONObject(response.getString("datos"));
                    TVNombreRest.setText(Jsondatos.getString("Nombre_Restaurante"));
                    desc.setText(Jsondatos.getString("Descripcion"));
                    dir.setText(Jsondatos.getString("Direccion"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(perfilRestaurante.this, "Error de conexion en el perfil", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void BorrarWebService(String Nombre_Restaurante, String Nombre_Usuario) {
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("Nombre_Restaurante", Nombre_Restaurante);
        hashMapToken.put("Nombre_Usuario", Nombre_Usuario);


        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_BORRCOM, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("El usuario se borró correctamente")) {
                        Toast.makeText(perfilRestaurante.this, estado, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(perfilRestaurante.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(perfilRestaurante.this, "No se pudo borrar al usuario", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(perfilRestaurante.this, "No se pudo borrar al usuario", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, perfilRestaurante.this, volley);

    }

}
