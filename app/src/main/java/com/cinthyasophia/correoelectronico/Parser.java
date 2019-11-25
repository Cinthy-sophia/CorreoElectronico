package com.cinthyasophia.correoelectronico;
import android.content.Context;
import android.widget.Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Parser {

    private ArrayList<Contacto> contactos;
    private ArrayList<Correo> correos;
    private Cuenta usuario;
    private InputStream file;

    public Parser(Context context) {

        file = context.getResources().openRawResource(R.raw.correos);
        contactos= new ArrayList<>();
        correos= new ArrayList<>();
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

            usuario = new Cuenta(array.getJSONObject(0).getInt("id"),
                    array.getJSONObject(0).getString("name"),
                    array.getJSONObject(0).getString("firstSurname"),
                    array.getJSONObject(0).getString("email"));

            int id;
            String nombre;
            String apellido1;
            String apellido2;
            String direccion;
            String empresa;
            String fechaNac;
            String foto;
            String telefono1;
            String telefono2;
            String email;

            for (int i = 0; i < array.length(); i++) {

                id = array.getJSONObject(i).getInt("id");
                nombre = array.getJSONObject(i).getString("name");
                apellido1 = array.getJSONObject(i).getString("firstSurname");
                apellido2 = array.getJSONObject(i).getString("secondSurname");
                //foto= array.getJSONObject(i).getString("photo");
                fechaNac = array.getJSONObject(i).getString("birth");
                empresa = array.getJSONObject(i).getString("company");
                email = array.getJSONObject(i).getString("email");
                telefono1 = array.getJSONObject(i).getString("phone1");
                telefono2 = array.getJSONObject(i).getString("phone2");
                direccion = array.getJSONObject(i).getString("address");

                //contactos[i] = new Contacto(id, nombre, apellido1, apellido2, fechaNac, empresa, email, telefono1, telefono2, direccion);
            }
            parsed = true;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return parsed;
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public ArrayList<Correo> getCorreos() {
        return correos;
    }

    public Cuenta getUsuario() {
        return usuario;
    }
}