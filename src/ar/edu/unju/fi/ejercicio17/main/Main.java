package ar.edu.unju.fi.ejercicio17.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio17.model.Jugador;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Jugador> jugadores = new ArrayList<Jugador>();
		
		Scanner lerScanner = new Scanner(System.in);
        int opcion = 0;

        do {
        	System.out.println("----------------------------------------------");
            System.out.println("\n\t\tMENÚ DE JUGADORES");
            System.out.println("----------------------------------------------");
            System.out.println("1. Alta de jugador");
            System.out.println("2. Mostrar datos de un jugador: ");
            System.out.println("3. Mostrar jugadores ordenados por apellido");
            System.out.println("4. Modificar datos de un jugador");
            System.out.println("5. Eliminar un jugador");
            System.out.println("6. Mostrar cantidad total de jugadores");
            System.out.println("7. Mostrar jugadores por nacionalidad");
            System.out.println("8. Salir");
            System.out.print("Ingrese una opción: ");

            try {
                opcion = lerScanner.nextInt();
                lerScanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                    	agregarJugador(lerScanner,jugadores);
                        break;
                    case 2:
                        mostrarDatosJugador(lerScanner,jugadores);
                        break;
                    case 3:
                        mostrarJugadoresOrdenadosApellido(jugadores);
                        break;
                    case 4:
                        modificarJugador(lerScanner, jugadores);
                        break;
                    case 5:
                    	eliminarJugador(lerScanner, jugadores);
                        break;
                    case 6:
                    	totalJugadores(jugadores);
                        break;
                    case 7:
                    	mostrarCantidadPorNacionalidad(lerScanner, jugadores);
                        break;
                    case 8:
                        System.out.println("Saliendo del menú...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                lerScanner.nextLine(); // Limpiar entrada errónea
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado. Motivo: " + e.getMessage());
            }
        } while (opcion != 8);

        lerScanner.close();
	}
	//metodo para agregar a un jugador
	private static void agregarJugador(Scanner entrada, List<Jugador> jugadores) {
        try {
            System.out.print("Ingrese el nombre del jugador: ");
            String nombre = entrada.nextLine();

            System.out.print("Ingrese el apellido del jugador: ");
            String apellido = entrada.nextLine();

            System.out.print("Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
            LocalDate fechaNacimiento = LocalDate.parse(entrada.nextLine());

            System.out.print("Ingrese la nacionalidad del jugador: ");
            String nacionalidad = entrada.nextLine();

            System.out.print("Ingrese la estatura del jugador (en cm): ");
            double estatura = entrada.nextDouble();
            entrada.nextLine();

            System.out.print("Ingrese el peso del jugador (en kg): ");
            double peso = entrada.nextDouble();
            entrada.nextLine(); 

            System.out.print("Ingrese la posición del jugador (delantero, medio, defensa, arquero): ");
            String posicion = entrada.nextLine();

            // Crear un nuevo objeto Jugador y agregarlo al ArrayList
            Jugador jugador = new Jugador(nombre, apellido, fechaNacimiento, nacionalidad, estatura, peso, posicion);
            jugadores.add(jugador);
            System.out.println(jugadores.size()+"Jugador dado de alta correctamente.");
            
            
        } catch (Exception e) {
            System.out.println("Error al dar de alta al jugador./n Motivo del Eror: " + e.getMessage());
        }
        
    }
	//Metodo para mostrar los datos
	private static void mostrarDatosJugador(Scanner entrada, List<Jugador> jugadores) {
		
        System.out.println("********* "+ "Cantidad Jugadores: "+jugadores.size()+" ************");
		try {
            System.out.print("Ingrese el nombre del jugador: ");
            String nombre = entrada.nextLine();

            System.out.print("Ingrese el apellido del jugador: ");
            String apellido = entrada.nextLine();

            // Buscar el jugador en el ArrayList
            Jugador jugador = buscarJugador(nombre, apellido, jugadores);
            if (jugador != null) {
                System.out.println("\nDatos del jugador: ");
                System.out.println("Nombre: " + jugador.getNombre() + " " + jugador.getApellido());
                System.out.println("Fecha de nacimiento: " + jugador.getFechaNacimiento());
                System.out.println("Nacionalidad: " + jugador.getNacionalidad());
                System.out.println("Estatura: " + jugador.getEstatura() + " cm");
                System.out.println("Peso: " + jugador.getPeso() + " kg");
                System.out.println("Posición: " + jugador.getPosicion());
                System.out.println("Edad: " + jugador.calcularEdad() + " años");
            } else {
                System.out.println("Jugador no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los datos del jugador./n Motivo del Eror: " + e.getMessage());
        }
    }
	//muestro los juagores ordenados por apellido 
    private static void mostrarJugadoresOrdenadosApellido(List<Jugador> jugadores) {
        
    	try {
            // Ordenar el ArrayList de jugadores por apellido
            Collections.sort(jugadores, (j1, j2) -> j1.getApellido().compareToIgnoreCase(j2.getApellido()));
                       
            // Mostrar los jugadores ordenados
            System.out.println("\nJugadores ordenados por apellido:");
            for (Jugador jugador : jugadores) {
                System.out.println(jugador);
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar jugadores ordenados. Motivo: " + e.getMessage());
        }
  
    }
    

	private static Jugador buscarJugador(String nombre, String apellido, List<Jugador> jugadores) {       	
            // Buscar jugador
            System.out.println("\nBuscar por apellido y nombre:"+ apellido +" "+nombre);
            for (Jugador jugador : jugadores) {
            	if (jugador.getApellido().equals(apellido) && jugador.getNombre().equals(nombre)) {
            		return jugador;
            	}
            }
			return null;     
	}
	
	
	
	
	private static void modificarJugador(Scanner leerScanner, List<Jugador> jugadores) {
    	try {
    		
            System.out.print("Ingrese el nombre del jugador que decea modificar: ");
            String nombre = leerScanner.nextLine();

            System.out.print("Ingrese el apellido del jugador que decea modificar: ");
            String apellido = leerScanner.nextLine();

            // Buscar el jugador en el ArrayList
            Jugador jugador = buscarJugador(nombre, apellido, jugadores);
            if (jugador != null) {
                System.out.println("\nDatos del jugador a modificar: ");
                
                System.out.print("Ingrese el nuevo nombre del jugador : ");
                String nombreMod = leerScanner.nextLine();
                jugador.setNombre(nombreMod);
                
                System.out.print("Ingrese el nuevo apellido del jugador: ");
                String apellidomod = leerScanner.nextLine();
                jugador.setApellido(apellidomod);

                System.out.print("Ingrese la nueva fecha de nacimiento (YYYY-MM-DD): ");
                LocalDate fechaNacimiento = LocalDate.parse(leerScanner.nextLine());
                jugador.setFechaNacimiento(fechaNacimiento);
                

                System.out.print("Ingrese la nueva nacionalidad del jugador: ");
                String nacionalidad = leerScanner.nextLine();
                jugador.setNacionalidad(nacionalidad);
                
                System.out.print("Ingrese la nueva estatura del jugador (en cm): ");
                double estatura = leerScanner.nextDouble();
                leerScanner.nextLine();
                jugador.setEstatura(estatura);
                

                System.out.print("Ingrese el nuevo peso del jugador (en kg): ");
                double peso = leerScanner.nextDouble();
                leerScanner.nextLine();
                jugador.setPeso(peso);

                System.out.print("Ingrese la nueva posición del jugador (delantero, medio, defensa, arquero): ");
                String posicion = leerScanner.nextLine();
                jugador.setPosicion(posicion);
                
                
                
            } else {
                System.out.println("Jugador no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los datos del jugador./n Motivo del Eror: " + e.getMessage());
        }
        
    }
	
	private static void eliminarJugador(Scanner leerScanner, List<Jugador> jugadores) {
    
		 System.out.print("Ingrese el nombre del jugador que decea eliminar: ");
         String nombre = leerScanner.nextLine();

         System.out.print("Ingrese el apellido del jugador que decea eliminar: ");
         String apellido = leerScanner.nextLine();
         
         Iterator<Jugador> iterator = jugadores.iterator();
         while (iterator.hasNext()) {
             Jugador jugador = iterator.next();
             if (jugador.getNombre().equalsIgnoreCase(nombre) && jugador.getApellido().equalsIgnoreCase(apellido)) {
                 iterator.remove();
                 System.out.println("Jugador eliminado correctamente.");
                 return;
             }
         }
         System.out.println("No se encontró un jugador con ese nombre y apellido.");
        
    }
	//muestra las cantidad total de elementos dentro del araylist 
	 private static void totalJugadores(List<Jugador> jugadores) {
		
        System.out.println("********* "+ "Cantidad Jugadores: "+jugadores.size()+" ************");  
	 }
	 
	 
	 private static void mostrarCantidadPorNacionalidad(Scanner scanner, List<Jugador> jugadores) {
	        System.out.print("Ingrese la nacionalidad para contar jugadores: ");
	        String nacionalidad = scanner.nextLine();

	        int contador = 0;
	        for (Jugador jugador : jugadores) {
	            if (jugador.getNacionalidad().equalsIgnoreCase(nacionalidad)) {
	                contador++;
	            }
	        }
	        System.out.println("La cantidad de jugadores de nacionalidad " + nacionalidad + " es: " + contador);
	    }

}