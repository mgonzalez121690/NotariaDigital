package com.maveware.notariadigital.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.maveware.notariadigital.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MenuPrincipalActivity extends AppCompatActivity {

    @InjectView(R.id.btn_mostrar_cotizador)
    Button btnMostrarCotizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_layout);
        ButterKnife.inject(this);

        btnMostrarCotizador.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuPrincipalActivity.this, CotizaPrestamoActivity.class);
                startActivity(intent);
            }
        });
    }
}
