
package com.maveware.notariadigital.helper;

/**
 * Created by GOAM on 18/05/2017.
 */

public class DBManagerHelper {
    public static final String TABLE_PERSONA = "TB_PERSONA";
    public static final String CAT_ROLE_PERSONA = "CAT_ROLE_PERSONA";
    public static final String TABLE_PERSONA_ROL = "TB_PERSONA_ROLE";
    public static final String TABLE_DOMICILIO = "TB_DOMICILIO";
    public static final String TABLE_PRESTAMO = "TB_PRESTAMO";
    public static final String  TABLE_PRESTAMO_PERSONA = "TB_PRESTAMO_PERSONA";
    public static final String TABLE_ABONOS = "TB_ABONOS";



    public static String createTablePersona(){
        StringBuilder query = new StringBuilder();
        query.append(" CREATE TABLE ");
        query.append(TABLE_PERSONA);
        query.append(" ( ");
        query.append(" ID_PERSONA INTEGER PRIMARY KEY ");
        query.append(" NOMBRE TEXT NULL, ");
        query.append(" APELLIDO_PATERNO TEXT NULL, ");
        query.append(" APELLIDO_MATERNO TEXT NULL, ");
        query.append(" FEC_CREACION DATETIME DEFAULT CURRENT_TIMESTAMP, ");
        query.append(" BLN _ACTIVO INTEGER NULL, ");
        query.append(" ); ");

     return query.toString();
    }

    public static String createTableRolePersona(){
        StringBuilder query = new StringBuilder();
        query.append(" CREATE TABLE ");
        query.append( CAT_ROLE_PERSONA );
        query.append(" ( ");
        query.append(" ID_ROLE_PERSONA INT NOT NULL ");
        query.append(" DESCRIPCION TEXT NULL ");
        query.append(" FEC_CREACION TIMESTAMP NULL ");
        query.append(" BLN_ACTIVO INT NULL ");
        query.append(" ); ");
        return query.toString();
    }


    public static String createTablePersonaRol(){
        StringBuilder query = new StringBuilder();
        query.append(" CREATE TABLE ");
        query.append( TABLE_PERSONA_ROL );
        query.append(" ( ");
        query.append("ID_PERSONA TEXT NOT NULL");
        query.append("ID_ROLE_PERSONA INT NOT NULL");
        query.append("FEC_CREACION TIMESTAMP NULL");
        query.append("BLN_ACTIVO INT NULL");

        return query.toString();

    }

    public static String createTableDomicilio(){
        StringBuilder query = new StringBuilder();
        query.append(" CREATE TABLE ");
        query.append( TABLE_DOMICILIO );
        query.append(" ( ");
        query.append("ID_DOMICILIO` TEXT NOT NULL");
        query.append("CALLE` TEXT NULL");
        query.append("NUM_EXT` TEXT NULL");
        query.append("NUM_INT` TEXT NULL");
        query.append("CP` TEXT NULL");
        query.append("COLONIA` TEXT NULL");
        query.append("DELEGACION` TEXT NULL");
        query.append("ENTIDAD` TEXT NULL");
        query.append("PAIS` INT NULL");
        query.append("ID_PERSONA` TEXT NOT NULL");
        query.append("FEC_CREACION` TIMESTAMP NULL");
        query.append("BLN_ACTIVO` INT NULL");

        return query.toString();

    }
}
