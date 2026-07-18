# Semana-9-APP-LLanquihue-Tour

![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 🧠 Evaluación Semana 9 - Programacion Orientada a Objetos - HERENCIA , COMPOCICION Y POLIFORMISMO

## 👤 Autor del proyecto
- **Nombre completo:** Claudio Antonio Fuentes Aguilar.
- **Sección:** 2026_202_QL_PRY2202_24613389_PCT 002A
- **Carrera:** Analista Programador
- **Sede:** Online

---

## 📘 Descripción general del sistema

Llanquihue Tour : Este proyecto es una aplicación de escritorio desarrollada en Java que permite a la agencia de turismo **Llanquihue Tour** gestionar sus entidades operativas clave, tales como Guías Turísticos, Colaboradores Externos y Vehículos. 

El sistema implementa conceptos avanzados de la Programación Orientada a Objetos (POO), incluyendo herencia, interfaces, colecciones polimórficas y validación dinámica de tipos, además de proporcionar un menu consola básica completada con  la libreria Scanner.

---

## 🧱 Estructura general del proyecto

```plaintext
Estructura de Paquetes
src/
│
├── model/                              # Capa del Modelo (lógica de negocio y entidades)
│   ├── Personas/
│   │   ├── Colaborador.java            # Superclase para el personal
│   │   ├── Cliente.java                # Entidad Cliente
│   │   ├── Guia.java                   # Subclase especializada de Colaborador
│   │   ├── OperadorTransporte.java     # Subclase especializada de Colaborador
│   │   └── Proveedor.java              # Subclase especializada de Colaborador
│   ├── Tours/
│   │   └── Tour.java                   # Entidad Tour (composición con Guia)
│   └── Reservaciones/
│       └── Reserva.java                # Entidad Reserva (composición con Cliente y Tour)
├── data/                               # Capa de Datos / Controladores de colecciones
│   ├── GestorEntidades.java            # Administra colaboradores y clientes
│   ├── GestorTours.java                # Administra los tours
│   └── GestorReservas.java             # Administra las reservas
├── utill/                              # Capa de utilidades
│   └── Mostrable.java                  # Interfaz común para mostrar información
└── ui/                                 # Capa de Interfaz de Usuario
    └── Main.java                       # Clase de entrada (punto de arranque del sistema)

## 🔧 Requisitos e Instalación
Prerrequisitos

    Tener instalado el Java Development Kit (JDK) versión 11 o superior.

    Un IDE compatible (IntelliJ IDEA, Eclipse, NetBeans) o acceso a la terminal de comandos.

Ejecución

    Clona o descarga este repositorio en tu máquina local.

    Abre el proyecto en tu IDE preferido.

    Dirígete a la clase src/ui/Main.java.

    Ejecuta el método main para inicializar la aplicación.


📝 Ejemplo de Uso de la Aplicación

Al iniciar el programa: se compondra un menu en consola que te permitira realizar la gestion de personas , tours , y las reservas , Ejemplo de modo de uso 

    --- Bienvenidos a Llanquihue Tour ---
Ingrese una opcion:
1 - Gestion de Personas
2 - Gestion de Tours
3 - Gestion de Reservas
4 - Salir
Opcion: 1

--- Gestion de Personas ---
Ingrese una opcion:
1 - Registrar Colaborador
2 - Mostrar informacion Colaborador
3 - Registrar Cliente
4 - Mostrar informacion Clientes
5 - Volver al menu principal
Opcion: 1

--- Registrar Colaborador ---
1 - Guia Turistico
2 - Operador de Transporte
3 - Proveedor
Tipo de Colaborador: 1
Ingrese el nombre: Matias Soto
Ingrese el cargo: Guia de Montaña
- Colaborador registrado -

Opcion: 5

--- Gestion de Tours ---
Opcion: 1

--- Registrar Tour ---
Ingrese el id del tour: T001
Ingrese el nombre del tour: Ascenso Volcan Osorno
Ingrese el destino: Volcan Osorno

Guias disponibles:
1 - Matias Soto
0 - No asignar guia
Seleccione un guia: 1
- Tour registrado -

Opcion: 3

--- Gestion de Reservas ---
Opcion: 1

--- Registrar Reserva ---
Ingrese el id de la reserva: R001
Ingrese la fecha (dd-mm-aaaa): 12-08-2026

Clientes disponibles:
1 - Blanca Nieves (Rut: 12345678-5)
Seleccione un cliente: 1

Tours disponibles:
1 - Ascenso Volcan Osorno (Id: T001)
Seleccione un tour: 1
- Reserva registrada -

Opcion: 2

Reservas Registradas:

ID      : R001
Fecha   : 12-08-2026
Cliente : Blanca Nieves
Tour    : Ascenso Volcan Osorno

Opcion: 3
Volviendo al menu principal...

Opcion: 4
--- Saliendo Del Registro ---

**Repositorio GitHub:** https://github.com/claudioaguilar96/Semana-9-APP-LLanquihue-Tour
**Fecha de entrega:** Julio 2026

---

© Duoc UC | Escuela de Informática y Telecomunicaciones 
