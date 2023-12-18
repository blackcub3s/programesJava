/*
1. Introdueix una matriu de 5 files i 7 columnes i a continuació visualitza:
• Tota la matriu
• La fila cinc completa
• l'element de la quarta fila tercera columna i l'element de la cinquena fila tercera columna. Intercanvia el contingut d'aquests dos elements i visualitzar-los de nou
• La primera columna i la quarta. Intercanvia el c
ontingut d'aquestes columnes i visualitzar-les de nou
 */

package Exercicis3;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author santi
 * @fecha  12 dic. 2023
 * 
 */
public class Exercici1 {
    
    //IMPRIMIM LA MATRIU
    //NOTA: COMPTE QUE METODE Arrays.toString només pot imprimir
    //      arrays a seques, no arrays d'arrays (per això el
    //      crido a cada fila)
    public static void visualitzaMatriu(int[][] matriu) {
        System.out.print("[");
        for (int i = 0; i < matriu.length - 1; ++i) {
            System.out.println(Arrays.toString(matriu[i])+","); 
        }
        System.out.println(Arrays.toString(matriu[matriu.length - 1])+"]");         
    }

    
    public static void main (String[] args) {
        
        
        
        int[][] matriu = new int[5][7];
        Random rd = new Random();
        
        //RELLENEM LA MATRIU
        for (int i = 0; i < matriu.length; ++i) {
            for (int j = 0; j < matriu[0].length; ++j) {
                matriu[i][j] = rd.nextInt(30);
            }
        }
        
        System.out.println("Visualitzo matriu sencera:");
        visualitzaMatriu(matriu);
        
        System.out.println("\n5a fila de la matriu:");
        System.out.println(Arrays.toString(matriu[4]));
        
        
        System.out.println("\nimprimeixo element 4a fila 3a columna");
        System.out.println(matriu[3][2]);
        System.out.println("imprimeixo element 5a fila 3a columna");
        System.out.println(matriu[4][2]);
        
        System.out.println("Intercanvio matriu[3][2] per matriu[4][2] (swap)");
        int auxiliar = matriu[4][2];
        matriu[4][2] = matriu[3][2];
        matriu[3][2] = auxiliar;

        System.out.println("\nimprimeixo element 4a fila 3a columna");
        System.out.println(matriu[3][2]);
        System.out.println("imprimeixo element 5a fila 3a columna");
        System.out.println(matriu[4][2]);        
        
        
        System.out.println("\nMatriu abans de permutar:");
        visualitzaMatriu(matriu);
        System.out.println("\nPermuto columnes 1 i 4 i imprimeixo:");
        for (int i = 0; i < matriu.length; ++i) {
            auxiliar = matriu[i][0];
            matriu[i][0] = matriu[i][3];
            matriu[i][3] = auxiliar;  
        }  
        visualitzaMatriu(matriu);
        
    }  
}
