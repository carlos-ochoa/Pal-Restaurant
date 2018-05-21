package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private static final String IP_REGISTRAR = "http://pruebagamash.esy.es/archPHP/Iniciar_Sesion_GETID.php?Nombre_Usuario=";

    private String USER = "";
    private String PASSWORD = "";

    EditText TEnombre_usuario, TEPass;
    Button btnReg, btnIni;
    private VolleyRP volley;
    private RequestQueue mRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        btnIni = findViewById(R.id.btnIni);
        btnReg = findViewById(R.id.btnReg);  //Creamos el botòn que nos llevará a registrar usuario.

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(MainActivity.this, Registro.class); //"Llamamos" al registro desde el main
                MainActivity.this.startActivity(intentReg);  //Comenzar la actividad del registro

            }
        });

        btnIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VerificarLogin(TEnombre_usuario.getText().toString(), TEPass.getText().toString());
            }
        });
    }

    public void VerificarLogin(String user, String password){
        USER = user;
        PASSWORD = password;
        SolicitudJSON(IP_REGISTRAR+user);
    }

    public void SolicitudJSON(String URL){
        JsonObjectRequest solicitud = new JsonObjectRequest(URL,null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                VerificarPassword(datos);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Ocurrio un error, por favor contactese con el administrador",Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud,mRequest,this,volley);
    }

    public void VerificarPassword(JSONObject datos){
        try {
            String estado = datos.getString("resultado");
            if(estado.equals("CC")){
                JSONObject Jsondatos = new JSONObject(datos.getString("datos"));
                String usuario = Jsondatos.getString("Nombre_Usuario");
                String contraseña = Jsondatos.getString("Contrasena");
                if(usuario.equals(USER) && contraseña.equals(PASSWORD)){
                    Toast.makeText(this,"Usted se ha logeado correctamente",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this,Perfil_Comensal.class);
                    startActivity(i);
                }
                else Toast.makeText(this,"La contraseña es incorrecta",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,estado,Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            Toast.makeText(MainActivity.this, "No se pudo ingresar, error inesperado", Toast.LENGTH_SHORT).show();
        }
    }

    /*private void registrarWebService(String nombre_usuario, String pass){
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("Nombre", nombre_usuario);
        hashMapToken.put("Contraseña", pass);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_REGISTRAR, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("El usuario si existe")) {
                        Toast.makeText(MainActivity.this, estado, Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intentReg = new Intent(MainActivity.this, Perfil_Comensal.class); //"Llamamos" al registro desde el main
                        MainActivity.this.startActivity(intentReg);  //Comenzar la actividad del registro
                    } else {
                        Toast.makeText(MainActivity.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Usuario inexistente", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Usuario inexistente", Toast.LENGTH_SHORT).show();
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

