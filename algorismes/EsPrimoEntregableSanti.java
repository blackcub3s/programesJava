
/**
 * @author Santi Sanchez Sans
 */
import java.util.Scanner;
public class EsPrimoEntregableSanti {

	//PRECONDICIO: n>=1 i n<=1000, ambdos enters
	//POSTCONDICIO: retorna si numero es primer (ho fa amb una implementacio eficient fins a arrel de n).
	public static boolean esPrimo(int numero) {
        if (numero == 1)
            return false;
        for (int i = 2; i <= Math.sqrt(numero); ++i) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
	}

	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		System.out.println("\nIntrodueix un valor enter positiu:");
		int n = lector.nextInt(); 
            
        //nombre primer 999999937 (la solucio no eficient que itera per tots els divisors tarda en trobar lo un 2 segons).
        //Amb la solucio que itera fins a arrel de n tarda menys de 0.25 segons.
		System.out.println(esPrimo(n));
	}
}
   