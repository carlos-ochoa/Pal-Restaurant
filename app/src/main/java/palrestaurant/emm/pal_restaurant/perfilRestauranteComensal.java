package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class perfilRestauranteComensal extends AppCompatActivity {

    Button reserva, encuesta, resena;
    TextView restaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_restaurante_comensal);

        final String nombreRestaurante = getIntent().getStringExtra("nombreRest");

        reserva = (Button) findViewById(R.id.btnReservar);
        encuesta = (Button) findViewById(R.id.btnEncuesta);
        resena = (Button) findViewById(R.id.btnResena);
        restaurante = (TextView) findViewById(R.id.TVNombreRest);

        restaurante.setText(nombreRestaurante);

        reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(perfilRestauranteComensal.this, Reservar_lugar.class);
                intentReg.putExtra("nRes",nombreRestaurante);
                perfilRestauranteComensal.this.startActivity(intentReg);
            }
        });

        encuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(perfilRestauranteComensal.this, activity_responder_encuesta.class);
                intentReg.putExtra("nRes",nombreRestaurante);
                perfilRestauranteComensal.this.startActivity(intentReg);
            }
        });

        resena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(perfilRestauranteComensal.this, activity_resenas.class);
                intentReg.putExtra("nRes",nombreRestaurante);
                perfilRestauranteComensal.this.startActivity(intentReg);
            }
        });
    }
}
