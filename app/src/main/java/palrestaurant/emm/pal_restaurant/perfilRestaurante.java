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

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;

public class perfilRestaurante extends AppCompatActivity {

    private static final String IP_BORRCOM = "http://pruebagamash.esy.es/archPHP/Eliminar_Rest_2.php";

    Button btnEliminar, btnActualizar, btnCrear, btnLeer;
    private VolleyRP volley;
    private RequestQueue mRequest;
    TextView TVNombreRest, TVNombre_Usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_restaurante2);

        final String nombreRestaurante = getIntent().getStringExtra("nombreUsuario");

        btnLeer = findViewById(R.id.btnQR);
        btnActualizar  = findViewById(R.id.btnActu);
        btnEliminar = findViewById(R.id.btnEliminar);
        TVNombreRest = findViewById(R.id.TVNombreRest);
        TVNombre_Usuario = findViewById(R.id.TVNombreRest);
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
