package com.example.ejerciciofragmentos.vista.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ejerciciofragmentos.R;
import com.example.ejerciciofragmentos.modelo.entidades.Actor;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ActorAdapter extends ArrayAdapter<Actor> {
    public ActorAdapter(@NonNull Context context, int resource, @NonNull Actor[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Actor actor = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.actor_element, parent, false);
        }

        TextView textViewNombre = convertView.findViewById(R.id.textViewNombActor);
        TextView textViewFechaNacimiento = convertView.findViewById(R.id.textViewFechaNacAct);

        textViewNombre.setText(actor.getNombre());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaFormateada = dateFormat.format(actor.getFechaNacimiento());


        textViewFechaNacimiento.setText(fechaFormateada);

        return convertView;
    }
}
