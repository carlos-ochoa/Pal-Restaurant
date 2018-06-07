package palrestaurant.emm.pal_restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.HashMap;
import java.util.List;

public class presentarResultados extends AppCompatActivity {

    private static final String IP0 = "http://pruebagamash.esy.es/archPHP/cp2.php?Tipo_Platillo=";
    private static final String IP1 = "http://pruebagamash.esy.es/archPHP/cp3.php?Nombre_Platillo=";
    private static final String IP2 = "http://pruebagamash.esy.es/archPHP/cp4.php?Tipo_Platillo=";
    private static final String IP3 = "http://pruebagamash.esy.es/archPHP/cp5.php?Tipo_Platillo=";
    private static final String IP4 = "http://pruebagamash.esy.es/archPHP/cp6.php?Tipo_Platillo=";
    private static final String IP5 = "http://pruebagamash.esy.es/archPHP/cp7.php?Tipo_Platillo=";
    private static final String IP6 = "http://pruebagamash.esy.es/archPHP/cp8.php?Tipo_Platillo=";
    private static final String IP_Perfil = "http://pruebagamash.esy.es/archPHP/GetPerfil.php?Nombre=";
    private static final String dos = "&Nombre_Platillo=";
    private static final String tres = "&Perfil=";
    private String IP;
    private String rest;

    private String perfil;
    private String respuesta;


    List<Platillo> platillos;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentar_resultados);
        final String nombreRestaurante = getIntent().getStringExtra("nombreUsuario");

        final String nombreUsuario = getIntent().getStringExtra("nombreUsuario");
        final String nombrePlatillo = getIntent().getStringExtra("platilloTexto");
        final String nPlatillo = getIntent().getStringExtra("platilloNombre");

        rest = nombreRestaurante;

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        platillos = new ArrayList<>();

        //if(!nombrePlatillo.equals("")){
            obtenerPerfil(IP_Perfil+nombreUsuario, nombrePlatillo);

        //}
        /*else if(nPlatillo.length() > 0){
            Toast.makeText(presentarResultados.this, nPlatillo, Toast.LENGTH_SHORT).show();
            cargarPlatillos2(IP1+nPlatillo, rest);
        }
        /*else if(!nombrePlatillo.equals("")){
            IP = IP2;
            cargarPlatillos(IP+nombrePlatillo);
        }*/
        /*else if(!nombrePlatillo.equals("") && !nombrePlatillo.equals("")){
            IP = IP3;
            cargarPlatillos3(IP+nombrePlatillo+dos+nPlatillo, rest);
        }*/
    }

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
                        Toast.makeText(presentarResultados.this, "Error de conexion en el perfil", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void cargarPlatillos(String URL, final String resta){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(presentarResultados.this, "Cargando...", Toast.LENGTH_SHORT).show();
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject platillo = array.getJSONObject(i);
                        platillos.add(new Platillo(
                                platillo.getString("Nombre_Platillo"),
                                //platillo.getString("Tipo_Platillo"),
                                platillo.getString("Precio"),
                                platillo.getString("Descripcion_Platillo")));
                    }
                    Adapter adapter = new Adapter(presentarResultados.this, platillos, resta);
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
                        Toast.makeText(presentarResultados.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void cargarPlatillos2(String URL, final String resta){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(presentarResultados.this, "CARGANDO...", Toast.LENGTH_SHORT).show();
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject platillo = array.getJSONObject(i);
                         platillos.add(new Platillo(
                                platillo.getString("Nombre_Platillo"),
                                //platillo.getString("Tipo_Platillo"),
                                platillo.getString("Precio"),
                                platillo.getString("Descripcion_Platillo")));
                    }
                    Adapter adapter = new Adapter(presentarResultados.this, platillos, resta);
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
                        Toast.makeText(presentarResultados.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void cargarPlatillos3(String URL, final String resta){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(presentarResultados.this, "CARGANDO...", Toast.LENGTH_SHORT).show();
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject platillo = array.getJSONObject(i);
                        platillos.add(new Platillo(
                                platillo.getString("Nombre_Platillo"),
                                //platillo.getString("Tipo_Platillo"),
                                platillo.getString("Precio"),
                                platillo.getString("Descripcion_Platillo")));
                    }
                    Adapter adapter = new Adapter(presentarResultados.this, platillos, resta);
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
                        Toast.makeText(presentarResultados.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
