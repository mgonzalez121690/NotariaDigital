package com.maveware.notariadigital.helper;

import android.util.Patterns;

import com.maveware.notariadigital.constants.Constants;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by GOAM on 12/02/2017.
 */
public class UtileriasHelper {

    public static String getFormatoMonetario(double valor){
        Locale locale = new Locale("es","MX");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);

        return nf.format(valor);
    }

    public static boolean validarNumero(Object valor){
        if(valor == null) {
            return false;
        }else{
            String cadena = String.valueOf(valor);
            if(!cadena.matches("[0-9]*") || cadena.isEmpty())
                return false;
            Double valorDouble = Double.parseDouble(cadena);
            if(valorDouble <= 0)
                return false;

        }
        return true;
    }


}
