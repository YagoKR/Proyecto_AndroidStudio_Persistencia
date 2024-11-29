package com.example.ejerciciofragmentos.vista.actividades;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import com.example.ejerciciofragmentos.R;
import com.example.ejerciciofragmentos.modelo.entidades.Actor;
import com.example.ejerciciofragmentos.modelo.entidades.Pelicula;
import com.example.ejerciciofragmentos.vista.fragmentos.DatosActores;
import com.example.ejerciciofragmentos.vista.fragmentos.DatosPelicula;
import com.example.ejerciciofragmentos.vista.fragmentos.ListaActores;
import com.example.ejerciciofragmentos.vista.fragmentos.ListaPeliculas;

public class MainActivity extends AppCompatActivity implements ListaPeliculas.OnPeliculaSelectedListener, ListaActores.OnActorSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        findViewById(R.id.fragmentListaActores).setVisibility(View.GONE);
        findViewById(R.id.fragmentDatosActores).setVisibility(View.GONE);
    }

    @Override
    public void onPeliculaSelected(Pelicula pelicula) {
        boolean esTablet = (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;

        if (esTablet) {
            DatosPelicula datosPeliculasFragment = DatosPelicula.newInstance (
                    pelicula.getNombre(),
                    pelicula.getSinopsis(),
                    pelicula.getGenero(),
                    pelicula.getFecha().toString(),
                    pelicula.getImagen()
                 );
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentDatosPeliculas, datosPeliculasFragment)
                            .commit();

        } else {
            Intent i = new Intent (MainActivity.this, VistaPelicula.class);
            i.putExtra("nombre",pelicula.getNombre());
            i.putExtra("sinopsis",pelicula.getSinopsis());
            i.putExtra("genero",pelicula.getGenero());
            i.putExtra("fecha",pelicula.getFecha().toString());
            i.putExtra("imagen",pelicula.getImagen());
            i.putExtra("idPelicula", pelicula.getId());
            startActivity(i);

        }
    }

    @Override
    public void onActorSelected(Actor actor) {
        boolean esTablet = (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
        if (esTablet) {
            DatosActores datosActoresFragment = DatosActores.newInstance (
                    actor.getNombre(),
                    actor.getFechaNacimiento().toString()

            );
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentDatosActores, datosActoresFragment)
                    .commit();

        } else {
            Intent i = new Intent (MainActivity.this, VistaActores.class);
            i.putExtra("nombre",actor.getNombre());
            i.putExtra("fecha",  actor.getFechaNacimiento().toString());
            startActivity(i);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.actor) {
            findViewById(R.id.fragmentListaPeliculas).setVisibility(View.GONE);
            findViewById(R.id.fragmentListaActores).setVisibility(View.VISIBLE);
            findViewById(R.id.fragmentDatosActores).setVisibility(View.GONE);
            return true;
        }

        if (item.getItemId() == R.id.pelis) {
            findViewById(R.id.fragmentListaPeliculas).setVisibility(View.VISIBLE);
            findViewById(R.id.fragmentListaActores).setVisibility(View.GONE);
            findViewById(R.id.fragmentDatosActores).setVisibility(View.GONE);
            return true;
        }
        
        if (item.getItemId() == R.id.salir) {
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }
}