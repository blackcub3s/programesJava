/*
Desenvolupar una classe anomenada Triangle que:
• Tinga tres atributs priva't de tipus Punt.
• Tinga un constructor amb tres paràmetres de tipus Punt que inicialitze els tres atributs.
• Tinga un constructes per defecte (sense paràmetres) que inicialitze els tres atributs al valor que es vulga.
• Tinga un constructor amb sis paràmetres de tipus double que inicialitze els tres atributs.
• Tinga un getvalor per a cadascun dels atributs.
• Tinga un mètode calcularDistancia que rep un paràmetre de tipus Punt i que retorna un double.
• Tinga un mètode calcularArea que no rep cap paràmetre i retorna un double.
• Tinga un mètode calcularPerimetre que no rep cap paràmetre i retorna un double.
 */
package practica1;

public class Triangle {
    
    private Punt p1, p2, p3;
    
    //El constructor per defecte crea un triangle rectangle de vertexs a origen (0,0), (1,0) i (0,1).
    public Triangle() {
        p1 = new Punt(); 
        p2 = new Punt(1,1);
        p3 = new Punt(2,0);
    }
    
    public Triangle(Punt p1, Punt p2, Punt p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public Triangle(double p1_x, double p1_y, double p2_x, double p2_y, double p3_x, double p3_y) {
        p1 = new Punt(p1_x, p1_y);
        p2 = new Punt(p2_x, p2_y);
        p3 = new Punt(p3_x, p3_y);
    }
    
    //GETTERS
    
    public Punt getVertex1() {
        return p1;
    }
    
    public Punt getVertex2() {
        return p2;
    }
    
    public Punt getVertex3() {
        return p3;
    }    
    
    //---------------// 
    //MÈTODES PRIVATE
    //---------------// 
    
    //pre: 2 doubles
    //post: el menor dels dos.
    private static double min2(double a, double b) {
        if (a<b)
            return a;
        return b;
    }
    
    //PRE: components d'un vector V (Vx,Vy) i un vector W (Wx, Wy)
    //POST: retorna el producte escalar d'ambdós vectors
    private static double prodEscalar(double Vx, double Vy, double Wx, double Wy) {
        return Vx*Wx + Vy*Wy;
    }
    
    //PRE: components d'un vector V (Vx,Vy) 
    //POST: retorna el modul del vector V (la seva longitud)
    private static double modul(double Vx, double Vy) {
        return Math.sqrt(Vx*Vx + Vy*Vy);
    }    
    
    //---------------// 
    //MÈTODES PER FER LES 
    //OPERACIONS DEMANADES
    //---------------// 
    
    //PER CALCULAR L'ÀREA FEM SERVIR LA FÓRMULA D'HERON JA QUE ENS INTERESSA
    //TROBAR-LA PER A QUALSEVOL TRIANGLE (RECTANGLE, EQUILATER, ESCALÈ)
    // https://es.wikipedia.org/wiki/F%C3%B3rmula_de_Her%C3%B3n
    public double calcularArea() {
        //calculem els catets del triangle;
        double a = p1.calcularDistancia(p2);
        double b = p2.calcularDistancia(p3);
        double c = p3.calcularDistancia(p1);
        
        //trobem el semiperimetre s i retornem l'àrea del triangle amb fórmula d'heron
        double s = (a + b + c)/2;
        return Math.sqrt(s*(s - a)*(s - b)*(s - c));
    }
   
    public double calcularPerimetre() {
        return p1.calcularDistancia(p2) + p2.calcularDistancia(p3) + p3.calcularDistancia(p1);
    }    
    
    //FUNCIONAMENT: La funció distanciaPuntAcatet troba la distància més petita 
    //              d'un punt p a un segment definit per dos punts P1 i P2 
    //              (i.e. distància més petita
    //              a un catet que hi ha entre P1 i P2, que podrien ser dos
    //              vèrtexs qualsevols d'un triangle)
    //
    //PRE: tres punts: P1, P2 i p i un boolea "debugging". P1 i P2 son dos punts que NO estaran 
    //     alineats verticalment, ja que hem definit la recta com una funcio
    //     i les funcions no poden tenir varis valors de f(x) per un mateix 
    //     valor de x (si la recta fos "vertical" resultaria en un pendent
    //     inifinit, cosa que donaria una divisió per zero en el pendent m i
    //      donaria error). debugging serà true si volem veure l'impressió de les
    //      components de tots els vectors utilitzats per tots els catets i els cosinus
    //      calculats.
    //POST: Distància més petita del punt p al segment definit pels punts P1 
    //      i P2 (sigui per projecció ortogonal o per distància a punt P1 o P2,
    //      el que minimitzi la distancia).
    //
    //EXPLICACIÓ:
    //              La fórmula de projecció d'un punt a una recta -projecció 
    //              ortogonal- serveix per trobar la distància de punt p a la recta
    //              que conté el segment definit per P1 i P2. La mencionada projecció 
    //              ens servirà per trobar la distància de p al segment, si i només si,
    //              l'angle definit pels punts p, P1 i P2 (alfa), per una banda,
    //              i l'angle definit pels punts P1, P2, p (beta), per l'altra, són
    //              tots dos compresos entre 0 i 90 graus (ambdós angles inclosos)
    //              cosa que es donarà quan els cosinus dels dos angles
    //              siguin comprèsos en l'interval tancat [0,1].
    
    //              En cas contrari, si un dels dos angles mencionats
    //              fos SUPERIOR a 90 graus (cosinus negatiu) 
    //              voldria dir que per trobar la distància del punt p al segment
    //              P1 P2 hem de fer la distància del punt p al punt P1 o P2 
    //              més proper ja que, en aquest cas específic, si apliquessim la 
    //              la projecció ortogonal de p a la recta P1 P2 no obtindriem pas
    //              la distancia al segment sino una distancia més petita de la 
    //              real, que seria la projecció ortogonal a la recta que conté
    //              el segment, però NO al segment.
    //              
    /*
        CÀLCUL DE b --> podem calcular l'ordenada a l'origen b (punt de tall de la recta amb eix ordenades) amb l'equacio
        punt pendent: y - yo = m (x - x0). Ho podem fer ja que m es coneguda, l'acabem de calcular;
        x0 i y0 també (són les coordenades de qualsevol dels punts P1 o P2) i X valdrà zero.
        la y aleshores queda y = -m*x0 + y0 i com que per a cas x = 0 tenim y = b podem escriure:
    
        CÀLCUL DE m --> podem calcular el pendent m de la recta trobant el nombre d'unitats verticals
        que canvia la funció per cada unitat horitzontal (i.e. és la tangent de l'angle definit per la recta
        i l'horitzontal.
    
        CÀLCUL DE la distancia de p a la recta r definida per P1 i P2. --> apliquem la fórmula de càlcul de distancia
        d'un punt a una recta (https://es.wikipedia.org/wiki/Distancia_de_un_punto_a_una_recta). La formula utilitzada
        té noms diferents per acomodar la nomenclatura usada per pendent i el punt d(p,R) = |m*Px - Py*b| / sqrt(m^2 + 1)   
    */
    private static double distanciaPuntAcatet(Punt P1, Punt P2, Punt p, boolean debugging) {
        //CARACTERITZEM LA RECTA ON PERTANY EL CATET DEL TRIANGLE
        //QUE VA DEL VERTEX P1 A P2 
        double m = (P1.getY() - P2.getY())/(P1.getX() - P2.getX()); //pendent de la recta r que passa per P1 i P2.
        double b = -m*P1.getX() + P1.getY(); //ordenada a l'origen
        
        //BUSQUEM VECTOR P1 A P2 (UN VECTOR DIRECTOR DE LA RECTA QUE CONTÉ EL CATET)
        double Vi_P1P2 = P2.getX() - P1.getX(); //component X del vector director que va de P1 a P2
        double Vj_P1P2 = P2.getY() - P1.getY(); //component y del vector director que va de P1 a P2
        
        //BUSQUEM VECTOR P2 A P1 (UN VECTOR DIRECTOR DE LA RECTA QUE CONTÉ EL CATET)
        double Vi_P2P1 = -Vi_P1P2; //component X del vector director que va de P2 a P1
        double Vj_P2P1 = -Vj_P1P2; //component y del vector director que va de P2 a P1
        
        //BUSQUEM EL VECTOR QUE VA DEL VERTEX P1 AL PUNT p
        double Vi_P1p = p.getX() - P1.getX();
        double Vj_P1p = p.getY() - P1.getY();
        
        //BUSQUEM EL VECTOR QUE VA DEL VERTEX P2 AL PUNT p
        double Vi_P2p = p.getX() - P2.getX();
        double Vj_P2p = p.getY() - P2.getY();
              
        //cosAlfa: angle entre vectors que van de de 
        //         P1 a p (V_P1p) i de P1 a P2 (V_P1P2)
        double cosAlfa = prodEscalar(Vi_P1p,Vj_P1p,Vi_P1P2,Vj_P1P2)/(modul(Vi_P1p,Vj_P1p)*modul(Vi_P1P2,Vj_P1P2));
        
        //cosBeta: cosinus de l'angle entre vectors que van de 
        //         P2 a p (V_P2p) i de P2 a P1 (v_P2P1)
        double cosBeta = prodEscalar(Vi_P2p,Vj_P2p,Vi_P2P1,Vj_P2P1)/(modul(Vi_P2p,Vj_P2p)*modul(Vi_P2P1,Vj_P2P1));
        
        if (debugging) {
            System.out.println("_________CATET NOU:_____________");
            System.out.println("Vi_P1P2: " + Vi_P1P2 + " Vj_P1P2: " + Vj_P1P2);
            System.out.println("Vi_P2P1: " + Vi_P2P1 + " Vj_P2P1: " + Vj_P2P1);
            System.out.println("Vi_P1p: " + Vi_P1p + " Vj_P1p: " + Vj_P1p);
            System.out.println("Vi_P2p: " + Vi_P2p + " Vj_P2p: " + Vj_P2p);
        
        
            System.out.println("cosAlfa (Angle(V_P1p i V_P1P2)): "+cosAlfa+" cosBeta  Angle(V_P2p i v_P2P1): "+cosBeta);
            System.out.println("_________FI CATET_____________");
        }
        //cas en que els angles alfa i beta siguin aguts aleshores podrem aplicar la projeccio ortogonal
        //del punt p a la recta del segment,  ja que aquesta projeccio cau damunt
        //del segment (o catet). Això es donarà quan els cosinus valguin ambdós
        // en interval [0,1] (la funció cosinus està acotada entre -1 i 1 per tant 
        // podem demanar només la condició de que sigui superior a 0:
        if (cosAlfa >= 0 && cosBeta >= 0) {
            return Math.abs(m*p.getX() - p.getY() + b)/Math.sqrt(Math.pow(m,2)+ 1); //FEM PROJECCIÓ ORTOGONAL DE P SOBRE RECTA (CAU EN SEGMENT)
        }
        return min2(P1.calcularDistancia(p),P2.calcularDistancia(p));//TROBEM VERTEX MES PROPER (PROJECCIO ORTOGONAL NO CAURIA EN SEGMENT)    
    }   
    
    
    
    //Aquesta funció calcula la distancia més petita del punt p al triangle. 
    //Si dóna 0 el punt cau en un catet del triangle. No informa de si p és dins
    // o fora del triangle, retorna una distància positiva en tant que per 
    // simplicitat hem entès el triangle com una figura buida.
    public double calcularDistancia(Punt p) {
        
        //per provar que està ben fet podem veure càlculs si el posem a true. 
        boolean debug = false; 
        
        //calculem distancia de p a cada catet del triangle. Catets definits
        //pels vèrtexs del triangle: p1 a p2, p2 a p3, p3 a p1, respectivament.
        double distancia_p_a_rectaV1V2 = Triangle.distanciaPuntAcatet(p1, p2, p, debug);
        double distancia_p_a_rectaV2V3 = Triangle.distanciaPuntAcatet(p2, p3, p, debug);
        double distancia_p_a_rectaV1V3 = Triangle.distanciaPuntAcatet(p3, p1, p, debug);
        
        if(debug)   System.out.println("distMin_p_a_Catet1: "+distancia_p_a_rectaV1V2+"distMIn_p_a_Catet2: "+distancia_p_a_rectaV2V3+" distMin_p_a_Catet3: "+distancia_p_a_rectaV1V3);
        
        //mirem distàncies de p a cada catet i tornem la mínima. 
        return min2(distancia_p_a_rectaV1V2,min2(distancia_p_a_rectaV2V3,distancia_p_a_rectaV1V3));
    }
    
    

}
