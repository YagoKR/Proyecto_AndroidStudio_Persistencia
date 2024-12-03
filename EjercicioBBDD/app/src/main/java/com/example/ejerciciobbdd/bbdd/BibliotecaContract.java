package com.example.ejerciciobbdd.bbdd;

import android.provider.BaseColumns;

public class BibliotecaContract {
    private BibliotecaContract () {};

    public static class Libro implements BaseColumns {
        public static final String TABLE_NAME = "libro";
        public static final String COLUMN_TITULO = "titulo";
        public static final String COLUMN_COD_LIBRO = "cod_libro";
    }
}
