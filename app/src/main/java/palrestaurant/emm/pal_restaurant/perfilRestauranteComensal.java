package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class perfilRestauranteComensal extends AppCompatActivity {

    Button reserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_restaurante_comensal);

        reserva = (Button) findViewById(R.id.btnReservar);

        reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(perfilRestauranteComensal.this, Reservaciones_Comensal.class);
                perfilRestauranteComensal.this.startActivity(intentReg);
            }
        });
    }
}
