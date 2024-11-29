package com.example.ejerciciofragmentos.vista.actividades;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ejerciciofragmentos.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VistaActores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vista_actores);
        String nombre = getIntent().getStringExtra("nombre");
        String fecha = getIntent().getStringExtra("fecha");

        EditText textViewNombre = findViewById(R.id.nombreActor);
        EditText textViewFecha = findViewById(R.id.editTextDate);

        if (nombre != null) {
            textViewNombre.setText(nombre);
            textViewNombre.setEnabled(false);
        }

        if (fecha != null) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.getDefault());
            try {
                Date date = inputFormat.parse(fecha);
                if (date != null) {
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String fechaFormateada = outputFormat.format(date);

                    textViewFecha.setText(fechaFormateada);
                    textViewFecha.setEnabled(false);
                }
            } catch (ParseException e) {
                textViewFecha.setText("Fecha inválida");
                textViewFecha.setEnabled(false);
            }

        }

        Button editeButton = findViewById(R.id.button4);
        editeButton.setOnClickListener(v -> {
            textViewNombre.setEnabled(true);
            textViewFecha.setEnabled(true);
        });

        Button saveButton = findViewById(R.id.button5);
        saveButton.setOnClickListener(v -> {
            textViewNombre.setEnabled(false);
            textViewFecha.setEnabled(false);
            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();

        });

        }
    }
