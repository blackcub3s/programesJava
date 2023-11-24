/*
    2. Desenvolupar una classe anomenada Cercle que:
        • Tinga dos atributs priva't:
            o El centre del cercle: Punt
            o El radi del cercle : double
        • Tinga un constructor amb dos paràmetres de tipus Punt i double que inicialitze els dos atributs.
        • Tinga un constructor per defecte (sense paràmetres) que inicialitze els dos atributs al valor que es vulga.
        • Tinga un constructor amb tres paràmetres de tipus double que inicialitze els dos atributs.
        • Tinga un getvalor per a cadascun dels atributs.
        • Tinga un mètode calcularDistancia que rep un paràmetre de tipus Punt i que retorna un double.
        • Tinga un mètode calcularArea que no rep cap paràmetre i retorna un double.
        • Tinga un mètode calcularPerimetre que no rep cap paràmetre i retorna un double.
 */
package practica1;

public class Cercle {
    private Punt centre;
    private double radi;
    
    public Cercle() {
        centre = new Punt(); //fem un centre per defecte a 0,0
        radi = 0.0;
    }
    
    public Cercle(Punt centre, double radi) {
        this.centre = centre;
        this.radi = radi;
    }
    
    public Cercle(double xCentre, double yCentre, double radi) {
        centre = new Punt(xCentre, yCentre);
        this.radi = radi;
    }
    
    public double getRadi() {
        return radi;
    }
    
    public Punt getCentre() {
        return centre;
    }
    
    //no es demana a l'enunciat pero fem un setter per canviar el centre
    public void setCentre(Punt centre) {
        this.centre = centre;
    }
    
    public double calcularDistancia(Punt p) {
        double distanciaCentrePunt = centre.calcularDistancia(p);
        if (distanciaCentrePunt < radi) {
            return 0; //el punt és dins del cercle
        }
        return distanciaCentrePunt - radi;
    }
    
    public double calcularArea(){
        return Math.PI*Math.pow(radi,2);
    }
    
    public double calcularPerimetre() {
        return 2*Math.PI*radi;
    }
    
    
    
    
}
