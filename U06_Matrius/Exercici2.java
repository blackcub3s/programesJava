/*
2. Introdueix una matriu de 7 x 5 i visualitza-la. A continuació:
• Crea una nova matriu de 7 x 7 on les cinc primeres columnes siguen les 
  de la matriu origen
• Guarda en la columna sisena la suma de les cinc anteriors
• Guarda en la columna setena la mitjana de les cinc primeres

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
public class Exercici2 {
    
        
    //IMPRIMEIX UNA MATRIU DE FORMA VISUALMENT ACCEPTABLE
    public static void visualitzaMatriu(int[][] matriu) {
        System.out.print("[");
        System.out.println(Arrays.toString(matriu[0])+","); 
        for (int i = 1; i < matriu.length - 1; ++i) {
            System.out.println(" "+Arrays.toString(matriu[i])+","); 
        }
        System.out.println(" "+Arrays.toString(matriu[matriu.length - 1])+"]");         
    }

    //PRE: Una matriu (amb mateixa longitud de files sempre)
    //POST: matriu té dins nombres aleatoris (passant cap al main per referència)
    public static void introduirNombresAleatoris(int[][] m) {
        Random rd = new Random();
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[0].length; ++j) { 
                m[i][j] = rd.nextInt(10); //aleatoris de 0 a 9 (enters)
            }
        }
    }
    
    //PRE: m1 i m2 (passades per referència)
    //POST: m2 tindrà les 5 primeres columnes de la m1 en les 5 primeres columnes.
    public static void emplenaSegonaMatriu_copiaFiles(int[][] m1, int[][] m2) {
        for (int i = 0; i < m1.length; ++i) {
            for (int j = 0; j < m1[0].length; ++j) {//podies posar m2.length també
                m2[i][j] = m1[i][j]; //transposem la part demanada de m1 en m2
            } 
        } 
    }
    
    
    
    //PRE: m1 i m2 (passades per referència)
    //POST: - m2 tindrà la columna 7 emplenada de la suma de les cinc cols anteriors
    //        (suma de fila)
    //      - m2 tindrà la columna 7 emplenada de la mitjana aritmètica de les
    //        cinc columnes anteriors (mitjanes de fila), truncada a l'enter mes pròxim  
    public static void emplenaSegonaMatriu_sumaImitjana(int[][] m1, int[][] m2) {
        for (int i = 0; i < m1.length; ++i) {
            int suma = 0;
            for (int nombre: m1[i]) {
                suma += nombre; 
            }
            m2[i][5] = suma; //empleno columna 6
            m2[i][6] = suma/5; //empleno columna 7 (mitjana truncada, compte)
        }
    }    
    
    
    
    public static void main(String[] args) {
        int[][] matriu = new int[7][5]; //matrius de 7 files, 5 columnes
        
        introduirNombresAleatoris(matriu);
        
        System.out.println("Matriu1: 7 files i 5 columnes (Amb nres aleatoris):");
        visualitzaMatriu(matriu);
        
        //DECLARO UNA NOVA MATRIU (QUADRADA, DE 7 FILES I 7 COLUMNES)
        int[][] matriu2 = new int[7][7];
        
        //EMPLENO LA SEGONA MATRIU
        emplenaSegonaMatriu_copiaFiles(matriu, matriu2);
        emplenaSegonaMatriu_sumaImitjana(matriu,matriu2);
        
        //VISUALITZO SEGONA MATRIU
        System.out.println("\nMatriu2 demanada, amb 7fils i 7cols:");
        visualitzaMatriu(matriu2);
            
    }
}
