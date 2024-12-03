package com.example.ejerciciobbdd.vista;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ejerciciobbdd.R;
import com.example.ejerciciobbdd.bbdd.LibroDAO;
import com.example.ejerciciobbdd.bbdd.entidades.Libro;
import com.example.ejerciciobbdd.vista.adaptadores.AdaptadorLibro;
import com.example.ejerciciobbdd.vista.listeners.CustomClickListener;

import java.util.List;

public class VisualizarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_visualizar);

        ListView listLibros = findViewById(R.id.listView);
        Button btnRefrescar = findViewById(R.id.btnRefrescar);

        btnRefrescar.setOnClickListener(new CustomClickListener(this, listLibros));
    }
}