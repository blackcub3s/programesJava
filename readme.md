# INTRODUCCIÓ

Aquesta carpeta conté programes en java que he fet sota el marc del cicle formatiu de grau superior de primer de DAW/DAM. Per executar els programes, si n'hi ha múltiples en una carpeta es poden posar dins d'un package a netbeans i es pot executar la classe que tingui el main.

# Programes de classes

## Classe Triangle

Dins la carpeta [Practica1](./practica1/) podem veure tres classes que implementen operacions I atributs amb els que treballar amb diferents tipus d'objectes del pla (punt, cercle i triangle). En especial m'agradaria fer referència a la classe [Triangle](./practica1/Triangle.java) que, igual que les altres, podeu provar des de l'arxiu java [Practica_1](./practica1/Practica_1.java).

Dins la classe Triangle he definit la idea de triangle a partir de tres punts P (vegeu classe [Punt](./practica1/Punt.java) i he implementat divereses funcions per calcular l'àrea de qualsveol triangle (la fórmula d'heron), per calcular el perímetre i, finalment, la funcionalitat de la classe més interessant i que he volgut dedicar aquesta entrada a github: he fet un parell de funcions per poder trobar la distància mínima de qualsevol punt del pla a un triangle.

### Càlcul de la distància de p a un triangle t donat

Per tal de poder provar la distància d'un punt **p** a un triangle de vèrtexs **P1**, **P2**, **P3** de manera que doni la distancia mínima amb la major exactitud possible he seguit el següent procediment: primer, he trobar una forma de calcular la distància mínima del punt **p** a un catet del triangle, és a dir, a un segment (per exemple, el segment *P1 P2*). Un cop trobada la manera d'aconseguir-ho, aleshores he fet la distància mínima de **p** als tres segments, trobant així la distància mínima de **p** al triangle.

Més en detall, val a dir que per fer el càlcul de la distància de **p** a un catet del triangle ha calgut veure si la projecció ortogonal del punt **p** a la recta que conté el segment definit per, per exemple, **P1 P2**, cau DINS del segment mirant per trigonometria si els cosinus dels angles **α** i **β** ens indiquen que ambdós angles són aguts -vegeu imatge de sota per entendre-ho i nota al peu [^1]- (funció `distanciaPuntAcatet(Punt P1, Punt P2, Punt p, boolean debugging)`); si els angles són aguts la distància mínima del punt **p** al segment mencionat seria la projecció ortogonal del punt **p** a la recta que el conté (a la imatge de sota podeu veure un cas com aquest al triangle de l'esquerra, la distància en verd). En cas contrari, si la projecció de **p** no cau dins el segment *P1 P2* aleshores la distància no es podria trobar amb la projecció ortogonal, que ens trobaria una distància més petita cap a la recta que conté el segment... però no pas el segment. En aquest últim cas, hauriem de trobar la distància mínima cap al vèrtex més proper (a la imatge de sota podeu veure el triangle de la dreta, distància en verd -la mínima- i en vermell -la de la projecció ortogonal, que no hagués estat correcta si l'haguessim donat com a bona-).


<p align="center">
  <img src="./practica1/auxiliars/diagramaDistanciaMinimaTriangle.svg" alt="imatge triangle no carrega">
</p>

En la funció `distanciaPuntAcatet(Punt P1, Punt P2, Punt p, boolean debugging)` hem utilitzat quatre vectors per trobar els dos angles **α** i **β**. 

Per exemple, Per trobar el cosinus de l'angle **α** hem fet servir dos vectors. El vector que va de P1 a P2 i el vector que va de  que va de P1 a p: 

-  $$\vec{u}_{P_1 \to P_2}$$
-  $$\vec{v}_{P_1 \to p}$$

Mentre que per trobar el cosinus de l'angle per trobar **β** hem pres el vector que va de P2 a P1 i el vector que va de P2 a p:
 
 - $$\vec{x}_{P_2 \to P_1}$$
 - $$\vec{w}_{P_2 \to p}$$
 
Concretament, per trobar el cosinus de l'angle dels dos primers vectors **u** i **v** hem fet servir l'expressió clàssica de dividir el producte vectorial dels dos vectors entre el producte dels seus mòduls:


$$ cos(\alpha) = {\vec{u} \cdot \vec{v} \over ||\vec{u}|| \cdot ||\vec{v}||} $$

Expressió que queda representada en la següent línia de codi:

https://github.com/blackcub3s/programesJava/blob/02e74f75dc474e2331168dc4911930cf64bf8082/practica1/Triangle.java#L173-L175

Línia que crida a les funcions per calcular el producte escalar i el mòdul d'un vector, definides en sengles funcions:

https://github.com/blackcub3s/programesJava/blob/02e74f75dc474e2331168dc4911930cf64bf8082/practica1/Triangle.java#L63-L73

Agafant com a exemple el triangle de l'esquerra en l'anterior figura, el que expliquem queda representat gràficament de la següent manera:

<p align="center">
    <img src="./practica1/auxiliars/diagramaDistanciaMinimaTriangleVECTORS.svg" alt = "imatge de vectors no carrega"> 
</p>

La funció que implementa la distància del punt **p** a un segment d'extrems P1 P2 és la funció `distanciaPuntAcatet(Punt P1, Punt P2, Punt p, boolean debugging)` i podeu veure-la completa a continuació:

https://github.com/blackcub3s/programesJava/blob/ddd205c55619b16cd3aa8901763c7ef209b79845/practica1/Triangle.java#L151-L201

Per fer la distància mínima de **p** al triangle (la distància **d** de la penúltima captura) s'ha fet amb la funció `calcularDistancia(Punt p)` que calcula el mínim de les tres distàncies possibles de **p** als catets.

https://github.com/blackcub3s/programesJava/blob/ddd205c55619b16cd3aa8901763c7ef209b79845/practica1/Triangle.java#L209-L224

Podeu veure un esquema visual del que faria aquesta funció prenent el triangle de la figura inicial, però ampliant-ho per als tres catets del mateix:

<p align="center">
    <img src="./practica1/auxiliars/diagramaDistanciaMinimaTriangleGLOBAL.svg" alt = "imatge de les 3 distancies no carrega"> 
</p>

[^1]: la projecció ortogonal del punt p caurà en el segment P1 P2 si i només si l'angle definit pels punts p, P1 i P2 (angle **α**), per una banda,i l'angle definit pels punts P1, P2, p (angle **β** ), per l'altra, són
tots dos compresos entre 0 i 90 graus (ambdós angles inclosos) cosa que es donarà quan els cosinus dels dos angles
estiguin dins de l'interval tancat [0,1].


# Programes d'algorismes

TO DO