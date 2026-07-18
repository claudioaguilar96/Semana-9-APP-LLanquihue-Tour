package model.Personas;

public class Proveedor extends Colaborador {
    // Atributos
    // ---------

    // Constructor
    public Proveedor(String nombre, String cargo) {
        super(nombre, cargo);
    }

    // Metodo mostrar Informacion
    @Override
    public void mostrarInformacion() {
        System.out.println("Informacion del Proveedor: " + nombre + " | Cargo: " + cargo);
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Proveedor{" +
                "nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }

}
