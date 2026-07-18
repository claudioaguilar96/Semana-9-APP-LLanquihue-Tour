package data;

import model.Personas.Colaborador;
import model.Personas.Guia;
import model.Tours.Tour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorTours {

    // Ruta del archivo txt
    private static final String ARCHIVO_TOURS = "src/main/resources/ListaTour.txt";

    // Lista de registro
    private ArrayList<Tour> registroTours;

    // Referencia a Gestor de Entidades
    private GestorEntidades gestorEntidades;

    // Constructor
    public GestorTours(GestorEntidades gestorEntidades) {
        this.gestorEntidades = gestorEntidades;
        registroTours = new ArrayList<>();

        cargarTours();
    }

    // Metodo Agregar tour
    // Valida que no exista ya un tour con el mismo id
    // Retorna true si se agrego, false si era un duplicado
    public boolean agregarTour(Tour tour) {
        for (Tour t : registroTours) {
            if (t.getIdTour().equalsIgnoreCase(tour.getIdTour())) {
                return false;
            }
        }

        registroTours.add(tour);
        guardarTour(tour);
        return true;
    }

    // Metodo Buscar tour registrado por id
    public Tour buscarPorId(String idTour) {
        for (Tour t : registroTours) {
            if (t.getIdTour().equalsIgnoreCase(idTour)) {
                return t;
            }
        }
        return null;
    }

    // Metodo guardar los Tours en archivo txt
    private void guardarTour(Tour tour) {
        String guiaNombre = tour.getGuia() != null ? tour.getGuia().getNombre() : "";
        String linea = tour.getIdTour() + ";" + tour.getNombreTour() + ";" + tour.getDestino() + ";" + guiaNombre;

        try (FileWriter fw = new FileWriter(ARCHIVO_TOURS, true)) {
            fw.write(linea + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error al guardar tour en archivo: " + e.getMessage());
        }
    }

    // Metodo Cargar Tours y reconstruye los objetos
    private void cargarTours() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_TOURS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }

                String[] partes = linea.split(";", -1);
                if (partes.length < 3) {
                    continue; // Linea con formato invalido, se ignora
                }

                String idTour = partes[0];
                String nombre = partes[1];
                String destino = partes[2];
                Guia guia = (partes.length > 3 && !partes[3].isBlank()) ? buscarGuia(partes[3]) : null;

                registroTours.add(new Tour(idTour, nombre, destino, guia));
            }

        } catch (IOException e) {
            System.out.println("No se encontro " + ARCHIVO_TOURS);
        }
    }

    // Busca un guia ya registrado en GestorEntidades
    private Guia buscarGuia(String nombre) {
        for (Colaborador c : gestorEntidades.getRegistroColaboradores()) {
            if (c instanceof Guia && c.getNombre().equalsIgnoreCase(nombre)) {
                return (Guia) c;
            }
        }
        return null;
    }


    // Mostrar tours por consola
    public void mostrarTours() {
        for (Tour t : registroTours) {
            t.mostrarInformacion();
        }
    }

    // Metodo Listar Tour - Ordena los datos y la da una estructuraI
    public String listarTours() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tours Registrados:\n\n");

        if (registroTours.isEmpty()) {
            sb.append("No hay tours registrados.\n");
            return sb.toString();
        }

        for (Tour t : registroTours) {
            sb.append("ID     : ").append(t.getIdTour()).append("\n");
            sb.append("Nombre : ").append(t.getNombreTour()).append("\n");
            sb.append("Destino: ").append(t.getDestino()).append("\n");
            sb.append("Guía   : ").append(t.getGuia() != null ? t.getGuia().getNombre() : "Sin asignar").append("\n");
            sb.append("\n");
        }

        return sb.toString();
    }

    // Getters
    public ArrayList<Tour> getRegistroTours() {
        return registroTours;
    }

}