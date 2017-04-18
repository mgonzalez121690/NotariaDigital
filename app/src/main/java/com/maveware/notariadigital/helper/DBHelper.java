package com.maveware.notariadigital.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.maveware.notariadigital.dao.table.PersonaTable;
import com.maveware.notariadigital.model.Persona;

/**
 * Created by mgonzalezy on 13/04/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Notaria.db";

    private static final String TYPE_TEXT = "TEXT";
    private static final String COMMA_SEP = ",";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(PersonaTable.CREATE_QUERY);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(PersonaTable.DROP_QUERY);
        onCreate(db);
    }

    public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

}
