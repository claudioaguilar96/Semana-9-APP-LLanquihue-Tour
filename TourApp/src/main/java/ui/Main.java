package ui;

import java.util.Scanner;
import java.util.ArrayList;

import data.GestorEntidades;
import data.GestorTours;
import data.GestorReservas;
import model.Personas.Colaborador;
import model.Personas.Cliente;
import model.Personas.Guia;
import model.Personas.OperadorTransporte;
import model.Personas.Proveedor;
import model.Tours.Tour;
import model.Reservaciones.Reserva;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        // Referenciamos los Gestores
        GestorEntidades gestorEntidades = new GestorEntidades();
        GestorTours gestorTours = new GestorTours(gestorEntidades);
        GestorReservas gestorReservas = new GestorReservas(gestorEntidades, gestorTours);

        // --- Menu Principal
        // Permite ingresar a las distintas opciones de Gestion del menu a traves de consola
        do {
            System.out.println("");
            System.out.println("--- Bienvenidos a Llanquihue Tour ---");
            System.out.println("Ingrese una opcion: ");
            System.out.println("1 - Gestion de Personas");
            System.out.println("2 - Gestion de Tours");
            System.out.println("3 - Gestion de Reservas");
            System.out.println("4 - Salir");
            System.out.print("Opcion: ");

            opcion = -1;

            // Validador de numeral
            // Indica si la opcion numeral es valida
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.\n");
            }

            // Opciones del Menu
            switch (opcion) {
                case 1:
                    menuPersonas(sc, gestorEntidades);
                    break;
                case 2:
                    menuTours(sc, gestorTours, gestorEntidades);
                    break;
                case 3:
                    menuReservas(sc, gestorReservas, gestorEntidades, gestorTours);
                    break;
                case 4:
                    System.out.println("--- Saliendo Del Registro ---");
                    break;
                default:
                    if (opcion != -1) {
                        System.out.println("Ingrese una Opcion Valida.\n");
                    }
                    break;
            }

        } while (opcion != 4);

        sc.close();
    }

    // --- Submenu Gestion de Personas
    // Permite Realizar la Gestion del Personal y de los Clientes
    public static void menuPersonas(Scanner sc, GestorEntidades gestor) {
        int opcion;

        do {
            System.out.println("\n--- Gestion de Personas ---");
            System.out.println("Ingrese una opcion: ");
            System.out.println("1 - Registrar Colaborador");
            System.out.println("2 - Mostrar informacion Colaborador");
            System.out.println("3 - Registrar Cliente");
            System.out.println("4 - Mostrar informacion Clientes");
            System.out.println("5 - Volver al menu principal");
            System.out.print("Opcion: ");

            opcion = -1;

            // Validador de numeral
            // Indica si la opcion numeral es valida
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.\n");
            }

            // Opciones del Menu
            switch (opcion) {
                case 1:
                    registrarColaborador(sc, gestor);
                    break;
                case 2:
                    System.out.println();
                    gestor.mostrarColaboradores();
                    break;
                case 3:
                    registrarCliente(sc, gestor);
                    break;
                case 4:
                    System.out.println();
                    gestor.mostrarClientes();
                    break;
                case 5:
                    System.out.println("Volviendo al menu principal\n");
                    break;
                default:
                    if (opcion != -1) {
                        System.out.println("Ingrese una Opcion Valida.\n");
                    }
                    break;
            }

        } while (opcion != 5);
    }

    // --- Registrar Colaborador ---
    // Pide el tipo de colaborador, nombre y cargo, crea el objeto correspondiente
    // y lo entrega al GestorEntidades para su registro y guardado en archivo
    private static void registrarColaborador(Scanner sc, GestorEntidades gestor) {
        int tipo = -1;

        System.out.println("\n--- Registrar Colaborador ---");
        System.out.println("1 - Guia Turistico");
        System.out.println("2 - Operador de Transporte");
        System.out.println("3 - Proveedor");
        System.out.print("Tipo de Colaborador: ");

        // Validador de numeral
        // Indica si la opcion numeral es valida
        try {
            tipo = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero valido.\n");
        }

        // Validador de numeral
        // Indica si la opcion numeral es valida (entre las opciones 1,2 y 3)
        if (tipo < 1 || tipo > 3) {
            System.out.println("Debe ingresar un numero valido.");
            return;
        }

        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese el cargo: ");
        String cargo = sc.nextLine();

        Colaborador colaborador;
        switch (tipo) {
            case 1:
                colaborador = new Guia(nombre, cargo);
                break;
            case 2:
                colaborador = new OperadorTransporte(nombre, cargo);
                break;
            case 3:
                colaborador = new Proveedor(nombre, cargo);
                break;
            default:
                colaborador = null;
                break;
        }

        // Validador de registro
        boolean agregado = gestor.agregarColaborador(colaborador);
        if (agregado) {
            System.out.println("- Colaborador registrado -\n");
        } else {
            System.out.println("Ya existe un colaborador del mismo tipo con ese nombre.\n");
        }
    }

    // --- Registrar Cliente ---
    // Pide nombre y rut, crea el Cliente y lo entrega al GestorEntidades
    private static void registrarCliente(Scanner sc, GestorEntidades gestor) {
        System.out.println("\n--- Registrar Cliente ---");

        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese el rut: ");
        String rut = sc.nextLine();

        Cliente cliente = new Cliente(nombre, rut);

        // Validador de Registro
        boolean agregado = gestor.agregarCliente(cliente);
        if (agregado) {
            System.out.println("- Cliente registrado -\n");
        } else {
            System.out.println("Ya existe un cliente registrado con ese rut.\n");
        }
    }

    // --- Submenu Gestion de Tours ---
    // Permite Realizar la Gestion de Tours y Su visualizacion
    public static void menuTours(Scanner sc, GestorTours gestorTours, GestorEntidades gestorEntidades) {
        int opcion;

        do {
            System.out.println("\n--- Gestion de Tours ---");
            System.out.println("Ingrese una opcion: ");
            System.out.println("1 - Registrar un Tour");
            System.out.println("2 - Mostrar Tours");
            System.out.println("3 - Volver al menu principal");
            System.out.print("Opcion: ");

            opcion = -1;

            // Validador de numeral
            // Indica si la opcion numeral es valida
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.\n");
            }

            // Opciones del Menu
            switch (opcion) {
                case 1:
                    registrarTour(sc, gestorTours, gestorEntidades);
                    break;
                case 2:
                    System.out.println();
                    gestorTours.mostrarTours();
                    break;
                case 3:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    if (opcion != -1) {
                        System.out.println("Ingrese una Opcion Valida.\n");
                    }
                    break;
            }

        } while (opcion != 3);
    }

    // --- Registrar Tour ---
    // Pide id, nombre y destino del tour, luego solicita un guia entre los ya
    // registrados en GestorEntidades y entrega el Tour a GestorTours
    private static void registrarTour(Scanner sc, GestorTours gestorTours, GestorEntidades gestorEntidades) {
        System.out.println("\n--- Registrar Tour ---");

        System.out.print("Ingrese el id del tour: ");
        String idTour = sc.nextLine();

        System.out.print("Ingrese el nombre del tour: ");
        String nombreTour = sc.nextLine();

        System.out.print("Ingrese el destino: ");
        String destino = sc.nextLine();

        Guia guia = seleccionarGuia(sc, gestorEntidades);

        Tour tour = new Tour(idTour, nombreTour, destino, guia);

        // Validador de Registro
        boolean agregado = gestorTours.agregarTour(tour);
        if (agregado) {
            System.out.println("- Tour registrado -\n");
        } else {
            System.out.println("Ya existe un tour registrado con ese id.\n");
        }
    }

    // --- Seleccionar Guia ---
    // Filtra los colaboradores registrados que sean Guia, los muestra con un
    // numero de referencia y retorna el elegido (o null si no hay o no se elige)
    private static Guia seleccionarGuia(Scanner sc, GestorEntidades gestorEntidades) {
        ArrayList<Guia> guias = new ArrayList<>();

        for (Colaborador c : gestorEntidades.getRegistroColaboradores()) {
            if (c instanceof Guia) {
                guias.add((Guia) c);
            }
        }

        if (guias.isEmpty()) {
            System.out.println("No hay guias registrados. El tour se registrara sin guia.\n");
            return null;
        }

        System.out.println("\nGuias disponibles:");
        for (int i = 0; i < guias.size(); i++) {
            System.out.println((i + 1) + " - " + guias.get(i).getNombre());
        }
        System.out.println("0 - No asignar guia");
        System.out.print("Seleccione un guia: ");

        int seleccion = -1;

        // Validador de numeral
        // Indica si la opcion numeral es valida
        try {
            seleccion = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero valido. El tour se registrara sin guia.\n");
            return null;
        }

        if (seleccion == 0) {
            return null;
        }

        if (seleccion < 1 || seleccion > guias.size()) {
            System.out.println("Seleccion invalida. El tour se registrara sin guia.\n");
            return null;
        }

        return guias.get(seleccion - 1);
    }

    // --- Submenu Gestion de Reservas ---
    // Permite Gestionar las Reservas y Visualizar las reservas
    public static void menuReservas(Scanner sc, GestorReservas gestorReservas,
                                    GestorEntidades gestorEntidades, GestorTours gestorTours) {
        int opcion;

        do {
            System.out.println("\n--- Gestion de Reservas ---");
            System.out.println("Ingrese una opcion: ");
            System.out.println("1 - Registrar Reservas");
            System.out.println("2 - Mostrar Reservas");
            System.out.println("3 - Volver al menu principal");
            System.out.print("Opcion: ");

            opcion = -1;

            // Validador de numeral
            // Indica si la opcion numeral es valida
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.\n");
            }

            switch (opcion) {
                case 1:
                    registrarReserva(sc, gestorReservas, gestorEntidades, gestorTours);
                    break;
                case 2:
                    System.out.println();
                    gestorReservas.mostrarReservas();
                    break;
                case 3:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    if (opcion != -1) {
                        System.out.println("Ingrese una Opcion Valida.\n");
                    }
                    break;
            }

        } while (opcion != 3);
    }

    // --- Registrar Reserva ---
    // Pide id y fecha de la reserva, luego solicita elegir un Cliente y un Tour
    // ya registrados, y entrega la Reserva a GestorReservas
    private static void registrarReserva(Scanner sc, GestorReservas gestorReservas,
                                         GestorEntidades gestorEntidades, GestorTours gestorTours) {
        System.out.println("\n--- Registrar Reserva ---");

        System.out.print("Ingrese el id de la reserva: ");
        String idReserva = sc.nextLine();

        System.out.print("Ingrese la fecha (dd-mm-aaaa): ");
        String fecha = sc.nextLine();

        Cliente cliente = seleccionarCliente(sc, gestorEntidades);
        if (cliente == null) {
            System.out.println("No se puede registrar la reserva sin un cliente. Registro cancelado.\n");
            return;
        }

        Tour tour = seleccionarTour(sc, gestorTours);
        if (tour == null) {
            System.out.println("No se puede registrar la reserva sin un tour. Registro cancelado.\n");
            return;
        }

        Reserva reserva = new Reserva(idReserva, fecha, cliente, tour);

        // Validador de Registro
        boolean agregado = gestorReservas.agregarReserva(reserva);
        if (agregado) {
            System.out.println("- Reserva registrada -\n");
        } else {
            System.out.println("Ya existe una reserva registrada con ese id.\n");
        }
    }

    // --- Seleccionar Cliente ---
    // Muestra los clientes ya registrados en GestorEntidades y retorna el elegido
    private static Cliente seleccionarCliente(Scanner sc, GestorEntidades gestorEntidades) {
        ArrayList<Cliente> clientes = gestorEntidades.getRegistroClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados. Registre un cliente primero.\n");
            return null;
        }

        System.out.println("\nClientes disponibles:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + " - " + clientes.get(i).getNombre() + " (Rut: " + clientes.get(i).getRut() + ")");
        }
        System.out.print("Seleccione un cliente: ");

        int seleccion = -1;

        // Validador de numeral
        // Indica si la opcion numeral es valida
        try {
            seleccion = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero valido.\n");
            return null;
        }

        if (seleccion < 1 || seleccion > clientes.size()) {
            System.out.println("Seleccion invalida.\n");
            return null;
        }

        return clientes.get(seleccion - 1);
    }

    // --- Seleccionar Tour ---
    // Muestra los tours ya registrados en GestorTours y retorna el elegido
    private static Tour seleccionarTour(Scanner sc, GestorTours gestorTours) {
        ArrayList<Tour> tours = gestorTours.getRegistroTours();

        if (tours.isEmpty()) {
            System.out.println("No hay tours registrados. Registre un tour primero.\n");
            return null;
        }

        System.out.println("\nTours disponibles:");
        for (int i = 0; i < tours.size(); i++) {
            System.out.println((i + 1) + " - " + tours.get(i).getNombreTour() + " (Id: " + tours.get(i).getIdTour() + ")");
        }
        System.out.print("Seleccione un tour: ");

        int seleccion = -1;

        // Validador de numeral
        // Indica si la opcion numeral es valida
        try {
            seleccion = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero valido.\n");
            return null;
        }

        if (seleccion < 1 || seleccion > tours.size()) {
            System.out.println("Seleccion invalida.\n");
            return null;
        }

        return tours.get(seleccion - 1);
    }
}