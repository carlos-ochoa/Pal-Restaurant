package palrestaurant.emm.pal_restaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class presentarResultados_resenas
        extends AppCompatActivity {

    private static final String IP0 = "http://pruebagamash.esy.es/archPHP/cr.php?ID_Rest=";
    private String IP;
    private String rest;
    private String respuesta;
    List<resena> res;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentar_resultados);
        final String nombreRestaurante = getIntent().getStringExtra("nombreUsuario");
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        res = new ArrayList<>();
        cargarResena(IP0+"1");

    }
/*
    private void obtenerPerfil(String URL, final String nombrePlatillo){
        JsonObjectRequest stringRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject Jsondatos = new JSONObject(response.getString("perfil"));
                    respuesta = Jsondatos.getString("Perfil");
                    Toast.makeText(presentarResultados.this,"Perfil: " + respuesta,Toast.LENGTH_SHORT).show();
                    cargarPlatillos(IP0+nombrePlatillo+tres+respuesta, rest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(presentarResultados_resenas.this, "Error de conexion en el perfil", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }*/

    private void cargarResena(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(presentarResultados_resenas.this, "Cargando...", Toast.LENGTH_SHORT).show();
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject resJ = array.getJSONObject(i);
                        res.add(new resena(
                                resJ.getString("Nombre_Usuario"),
                                resJ.getString("Descripcion_Resena")));
                    }
                    AdapterResena adapter = new AdapterResena(presentarResultados_resenas.this, res);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(presentarResultados_resenas.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

}