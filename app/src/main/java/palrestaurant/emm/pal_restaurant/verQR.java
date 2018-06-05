package palrestaurant.emm.pal_restaurant;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class verQR extends AppCompatActivity {

    ImageView codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_qr);

        codigo = (ImageView) findViewById(R.id.codigo2);

        final String data = getIntent().getStringExtra("data2QR");

        Toast.makeText(verQR.this,data, Toast.LENGTH_SHORT).show();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE,400,400);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            codigo.setImageBitmap(bitmap);
        } catch (WriterException e){
            e.printStackTrace();
        }
    }
}
