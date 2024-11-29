package com.example.ejerciciopreferencias;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getBaseContext().getSharedPreferences("preferenciasGuardadas", MODE_PRIVATE);
        preferencesHelper pH = new preferencesHelper(sp);
        UserPreferences u = pH.cargarPreferencias();

        Spinner spinnerColor = findViewById(R.id.spinnerColor);
        Spinner spinnerActividad = findViewById(R.id.spinnerActividad);
        EditText nomUsr = findViewById(R.id.editNombre);
        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnAccion= findViewById(R.id.btnAccion);

        String [] colores = {"Rojo", "Verde", "Azul"};
        ArrayAdapter<String> adapter =  new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerColor.setAdapter(adapter);

        String [] actividades = {"Editor","Gmail","Imagen"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, actividades);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerActividad.setAdapter(adapter1);

        String col = u.getColor();
        String act = u.getActividad();
        spinnerColor.setSelection(adapter.getPosition(col));
        spinnerActividad.setSelection(adapter1.getPosition(act));

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String color = spinnerColor.getSelectedItem().toString();
                String actividad = spinnerActividad.getSelectedItem().toString();
                String nome = nomUsr.getText().toString();
                pH.guardarPreferencias(color, actividad, nome, "imagen");
                Toast.makeText(getApplicationContext(), "Datos guardados con éxito", Toast.LENGTH_LONG).show();
            }
        });

        btnAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserPreferences pref = pH.cargarPreferencias();
                String activity = pref.getActividad();

                if (activity.equals("Editor")) {
                    Intent i = new Intent(MainActivity.this, Editor.class);
                    startActivity(i);
                }
                if (activity.equals("Gmail")) {
                    Intent gmailIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                    if (gmailIntent != null) {
                        startActivity(gmailIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "La aplicación Gmail no está instalada.", Toast.LENGTH_SHORT).show();
                    }

                }
                if (activity.equals("Imagen")) {
                    Intent i = new Intent(MainActivity.this, Imagen.class);
                    startActivity(i);
                }
            }
        });
    }
}