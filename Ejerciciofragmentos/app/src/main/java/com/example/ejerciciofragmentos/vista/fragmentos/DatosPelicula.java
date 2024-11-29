package com.example.ejerciciofragmentos.vista.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ejerciciofragmentos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatosPelicula#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosPelicula extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_SINOPSIS = "sinopsis";
    private static final String ARG_FECHA = "fecha";
    private static final String ARG_GENERO = "genero";
    private static final String ARG_IMAGEN = "imagen";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mNombre;
    private String mSinopsis;
    private String mFecha;
    private String mGenero;
    private String mImagen;

    public DatosPelicula() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param nombre   Parameter 1.
     * @param sinopsis Parameter 2.
     * @param fecha
     * @param genero
     * @return A new instance of fragment fragment_datos_pelicula.
     */
    // TODO: Rename and change types and number of parameters

    public static DatosPelicula newInstance(String nombre, String sinopsis, String fecha, String genero, String imagen) {
        DatosPelicula fragment = new DatosPelicula();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putString(ARG_SINOPSIS, sinopsis);
        args.putString(ARG_FECHA, fecha);
        args.putString(ARG_GENERO, genero);
        args.putString(ARG_IMAGEN, imagen);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNombre = getArguments().getString(ARG_NOMBRE);
            mSinopsis = getArguments().getString(ARG_SINOPSIS);
            mFecha = getArguments().getString(ARG_FECHA);
            mGenero = getArguments().getString(ARG_GENERO);
            mImagen = getArguments().getString(ARG_IMAGEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos_pelicula, container, false);

        TextView nombreTextView = view.findViewById(R.id.textViewNombre);
        TextView sinopsisTextView = view.findViewById(R.id.textViewSinopsis);
        TextView fechaTextView = view.findViewById(R.id.textViewFecha);
        TextView generoTextView = view.findViewById(R.id.textViewGenero);
        TextView imagenTextView = view.findViewById(R.id.textViewImagen);

        nombreTextView.setText(mNombre);
        sinopsisTextView.setText(mSinopsis);
        fechaTextView.setText(mFecha);
        generoTextView.setText(mGenero);
        imagenTextView.setText(mImagen);

        return view;
    }
}