/*
1. Desenvolupar una classe anomenada Punt que:
• Tinga dos atributs priva't de tipus double.
• Tinga un constructor amb dos paràmetres de tipus double que inicialitze els dos atributs.
• Tinga un constructor per defecte (sense paràmetres) que inicialitze els dos atributs al valor que es vulga.
• Tinga un getvalor per a cadascun dels atributs.
• Tinga un mètode calcularDistancia que rep un paràmetre de tipus Punt i que retorna un double.
 */
package practica1;

public class Punt {
    private double x,y;
    
    public Punt(double x, double y) {
        this.x = x;
        this.y = y; 
    }
    
    public Punt() {
        x = 0.0;
        y = 0.0;
    }
    
    public double getX() {
        return x;
    } 
    
    public double getY() {
        return y;
    }
    
    public double calcularDistancia(Punt p) {
        //return Math.sqrt((x - p.x)*(x - p.x) + (y - p.y)*(y - p.y));  //TAMBÉ ES POT FER AIXÍ.
        return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
    }
    
    
    public String imprimirPuntBonic() {
        return "<"+String.valueOf(x)+","+String.valueOf(y)+">";
    }

    
    
}
