/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mx.uttt.Vecotre;

/**
 *
 * @author Luis Enrique
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class menu {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Selecciona una opcin:");
            System.out.println("1) Generar 30 numeros aleatorios y realizar operaciones");
            System.out.println("2) Clasificacion de puntajes de estudiantes");
            System.out.println("3) Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    OperacionesVector operaciones = new OperacionesVector();
                    operaciones.mostrarResultados();
                    break;
                case 2:
                    ClasificacionPuntajes puntajes = new ClasificacionPuntajes();
                    puntajes.mostrarClasificacion();
                    break;
                case 3:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }
}

class OperacionesVector {

    private int[] vect;
    private int tam = 30;

    public OperacionesVector() {
        vect = new int[tam];
        Random random = new Random();
        for (int i = 0; i < tam; i++) {
            vect[i] = random.nextInt(30) + 1;
        }
        Arrays.sort(vect);  // Ordenar el vector
    }

    public int obtenerMaximo() {
        return Arrays.stream(vect).max().getAsInt();
    }

    public int obtenerMinimo() {
        return Arrays.stream(vect).min().getAsInt();
    }

    public double calcularMedia() {
        return Arrays.stream(vect).average().orElse(0);
    }

    public int calcularModa() {
        Map<Integer, Integer> frecuencias = new HashMap<>();
        for (int num : vect) {
            frecuencias.put(num, frecuencias.getOrDefault(num, 0) + 1);
        }
        int moda = vect[0];
        int maxFre = 0;
        for (Map.Entry<Integer, Integer> entry : frecuencias.entrySet()) {
            if (entry.getValue() > maxFre) {
                maxFre = entry.getValue();
                moda = entry.getKey();
            }
        }
        return moda;
    }

    public double calcularMediana() {
        if (tam % 2 == 0) {
            return (vect[tam / 2 - 1] + vect[tam / 2]) / 2.0;
        } else {
            return vect[tam / 2];
        }
    }

    public void calcularFrecuencias() {
        Map<Integer, Integer> frecuencias = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            frecuencias.put(i, 0);
        }
        for (int num : vect) {
            if (num >= 1 && num <= 10) {
                frecuencias.put(num, frecuencias.get(num) + 1);
            }
        }
        System.out.println("Número | Cantidad | Porcentaje");
        for (int i = 1; i <= 10; i++) {
            int cantidad = frecuencias.get(i);
            double porcentaje = (double) cantidad / tam * 100;
            System.out.printf("%6d | %8d | %10.2f%%\n", i, cantidad, porcentaje);
        }
    }

    public void mostrarResultados() {
        System.out.println("Vector generado y ordenado: " + Arrays.toString(vect));
        System.out.println("Numero más grande: " + obtenerMaximo());
        System.out.println("Numero más pequeño: " + obtenerMinimo());
        System.out.println("Media: " + calcularMedia());
        System.out.println("Moda: " + calcularModa());
        System.out.println("Mediana: " + calcularMediana());
        calcularFrecuencias();
    }
}

class ClasificacionPuntajes {

    private int[] puntajes;
    private int numEs;

    public ClasificacionPuntajes() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de estudiantes:");
        numEs = scanner.nextInt();
        puntajes = new int[numEs];
        Random random = new Random();
        for (int i = 0; i < numEs; i++) {
            puntajes[i] = random.nextInt(21);  // Puntajes entre 0 y 20
        }
    }

    public void mostrarClasificacion() {
        int deficientes = 0, regulares = 0, buenos = 0, excelentes = 0;

        for (int puntaje : puntajes) {
            if (puntaje >= 0 && puntaje <= 5) {
                deficientes++;
            } else if (puntaje >= 6 && puntaje <= 10) {
                regulares++;
            } else if (puntaje >= 11 && puntaje <= 15) {
                buenos++;
            } else if (puntaje >= 16 && puntaje <= 20) {
                excelentes++;
            }
        }

        System.out.println("Puntajes generados: " + Arrays.toString(puntajes));
        System.out.println("0-5 Deficientes: " + deficientes);
        System.out.println("6-10 Regulares: " + regulares);
        System.out.println("11-15 Buenos: " + buenos);
        System.out.println("16-20 Excelentes: " + excelentes);
    }

}
