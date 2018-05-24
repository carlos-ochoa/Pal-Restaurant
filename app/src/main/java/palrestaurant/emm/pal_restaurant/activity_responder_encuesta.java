package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class activity_responder_encuesta extends AppCompatActivity {

    private static final String IP_REGISTRAR = "http://pruebagamash.esy.es/archPHP/insertar_encuesta_post.php";
    Button siguiente, finalizar;
    RadioButton r1,r2,r3,r4,r5;
    TextView pregunta;
    int i=0;
    String [ ] preguntas= { "La rapidez con la que le fue otorgado el servicio",
                            "¿Cómo calificaría la limpieza del restaurante?",
                            "La calidad del servicio recibido ha sido",
                            "La relación precio / calidad del servicio es",
                            "El tiempo de espera para ser atendido fue",
                            "¿Con qué nivel de eficacia lo atendieron?",
                            "¿Cumplieron con lo que solicitó?",
                            "¿Cuáles son las probabilidades de que recomiende el restaurante?"};
    int respuestas []= new int[8];
    private VolleyRP volley;
    private RequestQueue mRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_encuesta);
        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();
        siguiente = findViewById(R.id.btnSiguiente);
        finalizar = findViewById(R.id.btnFinalizar);
        pregunta = findViewById(R.id.text);
        r1=findViewById(R.id.radioButton1);
        r2=findViewById(R.id.radioButton2);
        r3=findViewById(R.id.radioButton3);
        r4=findViewById(R.id.radioButton4);
        r5=findViewById(R.id.radioButton5);
        for (int j=0; j<7; j++)
            respuestas[j]=0;


        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i <= 7) {
                    if (r1.isChecked())
                        respuestas[i] = 5;
                    if (r2.isChecked())
                        respuestas[i] = 4;
                    if (r3.isChecked())
                        respuestas[i] = 3;
                    if (r4.isChecked())
                        respuestas[i] = 2;
                    if (r2.isChecked())
                        respuestas[i] = 1;
                    pregunta.setText(preguntas[i]);

                }
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar_respuestas(123, respuestas[0], respuestas[1], respuestas[2], respuestas[3], respuestas[4],
                        respuestas[5], respuestas[6], respuestas[7]);
            }
        });


    }
//SIRVEszr
    private void insertar_respuestas(
            int idRestaurante,int Pre1,int Pre2,int Pre3, int Pre4,int Pre5,
            int Pre6, int Pre7, int Pre8
    ){
        HashMap<String,Integer> hashMapToken = new HashMap<>();
        hashMapToken.put("ID_Restaurante", idRestaurante);
        hashMapToken.put("Valor_Pre1", Pre1);
        hashMapToken.put("Valor_Pre2", Pre2);
        hashMapToken.put("Valor_Pre3", Pre3);
        hashMapToken.put("Valor_Pre4", Pre4);
        hashMapToken.put("Valor_Pre5", Pre5);
        hashMapToken.put("Valor_Pre6", Pre6);
        hashMapToken.put("Valor_Pre7", Pre7);
        hashMapToken.put("Valor_Pre8", Pre8);

        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_REGISTRAR, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("Gracias por contestar")) {
                        Toast.makeText(activity_responder_encuesta.this, estado, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(activity_responder_encuesta.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(activity_responder_encuesta.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_responder_encuesta.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);
    }



}
