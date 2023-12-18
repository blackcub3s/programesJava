/*
3. Crea un vector V numèric de 50 elements i un altre P 
de 20 elements. Genera després M(50,20) de tal forma que
M(I,J) és igual a V(I) * P(J)
 */

package Exercicis3;

import java.util.Arrays;
import java.util.Random;

/**
 * @author santi
 * @fecha  13 dic. 2023
 */
public class Exercici3 {
    
    //imprimeix una matriu amb espais variables per acomodar-se als nombres impresos
    //(l'espaiat variable del tabulador en aquest cas
    // era molt gran per encabir tota la matriu en pantalla, 
    // per això l'ús de "%-4d", com a màxim 4 espais per cada nombre
    // i justificat a l'esquerra)
    public static void visualitzaMatriu(int[][] matriu) {
        for (int i = 0; i < matriu.length; ++i) {
            for (int valor: matriu[i]) {
                System.out.printf("%-4d", valor); 
            }
            System.out.println(""); //salt de linia
        }
    }        
    
    
    
    public static void emplenaVector(int[] v) {
        Random rd = new Random();
        for (int i = 0; i < v.length; ++i) {
            v[i] = rd.nextInt(10); //aleatoris de 0 a 9;
        }
    }
    
    public static void main(String[] args) {
        
        //DECLAREM I EMPLENEM ELS VECTORS
        int[] V = new int[50];
        int[] P = new int[20];  
        
        emplenaVector(V);
        emplenaVector(P);
        
        //DECLAREM LA MATRIU I L'EMPLENEM
        int[][] M = new int[50][20];
        for (int i = 0; i < M.length; ++i) {
            for (int j = 0; j < M[0].length; ++j) {
                M[i][j] = V[i]*P[j]; 
            }
        }
        
        
        //VISUALITZEM ELS VECTORS
        System.out.println("\nVector V:");
        System.out.println(Arrays.toString(V));
        
        System.out.println("\nVector P:");
        System.out.println(Arrays.toString(P));
        
        //VISUALITZEM LA MATRIU RESULTANT
        System.out.println("\nMatriu M:");
        visualitzaMatriu(M);
    }
}
