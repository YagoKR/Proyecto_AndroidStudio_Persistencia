package com.example.ejerciciopreferencias;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Editor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editor);

        RelativeLayout rL = findViewById(R.id.layoutR);
        TextView texto = findViewById(R.id.textoNombre);

        SharedPreferences sp = getBaseContext().getSharedPreferences("preferenciasGuardadas", MODE_PRIVATE);
        preferencesHelper pH = new preferencesHelper(sp);
        UserPreferences use = pH.cargarPreferencias();

        if (use.getColor().equalsIgnoreCase("Rojo")) {
            rL.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            texto.setText(use.getNombreUsuario());
        }
        if (use.getColor().equalsIgnoreCase("Verde")) {
            rL.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            texto.setText(use.getNombreUsuario());
        }
        if (use.getColor().equalsIgnoreCase("Azul")) {
            rL.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            texto.setText(use.getNombreUsuario());
        }
    }
}