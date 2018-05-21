package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class activity_responder_encuesta extends AppCompatActivity {

    Button siguiente;
    RadioButton r1,r2,r3,r4,r5;
    TextView pregunta;
    int i=0, resp=0;
    String [ ] preguntas= { "La rapidez con la que le fue otorgado el servicio",
                            "¿Cómo calificaría la limpieza del restaurante?",
                            "La calidad del servicio recibido ha sido",
                            "La relación precio / calidad del servicio es",
                            "El tiempo de espera para ser atendido fue",
                            "¿Con qué nivel de eficacia lo atendieron?",
                            "¿Cumplieron con lo que solicitó?",
                            "¿Cuáles son las probabilidades de que recomiende el restaurante?"};
    int respuestas []= new int[8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responder_encuesta);
        siguiente = findViewById(R.id.btnSiguiente);
        pregunta = findViewById(R.id.text);
        r1=findViewById(R.id.radioButton1);
        r2=findViewById(R.id.radioButton2);
        r3=findViewById(R.id.radioButton3);
        r4=findViewById(R.id.radioButton4);
        r5=findViewById(R.id.radioButton5);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(i!=7)
                {
                    if (r1.isChecked())
                        respuestas[i]=5;
                    if (r2.isChecked())
                        respuestas[i]=4;
                    if (r3.isChecked())
                        respuestas[i]=3;
                    if (r4.isChecked())
                        respuestas[i]=2;
                    if (r2.isChecked())
                        respuestas[i]=1;
                    pregunta.setText(preguntas[i]);

                }
                if(i==7)
                {
                    siguiente.setText("Finzalizar encuesta");

                }


            }
        });


    }


}
