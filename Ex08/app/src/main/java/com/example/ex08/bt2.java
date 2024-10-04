package com.example.ex08;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bt2 extends AppCompatActivity {

    ImageView myimg;
    ImageButton btncapture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myimg = findViewById(R.id.myimg);
        btncapture = findViewById(R.id.btncapture);
        btncapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(ACTION_IMAGE_CAPTURE);
                if (ActivityCompat.checkSelfPermission(bt2.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(bt2.this,new
                            String[]{Manifest.permission.CAMERA}, 1);
                    return;
                }
                startActivityForResult(cameraintent,99);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            myimg.setImageBitmap(photo);
        }
    }
}