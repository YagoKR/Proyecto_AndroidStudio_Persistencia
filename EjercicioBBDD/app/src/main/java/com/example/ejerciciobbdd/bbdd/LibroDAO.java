package com.example.ejerciciobbdd.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ejerciciobbdd.bbdd.entidades.Libro;

import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public SQLiteDatabase db;
    public BibliotecaDbHelper bdHelper;

    public LibroDAO (Context context) {
        bdHelper = new BibliotecaDbHelper(context);
    }

    public void open () {
        db = bdHelper.getWritableDatabase();
    }

    public void close () {
        bdHelper.close();
    }

    public long insertarDatos (Libro libro) {
        ContentValues values = new ContentValues();
        values.put (BibliotecaContract.Libro.COLUMN_COD_LIBRO, libro.getCod_libro());
        values.put (BibliotecaContract.Libro.COLUMN_TITULO, libro.getTitulo());
        return db.insert(BibliotecaContract.Libro.TABLE_NAME, null, values);
    }

    public List<Libro> visualizarDatos () {
        List<Libro> libros = new ArrayList<>();
        String [] projection = {BibliotecaContract.Libro._ID, BibliotecaContract.Libro.COLUMN_COD_LIBRO, BibliotecaContract.Libro.COLUMN_TITULO};
        String sortOrder = BibliotecaContract.Libro._ID + " ASC";

        Cursor cursor = db.query(BibliotecaContract.Libro.TABLE_NAME, projection, null, null, null, null, sortOrder);
        while (cursor.moveToNext()) {
            String codigo = cursor.getString(cursor.getColumnIndexOrThrow(BibliotecaContract.Libro.COLUMN_COD_LIBRO));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(BibliotecaContract.Libro.COLUMN_TITULO));
            libros.add(new Libro (codigo, titulo));
        }
        cursor.close();
        return libros;
    }
}
