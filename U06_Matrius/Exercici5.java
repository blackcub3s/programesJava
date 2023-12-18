/*
5. Crea un programa per a gestionar les notes d'un grup escolar. El grup 
es compon de 20 alumnes i té 3 mòduls.
El programa ha de permetre:
• Introduir les notes
• Calcular la nota mitjana de cada alumne
• Calcular la màxima nota de cada mòdul
• Calcular la nota mitjana per mòdul i quants alumnes la sobrepassen
 */
package Exercicis3;

import java.util.Random;
import java.util.Scanner;

/**
 * @author santi
 * @fecha 14 dic. 2023
 */
public class Exercici5 {

    // guardo variable global (0 SIGNIFICA QUE NO HI HA NINGU AFEGIT)
    //LA FAIG STATIC PER PODER-LA CRIDAR DES DEL MAIN I DESDE ELS METODES ESTATICS
    //NO PERQUÈ VULGUI CONSIDERAR-LA UN ATRIBUT ESTATIC (NO FAIG SERVIR EL PARADIGMA POO EN AQUEST EXERCICI)
    private static int nreEstudiantsAfegits = 0; //O ALUMNES AFEGITS AL PRINCIPI
    
    
    //PRE: la desviació típica (desviació estandard) i la mitjana que vull que
    //     tingui la distribució normal de nombres que genera la funció.
    //POST: un nombre que surt d'una distribució normal amb paràmetres 
    //       "mitjana" (μ) i desviació estandard "desvEst" (σ).
    //INFORMACIÓ:Les distribucions de notes en una classe, l'estatura, la intel·ligència,
    //són fenòmens naturals que es poden modelitzar segons una distribucio normal.
    //en el paquet random tenim una funció que permet obtenir dades segons
    //una distribució normal de mitjana 0 i desviació típica 1. Per transformar
    //aquesta distribució podem multiplicar les dades per la desviació estàndar que volem i 
    //sumar la mitjana que volem que tingui la distribució resultant.
    //
    // (podeu veure https://stackoverflow.com/questions/6011943/java-normal-distribution)
    private static double generaNotaDistribucioNormal(double mitjana, double desvEst) {
        Random rd = new Random();
        double nota = rd.nextGaussian()*desvEst + mitjana;
        if (nota <= 10 && nota >= 0)
            return nota;
        else if (nota > 10)
            return 10.0;
        return 0.0; //cas nota inferior a 0
    }


    public static void imprimirNoms(String[] nomsAlumnes) {
        if (nreEstudiantsAfegits > 0) {
            generaSaltsLinia(20);
            System.out.println("Els noms dels estudiants del grup classe són:\n");
            for (int i = 0; i < nreEstudiantsAfegits; ++i) {
                System.out.println(" - "+nomsAlumnes[i]);
            }
        }
        else {
            generaSaltsLinia(20);
            System.out.println("-- NO HI HA ESTUDIANTS PER VISUALITZAR NOMS -- ");
        }
    }
    /*PRE: matriu per introduir les notes, i matriu per introduir noms Alumnes.
      POST: notesGrups és ple de dades generades segons una distribució normal 
            que pot ser de mitjana 8 i desviació típica 2 (bons estudiants)
            o de mitjana 4 i desviació tipica 2. De forma aleatoria escollim si
            un estudiant té puntuacions estables i elevades (bon estudiant)
            o bé si sol treure puntuacions amb més variabilitat i més baixes,
            per després aplicar una distribució de puntuacions (μ = 8.5, σ = 1.5) 
            o bé l'altra (μ = 3 || σ = 2) per 
            generar consistència en les qualificacions de cada un d'ells
    */  
    public static void generaNotes_i_mitjana(double[][] notesGrup) {
        int nAlumnes = notesGrup.length; //en el nostre cas sera 20
        int nAssig = notesGrup[0].length; //en el nostre cas sera 3
        
        Random rd = new Random();
        boolean bonEstudiant;
        for (int i = nreEstudiantsAfegits; i < nAlumnes; ++i) {
            bonEstudiant = rd.nextBoolean();
            for (int j = 0; j < nAssig; ++j) {
                if (bonEstudiant)   
                    notesGrup[i][j] = generaNotaDistribucioNormal(8.5,1.5);// μ = 8.5 || σ = 1.5
                else
                    notesGrup[i][j] = generaNotaDistribucioNormal(3,2);// μ = 3 || σ = 2
            }
        }
        nreEstudiantsAfegits = notesGrup.length;
    }


    
    //funcio per imprimir la matriu de doubles amb les notes(els imprimeix a 2 decimals sempre)
    //calcula la mitjana aritmetica per cada alumne i els noms dels alumnes, afegint dos columnes
    //verticals en el print
    public static void visualitzaMatriuEstudiants(double[][] matriu, String[] nomsAlumnes) {
        if (nreEstudiantsAfegits > 0) {
            System.out.println("-------------- NOTES ESTUDIANTS --------------");
            System.out.println("ASS1    ASS2    ASS3    AVG    NOM I COGNOMS");
            System.out.println("----------------------------------------------");
            
            for (int i = 0; i < nreEstudiantsAfegits; ++i) {
                double avg = 0;
                for (double valor : matriu[i]) {
                    System.out.printf("%.2f\t", valor); //imprimeix les 3 notes estudiant
                    avg += valor;
                }
                //calcula i imprimeix mitjana de les 3 notes de l'estudiant i el seu nom
                avg = avg/3; 
                System.out.printf("%.2f\t", avg);
                System.out.println(nomsAlumnes[i]);
            }
        } 
        else {
            generaSaltsLinia(20);
            System.out.println("-- NO HI HA ESTUDIANTS PER VISUALITZAR DADES -- ");
        }
    }

    
    
    
    //PRE: noms Alumnes i nreEstudiantsAfegits (com a atribut variable global)
    //POST: arrau d'stringg nomsAlumnes amb noms aleatoris passa per referència 
    //      al main emplenada amb els buits amb noms i cognoms generats aleatoriament.
    public static void generaNomsEstudiantsAleatoriament(String[] nomsAlumnes) {
        Random rd = new Random();
        final String[] noms = {"Robert","Carles","Jesus","John","Karen","Joel",
            "Carlos","Pedro","Isaac","Josep","Albert","Hector","Silvia",
            "Ana","Amagoia","Mavis","Melisa","Jennifer","Cecilia","Adri",
            "Karina","Lorena","Diego","Marcello","Andra","Cristina","Cristian"};
        final String[] cognoms = {"Sanchez","Puigdemont","Alvarado","Roma",
            "Pineda","Ayelen","Llopis","Palausabulla", "Santos","Iberico",
           "Uson","Delso","Messegue","Sans","Prieto","Fernandez","Hernandez",
           "Artega","Prieto","Canut"};
        
                        
        for (int i = nreEstudiantsAfegits; i < nomsAlumnes.length; ++i) {
            nomsAlumnes[i] = noms[rd.nextInt(noms.length)]+
                    " "+cognoms[rd.nextInt(cognoms.length)]+" "+
                    cognoms[rd.nextInt(cognoms.length)];
        }

    }
    
    

    
    //PRE: matriu amb les notes dels estudiants (columnes nota assig, files notes estudiant)
    //POST: Un array amb 3 elements, mitjana de notes per cada modul (avgModuls)
    public static double[] fesMitjanaPerModuls(double[][] m) {
        double[] avgModuls = new double[3];
        if (nreEstudiantsAfegits > 0) {        
            for (int i = 0; i < nreEstudiantsAfegits; ++i) {
                avgModuls[0] += m[i][0];
                avgModuls[1] += m[i][1];
                avgModuls[2] += m[i][2];
            }
            avgModuls[0] /= nreEstudiantsAfegits;
            avgModuls[1] /= nreEstudiantsAfegits;
            avgModuls[2] /= nreEstudiantsAfegits;
            
        }
        return avgModuls;        
    }
    
    
    //PRE: matriu amb notes estudiants (notesGrup), vector amb mitjanes per modul
    //POST: imprimeix notes maximes per modul i nombre d'estudiants que superen la mitjana del cada modul
    public static void notesMaximesIcomparacioEnMitjana(double[][] notesGrup, double[] mitjanaPerModuls) {
        if (nreEstudiantsAfegits > 0) {
            
            //contindran notes màximes de les assignatures
            double maxAss1 = -1;
            double maxAss2 = -1;
            double maxAss3 = -1;
            
            //nombre estudiants amb notes superiors a la mitjana 
            //de l'assignatura 1, 2 i 3 (de forma respectiva)
            int nEst_notSup_avgAss1 = 0; 
            int nEst_notSup_avgAss2 = 0;
            int nEst_notSup_avgAss3 = 0;
            for (int i = 0; i < nreEstudiantsAfegits; ++i) {
                maxAss1 = Math.max(maxAss1, notesGrup[i][0]);
                maxAss2 = Math.max(maxAss2, notesGrup[i][1]);
                maxAss3 = Math.max(maxAss3, notesGrup[i][2]);
            
            //miro la nota de cada estudiant en cada assignatura i la comparo
            //amb la mitjana d'aquesta assignatura contant els que la superen
            if (notesGrup[i][0] > mitjanaPerModuls[0])
                nEst_notSup_avgAss1 += 1;
            
            if (notesGrup[i][1] > mitjanaPerModuls[1])
                nEst_notSup_avgAss2 += 1;
            
            if (notesGrup[i][2] > mitjanaPerModuls[2])
                nEst_notSup_avgAss3 += 1;
            }
            
            
            System.out.println("\nASS1    ASS2    ASS3 ");

            System.out.printf("%.2f\t",maxAss1);
            System.out.printf("%.2f\t",maxAss2);
            System.out.printf("%.2f\t",maxAss3);
            System.out.print("NOTES MAXIMES\n");
            
            System.out.printf("%-4d\t",nEst_notSup_avgAss1);
            System.out.printf("%-4d\t",nEst_notSup_avgAss2);
            System.out.printf("%-4d\t",nEst_notSup_avgAss3); 
            System.out.print("NOMBRE D'ESTUDIANTS AMB NOTA SUPERIOR A LA MITJANA DE L'ASSIGNATURA*\n");
            
            
            System.out.printf("%.2f\t", mitjanaPerModuls[0]);
            System.out.printf("%.2f\t", mitjanaPerModuls[1]);
            System.out.printf("%.2f\t", mitjanaPerModuls[2]);
            System.out.print("NOTA MITJANA\n");
            
        }
    }
    
    public static void afegeixAlumneInotes(double[][] notesGrup, String[] nomsAlumnes) {
        if (nreEstudiantsAfegits < notesGrup.length) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nom de l'alumne: ");
            String nomAlumne = sc.nextLine();
            nomsAlumnes[nreEstudiantsAfegits] = nomAlumne;
            System.out.println("");
            
            System.out.print("Introduiu Nota Assignatura 1: ");
            notesGrup[nreEstudiantsAfegits][0] = sc.nextDouble();
            System.out.println("");
            
            System.out.print("Introduiu Nota Assignatura 2: ");
            notesGrup[nreEstudiantsAfegits][1] = sc.nextDouble();
            System.out.println("");
            
            System.out.print("Introduiu Nota Assignatura 3: ");
            notesGrup[nreEstudiantsAfegits][2] = sc.nextDouble();
            System.out.println("");
            
            nreEstudiantsAfegits += 1;
            
            generaSaltsLinia(10);
            
            System.out.println("--------------------------------------------------");
            System.out.println(nomAlumne+" ha sigut afegit al sistema!");
            System.out.println("--------------------------------------------------");
            
        }    
        else {
            generaSaltsLinia(20);
            System.out.println("NO ES PODEN AFEGIR MÉS ESTUDIANTS!");
        }    
    }
    
    
    
    //NO HE TROBAT LA MANERAA D'ESBORRAR LA PANTALLA EN JAVA AIXÍ QUE
    //GENERO L'EFECTE D'ESBORRAT POSANT MÚLTIPLES SALTS DE LÍNIA, QUAN CONVÉ
    //PERQUÈ EL CONTINGUT ANTIC QUEDI PERDUT ENDARRERA
    public static void generaSaltsLinia(int n) {
        for (int i = 0; i < n; ++i) {
            System.out.print("\n");
        }
    }
    
    //esperem input d'usuari, que és la senyal que ens farà tornar al menú del programa
    public static void pausaVisualitzacio() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        System.out.println("---------------------------------------------");
        System.out.println("---- PREMEU UNA TECLA PER TORNAR AL MENÚ ----");
        System.out.println("---------------------------------------------");
        
        sc.nextLine();
    }
    

    public static void canviaNotaAssignaturaAestudiant(double[][] notesGrup, String[] nomsAlumnes) {
        Scanner sc = new Scanner(System.in);
        if (nreEstudiantsAfegits == 0) {
            generaSaltsLinia(20);
            System.out.println("NO HI HA ESTUDIANTS AFEGITS!");
        } 
        else {
            System.out.println("Introdueix el nom de l'estudiant al que vols canviar la nota:");
            String nomBuscat = sc.nextLine();
        
            //busquem l'estudiant
            boolean trobat = false;
        
            for (int i = 0; i < nreEstudiantsAfegits; ++i) {
                if (nomsAlumnes[i].equals(nomBuscat)) {
                    trobat = true;
                    System.out.println("Quina assignatura vols actualitzar (ASS1, ASS2, ASS3):");
                    String strEntrada = sc.nextLine();
                    if (strEntrada.equals("ASS1") || strEntrada.equals("ass1")) {
                        System.out.println("Introdueix la nota: ");
                        notesGrup[i][0] = sc.nextDouble();
                        sc.nextLine(); //menjem el salt de linia vestigial
                        generaSaltsLinia(10);
                        System.out.println("NOTA l'Assignatura 1 CANVIADA per a l'estudiant "+nomsAlumnes[i]);
                    }
                    else if (strEntrada.equals("ASS2") || strEntrada.equals("ass2")){
                        System.out.println("Introdueix la nota: ");
                        notesGrup[i][1] = sc.nextDouble();
                        sc.nextLine(); //menjem el salt de linia vestigial
                        generaSaltsLinia(10);
                        System.out.println("NOTA de l'Assignatura 2 CANVIADA per a l'estudiant "+nomsAlumnes[i]);                    
                    }
                    else if (strEntrada.equals("ASS3") || strEntrada.equals("ass3")){
                        System.out.println("Introdueix la nota: ");
                        notesGrup[i][2] = sc.nextDouble();
                        sc.nextLine(); //menjem el salt de linia vestigial
                        generaSaltsLinia(10);
                        System.out.println("NOTA de l'Assignatura 3 CANVIADA per a l'estudiant "+nomsAlumnes[i]);                    
                    }                
                    break;
                }
            }

            if (!trobat) {
                generaSaltsLinia(20);
                System.out.println("NO HI HA CAP ESTUDIANT AMB AQUEST NOM!");    
            }

        }
    }
    
    //PRE: matriu estudiants (notesGrup) el llistat de noms (nomsAlumnes) nreEstudiantsAfegits
    //     amb valor superior o igual a 0 (s'agafa com a varialbe global)
    //POST: Si hi ha alumnes afegits al sistema, ara notesGrup i nomsAlumnes tenen 
    //      l'alumne buscat eliminat i nreEstudiantsAfegits té una unitat menys.
    //      Si no n'hi ha la funcio emet missatge d'error.
    public static void eliminaAlumne(double[][] notesGrup, String[] nomsAlumnes) {
        if (nreEstudiantsAfegits > 0) {
            Scanner sc = new Scanner(System.in);
            System.out.printf("Escriu el nom de l'alumne a esborrar: ");
            String alumneAEsborrar = sc.nextLine();
            for (int i = 0; i < nreEstudiantsAfegits; ++i) {
                if (nomsAlumnes[i].equals(alumneAEsborrar)) {
                    
                    //PER ESBORRAR UN ALUMNE QUEDARIA UN ESPAI. ELIMINEM L'ESPAI
                    //DELS NOMS D'ALUMNES CORRENT TOTS ELS NOMS CAP A L'ESQUERRA
                    for (int j = i; j < nreEstudiantsAfegits - 1; ++j) {
                        nomsAlumnes[j] = nomsAlumnes[j+1];
                    }
                    nomsAlumnes[nreEstudiantsAfegits - 1] = " "; //L'ULTIM DE LA LLISTA L'ELIMINEM
                    
                    //ANÀLOGAMENT A LA MATRIU notesGrup ES GENERARIA UNA FILA EN BLANC
                    //AIXÍ QUE CORREM TOTES LES FILES CAP AMUNT
                    for (int j = i; j < nreEstudiantsAfegits - 1; ++j) {
                        notesGrup[j] = notesGrup[j+1];
                    }
                    notesGrup[nreEstudiantsAfegits - 1] = new double[3]; //L'ULTIM DE LA LLISTA ESBORREM LES NOTES (QUEDARA A 0)                    
                    
                    //FINALMENT REDUIM EN UNA UNITAT LA VARIABLE GLOBAL QUE GUARDA 
                    //EL NOMBRE D'ALUMNES
                    nreEstudiantsAfegits = nreEstudiantsAfegits - 1; 
                    break;
                }
            }
        }
        else {
            generaSaltsLinia(20);
            System.out.println("NO S'HA ELIMINAT A NINGÚ. NO HI HA ESTUDIANTS AFEGITS!");
        }
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        
        
        //Enunciat demana 20 files (alumnes) i 3 columnes (moduls).
        //Es pot variar el nombre d'alumnes però no el de moduls.
        
        Scanner sc = new Scanner(System.in);
        System.out.printf("Introdueix quants alumnes vols gestionar:");
        int nAlumnes = sc.nextInt();
        sc.nextLine(); //agafem el salt de linia 
        
        double[][] notesGrup = new double[nAlumnes][3]; 
        double[] mitjanaPerModuls = new double[3];
        String[] nomsAlumnes = new String[nAlumnes];
        
        char entradaTeclat;
        boolean sortir = false;
        while (!sortir) {
            System.out.println("\n\n\n\n\n");
            System.out.println("CONSULTA DADES:");
            System.out.println("   A. Mostra estudiants, notes i estadístiques.");
            System.out.println("   B. Mostra noms.");
            System.out.println("MODIFICA DADES:");
            System.out.println("   C. Afegeix alumne amb notes.");
            System.out.println("   D. Genera notes i noms aleatoris.");
            System.out.println("   E. Canvia nota.");
            System.out.println("   F. Elimina alumne.");
            System.out.println("FINALITZAR PROGRAMA:");
            System.out.println("   Q. SORTIR (QUIT)");
            System.out.println("");
            System.out.println("-------------------------");
            System.out.println("--- Escull una opció: ---");
            System.out.println("-------------------------");
            
            //si entrem enter dona error aixi que forcem que entri un 
            //caracter que no faci res al menu
            try {
                entradaTeclat = sc.nextLine().charAt(0);
                entradaTeclat = Character.toUpperCase(entradaTeclat);
            } catch (StringIndexOutOfBoundsException e) {
                entradaTeclat = 'Z';
            }

            switch (entradaTeclat) {
                case 'A':
                    generaSaltsLinia(5);                    
                    visualitzaMatriuEstudiants(notesGrup, nomsAlumnes);
                    mitjanaPerModuls = fesMitjanaPerModuls(notesGrup);
                    notesMaximesIcomparacioEnMitjana(notesGrup, mitjanaPerModuls);
                    pausaVisualitzacio();
                    break;
                case 'B':
                    imprimirNoms(nomsAlumnes);
                    pausaVisualitzacio(); //TO DO
                    break;
                case 'C':
                    afegeixAlumneInotes(notesGrup, nomsAlumnes);
                    pausaVisualitzacio();
                    break;                    
                case 'D':
                    boolean esGenerenDades = nomsAlumnes.length > nreEstudiantsAfegits;
                    generaNomsEstudiantsAleatoriament(nomsAlumnes); //AQUESTA FUNCIO PRIMER
                    generaNotes_i_mitjana(notesGrup);
                    generaSaltsLinia(10);  
                    if (esGenerenDades) 
                        System.out.println("Dades Generades!");
                    else    
                        System.out.println("el llistat de notes ja és ple!");
                    pausaVisualitzacio();
                    break;
                case 'E':
                    canviaNotaAssignaturaAestudiant(notesGrup, nomsAlumnes);
                    pausaVisualitzacio();
                    break;
                    
                case 'F':
                    eliminaAlumne(notesGrup, nomsAlumnes);
                    pausaVisualitzacio();
                    break;
                case 'Q':
                    //BOOLEÀ QUE PERMET SORTIR DEL BUCLE (MENÚ D'OPCIONS)
                    sortir = true;
                    
                    generaSaltsLinia(40);
                    System.out.println("---------------------");
                    System.out.println("-----  Adéu :)! -----");
                    System.out.println("---------------------");
                    break;
                default:
                    generaSaltsLinia(30);
                    System.out.println("---------------------");
                    System.out.println("--- OpcioInvàlida ---");
                    System.out.println("---------------------");
                    pausaVisualitzacio();       
            }
        }
    }
}
