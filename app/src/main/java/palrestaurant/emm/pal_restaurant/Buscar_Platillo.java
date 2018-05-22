package palrestaurant.emm.pal_restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Buscar_Platillo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar__platillo);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> Adaptador = new ArrayAdapter<String>(Buscar_Platillo.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.precios));
                Adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter(Adaptador);
    }
}
