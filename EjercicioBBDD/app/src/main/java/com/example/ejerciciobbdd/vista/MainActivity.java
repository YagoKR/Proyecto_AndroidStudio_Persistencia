package com.example.ejerciciobbdd.vista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ejerciciobbdd.R;
import com.example.ejerciciobbdd.bbdd.BibliotecaDbHelper;

public class MainActivity extends AppCompatActivity {
    public BibliotecaDbHelper bdHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bdHelper = new BibliotecaDbHelper(getApplicationContext());
        Button btnCrear = findViewById(R.id.btnCrear);
        Button btnInsertar = findViewById(R.id.btnInsertar);
        Button btnVisualizar = findViewById(R.id.btnVisualizar);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bdHelper.getWritableDatabase();
                Toast.makeText(getApplicationContext(),"Base de datos creada con éxito", Toast.LENGTH_LONG).show();
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InsertarActivity.class);
                startActivity(i);
            }
        });

        btnVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, VisualizarActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (bdHelper != null) {
            bdHelper.close();
        }
        super.onDestroy();
    }
}