package com.cinthyasophia.correoelectronico;

import java.io.Serializable;
import java.util.ArrayList;

public class Cuenta implements Serializable {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private ArrayList<Contacto> contactos;
    private ArrayList<Correo> correos;

    public Cuenta(int id, String nombre, String apellido, String email, ArrayList<Contacto> contactos, ArrayList<Correo> correos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contactos = contactos;
        this.correos = correos;
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public ArrayList<Correo> getCorreos() {
        return correos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }
}
