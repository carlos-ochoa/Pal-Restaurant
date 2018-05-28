package palrestaurant.emm.pal_restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class presentarResultados extends AppCompatActivity {

    private static final String IP = "http://pruebagamash.esy.es/archPHP/Cargar_platillos.php";

    List<Platillo> platillos;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentar_resultados);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        platillos = new ArrayList<>();

        cargarPlatillos();
    }

    private void cargarPlatillos(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, IP, new Response.Listener<String>() {
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
                    Adapter adapter = new Adapter(presentarResultados.this, platillos);
                    recyclerView.setAdapter(adapter);
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
