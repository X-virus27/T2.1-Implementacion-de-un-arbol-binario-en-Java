package app;

import modelo.Nodo;
import negocio.ArbolBinario;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolBinario<String> arbol = new ArbolBinario<>();
        int opcion;

        do {
            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Crear el árbol inicial");
            System.out.println("2. Visualizar el árbol");
            System.out.println("3. Consultar el número de nodos");
            System.out.println("4. Consultar el número de hojas");
            System.out.println("5. Consultar la altura");
            System.out.println("6. Consultar el grado de un nodo");
            System.out.println("7. Verificar si un nodo es hoja");
            System.out.println("8. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            System.out.println(); 

            switch (opcion) {
                case 0:
                    System.out.println("Nodos: " + arbol.contarNodos());
                    System.out.println("Hojas: " + arbol.contarHojas());
                    System.out.println("Altura: " + arbol.altura());
                    break;
                case 1:
                    if (!arbol.esVacio()) {
                        System.out.println("El árbol ya ha sido creado.");
                        break;
                    }
                    arbol.crearRaiz("UNIVERSIDAD");
                    Nodo<String> raiz = arbol.getRaiz();
                    Nodo<String> fisei = arbol.agregarIzquierdo(raiz, "FISEI");
                    Nodo<String> dide = arbol.agregarDerecho(raiz, "DIDE");
                    
                    Nodo<String> software = arbol.agregarIzquierdo(fisei, "SOFTWARE");
                    arbol.agregarDerecho(fisei, "TI");
                    
                    arbol.agregarIzquierdo(dide, "INVEST.");
                    arbol.agregarDerecho(dide, "VINCULACIÓN");
                    
                    arbol.agregarIzquierdo(software, "NIVEL1");
                    arbol.agregarDerecho(software, "NIVEL3");
                    
                    break;
                case 2:
                    arbol.mostrarArbol();
                    break;
                case 3:
                    System.out.println("Nodos: " + arbol.contarNodos());
                    break;
                case 4:
                    System.out.println("Hojas: " + arbol.contarHojas());
                    break;
                case 5:
                    System.out.println("Altura: " + arbol.altura());
                    break;
                case 6:
                    System.out.print("Ingrese nodo: ");
                    String nombreGrado = scanner.nextLine().toUpperCase();
                    Nodo<String> nodoGrado = arbol.buscar(nombreGrado);
                    if (nodoGrado != null) System.out.println("Grado: " + nodoGrado.grado());
                    break;
                case 7:
                    System.out.print("Ingrese nodo: ");
                    String nombreHoja = scanner.nextLine().toUpperCase();
                    Nodo<String> nodoHoja = arbol.buscar(nombreHoja);
                    if (nodoHoja != null) System.out.println("¿Es hoja?: " + (nodoHoja.esHoja() ? "Sí" : "No"));
                    break;    
                case 8:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 8);
        
        scanner.close();
    }
}