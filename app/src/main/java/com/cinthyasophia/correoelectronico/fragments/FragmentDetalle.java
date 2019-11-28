package com.cinthyasophia.correoelectronico.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cinthyasophia.correoelectronico.Contacto;
import com.cinthyasophia.correoelectronico.Correo;
import com.cinthyasophia.correoelectronico.Cuenta;
import com.cinthyasophia.correoelectronico.R;

public class FragmentDetalle extends Fragment {
    private ImageView ivFotoContactoD;
    private TextView tvContenidoD;
    private TextView tvTituloD;
    private TextView tvNombreContactoD;
    private TextView tvFechaEnviadoD;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_detalle,container,false);
        tvContenidoD = view.findViewById(R.id.tvContenidoD);
        tvTituloD = view.findViewById(R.id.tvTituloD);
        tvFechaEnviadoD = view.findViewById(R.id.tvFechaEnviadoD);
        tvNombreContactoD = view.findViewById(R.id.tvNombreContactoD);
        ivFotoContactoD = view.findViewById(R.id.ivFotoContactoD);

        return view;
    }

    public void mostrarDetalle(Cuenta cuenta, Correo correo){
        tvContenidoD.setText(correo.getContenido());
        tvTituloD.setText(correo.getTitulo());
        tvFechaEnviadoD.setText(correo.getFechaEnviado());
        StringBuilder sb = new StringBuilder();
        Contacto contact= null;

        if (!correo.getReceptor().equals(cuenta.getEmail())){
            for (Contacto c :cuenta.getContactos()) {
                if (c.getEmail().equals(correo.getReceptor())){
                    contact = c;

                }
            }
        }else if(correo.getReceptor().equals(cuenta.getEmail())){
            for (Contacto c: cuenta.getContactos()) {
                if (c.getEmail().equals(correo.getEmisor())){
                    contact = c;

                }
            }
        }

        if(correo.isSpam()){
            tvNombreContactoD.setText(correo.getEmisor());

        }else{

            tvNombreContactoD.setText(sb.append(contact.getNombre()).append(" ").append(contact.getApellido1()).append(" ").append(contact.getApellido1()).toString());
            String imageResource= "drawable/c"+contact.getFoto();
            int iResource= getActivity().getResources().getIdentifier(imageResource,null, getActivity().getPackageName());
            ivFotoContactoD.setImageResource(iResource);

        }

        if (!correo.isLeido()){
            correo.setLeido(true);
        }
    }

}
