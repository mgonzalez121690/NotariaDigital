package com.maveware.notariadigital.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.maveware.notariadigital.R;
import com.maveware.notariadigital.constants.Constants;
import com.maveware.notariadigital.model.PagosPorPlazo;
import com.maveware.notariadigital.model.PagosPorPlazoAdapter;

import java.util.ArrayList;

public class MostrarCotizacionActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<PagosPorPlazo> listaPagos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_cotizacion);
        getSupportActionBar().setTitle("Lista Pagos");
        listaPagos = getIntent().getParcelableArrayListExtra(Constants.LISTA_PAGOS);
        PagosPorPlazoAdapter pagosPorPlazoAdapter = new PagosPorPlazoAdapter(this, R.layout.item_cotizacion_layout, listaPagos);
        listView = (ListView) findViewById(R.id.lista_mostrar_cotizacion);
       // View viewHeader = (View) getLayoutInflater().inflate(R.layout.encabezado, null);
        //listView.addHeaderView(viewHeader);
        listView.setAdapter(pagosPorPlazoAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PagosPorPlazo p =(PagosPorPlazo) listView.getItemAtPosition(position);
                Toast.makeText(MostrarCotizacionActivity.this, "Position: "+position +" id: "+id+" "+p.getMontoCapital(), Toast.LENGTH_SHORT).show();
                //PopupMenu popup = new PopupMenu(MostrarCotizacionActivity.this, listView);
                //popup.getMenuInflater().inflate(R.menu.menupopup, popup.getMenu());
                //Toast.makeText(MostrarCotizacionActivity.this, "Hola", Toast.LENGTH_SHORT).show();
                //popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                  //  @Override
                   // public boolean onMenuItemClick(MenuItem item){
                        /*Toast.makeText(MostrarCotizacionActivity.this,
                                       "Click en: " +item.,
                                        Toast.LENGTH_SHORT
                                ).show();
                        return true;*/
                //    }
                //});
                //popup.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menupopup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_popup_guardar:
                Toast.makeText(MostrarCotizacionActivity.this, "Guardar", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean guardar(){

        return true;
    }
}
