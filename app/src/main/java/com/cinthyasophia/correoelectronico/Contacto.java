package com.cinthyasophia.correoelectronico;

import java.util.GregorianCalendar;

public class Contacto {
    private int id;
    private String nombre;
    private String apellido;
    private GregorianCalendar fechaNac;
    private int foto;
    private String email;
    private String telefono1;
    private String telefono2;
    private String dirección;

    public Contacto(int id, String nombre, String apellido, GregorianCalendar fechaNac, int foto, String email, String telefono1, String telefono2, String dirección) {

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.foto = foto;
        this.email = email;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.dirección = dirección;
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

    public GregorianCalendar getFechaNac() {
        return fechaNac;
    }

    public int getFoto() {
        return foto;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public String getDirección() {
        return dirección;
    }
}
