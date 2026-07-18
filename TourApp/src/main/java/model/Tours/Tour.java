package model.Tours;

import model.Personas.Guia;

public class Tour {
    // Atributos
    private String idTour;
    private String nombreTour;
    private String destino;
    private Guia guia;

    // Constructor
    public Tour(String idTour, String nombreTour, String destino, Guia guia) {
        this.idTour = idTour;
        this.nombreTour = nombreTour;
        this.destino = destino;
        this.guia = guia;
    }

    // Getters y Setters
    public String getIdTour() {
        return idTour;
    }
    public void setIdTour(String idTour) {
        this.idTour = idTour;
    }
    public String getNombreTour() {
        return nombreTour;
    }
    public void setNombreTour(String nombreTour) {
        this.nombreTour = nombreTour;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public Guia getGuia() {
        return guia;
    }
    public void setGuia(Guia guia) {
        this.guia = guia;
    }

    // Metodo mostrar Informacion
    public void mostrarInformacion() {
        System.out.println("Tour: " + nombreTour + " | ID: " + idTour + " | Destino: " + destino + " | Guía: " + (guia != null ? guia.getNombre() : "Sin asignar"));
    }
}