package com.example.ejerciciopreferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.widget.*;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;

public class Imagen extends AppCompatActivity {
    private static final int REQUEST_PERMISSIONS = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagen);
        checkPermissions();
        SharedPreferences sp = getBaseContext().getSharedPreferences("preferenciasGuardadas", MODE_PRIVATE);

        String [] lectura = {"assets","externo","interno","raw"};
        String [] guardado = {"preferencias","externo","interno"};

        ArrayAdapter<String> adapterL = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lectura);
        adapterL.setDropDownViewResource(android.R.layout.simple_spinner_item);

        ArrayAdapter<String> adapterG = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,guardado);
        adapterG.setDropDownViewResource(android.R.layout.simple_spinner_item);

        Spinner spinnerLectura = findViewById(R.id.spinnerLectura);
        spinnerLectura.setAdapter(adapterL);

        Spinner spinnerGuardado = findViewById(R.id.spinnerGuardado);
        spinnerGuardado.setAdapter(adapterG);

        ImageView imagen = findViewById(R.id.imageView);
        imagen.setImageResource(R.drawable.ic_launcher_background);


        EditText nom = findViewById(R.id.editImagen);
        Button guardar = findViewById(R.id.btnGuardar);
        Button cargar = findViewById(R.id.btnCargar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String seleccionL = spinnerLectura.getSelectedItem().toString();
                String seleccionG = spinnerGuardado.getSelectedItem().toString();
                String nomeImg = nom.getText().toString().trim();
                byte[] imageBytes = null;

                try {
                InputStream inputStream = null;
                DataInputStream dataInputStream = null;
                if (seleccionL.equalsIgnoreCase("assets")) {
                    inputStream = getAssets().open(nomeImg);
                }

                if (seleccionL.equalsIgnoreCase("externo")) {
                    File file = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), nomeImg );
                    inputStream = new FileInputStream(file);
                }

                if(seleccionL.equalsIgnoreCase("interno")){
                    inputStream = openFileInput(nomeImg);
                }

                if(seleccionL.equalsIgnoreCase("raw")){
                    int id = getResources().getIdentifier(nomeImg,"raw",getPackageName());
                    inputStream = getResources().openRawResource(id);
                }

                if (inputStream != null) {
                    imageBytes = new byte[inputStream.available()];
                    dataInputStream = new DataInputStream(inputStream);
                    dataInputStream.readFully(imageBytes);

                }

                if (seleccionG.equalsIgnoreCase("preferencias")) {
                    SharedPreferences.Editor editor = sp.edit();
                    String bit = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    editor.putString(nomeImg,bit);
                    editor.apply();
                }

                if (seleccionG.equalsIgnoreCase("externo")) {
                    File file = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), nomeImg );
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(imageBytes);
                    fos.close();
                }

                if (seleccionG.equalsIgnoreCase("interno")) {
                    FileOutputStream fos = openFileOutput(nomeImg, MODE_PRIVATE);
                    fos.write(imageBytes);
                    fos.close();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            }
        });

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String seleccionG = spinnerGuardado.getSelectedItem().toString();
                String nomeImg = nom.getText().toString().trim();
                byte[] imageBytes = null;

                try {
                    if (seleccionG.equalsIgnoreCase("preferencias")) {
                        SharedPreferences preferences = getSharedPreferences("preferenciasGuardadas", Context.MODE_PRIVATE);
                        String imageString = preferences.getString(nomeImg, null);
                        if (imageString != null) {
                            imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                        }
                    }
                    if (seleccionG.equalsIgnoreCase("externo")) {
                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), nomeImg);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            imageBytes = Files.readAllBytes(file.toPath());
                        }
                    }

                    if (seleccionG.equalsIgnoreCase("interno")) {
                        FileInputStream fis = openFileInput(nomeImg);
                        imageBytes = new byte[fis.available()];
                        fis.read(imageBytes);
                        fis.close();
                    }

                    if (imageBytes != null) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        imagen.setImageBitmap(bitmap);
                    }

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        });

    }
    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.length > 0) {
                boolean readPermissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean writePermissionGranted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (readPermissionGranted && writePermissionGranted) {
                    Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permisos denegados", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}