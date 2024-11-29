package com.example.ejerciciofragmentos.vista.fragmentos;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ejerciciofragmentos.mock.ObtencionDatos;
import com.example.ejerciciofragmentos.modelo.entidades.Pelicula;

import com.example.ejerciciofragmentos.R;
import com.example.ejerciciofragmentos.vista.adaptadores.PeliculaAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaPeliculas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaPeliculas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OnPeliculaSelectedListener listener;

    public interface OnPeliculaSelectedListener {
        void onPeliculaSelected(Pelicula pelicula);
    }
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_lista_peliculas.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaPeliculas newInstance(String param1, String param2) {
        ListaPeliculas fragment = new ListaPeliculas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_peliculas, container, false);

        ListView listView = view.findViewById(R.id.listViewPeliculas);

        ObtencionDatos obtencionDatos = new ObtencionDatos();
        List<Pelicula> peliculas = obtencionDatos.obtenerListadoPeliculas();

        PeliculaAdapter adapter = new PeliculaAdapter(getContext(), R.layout.pelicula_element, peliculas.toArray(new Pelicula[0]));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pelicula pelicula = (Pelicula) adapterView.getItemAtPosition(i);
                listener.onPeliculaSelected(pelicula);
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnPeliculaSelectedListener){
            listener = (OnPeliculaSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + "aquí tiene que haber un OnPeliculaSelectedListener");
        }
    }
}