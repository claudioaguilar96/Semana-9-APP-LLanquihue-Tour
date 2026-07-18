package model.Personas;

import util.Mostrable;

public class Cliente implements Mostrable{
    // Atributos
    protected String nombre;
    protected String rut;

    // Constructor
    public Cliente(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
    }

    // Getter and Setter
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }

    // Metodo Mostrar Informacion
    public void mostrarInformacion(){
        System.out.println("Informacion Cliente: " + nombre + " | Rut: " + rut);
    }

    // Metodo ToString
    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", rut='" + rut + '\'' +
                '}';
    }

}

