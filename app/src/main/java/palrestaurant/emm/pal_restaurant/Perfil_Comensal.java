package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Perfil_Comensal extends AppCompatActivity {

    Button btnBuscar, btnCambiar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__comensal);

        btnBuscar = findViewById(R.id.btnBuscar);
        btnCambiar = findViewById(R.id.btnCambiar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(Perfil_Comensal.this, Buscar_Platillo.class);
                Perfil_Comensal.this.startActivity(intentReg);

            }
        });

        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(Perfil_Comensal.this, ActualizarComensal.class); //"Llamamos" al registro desde el main
                Perfil_Comensal.this.startActivity(intentReg);  //Comenzar la actividad del registro
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    }
        });

    }
}
