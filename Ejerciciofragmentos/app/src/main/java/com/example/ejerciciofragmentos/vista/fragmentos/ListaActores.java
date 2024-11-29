package com.example.ejerciciofragmentos.vista.fragmentos;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ejerciciofragmentos.R;
import com.example.ejerciciofragmentos.mock.ObtencionDatos;
import com.example.ejerciciofragmentos.modelo.entidades.Actor;
import com.example.ejerciciofragmentos.modelo.entidades.Pelicula;
import com.example.ejerciciofragmentos.vista.adaptadores.ActorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaActores extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private OnActorSelectedListener listener;

    public interface OnActorSelectedListener {
        void onActorSelected(Actor actor);
    }

    public ListaActores() {
        // Required empty public constructor
    }

    public static ListaActores newInstance(int idPelicula) {
        ListaActores fragment = new ListaActores();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, idPelicula);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_actores, container, false);

        ListView listView = view.findViewById(R.id.listViewActores);

        ObtencionDatos obtencionDatos = new ObtencionDatos();


        int idPelicula = 0;
        if (getArguments() != null) {
            idPelicula = getArguments().getInt(ARG_PARAM1);
        }

        List<Actor> actores = obtencionDatos.obtenerListadoActores(idPelicula);

        ActorAdapter adapter = new ActorAdapter(getContext(), R.layout.actor_element, actores.toArray(new Actor[0]));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Actor actor = (Actor) adapterView.getItemAtPosition(i);
                listener.onActorSelected(actor);
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnActorSelectedListener) {
            listener = (OnActorSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " debe implementar OnActorSelectedListener");
        }
    }
}
