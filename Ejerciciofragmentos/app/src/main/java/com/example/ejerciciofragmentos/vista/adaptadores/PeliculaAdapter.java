package com.example.ejerciciofragmentos.vista.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ejerciciofragmentos.R;
import com.example.ejerciciofragmentos.modelo.entidades.Pelicula;

public class PeliculaAdapter extends ArrayAdapter<Pelicula> {
    public PeliculaAdapter(@NonNull Context context, int resource, @NonNull Pelicula[] objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Pelicula pelicula = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.pelicula_element, parent, false);
        }


        TextView textViewNombre = convertView.findViewById(R.id.textViewNombre);
        TextView textViewGenero = convertView.findViewById(R.id.textViewGenero);

        textViewNombre.setText(pelicula.getNombre());
        textViewGenero.setText(pelicula.getGenero());

        return convertView;
    }
}
