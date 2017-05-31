package com.maveware.notariadigital.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.maveware.notariadigital.dao.table.PersonaTable;
import com.maveware.notariadigital.model.Persona;

/**
 * Created by mgonzalezy on 13/04/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_SCHEMA_VERSION = 1;
    private static final String DATABASE_NAME = "prestadores.sqlite";

    public String CREATE_QUERY;
    public String DROP_QUERY;


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_SCHEMA_VERSION);
    }

    public DBHelper(Context context, String create_query){
        super(context, DATABASE_NAME, null, DATABASE_SCHEMA_VERSION);
        CREATE_QUERY = create_query;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(getCREATE_QUERY());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(getDROP_QUERY());
        onCreate(db);
    }

    public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public void guardar(String table, ContentValues valores){
        this.getWritableDatabase();
        this.getWritableDatabase().insert(table, null, valores);
        this.close();
    }

    public String getCREATE_QUERY() {
        return CREATE_QUERY;
    }

    public void setCREATE_QUERY(String CREATE_QUERY) {
        this.CREATE_QUERY = CREATE_QUERY;
    }

    public String getDROP_QUERY() {
        return DROP_QUERY;
    }

    public void setDROP_QUERY(String DROP_QUERY) {
        this.DROP_QUERY = DROP_QUERY;
    }
}
