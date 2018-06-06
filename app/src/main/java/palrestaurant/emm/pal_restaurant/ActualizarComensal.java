package palrestaurant.emm.pal_restaurant;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class ActualizarComensal extends AppCompatActivity {

    private static final String IP_ACTCOM = "http://pruebagamash.esy.es/archPHP/Actualizar_Comensal.php";

    EditText Nombre, Nombre_usuario, ContActual, contNueva;
    Button btnCambiarFoto, btnAct;

    private ImageView Foto;
    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;
    private String UPLOAD_URL ="http://pruebagamash.esy.es/archPHP/upload_Foto.php";
    private String KEY_IMAGEN = "foto";

    private static String APP_DIRECTORY = "MyPictureApp";
    private  static String MEDIA_DIRECTORY = APP_DIRECTORY + "PictureApp";
    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;
    // private static  String id_Comens;

    private RelativeLayout RLView;
    private  String mPath;

    private VolleyRP volley;
    private RequestQueue mRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_comensal);
        volley = VolleyRP.getInstance(this);
        mRequest = volley.getRequestQueue();

        btnCambiarFoto = findViewById(R.id.btnCambiar_foto);
        btnAct = findViewById(R.id.btnAct);
        Nombre = findViewById(R.id.EditT_Nombre);
        Nombre_usuario = findViewById(R.id.EditT_Nombre_Usuario);
        ContActual = findViewById(R.id.EditT_Cont);
        contNueva = findViewById(R.id.EditT_NuevaCont);
        // id_Comens=String.valueOf(R.id.);
        Foto = (ImageView) findViewById(R.id.Foto);
        btnCambiarFoto = (Button) findViewById(R.id.btnFoto);

        if(MyRequestStoragePermissions())
            btnCambiarFoto.setEnabled(true);
        else
            btnCambiarFoto.setEnabled(false);


        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarWebService(Nombre_usuario.getText().toString(), contNueva.getText().toString(),"1", ContActual.getText().toString() , Nombre.getText().toString());
            }
        });


        btnCambiarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOptions();

            }

            private void showOptions() {
                final CharSequence[] option = {"Subir Foto", "Elegir foto de galeria", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(ActualizarComensal.this);
                builder.setTitle("Elija una opcion");
                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (option[i] == "Subir Foto") {
                            AbrirCamara();
                            uploadImage();
                        } else if (option[i] == "Elegir foto de galeria") {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent, "Seleccione app de imagenes"), SELECT_PICTURE);
                            uploadImage();
                        } else {
                            dialogInterface.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        //Mostrar el di·logo de progreso
        final ProgressDialog loading = ProgressDialog.show(this,"Subiendo...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el di·logo de progreso
                        loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(ActualizarComensal.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el di·logo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(ActualizarComensal.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);

                //CreaciÛn de par·metros
                Map<String,String> params = new Hashtable<String, String>();

                //Agregando de par·metros
                params.put(KEY_IMAGEN, imagen);

                //Par·metros de retorno
                return params;
            }
        };

        //CreaciÛn de una cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
        requestQueue.add(stringRequest);
    }


    private void AbrirCamara() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();

        if(!isDirectoryCreated)
            isDirectoryCreated = file.mkdirs();
        if(isDirectoryCreated){
            Long etiquetaTiempo = System.currentTimeMillis()/1000;
            String NombreImagen = etiquetaTiempo.toString()+".jpg";
            mPath = Environment.getExternalStorageDirectory()+ File.separator + MEDIA_DIRECTORY
                    + File.separator + NombreImagen;

            File nuevoFile = new File(mPath);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(nuevoFile));
            startActivityForResult(intent, PHOTO_CODE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("file_path", mPath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPath= savedInstanceState.getString("file_path");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode) {
                case PHOTO_CODE:
                    MediaScannerConnection.scanFile(this,
                            new String[]{mPath}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String s, Uri uri) {
                                    Log.i("ExternalStorage", "Scanned" + s + ":" );
                                    Log.i("ExternalStorage", "-> Uri = " + uri);
                                }
                            });
                    Bitmap bitmap = BitmapFactory.decodeFile(mPath);
                    Foto.setImageBitmap(bitmap);
                    break;
                case SELECT_PICTURE:
                    Uri path = data.getData();
                    Foto.setImageURI(path);
                    break;
            }
        }
    }

    private boolean MyRequestStoragePermissions() {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M)
            return true;
        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED))
            return true;
        if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))||(shouldShowRequestPermissionRationale(CAMERA))){
            Snackbar.make(RLView, "Los permisos son necesarios para poder usar la aplicaciÛn",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View view) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
                }
            });
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
        }
        return false;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ActualizarComensal.this, "Permisos aceptados", Toast.LENGTH_SHORT).show();
                btnCambiarFoto.setEnabled(true);
            }
        }else{
            showExplanation();
        }
    }

    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActualizarComensal.this);
        builder.setTitle("Permisos denegados");
        builder.setMessage("Para usar las funciones de la app se necesita aceptar los permisos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        });
        builder.show();
    }


    private void ActualizarWebService(String nombre_usuario, String NuevaContraseÒa, String id_usuario,  String contraseÒa, String nombre) {
        HashMap<String,String> hashMapToken = new HashMap<>();
        hashMapToken.put("nombre_usuario", nombre_usuario);
        hashMapToken.put("NuevaContraseÒa", NuevaContraseÒa);
        hashMapToken.put("id_usuario", id_usuario);
        hashMapToken.put("contrasena", contraseÒa);
        hashMapToken.put("nombre", nombre);


        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, IP_ACTCOM, new JSONObject(hashMapToken), new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject datos) {
                try {
                    String estado = datos.getString("resultado");
                    if (estado.equalsIgnoreCase("El usuario se actualizÛ correctamente")) {
                        Toast.makeText(ActualizarComensal.this, estado, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ActualizarComensal.this, estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(ActualizarComensal.this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActualizarComensal.this, "No se pudo actualizar brother", Toast.LENGTH_SHORT).show();
            }
        });
        VolleyRP.addToQueue(solicitud, mRequest, this, volley);

    }
}