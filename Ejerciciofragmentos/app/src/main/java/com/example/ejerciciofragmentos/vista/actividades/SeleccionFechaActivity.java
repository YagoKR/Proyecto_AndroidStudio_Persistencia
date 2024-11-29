package com.example.ejerciciofragmentos.vista.actividades;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import com.example.ejerciciofragmentos.R;

public class SeleccionFechaActivity extends AppCompatActivity {
    private DatePicker selecFecha;
    private Button btnTime, btnGuardar;
    private TextView textViewFecha, textViewHora;
    private int hor, min;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccion_fecha);

        selecFecha = findViewById(R.id.datePicker);
        btnTime = findViewById(R.id.buttonHora);
        btnGuardar = findViewById(R.id.save);
        textViewFecha = findViewById(R.id.textViewFecha);
        textViewHora = findViewById(R.id.textViewHora);

        hor = 12;
        min = 0;

        selecFecha.init(
                selecFecha.getYear(), selecFecha.getMonth(), selecFecha.getDayOfMonth(),
                (view, year, monthOfYear, dayOfMonth) -> {

                    String dateText = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    textViewFecha.setText("Fecha: " + dateText);
                });


        btnTime.setOnClickListener(v -> {

            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);


            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    SeleccionFechaActivity.this,
                    (view, hourOfDay, minuteOfHour) -> {

                        hor = hourOfDay;
                        min = minuteOfHour;

                        String hourText = String.format("%02d:%02d", hor, min);
                        textViewHora.setText("Hora: " + hourText);
                    },
                    hour, minute, true);

            timePickerDialog.show();
        });


        btnGuardar.setOnClickListener(v -> {
            int day = selecFecha.getDayOfMonth();
            int month = selecFecha.getMonth();
            int year = selecFecha.getYear();


            String fechaSeleccionada = day + "/" + (month + 1) + "/" + year;
            String horaSeleccionada = String.format("%02d:%02d", hor, min);


            Intent resultIntent = new Intent();
            resultIntent.putExtra("fechaSeleccionada", fechaSeleccionada);
            resultIntent.putExtra("horaSeleccionada", horaSeleccionada);
            setResult(RESULT_OK, resultIntent);

            finish();
        });
    }
}