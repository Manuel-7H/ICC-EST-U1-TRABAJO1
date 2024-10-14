import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Datos a = new Datos();
        a.ingresarValores();
    }
}

class Datos {

    public ArrayList<Integer> ingresarValores() {
        Scanner c = new Scanner(System.in);
        ArrayList<Integer> lista = new ArrayList<>();
        boolean continua = true;

        while (continua) {
            System.out.println("Menu Principal");
            System.out.println("1. Ingrese un Arreglo");
            System.out.println("2. Ordenar un Arreglo");
            System.out.println("0. Salir");

            int seleccion = c.nextInt();

            switch (seleccion) {
                case 1:
                    System.out.print("Ingrese el tamaño del arreglo: ");
                    int cantidad = c.nextInt();
                    for (int i = 0; i < cantidad; i++) {
                        System.out.print("Ingrese el valor para la posición " + (i + 1) + ": ");
                        int dato = c.nextInt();
                        lista.add(dato);
                    }
                    System.out.println("\nLa lista de valores es: " + lista);
                    mostrarMenuOrdenamiento(lista);
                    continua = false; 
                    break;

                case 2:
                    if (lista.isEmpty()) {
                        System.out.println("Primero debe ingresar un arreglo en la opción 1.");
                    } else {
                        mostrarMenuOrdenamiento(lista);
                    }
                    continua = false; 
                    break;

                case 0:
                    continua = false;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Selección inválida, por favor intente de nuevo.");
            }
        }
        c.close();
        return lista; 
    }

    public void mostrarMenuOrdenamiento(ArrayList<Integer> lista) {
        Ordenamiento b = new Ordenamiento();
        Scanner b1 = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            System.out.println("\nMENU DE ORDENAMIENTO:");
            System.out.println("1. Metodo Burbuja");
            System.out.println("2. Metodo Seleccion");
            System.out.println("3. Metodo Insercion");
            System.out.println("4. Metodo Burbuja Mejorado");
            System.out.println("0. Regresar al Menu Principal");
            System.out.print("\nEscoja el algoritmo de ordenamiento: ");

            int metodo = b1.nextInt();

            if (metodo != 0) {
                System.out.println("\nEscoja ascendente o descendente:");
                System.out.println("1. Ascendente");
                System.out.println("2. Descendente");
                int orden = b1.nextInt();

                System.out.println("\nQuiere ver cada paso:");
                System.out.println("1. Sí");
                System.out.println("2. No");
                int verPasos = b1.nextInt();
                boolean mostrarPasos = verPasos == 1;

                switch (metodo) {
                    case 1:
                        b.ordenarBurbuja(lista, orden, mostrarPasos);
                        break;
                    case 2:
                        b.ordenarSeleccion(lista, orden, mostrarPasos);
                        break;
                    case 3:
                        b.ordenarInsercion(lista, orden, mostrarPasos);
                        break;
                    case 4:
                        b.ordenarBurbujaMejorado(lista, orden, mostrarPasos);
                        break;
                    default:
                        System.out.println("Selección inválida, por favor intente de nuevo.");
                }
            } else {
                continua = false;
                System.out.println("Regresando al Menu Principal...");
            }
        }
        b1.close();
    }
}

class Ordenamiento {

    public void ordenarBurbuja(ArrayList<Integer> lista, int orden, boolean mostrarPasos) {
        int n = lista.size();
        boolean intercambiado;
        for (int i = 0; i < n - 1; i++) {
            intercambiado = false;
            System.out.println("Pasada número " + (i + 1));
            for (int j = 0; j < n - i - 1; j++) {
                System.out.println("\ti=" + i + " j=" + j + " [j]=" + lista.get(j) + " [j+1]=" + lista.get(j + 1));
                boolean condicion = (orden == 1) ? lista.get(j) > lista.get(j + 1) : lista.get(j) < lista.get(j + 1);
                if (condicion) {
                    System.out.println("\t\tIntercambiamos " + lista.get(j) + " con " + lista.get(j + 1));
                    int temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                    intercambiado = true;
                }
                if (mostrarPasos) {
                    System.out.println("\t\t--------" + lista);
                }
            }
            if (!intercambiado) break; 
        }
        System.out.println("\nLista ordenada por Método Burbuja: " + lista);
    }

    public void ordenarSeleccion(ArrayList<Integer> lista, int orden, boolean mostrarPasos) {


        for (int i = 0; i < lista.size() - 1; i++) {
            int indice = i;
            System.out.println("Pasada número " + (i + 1));
            for (int j = i + 1; j < lista.size(); j++) {
                System.out.println("\ti=" + i + " j=" + j + " [i]=" + lista.get(i) + " [j]=" + lista.get(j));
                boolean condicion = (orden == 1) ? lista.get(j) < lista.get(indice) : lista.get(j) > lista.get(indice);
                if (condicion) {
                    System.out.println("\t\tNuevo índice para intercambio: " + j);
                    indice = j;
                }
              
            }
            if (indice != i) {
                System.out.println("\t\tIntercambiamos " + lista.get(i) + " con " + lista.get(indice));
                int temp = lista.get(i);
                lista.set(i, lista.get(indice));
                lista.set(indice, temp);
               
            }
            if (mostrarPasos) {
                System.out.println("\t\t--------" + lista);
            }
        }
        System.out.println("\nLista ordenada por Método Selección: " + lista);
    }

    public void ordenarInsercion(ArrayList<Integer> lista, int orden, boolean mostrarPasos) {
        int n = lista.size();
        for (int i = 1; i < n; i++) {
            int actual = lista.get(i);
            int j = i - 1;

            System.out.println("Pasada número " + i);
            System.out.println("\ti=" + i + " j=" + j + " [i]=" + lista.get(i) + " [j]=" + lista.get(j));

            while (j >= 0 && ((orden == 1) ? lista.get(j) > actual : lista.get(j) < actual)) {
                System.out.println("\t\tComparamos " + actual + " con " + lista.get(j));
                lista.set(j + 1, lista.get(j));
                j--;

                if (mostrarPasos) {
                    System.out.println("\t\t--------" + lista);
                }
            }
            lista.set(j + 1, actual);

            if (mostrarPasos) {
                System.out.println("\t\t--------" + lista);
            }
        }
        System.out.println("\nLista ordenada por Método Inserción: " + lista);
    }

    public void ordenarBurbujaMejorado(ArrayList<Integer> lista, int orden, boolean mostrarPasos) {
        int n = lista.size();
        boolean intercambiado;
        for (int i = 0; i < n - 1; i++) {
            intercambiado = false;
            System.out.println("Pasada número " + (i + 1));
            for (int j = 0; j < n - i - 1; j++) {
                System.out.println("\ti=" + i + " j=" + j + " [j]=" + lista.get(j) + " [j+1]=" + lista.get(j + 1));
                boolean condicion = (orden == 1) ? lista.get(j) > lista.get(j + 1) : lista.get(j) < lista.get(j + 1);
                if (condicion) {
                    System.out.println("\t\tIntercambiamos " + lista.get(j) + " con " + lista.get(j + 1));
                    int temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                    intercambiado = true;
                }
                if (mostrarPasos) {
                    System.out.println("\t\t--------" + lista);
                }
            }
            if (!intercambiado) break;
        }
        System.out.println("\nLista ordenada por Método Burbuja Mejorado: " + lista);
    }

    public void prinArreglo(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
