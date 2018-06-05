package palrestaurant.emm.pal_restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Subir_Menu extends AppCompatActivity {

    private static final String IP_REGISTRAR = "http://pruebagamash.esy.es/archPHP/insertar_platillo_insert.php";

    EditText nombre, tipo, precio, desc;
    Button registrar;
    private VolleyRP volley;
    private RequestQueue mRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir__menu);

        final String restaurante = getIntent().getStringExtra("nombreUsuario");

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        nombre = (EditText) findViewById(R.id.txtNombreP);
        tipo = (EditText) findViewById(R.id.txtTipoP);
        precio = (EditText) findViewById(R.id.txtPrecio);
        desc = (EditText) findViewById(R.id.txtDescP);
        registrar = (Button) findViewById(R.id.btnAceptar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarWebService(nombre.getText().toString(),tipo.getText().toString(),precio.getText().toString(), desc.getText().toString(), restaurante);
            }
        });
    }

    private void registrarWebService(String nombre, String tipo, String precio, String desc, final String restaurante){
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("Nombre_Platillo", nombre);
        hashMapToken.put("Tipo_Platillo", tipo);
        hashMapToken.put("Precio", precio);
        hashMapToken.put("Descripcion_Platillo", desc);
        hashMapToken.put("Nombre_Restaurante", restaurante);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_REGISTRAR, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("El platillo se registro correctamente")) {
                        Toast.makeText(Subir_Menu.this, estado + restaurante, Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intentReg = new Intent(Subir_Menu.this, perfilRestaurante.class); //"Llamamos" al registro desde el main
                        Subir_Menu.this.startActivity(intentReg);
                    } else {
                        Toast.makeText(Subir_Menu.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Subir_Menu.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Subir_Menu.this, "Error inesperado", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);
    }
}
