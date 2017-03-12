package com.maveware.notariadigital.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.maveware.notariadigital.R;
import com.maveware.notariadigital.constants.Constants;
import com.maveware.notariadigital.model.PagosPorPlazo;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CotizaPrestamoActivity extends AppCompatActivity {

    @InjectView(R.id.btn_realizar_cotizacion)
    Button btnRealizarCotizacion;
    @InjectView(R.id.txtMonto)
    EditText txtMonto;
    @InjectView(R.id.txtPlazo)
    EditText txtPlazo;
    @InjectView(R.id.spnPlazos)
    Spinner spnPlazos;
    @InjectView(R.id.txt_tasa_interes)
    EditText txtTasaInteres;

    private double monto;
    private int plazo;
    private double tasaInteres;

    private double sumaAbonoCap;
    private double sumaInteres;
    private double sumaTotalNeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotiza_prestamo);
        ButterKnife.inject(this);
        /*Cargo mi spinner con los plazos*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, Constants.PLAZOS);
         spnPlazos.setAdapter(adapter);

        btnRealizarCotizacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(CotizaPrestamoActivity.this, MostrarCotizacionActivity.class);
                /*Si queremos pasar informacióne entre activitys con
             Bundle bundle = new Bundle();
               */
                monto = Double.parseDouble(txtMonto.getText().toString());
                plazo = Integer.parseInt(txtPlazo.getText().toString());
                tasaInteres = Double.parseDouble(txtTasaInteres.getText().toString());
                ArrayList<PagosPorPlazo> lista = calcularPagos(monto, plazo, tasaInteres);
                intent.putParcelableArrayListExtra(Constants.LISTA_PAGOS, lista);

                startActivity(intent);
            }
        });
    }

    private ArrayList<PagosPorPlazo> calcularPagos(double monto, int plazo, double tasaInteres){
        ArrayList<PagosPorPlazo> listaPagosPorPlazo = new ArrayList<PagosPorPlazo>();
        PagosPorPlazo pagoPorPlazo = null;
        double tasaInteresDecimal = tasaInteres/100;
        /*Calculamos el abono a capital */
        double abonoACapital = monto / plazo;
        int numeroDePago = 0;
        while(true){
            numeroDePago++;
            pagoPorPlazo = new PagosPorPlazo();
            pagoPorPlazo.setMontoCapital(monto);
            pagoPorPlazo.setPagoCapital(abonoACapital);
            pagoPorPlazo.setNumeroDePago(numeroDePago);
            sumaAbonoCap +=abonoACapital;
            double abonoInteres =  monto * tasaInteresDecimal;
            pagoPorPlazo.setPagoInteres(abonoInteres);
            sumaInteres += abonoInteres;
            double abonoTotal = abonoACapital + abonoInteres;
            pagoPorPlazo.setAbono(abonoTotal);
            sumaTotalNeto += abonoTotal;
            listaPagosPorPlazo.add(pagoPorPlazo);
            /*Volvemos a calcular el monto*/
            monto -= abonoACapital;
            if(monto <= 0)
                break;
        }
           pagoPorPlazo = new PagosPorPlazo();

           pagoPorPlazo.setPagoCapital(sumaAbonoCap);
           pagoPorPlazo.setPagoInteres(sumaInteres);
           pagoPorPlazo.setAbono(sumaTotalNeto);
           pagoPorPlazo.setTotalNeto("Total: ");
            listaPagosPorPlazo.add(pagoPorPlazo);

        return listaPagosPorPlazo;
    }
}
