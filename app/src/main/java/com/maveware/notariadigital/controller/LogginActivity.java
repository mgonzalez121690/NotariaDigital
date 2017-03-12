package com.maveware.notariadigital.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.maveware.notariadigital.R;
import com.maveware.notariadigital.constants.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LogginActivity extends AppCompatActivity {

    @InjectView(R.id.txt_email)
    EditText txt_loggin_email;
    @InjectView(R.id.txt_password)
    EditText txt_loggin_password;
    @InjectView(R.id.btn_login)
    Button btn_loggin;
    @InjectView(R.id.link_signup)
    TextView linkSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin_layout);
        ButterKnife.inject(this);

        btn_loggin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = txt_loggin_email.getText().toString();
                String password = txt_loggin_password.getText().toString();
                login(email, password);
            }
        });
    }

    public void login(String email, String password){
        Log.d(Constants.TAG_LOGIN_ACTIVITY,"Login");

        if(!validateFields(email, password)){
            onLoginFailed();
            return;
        }

        btn_loggin.setEnabled(true);

        final ProgressDialog progressDialog = new ProgressDialog(LogginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(Constants.LOGIN_AUTENTICANDO);
        progressDialog.show();

        /*
        * TODO validate authentication
        * */

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){

    }

    public void onLoginSuccess(){
        btn_loggin.setEnabled(true);
        Intent intent = new Intent(LogginActivity.this, MenuPrincipalActivity.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed(){
        Toast.makeText(getBaseContext(), "Login fállido", Toast.LENGTH_LONG).show();
        //btn_loggin.setEnabled(false);
    }

    /**
     * Método para validar el acceso a la aplicación
     * @return true si el acceso es valido, false si no lo es.
     */
    public boolean validateFields(String email, String password) {
        boolean valid = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txt_loggin_email.setError(Constants.EMAIL_ERROR_INVALIDO);
            valid = false;
        }else
            txt_loggin_email.setError(null);

        if(password.isEmpty() || password.length() <4 || password.length()>10){
            txt_loggin_password.setError(Constants.PASSWORD_ERROR_INVALIDO);
            valid = false;
        }else
            txt_loggin_password.setError(null);

        return valid;
    }


}
