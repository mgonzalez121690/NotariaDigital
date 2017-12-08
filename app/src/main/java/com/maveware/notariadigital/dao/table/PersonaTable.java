package com.maveware.notariadigital.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.maveware.notariadigital.helper.DBHelper;

/**
 * Created by mgonzalezy on 13/04/2017.
 */

public class PersonaTable extends DBHelper {

    public static final String TABLE_NAME = "PERSONA";
    public static final String ID_PERSONA="ID_PERSONA",
                                NOMBRE = "NOMBRE",
                               APELLIDO_PATERNO = "APELLIDO_PATERNO",
                               APELLIDO_MATERNO = "APELLIDO_PATERNO",
                               TIPO_PERSONA = "TIPO_PERSONA";

    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " ("+
            ID_PERSONA + " TEXT,"+
            NOMBRE + " TEXT, "+
            APELLIDO_PATERNO +" TEXT, "+
            APELLIDO_MATERNO +" TEXT, "+
            TIPO_PERSONA +" INTEGER)";

    public static final String DROP_QUERY = "DROP TABLE "+TABLE_NAME;
    public static final String SELECT_QUERY = "SELECT * FROM "+TABLE_NAME;


    public PersonaTable(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
           super.setCREATE_QUERY(CREATE_QUERY);
           super.onCreate(db);
    }

    @Override
    public void guardar(String table, ContentValues valores){
        super.guardar(table, valores);
    }
}
