package palrestaurant.emm.pal_restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActualizarComensal extends AppCompatActivity {

    EditText Nombre, Nombre_usuario, ContActual, contNueva;
    Button btnCambiarFoto, btnAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_comensal);

        btnCambiarFoto = findViewById(R.id.btnCambiar_foto);
        btnAct = findViewById(R.id.btnAct);
        Nombre = findViewById(R.id.EditT_Nombre);
        Nombre_usuario = findViewById(R.id.EditT_Nombre_Usuario);
        ContActual = findViewById(R.id.EditT_Cont);
        contNueva = findViewById(R.id.EditT_NuevaCont);

        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        btnCambiarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
