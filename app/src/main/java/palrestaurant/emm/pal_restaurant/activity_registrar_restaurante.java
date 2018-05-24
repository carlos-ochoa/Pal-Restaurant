package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class activity_registrar_restaurante extends AppCompatActivity{

    private static final String IP_REGISTRAR = "http://pruebagamash.esy.es/archPHP/Registrar_Restaurante_INSERT.php";

    EditText nombre, tipo, pass, direccion, tel, web, desc;
    Button registrar;
    String nombreRest;
    private VolleyRP volley;
    private RequestQueue mRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_restaurante);

        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        nombre = (EditText) findViewById(R.id.txtNombre);
        tipo = (EditText) findViewById(R.id.txtTipo);
        pass = (EditText) findViewById(R.id.txtPass);
        direccion = (EditText) findViewById(R.id.txtDirec);
        tel = (EditText) findViewById(R.id.txtTel);
        web = (EditText) findViewById(R.id.txtWeb);
        desc = (EditText) findViewById(R.id.txtDesc);
        registrar = (Button) findViewById(R.id.btnCrear1);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrarWebService(nombre.getText().toString(),pass.getText().toString(),tipo.getText().toString(), direccion.getText().toString(), tel.getText().toString(), web.getText().toString(), desc.getText().toString(), "Restaurante");
            }
        });
    }

    private void registrarWebService(String nombre,String pass,String tipo, String direccion, String tel, String web, String desc, String tipoUsuario){
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("Nombre_Usuario", nombre);
        hashMapToken.put("Contrasena", pass);
        hashMapToken.put("Nombre_Restaurante", nombre);
        hashMapToken.put("Tipo_Usuario", tipoUsuario);
        hashMapToken.put("Tipo_Rest", tipo);
        hashMapToken.put("Direccion", direccion);
        hashMapToken.put("Telefono", tel);
        hashMapToken.put("Sitio_Web", web);
        hashMapToken.put("Descripcion", desc);



        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_REGISTRAR, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("El usuario se registró correctamente")) {
                        Toast.makeText(activity_registrar_restaurante.this, estado, Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intentReg = new Intent(activity_registrar_restaurante.this, perfilRestaurante.class); //"Llamamos" al registro desde el main
                        activity_registrar_restaurante.this.startActivity(intentReg);
                    } else {
                        Toast.makeText(activity_registrar_restaurante.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(activity_registrar_restaurante.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_registrar_restaurante.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);
    }

    /*private void SubirToken(String id,String token){
        SolicitudesJson s = new SolicitudesJson() {
            @Override
            public void solicitudCompletada(JSONObject j) {
                Toast.makeText(Registro.this, "Se registro correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void solicitudErronea() {
                Toast.makeText(Registro.this, "No se pudo subir el token", Toast.LENGTH_SHORT).show();
                finish();
            }
        };

        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("id",id);
        hashMapToken.put("token",token);

        s.solicitudJsonPOST(this,SolicitudesJson.IP_TOKEN_UPLOAD,hashMapToken);
    }*/

}
