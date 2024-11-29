package com.example.ejerciciopreferencias;

public class UserPreferences {
    private String color, actividad, nombreUsuario, imagen;

    public UserPreferences(String color, String actividad, String nombreUsuario, String imagen) {
        this.color = color;
        this.actividad = actividad;
        this.nombreUsuario = nombreUsuario;
        this.imagen = imagen;
    }

    public UserPreferences() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
