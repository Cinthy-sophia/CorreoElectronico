package com.cinthyasophia.correoelectronico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cinthyasophia.correoelectronico.fragments.FragmentAll;
import com.cinthyasophia.correoelectronico.fragments.FragmentDetalle;
import com.cinthyasophia.correoelectronico.fragments.FragmentEnviar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ICorreoListener{
    FragmentAll fragment;
    Parser parser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parser = new Parser(this);
        parser.parse();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        TextView tvNombreCuenta = hView.findViewById(R.id.tvNombreCuenta);
        TextView tvCorreoCuenta = hView.findViewById(R.id.tvCorreoCuenta);

        tvNombreCuenta.setText(parser.getUsuario().getNombre());
        tvCorreoCuenta.setText(parser.getUsuario().getEmail());

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{

            super.onBackPressed();
        }
    }

    @Override
    public void onCorreoSeleccionado(int adapterPosition) {
        boolean hayDetalle= (getSupportFragmentManager().findFragmentById(R.id.fragmentD)!=null);
        Correo correo =  fragment.getCorreos().get(adapterPosition);

        if (hayDetalle){
            FragmentDetalle frgDetalle = (FragmentDetalle) getSupportFragmentManager().findFragmentById(R.id.fragmentD);
            frgDetalle.mostrarDetalle(parser.getUsuario(),correo);
        }else{
            Intent i = new Intent(this,DetalleActivity.class);
            Object[] c = new Object[2];
            c[0]= correo;
            c[1]= parser.getUsuario();

            i.putExtra(DetalleActivity.EXTRA_TEXTO,c);

            startActivity(i);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Bundle b = new Bundle();
        fragment = new FragmentAll();


        if (id == R.id.mIRecibidos) {
            b.putString("RECIBIDOS", "Mi texto");
            fragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.fLContentFrame, fragment).commit();
            setTitle("Recibidos");

        } else if (id == R.id.mIEnviados) {
            b.putString("ENVIADOS", "Mi texto");
            fragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.fLContentFrame, fragment).commit();
            setTitle("Enviados");
        } else if (id == R.id.mINoLeidos) {
            b.putString("NO_LEIDOS", "Mi texto");
            fragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.fLContentFrame, fragment).commit();
            setTitle("No Leidos");
        } else if (id == R.id.mISpam) {
            b.putString("SPAM", "Mi texto");
            fragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.fLContentFrame, fragment).commit();
            setTitle("Spam");
        } else if (id == R.id.mIPapelera) {
            b.putString("PAPELERA", "Mi texto");
            fragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.fLContentFrame, fragment).commit();
            setTitle("Papelera");
        } else if (id == R.id.mIEnviar) {
            FragmentEnviar frgEnviar = new FragmentEnviar();
            getSupportFragmentManager().beginTransaction().replace(R.id.fLContentFrame, frgEnviar).commit();
            setTitle("Enviar Correo");
        }
        fragment.setListener(this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
