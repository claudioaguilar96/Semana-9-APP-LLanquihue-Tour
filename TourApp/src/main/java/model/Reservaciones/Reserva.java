package model.Reservaciones;

import model.Personas.Cliente;
import model.Tours.Tour;

public class Reserva {
    // Atributos
    private String idReserva;
    private String fecha;
    private Cliente cliente;
    private Tour tour;

    // Constructor
    public Reserva(String idReserva, String fecha, Cliente cliente, Tour tour) {
        this.idReserva = idReserva;
        this.fecha = fecha;
        this.cliente = cliente;
        this.tour = tour;
    }

    // Getter and Setter
    public String getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Tour getTour() {
        return tour;
    }
    public void setTour(Tour tour) {
        this.tour = tour;
    }

    // Metodo Mostrar Informacion
    public void mostrarInformacion() {
        System.out.println("Reserva: " + idReserva + " | Fecha: " + fecha + " | Cliente: " + cliente.getNombre() + " | Tour: " + tour.getNombreTour());
    }
}