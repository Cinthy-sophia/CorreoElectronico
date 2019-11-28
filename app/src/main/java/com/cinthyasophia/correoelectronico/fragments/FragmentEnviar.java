package com.cinthyasophia.correoelectronico.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cinthyasophia.correoelectronico.R;

public class FragmentEnviar extends Fragment {
    private TextView etReceptor;
    private TextView etContenidoE;
    private Button bEnviar;
    private TextView etTituloE;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_enviar, container, false);

        etReceptor = layout.findViewById(R.id.etReceptor);
        etContenidoE = layout.findViewById(R.id.etContenido);
        bEnviar = layout.findViewById(R.id.bEnviar);
        etTituloE = layout.findViewById(R.id.etTitulo);



        bEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                StringBuilder sb =  new StringBuilder();
                sb.append("Correo enviado a ").append(etReceptor.getText());
                Toast.makeText(getActivity(),sb.toString(),Toast.LENGTH_LONG).show();
            }
        });


        return layout;
    }

}
