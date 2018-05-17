package palrestaurant.emm.pal_restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class activity_registrar_restaurante extends AppCompatActivity {

    EditText nombre, user, pass;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_restaurante);
        nombre = (EditText) findViewById(R.id.txtNombre);
        user = (EditText) findViewById(R.id.txtUsuario);
        pass = (EditText) findViewById(R.id.txtPassword);
    }
}
