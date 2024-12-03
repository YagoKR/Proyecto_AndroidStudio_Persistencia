package com.example.ejerciciobbdd.vista.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ejerciciobbdd.R;
import com.example.ejerciciobbdd.bbdd.entidades.Libro;

import java.util.List;

public class AdaptadorLibro extends ArrayAdapter<Libro> {
    public AdaptadorLibro(@NonNull Context context, int resource, @NonNull List<Libro> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Libro libro = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_libro, parent, false);
        }
        TextView textViewCodigo= convertView.findViewById(R.id.textViewCodigoLibro);
        TextView textViewTitulo = convertView.findViewById(R.id.textViewTitulo);

        textViewCodigo.setText(libro.getCod_libro());
        textViewTitulo .setText(libro.getTitulo());

        return convertView;
    }
}
