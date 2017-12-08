package com.maveware.notariadigital.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.maveware.notariadigital.R;
import com.maveware.notariadigital.constants.Constants;
import com.maveware.notariadigital.helper.UtileriasHelper;
import com.maveware.notariadigital.model.PagosPorPlazo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CotizaPrestamoActivity extends AppCompatActivity {

    @InjectView(R.id.btn_realizar_cotizacion)
    Button btnRealizarCotizacion;
    @InjectView(R.id.btnLimpiarCampos)
    Button btnLimpiar;
    @InjectView(R.id.txt_monto)
    EditText txtMonto;
    @InjectView(R.id.txt_plazo)
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


                if(!UtileriasHelper.validarNumero(txtMonto.getText())) {
                    txtMonto.setError("El monto debe ser mayor a 0");
                    return;
                }else if(!UtileriasHelper.validarNumero(txtPlazo.getText())) {
                    txtPlazo.setError("El plazo debe ser mayor a 0");
                    return;
                }else if (!UtileriasHelper.validarNumero(txtTasaInteres.getText())) {
                        txtTasaInteres.setError("La tasa de interes debe ser mayor a 0");
                    return;
                 }

                monto = Double.parseDouble(txtMonto.getText().toString());
                plazo = Integer.parseInt(txtPlazo.getText().toString());
                tasaInteres = Double.parseDouble(txtTasaInteres.getText().toString());

                ArrayList<PagosPorPlazo> lista = calcularPagos(monto, plazo, tasaInteres);
                intent.putParcelableArrayListExtra(Constants.LISTA_PAGOS, lista);

                startActivity(intent);
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                txtMonto.setText("");
                txtPlazo.setText("");
                txtTasaInteres.setText("");
            }
        });
    }

    private ArrayList<PagosPorPlazo> calcularPagos(double monto, int plazo, double tasaInteres){

        BigDecimal sumaAbonoCapital = new BigDecimal(0);
        sumaAbonoCapital.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal sumaInteres = new BigDecimal(0);
        sumaInteres.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal sumaTotalNeto = new BigDecimal(0);
        sumaTotalNeto.setScale(2, BigDecimal.ROUND_HALF_DOWN);
        ArrayList<PagosPorPlazo> listaPagosPorPlazo = new ArrayList<PagosPorPlazo>();
        try {

            PagosPorPlazo pagoPorPlazo = null;
            BigDecimal montoDecimal = new BigDecimal(monto);
            montoDecimal.setScale(2, BigDecimal.ROUND_CEILING);
            BigDecimal bigPlazo = new BigDecimal(Double.parseDouble(String.valueOf(plazo)));
            BigDecimal tasaInteresDecimal = new BigDecimal(tasaInteres / 100);
            tasaInteresDecimal.setScale(2, BigDecimal.ROUND_CEILING);
            BigDecimal abonoACapital = montoDecimal.divide(bigPlazo, 2 , BigDecimal.ROUND_CEILING);

            BigDecimal montoCero = new BigDecimal(0);
            BigDecimal abonoInteres  = new BigDecimal(0);
            BigDecimal abonoTotal = new BigDecimal(0);
            //double tasaInteresDecimal = tasaInteres/100;
        /*Calculamos el abono a capital */
            //double abonoACapital = monto / plazo;
            int numeroDePago = 0;
            while (true) {
                numeroDePago++;
                pagoPorPlazo = new PagosPorPlazo();
                pagoPorPlazo.setMontoCapital(montoDecimal.doubleValue());
                pagoPorPlazo.setPagoCapital(abonoACapital.doubleValue());
                pagoPorPlazo.setNumeroDePago(numeroDePago);
                sumaAbonoCapital = sumaAbonoCapital.add(abonoACapital);
                abonoInteres = montoDecimal.multiply(tasaInteresDecimal);
                pagoPorPlazo.setPagoInteres(abonoInteres.doubleValue());
                sumaInteres = sumaInteres.add(abonoInteres);
                abonoTotal = abonoACapital.add(abonoInteres);
                pagoPorPlazo.setAbono(abonoTotal.doubleValue());
                sumaTotalNeto  = sumaTotalNeto.add(abonoTotal);
                listaPagosPorPlazo.add(pagoPorPlazo);
            /*Volvemos a calcular el monto*/
                montoDecimal = montoDecimal.subtract(abonoACapital);
                if (montoDecimal.doubleValue() <= montoCero.doubleValue())
                    break;
            }
            /*Calculamos la suma de cada concepto para mostrarlo en la última fila */
            pagoPorPlazo = new PagosPorPlazo();

            pagoPorPlazo.setPagoCapital(sumaAbonoCapital.doubleValue());
            pagoPorPlazo.setPagoInteres(sumaInteres.doubleValue());
            pagoPorPlazo.setAbono(sumaTotalNeto.doubleValue());
            pagoPorPlazo.setTotalNeto("Total: ");
            listaPagosPorPlazo.add(pagoPorPlazo);
        }catch (Exception e){
            Log.e("PRESTAMO", e.getMessage());
        }

        return listaPagosPorPlazo;
    }
}
