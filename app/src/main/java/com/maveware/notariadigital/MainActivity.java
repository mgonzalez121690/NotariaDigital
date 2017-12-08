package com.maveware.notariadigital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.maveware.notariadigital.controller.LogginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, LogginActivity.class);
       /* try {
            Thread.sleep(5000);
            startActivity(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        startActivity(intent);
        finish();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){

        }
    }
}
