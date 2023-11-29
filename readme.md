# INTRODUCCIÓ

Aquesta carpeta conté programes en Java que he fet sota el marc del cicle formatiu de grau superior de primer de DAW/DAM. Per tal d'executar els programes, si n'hi ha múltiples en una carpeta es poden posar dins d'un package a netbeans i es pot executar la classe que tingui el main.

# Programes de Programació Orientada a Objectes

## Classe Triangle

Dins la carpeta [Practica1](./practica1/) podem veure tres classes que implementen operacions I atributs amb els que treballar amb diferents tipus d'objectes del pla (punt, cercle i triangle). En especial m'agradaria fer referència a la classe [Triangle](./practica1/Triangle.java) que, igual que les altres, podeu provar des de l'arxiu java [Practica_1](./practica1/Practica_1.java).

Dins la classe Triangle he definit la idea de triangle a partir de tres punts P (vegeu classe [Punt](./practica1/Punt.java) i he implementat divereses funcions per calcular l'àrea de qualsveol triangle (la fórmula d'heron), per calcular el perímetre i, finalment, la funcionalitat de la classe més interessant i que he volgut dedicar aquesta entrada a github: he fet un parell de funcions per poder trobar la distància mínima de qualsevol punt del pla a un triangle.

### Càlcul de la distància de p a un triangle t donat

Per tal de poder trobar la distància mínima d'un punt **p** a un triangle de vèrtexs **P1**, **P2**, **P3** he seguit el procediment que he considerat que dona la millor aproximació: primer, he trobat una forma de calcular la distància mínima del punt **p** a un catet del triangle, és a dir, a un segment concret (per exemple, el segment *P1 P2*). Després he trobat la distància mínima del punt **p** als altres dos segments (*P2 P3* i *P3 P1*) i, amb aquestes tres dades, he pogut trobar la distància de **p** al triangle tot fent la mínima de les tres distàncies de **p** als tres segments.

Més en detall, val a dir que per a fer el càlcul de la distància de **p** a un catet del triangle ha calgut veure si la projecció ortogonal del punt **p** a la recta que conté el segment que ens interessa (e.g, segment *P1 P2*), cau DINS d'aquest segment (podria ser que caigués en la recta que el conté, però no en el propi segment). Per tal de poder saber en quin cas ens trobem, hem de mirar, per trigonometria, si els cosinus dels angles **α** i **β** ens indiquen que ambdós angles siguin aguts -vegeu imatge de sota per entendre-ho i nota al peu [^1]-. 

<p align="center">
  <img src="./practica1/auxiliars/diagramaDistanciaMinimaTriangle.svg" alt="imatge triangle no carrega">
</p>

Si els dos angles són aguts, aleshores la distància mínima del punt **p** al segment mencionat sí que la podriem trobar fent la projecció ortogonal del punt **p** a la recta que conté el segment, ja que cau també en el segment (a la imatge de damunt podeu veure un cas com aquest -al triangle de l'esquerra, la distància en verd-). En cas contrari, és a dir, cas en que un dels angles fos superior a 90 graus, tindriem que la projecció de **p** no cauria dins el segment *P1 P2* i, aleshores, la distància no es podria trobar fent la projecció ortogonal, que ens trobaria una distància més petita, la distància cap a la recta que conté el segment; però no pas cap al segment. En aquest últim cas, hauriem de trobar la distància mínima cap al vèrtex més proper (a la imatge de damunt podeu veure el triangle de la dreta, distància en verd -la mínima- i en vermell -la de la projecció ortogonal, que no hagués estat correcta si l'haguessim donat com a bona-).

En la funció `distanciaPuntAcatet(Punt P1, Punt P2, Punt p, boolean debugging)` hem utilitzat quatre vectors per trobar els dos angles **α** i **β**. 

Així doncs, per trobar el cosinus de l'angle **α** hem fet servir dos vectors (el vector que va de P1 a P2 i el vector que va de  que va de P1 a p): 

-  $$\vec{u}_{P_1 \to P_2}$$
-  $$\vec{v}_{P_1 \to p}$$

Anàlogament, per trobar el cosinus de l'angle **β** hem pres el vector que va de P2 a P1 i el vector que va de P2 a p:
 
 - $$\vec{x}_{P_2 \to P_1}$$
 - $$\vec{w}_{P_2 \to p}$$
 
Per exemple, per trobar el cosinus de l'angle dels dos primers vectors **u** i **v** hem fet servir l'expressió clàssica de dividir el producte vectorial dels dos vectors entre el producte dels seus mòduls:


$$ cos(\alpha) = {\vec{u} \cdot \vec{v} \over ||\vec{u}|| \cdot ||\vec{v}||} $$

Expressió que queda representada en la següent línia de codi:

https://github.com/blackcub3s/programesJava/blob/02e74f75dc474e2331168dc4911930cf64bf8082/practica1/Triangle.java#L173-L175

Línia que crida a sengles funcions que hem programat per calcular el producte escalar i el mòdul d'un vector:

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