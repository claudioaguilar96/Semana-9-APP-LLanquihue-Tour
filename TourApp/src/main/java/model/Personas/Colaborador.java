package model.Personas;

import util.Mostrable;

public class Colaborador implements Mostrable{
    // Atributos
    protected String nombre;
    protected String cargo;

    // Constructor
    public Colaborador(String nombre, String cargo) {
        this.nombre = nombre;
        this.cargo = cargo;
    }

    // Getter and Setter
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Metodo Mostrar Informacionw
    public void mostrarInformacion() {
        System.out.println("Informacion del Colaborador: " + nombre + " | Cargo: " + cargo);
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Colaborador{" +
                "nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }

}
