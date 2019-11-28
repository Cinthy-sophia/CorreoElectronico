package com.cinthyasophia.correoelectronico;

import com.cinthyasophia.correoelectronico.Util.Lib;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

public class Correo implements Serializable {
    private Lib lib;
    private String emisor;
    private String receptor;
    private String titulo;
    private String contenido;
    private GregorianCalendar fechaEnviado;
    private boolean leido;
    private boolean borrado;
    private boolean spam;

    public Correo(String emisor, String receptor, String titulo, String contenido, String fechaEnviado, boolean leido, boolean borrado, boolean spam) {
        lib = new Lib();
        this.emisor = emisor;
        this.receptor = receptor;
        this.titulo = titulo;
        this.contenido = contenido;
        setFechaEnviado(fechaEnviado);
        this.leido = leido;
        this.borrado = borrado;
        this.spam = spam;
    }

    public void setFechaEnviado(String fechaEnviado) {
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        GregorianCalendar fechaN= new GregorianCalendar();
        Date d= new Date();
        try{
            d= format.parse(fechaEnviado);
        }catch (ParseException pe){
            pe.printStackTrace();
        }
        fechaN.setTime(d);
        this.fechaEnviado = fechaN;
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

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public String getFechaEnviado() {
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String d;
        d= format.format(fechaEnviado.getTime());

        return d;
    }

    public boolean isLeido() {
        return leido;
    }

    public boolean isSpam() {
        return spam;
    }

    public static class ComparatorFecha implements Comparator<Correo> {

        @Override
        public int compare(Correo o1, Correo o2) {
            return o2.fechaEnviado.compareTo(o1.fechaEnviado);
        }
    }
}
