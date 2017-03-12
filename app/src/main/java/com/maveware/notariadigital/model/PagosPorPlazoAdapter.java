package com.maveware.notariadigital.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.maveware.notariadigital.R;
import com.maveware.notariadigital.helper.UtileriasHelper;

import java.util.List;

/**
 * Created by GOAM on 19/11/2016.
 */
public class PagosPorPlazoAdapter extends ArrayAdapter<PagosPorPlazo> {
    Context context;
    int layout;
    List<PagosPorPlazo> lista;

    public PagosPorPlazoAdapter(Context context,int layoutResource, List<PagosPorPlazo> lista) {
        super(context, layoutResource, lista);
        this.context = context;
        this.layout = layoutResource;
        this.lista = lista;
    }

    @Override
    public View getView(int posicion, View converterVIew, ViewGroup parent){
        /*Obtengo inflater*/

        //PagosPorPlazo pagosPorPlaz = new PagosPorPlazo();

        /*Validamos el view actual*/
        if(converterVIew == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            converterVIew = inflater.inflate(
                    layout,
                    parent,
                    false);

            /*Obtenemos las referencias UI*/
            /*TextView  capital =  (TextView) converterVIew.findViewById(R.id.lblItemCapital);
            TextView abonoCapital = (TextView) converterVIew.findViewById(R.id.lblItemAbonoCapital);
            TextView interes =      (TextView) converterVIew.findViewById(R.id.lblItemInteres);
            TextView pagoPeriodo =  (TextView) converterVIew.findViewById(R.id.lblItemPagoPorPeriopdp);*/
            //converterVIew.setTag(pagosPorPlaz);

        } /*else{
            pagosPorPlaz = (PagosPorPlazo) converterVIew.getTag();
        }*/

        PagosPorPlazo pagosPorPlazo = getItem(posicion);
        TextView  capital =  (TextView) converterVIew.findViewById(R.id.lblItemCapital);
        TextView abonoCapital = (TextView) converterVIew.findViewById(R.id.lblItemAbonoCapital);
        TextView interes =      (TextView) converterVIew.findViewById(R.id.lblItemInteres);
        TextView pagoPeriodo =  (TextView) converterVIew.findViewById(R.id.lblItemPagoPorPeriopdp);
        /* Setup, la librería Glide permite
         * cargar imágenes de forma asincrona
         * para no entorpecer el hilo principal de la UI.
         * Para usarla hay que agregar la siguiente dependecia
         * a build.gradle a nivel módulo.
         * Glide.with();
         */
        //Creamos los sets que se mostraran en a vista
        if(pagosPorPlazo.getMontoCapital()==0)
            capital.setText(pagosPorPlazo.getTotalNeto());
        else
            capital.setText(UtileriasHelper.getFormatoMonetario(pagosPorPlazo.getMontoCapital()));

        abonoCapital.setText(UtileriasHelper.getFormatoMonetario(pagosPorPlazo.getPagoCapital()));
        interes.setText(UtileriasHelper.getFormatoMonetario(pagosPorPlazo.getPagoInteres()));
        pagoPeriodo.setText(UtileriasHelper.getFormatoMonetario(pagosPorPlazo.getAbono()));

        return converterVIew;
    }

    static class PagosPorPlazoHolder{
        TextView capital;
        TextView abonoCapital;
        TextView interes;
        TextView pagoPeriodo;

    }

}
