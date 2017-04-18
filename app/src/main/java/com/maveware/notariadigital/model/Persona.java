package com.maveware.notariadigital.model;

import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by mgonzalezy on 13/04/2017.
 */

public class Persona {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int tipoPersona;

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, int tipoPersona){
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.tipoPersona = tipoPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public int getTipoPersona() {
        return tipoPersona;
    }
}
