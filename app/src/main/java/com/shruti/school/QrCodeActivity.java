package com.shruti.school;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.WriterException;

public class QrCodeActivity extends AppCompatActivity {
    ImageView ivQRCode;
    private static final int QRWidth = 300;
    private static final int QRHeight = 300;

    Bitmap bitmap;
    String strUsername;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        preferences = PreferenceManager.getDefaultSharedPreferences(QrCodeActivity.this);
        strUsername = preferences.getString("Username", "");
        ivQRCode = findViewById(R.id.ivQrCodeQR);

        try {
            createQRCode();
        } catch (WriterException e) {
            Toast.makeText(QrCodeActivity.this, "Error generating QR code", Toast.LENGTH_SHORT).show();
        }
    }

    private void createQRCode() throws WriterException {
        bitmap = TextToImageEncode(strUsername);
        ivQRCode.setImageBitmap(bitmap);
    }

    private Bitmap TextToImageEncode(String strUsername) throws WriterException {
        BitMatrix bitMatrix;
        bitMatrix = new MultiFormatWriter().encode(strUsername, BarcodeFormat.QR_CODE, QRWidth, QRHeight);

        int[] pixels = new int[bitMatrix.getWidth() * bitMatrix.getHeight()];

        for (int x = 0; x < bitMatrix.getWidth(); x++) {
            int offset = x * bitMatrix.getHeight();
            for (int y = 0; y < bitMatrix.getHeight(); y++) {
                pixels[offset + y] = bitMatrix.get(x, y) ? ContextCompat.getColor(this, R.color.black) :
                        ContextCompat.getColor(this, R.color.white);
            }
        }

        bitmap = Bitmap.createBitmap(QRWidth, QRHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, QRWidth, 0, 0, QRWidth, QRHeight);

        return bitmap;
    }
}