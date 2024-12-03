package com.example.ejerciciobbdd.bbdd.entidades;

public class Libro {
    public String cod_libro, titulo;

    public Libro(String cod_libro, String titulo) {
        this.cod_libro = cod_libro;
        this.titulo = titulo;
    }

    public String getCod_libro() {
        return cod_libro;
    }

    public void setCod_libro(String cod_libro) {
        this.cod_libro = cod_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
