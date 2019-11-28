package com.cinthyasophia.correoelectronico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.cinthyasophia.correoelectronico.fragments.FragmentDetalle;

import java.util.Objects;

public class DetalleActivity extends AppCompatActivity {
    public static final String EXTRA_TEXTO= "com.cinthyasophia.correoelectronico.EXTRA_TEXTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        FragmentDetalle frgDetalle = (FragmentDetalle) getSupportFragmentManager().findFragmentById(R.id.fragmentD);
        Object[] c = (Object[]) getIntent().getSerializableExtra(EXTRA_TEXTO);
        Correo correo = (Correo) c[0];
        Cuenta cuenta = (Cuenta) c[1];
        frgDetalle.mostrarDetalle(cuenta,correo);
    }
}
