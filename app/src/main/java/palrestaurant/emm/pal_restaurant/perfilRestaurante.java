package palrestaurant.emm.pal_restaurant;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.Button;
import java.util.HashMap;

public class perfilRestaurante extends AppCompatActivity {

    private static final String IP_BORRCOM = "http://pruebagamash.esy.es/archPHP/Borrar_Comensal.php";

    View btnEliminar;
    private VolleyRP volley;
    private RequestQueue mRequest;
    TextView TVNombreRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_restaurante);

        btnEliminar = findViewById(R.id.btnEliminar);
        TVNombreRest = findViewById(R.id.TVNombreRest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BorrarWebService(TVNombreRest.getText().toString());

            }
        });



    }

    private void BorrarWebService(String TVNombreRest) {
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("Nombre", TVNombreRest);

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
