package com.example.ejerciciobbdd.vista.listeners;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.example.ejerciciobbdd.bbdd.LibroDAO;
import com.example.ejerciciobbdd.bbdd.entidades.Libro;
import com.example.ejerciciobbdd.vista.adaptadores.AdaptadorLibro;

import java.util.List;

public class CustomClickListener implements View.OnClickListener{
    private Context context;
    private ListView listView;

    public CustomClickListener(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    public void onClick(View v) {
        LibroDAO libroDAO = new LibroDAO(context);
        libroDAO.open();
        List<Libro> libros = libroDAO.visualizarDatos();
        libroDAO.close();

        AdaptadorLibro adapter = new AdaptadorLibro(context, android.R.layout.simple_list_item_1, libros);
        listView.setAdapter(adapter);
    }
}
