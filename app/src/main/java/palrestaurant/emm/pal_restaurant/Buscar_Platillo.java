package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Buscar_Platillo extends AppCompatActivity {

    Button buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar__platillo);

        buscar = findViewById(R.id.btnBuscar);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> Adaptador = new ArrayAdapter<String>(Buscar_Platillo.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.precios));
                Adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter(Adaptador);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Buscar_Platillo.this,presentarResultados.class);
                //i.putExtra("nombreUsuario",usuario);
                startActivity(i);
            }
        });
    }
}
