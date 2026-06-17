package com.ifsc.contaclicks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class PhotoPreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);

        ImageView imageFull = findViewById(R.id.imageFull);
        Uri photoUri = getIntent().getData();

        if (photoUri == null) {
            finish();
            return;
        }

        String path = photoUri.getPath();
        if (path == null) {
            finish();
            return;
        }

        File photoFile = new File(path);
        Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
        imageFull.setImageBitmap(bitmap);
    }
}

