package model.Personas;

public class OperadorTransporte extends Colaborador {
    // Atributos
    // ---------

    // Constructor
    public OperadorTransporte(String nombre, String cargo) {
        super(nombre, cargo);
    }

    // Metodo mostrar Informacion
    @Override
    public void mostrarInformacion() {
        System.out.println("Informacion del Operador de Transporte: " + nombre + " | Cargo: " + cargo);
    }

    // Metodo toString
    @Override
    public String toString() {
        return "OperadorTransporte{" +
                "nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
