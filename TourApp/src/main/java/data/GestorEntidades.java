package data;

import model.Personas.Cliente;
import model.Personas.Colaborador;
import model.Personas.Guia;
import model.Personas.OperadorTransporte;
import model.Personas.Proveedor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorEntidades {

    // Rutas de los archivos txt
    private static final String ARCHIVO_COLABORADORES = "src/main/resources/colaboradores.txt";
    private static final String ARCHIVO_CLIENTES = "src/main/resources/clientes.txt";

    // Listas de registro
    private ArrayList<Colaborador> registroColaboradores;
    private ArrayList<Cliente> registroClientes;

    // Constructor
    public GestorEntidades() {
        registroColaboradores = new ArrayList<>();
        registroClientes = new ArrayList<>();

        cargarArchivoColaboradores();
        cargarArchivoClientes();
    }

    // Agregar colaborador
    // Valida que no exista ya un colaborador con el mismo nombre y mis||mo tipo
    // Retorna true si se agrego, false si era un duplicado
    public boolean agregarColaborador(Colaborador colaborador) {
        for (Colaborador c : registroColaboradores) {
            if (c.getClass() == colaborador.getClass() && c.getNombre().equalsIgnoreCase(colaborador.getNombre())) {
                return false;
            }
        }

        registroColaboradores.add(colaborador);
        guardarColaboradorArchivo(colaborador);
        return true;
    }

    // Agregar cliente
    // Valida que no exista ya un cliente con el mismo rut
    // Retorna true si se agrego, false si era un duplicado
    public boolean agregarCliente(Cliente cliente) {
        for (Cliente c : registroClientes) {
            if (c.getRut().equalsIgnoreCase(cliente.getRut())) {
                return false;
            }
        }

        registroClientes.add(cliente);
        guardarClienteArchivo(cliente);
        return true;
    }

    // Agrega los Colaboradores al archivo Colaboradores.txt
    private void guardarColaboradorArchivo(Colaborador colaborador) {
        String tipo;
        if (colaborador instanceof Guia) {
            tipo = "GUIA";
        } else if (colaborador instanceof OperadorTransporte) {
            tipo = "OPERADOR";
        } else if (colaborador instanceof Proveedor) {
            tipo = "PROVEEDOR";
        } else {
            tipo = "COLABORADOR";
        }

        String linea = tipo + ";" + colaborador.getNombre() + ";" + colaborador.getCargo();

        try (FileWriter fw = new FileWriter(ARCHIVO_COLABORADORES, true)) {
            fw.write(linea + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error al guardar colaborador en archivo: " + e.getMessage());
        }
    }

    // Agrega Los Clientes al Archivo Clientes.txt
    private void guardarClienteArchivo(Cliente cliente) {
        String linea = cliente.getNombre() + ";" + cliente.getRut();
        try (FileWriter fw = new FileWriter(ARCHIVO_CLIENTES, true)) {
            fw.write(linea + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error al guardar cliente en archivo: " + e.getMessage());
        }
    }


    // Lee colaboradores.txt y reconstruye los objetos
    private void cargarArchivoColaboradores() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_COLABORADORES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }

                String[] partes = linea.split(";");
                if (partes.length < 3) {
                    continue;
                }

                String tipo = partes[0];
                String nombre = partes[1];
                String cargo = partes[2];

                Colaborador colaborador;
                switch (tipo) {
                    case "GUIA":
                        colaborador = new Guia(nombre, cargo);
                        break;
                    case "OPERADOR":
                        colaborador = new OperadorTransporte(nombre, cargo);
                        break;
                    case "PROVEEDOR":
                        colaborador = new Proveedor(nombre, cargo);
                        break;
                    default:
                        colaborador = null;
                        break;
                }

                if (colaborador != null) {
                    registroColaboradores.add(colaborador);
                }
            }

        } catch (IOException e) {
            System.out.println("No se encontro " + ARCHIVO_COLABORADORES );
        }
    }

    // Lee clientes.txt y reconstruye los objetos
    private void cargarArchivoClientes() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CLIENTES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }

                String[] partes = linea.split(";");
                if (partes.length < 2) {
                    continue; // Linea con formato invalido, se ignora
                }

                String nombre = partes[0];
                String rut = partes[1];
                registroClientes.add(new Cliente(nombre, rut));
            }

        } catch (IOException e) {
            System.out.println("No se encontro " + ARCHIVO_CLIENTES);
        }
    }


    // Mostrar colaboradores por consola
    public void mostrarColaboradores() {
        for (Colaborador c : registroColaboradores) {
            c.mostrarInformacion();
        }
    }
    // Mostrar clientes por consola
    public void mostrarClientes() {
        for (Cliente cl : registroClientes) {
            cl.mostrarInformacion();
        }
    }


    // Devuelve los colaboradores registrados para mostrarlos en la GUI
    public String listarColaboradores() {
        StringBuilder sb = new StringBuilder();
        sb.append("Colaboradores Registrados:\n\n");

        if (registroColaboradores.isEmpty()) {
            sb.append("No hay colaboradores registrados.\n");
            return sb.toString();
        }

        for (Colaborador c : registroColaboradores) {
            String tipo;
            if (c instanceof Guia) {
                tipo = "Guía Turístico";
            } else if (c instanceof OperadorTransporte) {
                tipo = "Operador de Transporte";
            } else if (c instanceof Proveedor) {
                tipo = "Proveedor";
            } else {
                tipo = "Colaborador";
            }

            sb.append("Tipo   : ").append(tipo).append("\n");
            sb.append("Nombre : ").append(c.getNombre()).append("\n");
            sb.append("Cargo  : ").append(c.getCargo()).append("\n");
            sb.append("\n");
        }

        return sb.toString();
    }

    // Devuelve los clientes registrados para mostrarlos en la GUI
    public String listarClientes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Clientes Registrados:\n\n");

        if (registroClientes.isEmpty()) {
            sb.append("No hay clientes registrados.\n");
            return sb.toString();
        }

        for (Cliente cl : registroClientes) {
            sb.append("Nombre : ").append(cl.getNombre()).append("\n");
            sb.append("Rut    : ").append(cl.getRut()).append("\n");
            sb.append("\n");
        }

        return sb.toString();
    }

    // Getters

    public ArrayList<Colaborador> getRegistroColaboradores() {
        return registroColaboradores;
    }
    public ArrayList<Cliente> getRegistroClientes() {
        return registroClientes;
    }

}