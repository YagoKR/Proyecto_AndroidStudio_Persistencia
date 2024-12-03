package com.example.ejerciciobbdd.vista;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ejerciciobbdd.R;
import com.example.ejerciciobbdd.bbdd.LibroDAO;
import com.example.ejerciciobbdd.bbdd.entidades.Libro;

public class InsertarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insertar);

        EditText editCod = findViewById(R.id.editCodLibro);
        EditText editTitulo = findViewById(R.id.editTitulo);
        Button btnGuardarNuevo = findViewById(R.id.btnGuardarNuevo);

        btnGuardarNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = editCod.getText().toString();
                String tit = editTitulo.getText().toString();

                Libro lib = new Libro (codigo, tit);
                LibroDAO libDAO = new LibroDAO(InsertarActivity.this);

                libDAO.open();
                libDAO.insertarDatos(lib);
                libDAO.close();
                finish();

                Toast.makeText(getApplicationContext(), "Nuevo libro insertado con éxito", Toast.LENGTH_LONG).show();
            }
        });
    }
}