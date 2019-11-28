package com.cinthyasophia.correoelectronico;

import com.cinthyasophia.correoelectronico.Util.Lib;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Contacto implements Serializable {
    private Lib lib;
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private GregorianCalendar fechaNac;
    private int foto;
    private String email;
    private String telefono1;
    private String telefono2;
    private String dirección;

    public Contacto(int id, String nombre, String apellido1, String apellido2, String fechaNac, int foto, String email, String telefono1, String telefono2, String dirección) {
        lib = new Lib();
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNac = lib.getFecha(fechaNac);
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

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getFechaNac() {
        return lib.getFecha(fechaNac);
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
