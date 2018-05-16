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

public class Registrar_Comensal extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre, etNombre_Usuario, etContraseña;
    Button btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__comensal);

        etNombre=(EditText)findViewById(R.id.EditT_Nombre);
        etNombre_Usuario=(EditText)findViewById(R.id.EditT_Nombre_Usuario);
        etContraseña=(EditText)findViewById(R.id.EditT_Contraseña);
        btnCrear=(Button)findViewById(R.id.btnCrear);

        btnCrear.setOnClickListener(this);






    }

    @Override
    public void onClick(View view) {
        final String Nombre_Usuario=etNombre_Usuario.getText().toString();
        final String Contraseña=etContraseña.getText().toString();
        final String Nombre=etNombre.getText().toString();

        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean Exito = jsonResponse.getBoolean("Exito");

                    if(Exito){
                        Intent intent = new Intent(Registrar_Comensal.this, MainActivity.class);
                        Registrar_Comensal.this.startActivity(intent);
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registrar_Comensal.this);
                        builder.setMessage("Error en registro")
                                .setNegativeButton("Reintentar", null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        RegisterRequest registerRequest = new RegisterRequest(Nombre_Usuario,Contraseña,Nombre,respoListener);
        RequestQueue queue = Volley.newRequestQueue(Registrar_Comensal.this);
        queue.add(registerRequest);
    }
}