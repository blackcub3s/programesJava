package practica1;

/** @author santi*/
public class Practica_1 {
    public static void main(String[] args) {
        
        
        //INSTANCIO I INICIALITZO DOS PUNTS I MOSTRO LA DISTANCIA ENTRE ELS DOS
        Punt p = new Punt(0,0);
        Punt p2 = new Punt(3,4);
        Punt p3 = new Punt(1,-1);
        Punt p4 = new Punt(1,0.5);
        Punt p5 = new Punt(1.5,0.5);
        Punt p6_incentre_t = new Punt(1,0.414213); //sera l'incentre del triangle t que farem després
        
        
        //INSTANCIO OBJECTES DE LA CLASSE CERCLE I MOSTRO CALCUL D'ÀREA, PERÍMETRE I DISTANCIA
        Cercle c = new Cercle(); //cercle de radi 0.
        Cercle c2 = new Cercle(p, 3.4);  //passo punt P i radi
        Cercle c3 = new Cercle(1,2,3.4); //passo coordenades x,y del punt i el radi
        
        System.out.println("Cercle c té àrea: "+c.calcularArea()+" u^2");
        System.out.println("Cercle c3 té àrea: "+c3.calcularArea()+" u^2");
        System.out.println("Cercle c2 té perimetre: "+c2.calcularPerimetre()+" U");
        System.out.println("Cercle c2 es separa de punt P per : "+c2.calcularDistancia(p)+" U");
        System.out.println("Cercle c2 es separa de punt P per : "+c2.calcularDistancia(p2)+" U");
        
        //INSTANCIO CLASSE TRIANGLE I MOSTRO AREA, PERIMETRE I DISTANCIA A UN DELS PUNTS CREATS AL PRINCIPI.
        Triangle t = new Triangle(); //el triangle de vèrtexos (0,0), (1,1) i (2,0).
        
        System.out.println("Àrea del triangle t: "+t.calcularArea()+" U^2"); //dona correcte (ja que t té base 1 i altura 1 i és 0.5)
                
        System.out.println("-----------------------------------------------");
        System.out.println("----TRIANGLE t TÉ VÈRTEXS (0,0),(1,1),(2,0)----");
        System.out.println("-----------------------------------------------");
        System.out.println("Distancia del punt p(0,0) al triangle t ha de ser 0: "+t.calcularDistancia(p)+" U"); //ha de donar 0 perquè està damunt d'un catet del triangle.
        System.out.println("Distancia del punt p2(3,4) al triangle t ha de ser 3,60555...: "+t.calcularDistancia(p2)+" U"); 
        System.out.println("Distancia del punt p3(1,-1) al triangle t ha de ser 1: "+t.calcularDistancia(p3)+" U"); //ha de donar 1
        System.out.println("Distancia del punt p4(1,0.5) intern al triangle t ha de ser 0,35: "+t.calcularDistancia(p4)+" U"); //ha de donar exactament 0.5 (
        System.out.println("Distancia del punt p5(1.5,0.5) al triangle t ha de ser 0 -ja que cau damunt un catet-: "+t.calcularDistancia(p5)+" U");
        System.out.println("Distancia del punt p6_incentre_t(1,0.414213) al triangle t ha de ser igual\n"
                + "per als tres catets i en aquest cas serà idèntica a la de la coordenada Y de l'incentre, que és 0.414213\n"
                + "(fes debug=true) dins funcio distancia per veure-ho: "+t.calcularDistancia(p6_incentre_t)+" U");
        System.out.println("Calcular perimetre: "+t.calcularPerimetre()+" U"); //donarà 1 + 1 + sqrt(1) = 3,4142...
        

        //provo una funció per imprimir punts
        System.out.println("PROVO FUNCIÓ PER IMPRIMIR PUNTS --> Vertex 1: "+t.getVertex1().imprimirPuntBonic());
        
        
    }
}
