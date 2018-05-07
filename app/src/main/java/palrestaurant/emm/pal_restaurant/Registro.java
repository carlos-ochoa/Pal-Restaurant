package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registro extends AppCompatActivity {

    Button btnCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnCom = findViewById(R.id.btnCom);  //Creamos el botón que nos llevará a registrar comensal

        btnCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(Registro.this, Registrar_Comensal.class); //"Llamamos" al registro de comensal desde pantalla actual
                Registro.this.startActivity(intentReg);  //Comenzar la actividad de registrar comensal

            }
        });
    }
}