package com.cinthyasophia.correoelectronico.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinthyasophia.correoelectronico.Adapter;
import com.cinthyasophia.correoelectronico.Correo;
import com.cinthyasophia.correoelectronico.ICorreoListener;
import com.cinthyasophia.correoelectronico.Parser;
import com.cinthyasophia.correoelectronico.R;

import java.util.ArrayList;

public class FragmentAll extends androidx.fragment.app.Fragment {
    private ArrayList<Correo> correos;
    private RecyclerView rvDatos;
    private Parser parser;
    private Adapter adapter;
    private ICorreoListener listener;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayList<Correo> arrayAux= new ArrayList<>();

        correos = new ArrayList<>();

        parser = new Parser(getActivity());
        if (parser.parse()){
            arrayAux=parser.getCorreos();

        }


        Bundle b = getArguments();
        if (b!=null){

            if(b.containsKey("RECIBIDOS")){
                for (Correo c:arrayAux ) {
                    if (c.getReceptor().equals(parser.getUsuario().getEmail()) && !c.isSpam() && !c.isBorrado()){
                        correos.add(c);
                    }
                }

            }else if(b.containsKey("ENVIADOS")){
                for (Correo c:arrayAux ) {
                    if (!c.getReceptor().equals(parser.getUsuario().getEmail()) && !c.isSpam() && !c.isBorrado()){
                        correos.add(c);
                    }
                }

            }else if(b.containsKey("NO_LEIDOS")){
                for (Correo c: arrayAux) {
                    if (!c.isLeido() && !c.isSpam() && !c.isBorrado()){
                        correos.add(c);
                    }
                }

            }else if(b.containsKey("SPAM")){
                for (Correo c: arrayAux) {
                    if (c.isSpam()){
                        correos.add(c);
                    }
                }

            }else if(b.containsKey("PAPELERA")){
                for(Correo c : arrayAux){
                    if (c.isBorrado()){
                        correos.add(c);

                    }
                }
            }else{
                correos = arrayAux;
            }

        }
        correos.sort(new Correo.ComparatorFecha());
        adapter = new Adapter(parser.getUsuario(),correos,getActivity(), listener);

        return inflater.inflate(R.layout.fragment_recycler,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvDatos = getView().findViewById(R.id.rvDatos);

        rvDatos.setAdapter(adapter);
        rvDatos.setLayoutManager( new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
    }

    public void setListener(ICorreoListener listener) {
        this.listener = listener;
    }

    public ArrayList<Correo> getCorreos() {
        return correos;
    }
}
