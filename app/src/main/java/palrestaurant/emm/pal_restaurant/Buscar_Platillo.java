package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Buscar_Platillo extends AppCompatActivity {

    Button buscar;
    TextView plat;
    RadioButton gourmet, especialidad, familiar, buffet, comida_rapida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar__platillo);

        final String nombreRestaurante = getIntent().getStringExtra("nombreUsuario");

        buscar = findViewById(R.id.btnBuscar);
        plat = findViewById(R.id.txtPlatillo);
        gourmet = findViewById(R.id.radioBtnGourmet);
        especialidad = findViewById(R.id.radioBtnEsp);
        familiar = findViewById(R.id.radioBtnFam);
        buffet = findViewById(R.id.radioBtnBuf);
        comida_rapida = findViewById(R.id.radioBtnRap);



        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> Adaptador = new ArrayAdapter<String>(Buscar_Platillo.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.precios));
                Adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mySpinner.setAdapter(Adaptador);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String platillo = "";
                String nombre = "";
                String precio = "", may = "";
                if(!plat.getText().toString().equals(""))nombre = plat.getText().toString();
                if(gourmet.isChecked()) platillo = "Gourmet";
                else if(especialidad.isChecked()) platillo = "Especialidad";
                else if(familiar.isChecked()) platillo = "Familiar";
                else if(buffet.isChecked()) platillo = "Buffet";
                else if(comida_rapida.isChecked()) platillo = "Comida rápida";
                Toast.makeText(Buscar_Platillo.this, platillo, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Buscar_Platillo.this,presentarResultados.class);
                i.putExtra("nombreUsuario",nombreRestaurante);
                i.putExtra("platilloTexto",platillo);
                i.putExtra("platilloNombre",nombre);
                startActivity(i);
            }
        });
    }

    //Definir método para obtener restaurante
    
}
