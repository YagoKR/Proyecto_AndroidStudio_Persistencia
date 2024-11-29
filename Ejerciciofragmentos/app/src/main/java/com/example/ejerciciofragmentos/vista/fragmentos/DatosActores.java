package com.example.ejerciciofragmentos.vista.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ejerciciofragmentos.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatosActores#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosActores extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_FECHA = "fecha";

    // TODO: Rename and change types of parameters
    private String mNombre;
    private String mFecha;

    public DatosActores() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param nombre Parameter 1.
     * @param fecha Parameter 2.
     * @return A new instance of fragment DatosActores.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosActores newInstance(String nombre, String fecha) {
        DatosActores fragment = new DatosActores();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putString(ARG_FECHA, fecha);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNombre= getArguments().getString(ARG_NOMBRE);
            mFecha = getArguments().getString(ARG_FECHA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos_actores, container, false);

        EditText nombreTextView = view.findViewById(R.id.nombreActor);
        EditText fechaTextView = view.findViewById(R.id.editTextDate);

        nombreTextView.setText(mNombre);
        fechaTextView.setText(mFecha);

        return view;
    }
}