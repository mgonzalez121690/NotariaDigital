package com.maveware.notariadigital.controller;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.maveware.notariadigital.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GpsExample extends AppCompatActivity {

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @InjectView(R.id.navview)
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_example);
        ButterKnife.inject(this);
        getSupportActionBar().setTitle("Gps manager");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.basic_manu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu_opcion_1:
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(GpsExample.this, LogginActivity.class);
                                startActivity(intent);
                                break;

                        }
                        drawerLayout.closeDrawers();

                        return true;
                    }
                });

       /* btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(GpsExample.this, LogginActivity.class);
                startActivity(intent);
            }
        });*/
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home :
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
