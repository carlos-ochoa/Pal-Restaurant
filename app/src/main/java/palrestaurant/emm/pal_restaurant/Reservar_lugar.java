package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Reservar_lugar extends AppCompatActivity {

    private static final String IP_RESERVAR = "http://pruebagamash.esy.es/archPHP/Reservar_INSERT.php";
    Button btnAceptar, btnCancelar;
    EditText et_hora, et_mesa, et_fecha;
    ImageView codigo;
    private VolleyRP volley;
    private RequestQueue mRequest;
    private String hora, fecha, mesa;
    private String data2QR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_lugar);

        codigo = (ImageView) findViewById(R.id.codigo2);
        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);
        et_hora = findViewById(R.id.etHora);
        et_fecha = findViewById(R.id.etFecha);
        et_mesa = findViewById(R.id.etMesa);
        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora = et_hora.getText().toString();
                fecha = et_fecha.getText().toString();
                mesa = et_mesa.getText().toString();
                reservarWebService(hora,fecha,mesa);
                data2QR = hora + fecha + mesa;
                Intent i = new Intent(Reservar_lugar.this, verQR.class);
                i.putExtra("data2QR",data2QR);
                startActivity(i);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(Reservar_lugar.this, Perfil_Comensal.class); //"Llamamos" al registro desde el main
                Reservar_lugar.this.startActivity(intentReg);  //Comenzar la actividad del registro

            }
        });
    }

    private void reservarWebService(String hora, String fecha, String mesa){
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("Hora", hora);
        hashMapToken.put("Fecha", fecha);
        hashMapToken.put("Mesa",mesa);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_RESERVAR, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("Se ha hecho la reservacion")) {
                        Toast.makeText(Reservar_lugar.this, estado, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Reservar_lugar.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Reservar_lugar.this, "No se pudo hacer reservacion", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Reservar_lugar.this, "Error inesperado", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);
    }
}
