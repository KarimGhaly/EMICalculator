package com.example.admin.emicalculator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import java.lang.Object;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText etName;
    EditText etAge;
    ImageView imgView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView1 = (ImageView) findViewById(R.id.imgView1);
    }


    public void GoToNext(View view) {
        byte[] byteArray = null;

        Intent intent = new Intent(this, Second.class);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        intent.putExtra(Constants.clientName, etName.getText().toString());
        intent.putExtra(Constants.clientAge, etAge.getText().toString());
        if (flagImgCaptured)
        {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
            intent.putExtra(Constants.ImageKey, byteArray);
        }
        startActivity(intent);
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    public void GetPhoto(View view) {
        dispatchTakePictureIntent();

    }

    private Bitmap imageBitmap;
    private boolean flagImgCaptured =false;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imgView1.setImageBitmap(imageBitmap);
            flagImgCaptured = true;
        }
    }
}
