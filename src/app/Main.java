package app;

import java.util.Locale;
import java.util.Scanner;
import modelo.Nodo;
import negocio.ArbolBinario;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1 && "--pruebas".equals(args[0])) {
            ejecutarPruebas();
            return;
        }
        ArbolBinario<String> arbol = new ArbolBinario<>();
        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            boolean continuar = true;
            while (continuar) {
                mostrarMenu();
                if (!scanner.hasNextLine()) { break; }
                String opcion = scanner.nextLine().trim();
                switch (opcion) {
                    case "1":
                        arbol = crearArbolInicial();
                        System.out.println("Árbol inicial creado: 9 nodos, 5 hojas, altura 3.");
                        break;
                    case "2": arbol.mostrarArbol(); break;
                    case "3": System.out.println("Nodos: " + arbol.contarNodos()); break;
                    case "4": System.out.println("Hojas: " + arbol.contarHojas()); break;
                    case "5": System.out.println("Altura en aristas: " + arbol.altura()); break;
                    case "6":
                    case "7":
                        System.out.print("Nombre del nodo: ");
                        if (!scanner.hasNextLine()) { continuar = false; break; }
                        String nombre = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
                        Nodo<String> nodo = arbol.buscar(nombre);
                        if (nodo == null) {
                            System.out.println("Nodo no encontrado: " + nombre);
                        } else if ("6".equals(opcion)) {
                            System.out.println("Grado de " + nombre + ": " + nodo.grado());
                        } else {
                            System.out.println(nombre + (nodo.esHoja() ? " es hoja." : " no es hoja."));
                        }
                        break;
                    case "8":
                        continuar = false;
                        System.out.println("Programa finalizado.");
                        break;
                    default: System.out.println("Opción inválida. Ingrese un número del 1 al 8.");
                }
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\nMENU ÁRBOL BINARIO");
        System.out.println("1. Crear o reiniciar el árbol inicial");
        System.out.println("2. Visualizar el árbol");
        System.out.println("3. Consultar número de nodos");
        System.out.println("4. Consultar número de hojas");
        System.out.println("5. Consultar altura");
        System.out.println("6. Consultar grado de un nodo");
        System.out.println("7. Verificar si un nodo es hoja");
        System.out.println("8. Salir");
        System.out.print("Opción: ");
    }

    private static ArbolBinario<String> crearArbolInicial() {
        ArbolBinario<String> arbol = new ArbolBinario<>();
        Nodo<String> universidad = arbol.crearRaiz("UNIVERSIDAD");
        Nodo<String> fisei = arbol.agregarIzquierdo(universidad, "FISEI");
        Nodo<String> dide = arbol.agregarDerecho(universidad, "DIDE");
        Nodo<String> software = arbol.agregarIzquierdo(fisei, "SOFTWARE");
        arbol.agregarDerecho(fisei, "TI");
        arbol.agregarIzquierdo(dide, "INVEST.");
        arbol.agregarDerecho(dide, "VINCULACIÓN");
        arbol.agregarIzquierdo(software, "NIVEL1");
        arbol.agregarDerecho(software, "NIVEL3");
        return arbol;
    }

    /** Pruebas reproducibles sin bibliotecas externas; un fallo detiene el programa. */
    private static void ejecutarPruebas() {
        System.out.println("CASOS DE PRUEBA - ÁRBOL BINARIO");
        ArbolBinario<String> vacio = new ArbolBinario<>();
        verificar(vacio.esVacio() && vacio.contarNodos() == 0
                && vacio.contarHojas() == 0 && vacio.altura() == -1, "Árbol vacío");
        System.out.println("[OK] Vacío: nodos=0, hojas=0, altura=-1.");

        Nodo<String> unico = vacio.crearRaiz("ÚNICO");
        verificar(!vacio.esVacio() && vacio.contarNodos() == 1
                && vacio.contarHojas() == 1 && vacio.altura() == 0
                && unico.esHoja() && unico.grado() == 0, "Un solo nodo");
        System.out.println("[OK] Un nodo: nodos=1, hojas=1, altura=0, grado=0.");

        ArbolBinario<String> arbol = crearArbolInicial();
        verificar(arbol.contarNodos() == 9 && arbol.contarHojas() == 5
                && arbol.altura() == 3, "Árbol inicial");
        System.out.println("[OK] Inicial: nodos=9, hojas=5, altura=3.");
        verificar(arbol.buscar("FISEI").grado() == 2
                && arbol.buscar("SOFTWARE").grado() == 2, "Grados");
        System.out.println("[OK] Grados: FISEI=2; SOFTWARE=2.");
        verificar(arbol.buscar("NIVEL1").esHoja() && !arbol.buscar("FISEI").esHoja(), "Hojas");
        System.out.println("[OK] NIVEL1 es hoja; FISEI no es hoja.");

        Nodo<String> izquierdoOriginal = arbol.getRaiz().getIzquierdo();
        try {
            arbol.agregarIzquierdo(arbol.getRaiz(), "OTRO");
            throw new AssertionError("Se permitió sobrescribir el hijo izquierdo.");
        } catch (IllegalStateException esperado) {
            System.out.println("[OK] Inserción izquierda rechazada: " + esperado.getMessage());
        }
        Nodo<String> derechoOriginal = arbol.getRaiz().getDerecho();
        try {
            arbol.agregarDerecho(arbol.getRaiz(), "OTRO");
            throw new AssertionError("Se permitió sobrescribir el hijo derecho.");
        } catch (IllegalStateException esperado) {
            System.out.println("[OK] Inserción derecha rechazada: " + esperado.getMessage());
        }
        verificar(arbol.getRaiz().getIzquierdo() == izquierdoOriginal
                && arbol.getRaiz().getDerecho() == derechoOriginal
                && arbol.contarNodos() == 9, "Conservación del árbol");

        try {
            arbol.agregarIzquierdo(new Nodo<>("EXTERNO"), "HIJO");
            throw new AssertionError("Se permitió un padre ajeno al árbol.");
        } catch (IllegalArgumentException esperado) {
            System.out.println("[OK] Padre externo rechazado.");
        }
        try {
            arbol.crearRaiz("OTRA RAÍZ");
            throw new AssertionError("Se permitió reemplazar la raíz.");
        } catch (IllegalStateException esperado) {
            System.out.println("[OK] Segunda raíz rechazada.");
        }
        verificar(arbol.buscar("NO EXISTE") == null, "Búsqueda sin coincidencia");

        ArbolBinario<Integer> cadena = new ArbolBinario<>();
        Nodo<Integer> padre = cadena.crearRaiz(1);
        padre = cadena.agregarDerecho(padre, 2);
        cadena.agregarIzquierdo(padre, 3);
        verificar(cadena.contarNodos() == 3 && cadena.contarHojas() == 1
                && cadena.altura() == 2 && cadena.getRaiz().grado() == 1, "Árbol no balanceado");
        System.out.println("[OK] Cadena genérica: nodos=3, hojas=1, altura=2.");
        System.out.println("\nESTRUCTURA INICIAL");
        arbol.mostrarArbol();
        System.out.println("\nRESULTADO: todas las pruebas superadas.");
    }

    private static void verificar(boolean condicion, String caso) {
        if (!condicion) { throw new AssertionError("Falló el caso: " + caso); }
    }
}
