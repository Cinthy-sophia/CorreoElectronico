package com.cinthyasophia.correoelectronico;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Parser {

    //private ArrayList<Contacto> contactos;
    //private ArrayList<Correo> correos;
    private Cuenta usuario;
    private InputStream file;

    public Parser(Context context) {

        file = context.getResources().openRawResource(R.raw.correos);
        //contactos= new ArrayList<>();
        //correos= new ArrayList<>();
    }

    public boolean parse() {
        byte[] buffer = null;
        String json = new String();
        boolean parsed = false;
        try {
            buffer = new byte[file.available()];

            file.read(buffer);
            file.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONTokener tokener = new JSONTokener(json);
        try {
            JSONArray array = new JSONArray(tokener);


            int id;
            String nombre;
            String apellido;
            String email;
            ArrayList<Contacto> contactos= new ArrayList<>();
            ArrayList<Correo> correos= new ArrayList<>();

            int idContacto;
            String nombreContacto;
            String apellido1Contacto;
            String apellido2Contacto;
            String fechaNacContacto;
            int fotoContacto;
            String emailContacto;
            String telefono1Contacto;
            String telefono2Contacto;
            String direccionContacto;

            String emisor;
            String receptor;
            String titulo;
            String contenido;
            String fechaEnviado;
            boolean leido;
            boolean borrado;
            boolean spam;

            id= array.getJSONObject(0).getJSONObject("myAccount").getInt("id");
            nombre= array.getJSONObject(0).getJSONObject("myAccount").getString("name");
            apellido=array.getJSONObject(0).getJSONObject("myAccount").getString("firstSurname");
            email=array.getJSONObject(0).getJSONObject("myAccount").getString("email");
            System.out.println(id);

            for (int i = 0; i < array.getJSONObject(0).getJSONArray("contacts").length(); i++) {
                JSONObject objAux=array.getJSONObject(0).getJSONArray("contacts").getJSONObject(i);
                idContacto= objAux.getInt("id");
                nombreContacto=objAux.getString("name");
                apellido1Contacto=objAux.getString("firstSurname");
                apellido2Contacto=objAux.getString("secondSurname");
                fechaNacContacto=objAux.getString("birth");
                fotoContacto=objAux.getInt("foto");
                emailContacto=objAux.getString("email");
                telefono1Contacto=objAux.getString("phone1");
                telefono2Contacto=objAux.getString("phone2");
                direccionContacto=objAux.getString("address");

                contactos.add(new Contacto(idContacto,nombreContacto,apellido1Contacto,apellido2Contacto,fechaNacContacto,fotoContacto,emailContacto,telefono1Contacto,telefono2Contacto,direccionContacto));
            }

            for (int i = 0; i < array.getJSONObject(0).getJSONArray("mails").length(); i++) {
                JSONObject objAux=array.getJSONObject(0).getJSONArray("mails").getJSONObject(i);

                emisor=objAux.getString("from");
                receptor=objAux.getString("to");
                titulo=objAux.getString("subject");
                contenido=objAux.getString("body");
                fechaEnviado=objAux.getString("sentOn");
                leido=objAux.getBoolean("readed");
                borrado=objAux.getBoolean("deleted");
                spam=objAux.getBoolean("spam");

                correos.add(new Correo(emisor,receptor,titulo,contenido,fechaEnviado,leido,borrado,spam));
            }

            usuario= new Cuenta(id,nombre,apellido,email,contactos,correos);

            parsed = true;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return parsed;
    }

    public ArrayList<Contacto> getContactos() {
        return usuario.getContactos();
    }

    public ArrayList<Correo> getCorreos() {
        return usuario.getCorreos();
    }

    public Cuenta getUsuario() {
        return usuario;
    }

}