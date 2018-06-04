package palrestaurant.emm.pal_restaurant;

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

public class activity_resenas extends AppCompatActivity {

    private static final String IP_ACTREST = "http://pruebagamash.esy.es/archPHP/insertar_resena_post.php";
    Button publicar;
    EditText resena;
    int idRestaurante, idComensal;
    private VolleyRP volley;
    private RequestQueue mRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenas);
        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();
        publicar = findViewById(R.id.btn);
        resena = findViewById(R.id.eT);
        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar(idRestaurante, resena.getText().toString(),idComensal);
            }
        });
    }

    private void insertar(int idRestaurante, String rese, int idComensal)
    {
        Object restaurante= idRestaurante;
        Object resOb=rese;
        Object comensal= idComensal;
        HashMap<String,Object> hashMapToken = new HashMap<>();
        hashMapToken.put("ID_Rest", restaurante);
        hashMapToken.put("Descripcion_Reseña",resOb );
        hashMapToken.put("ID_Comensal", comensal);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_ACTREST, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("Gracias por contestar")) {
                        Toast.makeText(activity_resenas.this, estado, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(activity_resenas.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(activity_resenas.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_resenas.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);
    }
}