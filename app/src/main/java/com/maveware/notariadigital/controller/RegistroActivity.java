package com.maveware.notariadigital.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.maveware.notariadigital.R;
import com.maveware.notariadigital.constants.Constants;
import com.maveware.notariadigital.service.RegistroUsuarioService;
import com.maveware.notariadigital.service.impl.RegistroUsuarioServiceImpl;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegistroActivity extends AppCompatActivity {
    private static final String TAG = "RegistroActivity";

    @InjectView(R.id.txt_registro_nombre)
    EditText txtNombre;
    @InjectView(R.id.txt_registro_email)
    EditText txtRegistroEmail;
    @InjectView(R.id.txt_registro_password)
    EditText txtRegistroPassword;
    @InjectView(R.id.btn_crear_cuenta)
    Button btnCrarCuenta;
    @InjectView(R.id.link_login)
    TextView lblLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_layout);
        ButterKnife.inject(this);

        btnCrarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                String password = txtRegistroPassword.getText().toString();
                String email = txtRegistroEmail.getText().toString();
                registrar(email , password, nombre);
            }
        });

        lblLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this, LogginActivity.class);
            }
        });
    }

    private void registrar(String email, String password, String nombre ){
        Log.i(TAG,"Registrar");
        final Intent intent = new Intent(RegistroActivity.this, MenuPrincipalActivity.class);
        if(validateFields(email, password, nombre)){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                         startActivity(intent);
                    else {
                        Toast.makeText(getBaseContext(), Constants.REGISTRO_ERROR_CREAR_USUARIO, Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }

    /**
     * Método para validar el acceso a la aplicación
     * @return true si el acceso es valido, false si no lo es.
     */
    public boolean validateFields(String email, String password, String nombre) {
        boolean valid = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtRegistroEmail.setError(Constants.EMAIL_ERROR_INVALIDO);
            valid = false;
        }else
            txtRegistroEmail.setError(null);

        if(password.isEmpty() || password.length() <4 || password.length()>10){
            txtRegistroPassword.setError(Constants.PASSWORD_ERROR_INVALIDO);
            valid = false;
        }else
            txtRegistroPassword.setError(null);

        return valid;
    }

}
