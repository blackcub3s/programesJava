/*
    4. Generar un quadrat Llatí d'ordre N.
    Un quadrat Llatí d'ordre N és una matriu quadrada en la qual la primera 
    fila conté els N primers nombres naturals, en ordre, i cadascuna de les 
    següents files conté la rotació de la fila anterior un lloc a la dreta.

    Per exemple, per a N=5

    1 2 3 4 5
    5 1 2 3 4
    4 5 1 2 3
    3 4 5 1 2
    2 3 4 5 1
 */

package Exercicis3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author santi
 * @fecha  12 dic. 2023
 */
public class Exercici4 {
    
    
    //IMPRIMIM LA MATRIU
    public static void visualitzaMatriu(int[][] matriu) {
        System.out.print("[");
        for (int i = 0; i < matriu.length - 1; ++i) {
            System.out.println(Arrays.toString(matriu[i])+","); 
        }
        System.out.println(Arrays.toString(matriu[matriu.length - 1])+"]");         
    }

    //PRE: primera fila d'una matriu
    //POST: fila emplenada (passada per referencia) amb nombres de 1 fins a 
    //      primeraFila.length)
    public static void emplenaFila1(int[] primeraFila) {
        for (int i = 0; i < primeraFila.length; ++i) {
            primeraFila[i] = i + 1;
        }
    }
    
    
    //PRE: Dues files d'una matriu (dos arrays del mateix tamany)
    //POST: Fila2 serà igual que la fila1, però amb cada element mogut 
    //      una posició a la dreta (amb ultim element fila 1 posat a primer
    //      element fila 2). Els canvis passen per referència a la matriu
    //      de la funció generaQuadratLlati()
    public static void mouFilaAlaDreta(int[] fila1, int[] fila2) {
        int n = fila1.length; //nre columnes de la matriu d'on provenen les files
        for (int i = 1; i < n; ++i) {
            fila2[i] = fila1[i-1];
        }
        fila2[0] = fila1[n-1];
    }
    
    //PRE: L'ordre N del quadrat llatí (nombre de files i columnes)
    //POST: El quadrat llatí confeccionat
    public static int[][] generaQuadratLlati(int N) {
        int[][] matriu = new int[N][N];
        emplenaFila1(matriu[0]);
        for (int i = 0; i < matriu.length - 1; ++i) {
            mouFilaAlaDreta(matriu[i], matriu[i+1]);        
        }
        return matriu;
    }
    
    
    public static void main(String[] args) {
        System.out.println("De quin ordre vols el quadrat llati: ");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] quadratLlati = generaQuadratLlati(N); //CANVIEU ORDRE N SI VOLEU 
        visualitzaMatriu(quadratLlati);
    }
}
