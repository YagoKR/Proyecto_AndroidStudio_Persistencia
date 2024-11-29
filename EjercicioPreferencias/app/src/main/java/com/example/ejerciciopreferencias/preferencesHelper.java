package com.example.ejerciciopreferencias;

import android.content.SharedPreferences;

public class preferencesHelper {
    public SharedPreferences sp;

    public preferencesHelper(SharedPreferences sp) {
        this.sp = sp;
    }

    public void guardarPreferencias (String color, String actividad, String nombreUsuario, String imagen ) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("color", color);
        editor.putString("actividad", actividad);
        editor.putString("nombreUsuario", nombreUsuario);
        editor.putString("imagen", imagen);
        editor.apply();
    }

    public UserPreferences cargarPreferencias () {
        String color = sp.getString("color","colorDefault");
        String actividad = sp.getString("actividad", "actividadDefault");
        String nombre = sp.getString("nombreUsuario","nombreDefault");
        String img = sp.getString("imagen","imagenDefault");
        return new UserPreferences (color, actividad, nombre, img);
    }

}
