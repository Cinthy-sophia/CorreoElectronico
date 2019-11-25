package com.cinthyasophia.correoelectronico;

public class Cuenta {
    private int id;
    private String nombre;
    private String apellido;
    private String email;

    public Cuenta(int id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
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
