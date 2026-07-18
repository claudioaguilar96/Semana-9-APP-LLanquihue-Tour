package data;

import model.Personas.Cliente;
import model.Reservaciones.Reserva;
import model.Tours.Tour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorReservas {

    // Ruta del archivo txt
    private static final String ARCHIVO_RESERVAS = "src/main/resources/reservas.txt";

    // Lista de registro
    private ArrayList<Reserva> registroReservas;

    // Referencias a los otros gestores, para reconstruir Cliente y Tour
    private GestorEntidades gestorEntidades;
    private GestorTours gestorTours;

    // Constructor
    public GestorReservas(GestorEntidades gestorEntidades, GestorTours gestorTours) {
        this.gestorEntidades = gestorEntidades;
        this.gestorTours = gestorTours;
        registroReservas = new ArrayList<>();

        cargarReservas();
    }

    // Metodo Agregar reserva
    // Valida que no exista ya una reserva con el mismo id
    // Retorna true si se agrego, false si era un duplicado
    public boolean agregarReserva(Reserva reserva) {
        for (Reserva r : registroReservas) {
            if (r.getIdReserva().equalsIgnoreCase(reserva.getIdReserva())) {
                return false;
            }
        }

        registroReservas.add(reserva);
        guardarReserva(reserva);
        return true;
    }

    // Metodo guardar la reserva en archivo txt
    // Se guarda el rut del cliente y el idTour, no los objetos completos
    private void guardarReserva(Reserva reserva) {
        String linea = reserva.getIdReserva() + ";" + reserva.getFecha() + ";"
                + reserva.getCliente().getRut() + ";" + reserva.getTour().getIdTour();

        try (FileWriter fw = new FileWriter(ARCHIVO_RESERVAS, true)) {
            fw.write(linea + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error al guardar reserva en archivo: " + e.getMessage());
        }
    }

    // Metodo Cargar Reservas y reconstruye los objetos
    // Busca el Cliente por rut en GestorEntidades y el Tour por id en GestorTours
    private void cargarReservas() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_RESERVAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }

                String[] partes = linea.split(";");
                if (partes.length < 4) {
                    continue; // Linea con formato invalido, se ignora
                }

                String idReserva = partes[0];
                String fecha = partes[1];
                String rutCliente = partes[2];
                String idTour = partes[3];

                Cliente cliente = buscarCliente(rutCliente);
                Tour tour = gestorTours.buscarPorId(idTour);

                // Si no se encuentra el cliente o el tour, se ignora la linea
                if (cliente != null && tour != null) {
                    registroReservas.add(new Reserva(idReserva, fecha, cliente, tour));
                }
            }

        } catch (IOException e) {
            System.out.println("No se encontro " + ARCHIVO_RESERVAS);
        }
    }

    // Busca un cliente ya registrado en GestorEntidades, por rut
    private Cliente buscarCliente(String rut) {
        for (Cliente c : gestorEntidades.getRegistroClientes()) {
            if (c.getRut().equalsIgnoreCase(rut)) {
                return c;
            }
        }
        return null;
    }

    // Mostrar reservas por consola
    public void mostrarReservas() {
        for (Reserva r : registroReservas) {
            r.mostrarInformacion();
        }
    }

    // Metodo Listar Reservas - Ordena los datos y les da una estructura
    public String listarReservas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reservas Registradas:\n\n");

        if (registroReservas.isEmpty()) {
            sb.append("No hay reservas registradas.\n");
            return sb.toString();
        }

        for (Reserva r : registroReservas) {
            sb.append("ID      : ").append(r.getIdReserva()).append("\n");
            sb.append("Fecha   : ").append(r.getFecha()).append("\n");
            sb.append("Cliente : ").append(r.getCliente().getNombre()).append("\n");
            sb.append("Tour    : ").append(r.getTour().getNombreTour()).append("\n");
            sb.append("\n");
        }

        return sb.toString();
    }

    // Getters
    public ArrayList<Reserva> getRegistroReservas() {
        return registroReservas;
    }
}