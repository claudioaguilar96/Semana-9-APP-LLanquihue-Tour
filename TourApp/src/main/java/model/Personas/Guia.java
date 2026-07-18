package model.Personas;

public class Guia extends Colaborador {
    // Atributos
    // ---------

    // Constructor
    public Guia(String nombre, String cargo) {
        super(nombre, cargo);
    }

    // Metodo mostrar Informacion
    @Override
    public void mostrarInformacion() {
        System.out.println("Informacion del Guia Turistico: " + nombre + " | Cargo: " + cargo);
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Guia{" +
                "nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }

}

