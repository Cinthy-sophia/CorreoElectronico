package com.cinthyasophia.correoelectronico;

import java.util.GregorianCalendar;

public class Correo {
    private String emisor;
    private String receptor;
    private String titulo;
    private String contenido;
    private GregorianCalendar fechaEnviado;
    private boolean leido;
    private boolean spam;

    public Correo(String emisor, String receptor, String titulo, String contenido, GregorianCalendar fechaEnviado, boolean leido, boolean spam) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaEnviado = fechaEnviado;
        this.leido = leido;
        this.spam = spam;
    }

    public String getEmisor() {
        return emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public GregorianCalendar getFechaEnviado() {
        return fechaEnviado;
    }

    public boolean isLeido() {
        return leido;
    }

    public boolean isSpam() {
        return spam;
    }
}
