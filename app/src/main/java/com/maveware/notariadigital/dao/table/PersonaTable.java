package com.maveware.notariadigital.dao.table;

import android.provider.BaseColumns;

/**
 * Created by mgonzalezy on 13/04/2017.
 */

public class PersonaTable implements BaseColumns {

    public static final String TABLE_NAME = "PERSONA";
    public static final String NOMBRE = "NOMBRE",
                               APELLIDO_PATERNO = "APELLIDO_PATERNO",
                               APELLIDO_MATERNO = "APELLIDO_PATERNO",
                               TIPO_PERSONA = "";

    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " ("+
            _COUNT + " INTEGER, "+
            NOMBRE + " TEXT, "+
            APELLIDO_PATERNO +" TEXT, "+
            APELLIDO_MATERNO +" TEXT, "+
            TIPO_PERSONA +" INTEGER)";

    public static final String DROP_QUERY = "DROP TABLE "+TABLE_NAME;
    public static final String SELECT_QUERY = "SELECT * FROM "+TABLE_NAME;


}
