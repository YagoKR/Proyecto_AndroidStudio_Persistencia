package com.example.ejerciciofragmentos.vista.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ejerciciofragmentos.R;
import com.example.ejerciciofragmentos.mock.ObtencionDatos;
import com.example.ejerciciofragmentos.modelo.entidades.Actor;
import com.example.ejerciciofragmentos.vista.adaptadores.ActorAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class VistaPelicula extends AppCompatActivity {
    private ListView listViewActores;
    private TextView comentario;
    private TextView textViewFechaa;
    private TextView textViewHora;
    private static final int REQUEST_CODE_DATE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vista_peliculas);
        String nombre = getIntent().getStringExtra("nombre");
        String genero = getIntent().getStringExtra("genero");
        String sinopsis = getIntent().getStringExtra("sinopsis");
        String fecha = getIntent().getStringExtra("fecha");
        String imagen = getIntent().getStringExtra("imagen");
        int idPelicula = getIntent().getIntExtra("idPelicula", -1);


        TextView textViewNombre = findViewById(R.id.textViewNom);
        TextView textViewGenero = findViewById(R.id.textViewGen);
        TextView textViewSinopsis = findViewById(R.id.textViewSin);
        TextView textViewFecha = findViewById(R.id.textViewFec);
        TextView textViewImagen= findViewById(R.id.textViewImg);

        if (nombre != null) {
            textViewNombre.setText(nombre);
        }
        if (genero != null) {
            textViewGenero.setText(genero);
        }
        if (sinopsis != null) {
            textViewSinopsis.setText(sinopsis);
        }
        if (fecha != null) {
            textViewFecha.setText(fecha);
        }
        if(imagen != null) {
            textViewImagen.setText(imagen);
        }

        listViewActores = findViewById(R.id.listaActor);

        if (idPelicula != -1) {
            ObtencionDatos obtencionDatos = new ObtencionDatos();
            ArrayList<Actor> listaActores = obtencionDatos.obtenerListadoActores(idPelicula);

            ActorAdapter adapter = new ActorAdapter(this, R.layout.actor_element, listaActores.toArray(new Actor[0]));
            listViewActores.setAdapter(adapter);
        }
        textViewFechaa = findViewById(R.id.textView3);
        comentario = findViewById(R.id.coment);

        Button editButton = findViewById(R.id.editar);
        editButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Editar comentario");
            final EditText input = new EditText(this);
            input.setText(comentario.getText().toString());
            builder.setView(input);

            builder.setPositiveButton("Guardar", (dialog, which) -> {
                String newComment = input.getText().toString();

                comentario.setText(newComment);
            });

            builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

            builder.show();
        });

        Button indicarButton = findViewById(R.id.indicar);
        indicarButton.setOnClickListener(v -> {
            Intent intent = new Intent(VistaPelicula.this, SeleccionFechaActivity.class);
            startActivityForResult(intent, REQUEST_CODE_DATE);
        });

        RatingBar rb = findViewById(R.id.rating);
        Button guardarButton = findViewById(R.id.guardare);
        textViewHora = findViewById(R.id.textView4);

        guardarButton.setOnClickListener(v -> {
            String comentarioO = comentario.getText().toString();
            float puntuacion = rb.getRating();

            String horA = textViewHora.getText().toString();
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.getDefault());

            Date date = null;
            try {
                date = inputFormat.parse(fecha);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (date != null) {
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String fechaFormateada = outputFormat.format(date);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirmar Guardado");
            builder.setMessage("Comentario: " + comentarioO + "\n" +
                    "Puntuación: " + puntuacion + "\n" +
                    "Fecha: " + fechaFormateada + "\n"+
                    "Hora: " + horA );

            builder.setPositiveButton("Guardar", (dialog, which) -> {
                Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
            });

            builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
            builder.show();
        }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_DATE && resultCode == RESULT_OK && data != null) {
            String fechaSeleccionada = data.getStringExtra("fechaSeleccionada");
            String horaSeleccionada = data.getStringExtra("horaSeleccionada");

            textViewFechaa.setText(fechaSeleccionada);
            textViewHora.setText(horaSeleccionada);
        }
        }
    }
