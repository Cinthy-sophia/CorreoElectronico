package com.cinthyasophia.correoelectronico;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder> {
    private Cuenta usuario;
    private Context context;
    private ArrayList<Correo> correos;
    private ICorreoListener listener;

    public Adapter(Cuenta usuario, ArrayList<Correo> correos, Context context, ICorreoListener listener) {
        this.usuario = usuario;
        this.context = context;
        this.correos = correos;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new ItemViewHolder(item, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Correo correo = correos.get(position);
        holder.bindItem(usuario,correo,context);

    }

    @Override
    public int getItemCount() {
        return correos.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView ivFotoContacto;
        private TextView tvContenido;
        private TextView tvTituloCorreo;
        private TextView tvNombreContacto;
        private TextView tvFechaEnviado;
        private ICorreoListener listener;


        public ItemViewHolder(@NonNull View itemView, ICorreoListener listener) {
            super(itemView);
            this.listener= listener;
            ivFotoContacto = itemView.findViewById(R.id.ivFotoContacto);
            tvContenido = itemView.findViewById(R.id.tvContenido);
            tvTituloCorreo = itemView.findViewById(R.id.tvTituloCorreo);
            tvNombreContacto = itemView.findViewById(R.id.tvNombreContacto);
            tvFechaEnviado = itemView.findViewById(R.id.tvFechaEnviado);
            itemView.setOnClickListener(this);

        }
        public void bindItem(Cuenta usuario,Correo correo,Context context){
            tvContenido.setText(correo.getContenido());
            tvTituloCorreo.setText(correo.getTitulo());
            tvFechaEnviado.setText(correo.getFechaEnviado());


            tvFechaEnviado.setTypeface(Typeface.DEFAULT_BOLD);
            tvFechaEnviado.setGravity(Gravity.CENTER);
            int foto;
            StringBuilder sb = new StringBuilder();
            Contacto contact=null;

            if (!correo.getReceptor().equals(usuario.getEmail())){
                for (Contacto c :usuario.getContactos()) {
                    if (c.getEmail().equals(correo.getReceptor())){

                        contact = c;

                    }
                }
            }else if(correo.getReceptor().equals(usuario.getEmail())){
                for (Contacto c: usuario.getContactos()) {
                    if (c.getEmail().equals(correo.getEmisor())){
                        contact = c;

                    }
                }
            }

            if(correo.isSpam() || correo.isBorrado()){
                tvNombreContacto.setText(correo.getEmisor());

            }else{
                tvNombreContacto.setText(sb.append(contact.getNombre()).append(" ").append(contact.getApellido1()).append(" ").append(contact.getApellido1()).toString());
                foto= contact.getFoto();
                String imageResource= "drawable/c"+foto;
                int iResource= context.getResources().getIdentifier(imageResource,null, context.getPackageName());
                ivFotoContacto.setImageResource(iResource);

            }
            if (!correo.isLeido()){
                tvNombreContacto.setTypeface(Typeface.DEFAULT_BOLD);
                tvTituloCorreo.setTypeface(Typeface.DEFAULT_BOLD);
                tvFechaEnviado.setTextColor(Color.BLUE);
            }else{
                tvNombreContacto.setTypeface(Typeface.DEFAULT);
                tvTituloCorreo.setTypeface(Typeface.DEFAULT);
                tvFechaEnviado.setTextColor(Color.BLACK);
            }

        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onCorreoSeleccionado(getAdapterPosition());
            }

        }
    }
}
