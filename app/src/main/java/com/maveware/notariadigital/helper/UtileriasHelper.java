package com.maveware.notariadigital.helper;

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

}
