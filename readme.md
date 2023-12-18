# INTRODUCCIÓ

Aquesta carpeta conté programes en Java que he fet sota el marc del cicle formatiu de grau superior de primer de DAW/DAM. Per tal d'executar els programes, si n'hi ha múltiples en una carpeta es poden posar dins d'un package a netbeans i es pot executar la classe que tingui el main.

# Programes de Programació Orientada a Objectes

## Classe Triangle

Dins la carpeta [Practica1](./practica1/) podem veure tres classes que implementen operacions I atributs amb els que treballar amb diferents tipus d'objectes del pla (punt, cercle i triangle). En especial m'agradaria fer referència a la classe [Triangle](./practica1/Triangle.java) que, igual que les altres, podeu provar des de l'arxiu java [Practica_1](./practica1/Practica_1.java).

Dins la classe Triangle he definit la idea de triangle a partir de tres punts P (vegeu classe [Punt](./practica1/Punt.java)). També he implementat divereses funcions per calcular: 
- el perímetre.
- l'àrea de qualsevol triangle, mitjançant la fórmula d'heron.
- un parell de funcions que permeten trobar, en conjunt,**la distància mínima de qualsevol punt del pla a un triangle qualsevol**[^1], éssent aquesta la funcionalitat que comentaré en els següents subapartats de la classe Triangle. 

### Càlcul de la distància de p a un triangle t donat

Per tal de poder trobar la distància mínima d'un punt **p** a un triangle de vèrtexs **P1**, **P2**, **P3** he seguit el procediment que he considerat que dóna la millor aproximació: en primer lloc, he trobat una forma de calcular la distància mínima del punt **p** a un catet del triangle, és a dir, la distància mínima a un segment concret (per exemple, el segment *P1 P2*). En segon lloc, he trobat la distància mínima del punt **p** als altres dos segments (*P2 P3* i *P3 P1*). En tercer lloc, tenint ja calculades les tres distàncies anteriors, he pogut trobar la distància mínima de **p** al triangle tot fent la mínima de les tres distàncies anteriors.

Més en detall, és necessari fer notar que per a fer el càlcul de la distància de **p** a un catet/segment qualsevol no sempre podrem trobar la distància correcta aplicant la fórmula de càlcul de la distància d'un punt a una recta. Per al cas que ens ocupa, aquesta fórmula funciona si i només si la projecció ortogonal del punt **p** a la recta que conté el segment que ens interessa (e.g, segment *P1 P2*) cau DINS d'aquest segment. Si, pel contrari, aquesta projecció caigués en la recta que conté el segment però no en el propi segment, aleshores la fórmula de càlcul de la distància d'un punt a una recta no ens serviria, com veurem a continuació.

 Per tal de poder saber en quin cas dels dos ens trobem, hem de mirar, per trigonometria, si els cosinus dels angles **α** i **β** ens indiquen que ambdós angles siguin aguts -vegeu imatge de sota per entendre-ho i nota al peu [^2]-:

<p align="center">
  <img src="./practica1/auxiliars/diagramaDistanciaMinimaTriangle.svg" alt="imatge triangle no carrega">
</p>

_Si els dos angles són aguts_, aleshores la distància mínima del punt **p** al segment mencionat sí que la podriem trobar fent la <ins>projecció ortogonal del punt **p** a la recta que conté el segment</ins>, ja que cau també en el segment (a la imatge de damunt podeu veure un cas com aquest -al triangle de l'esquerra, la distància en verd-). En cas contrari, _si un dels angles fos superior a 90 graus_, tindriem que la projecció de **p** no cauria dins el segment *P1 P2* i, aleshores, la distància no es podria trobar fent la projecció ortogonal, que ens trobaria una distància més petita, la distància cap a la recta que conté el segment; però no pas cap al segment. En aquest últim cas, hauriem de trobar la <ins>distància mínima cap al vèrtex més proper</ins> (a la imatge de damunt podeu veure el triangle de la dreta, distància en verd -la mínima- i en vermell -la de la projecció ortogonal, que no hagués estat correcta si l'haguessim donat com a bona-).

En la funció `distanciaPuntAcatet(Punt P1, Punt P2, Punt p, boolean debugging)` hem utilitzat quatre vectors per trobar si els dos angles **α** i **β** són aguts o no: 

Així doncs, per trobar el cosinus de l'angle **α** hem fet servir dos vectors (el vector que va de P1 a P2 i el vector que va de  que va de P1 a p), que denominem:

-  $$\vec{u}_{P_1 \to P_2}$$
-  $$\vec{v}_{P_1 \to p}$$

Anàlogament, per trobar el cosinus de l'angle **β** hem pres el vector que va de P2 a P1 i el vector que va de P2 a p, que amb la notació vectorial escollida són:
 
 - $$\vec{x}_{P_2 \to P_1}$$
 - $$\vec{w}_{P_2 \to p}$$
 
A tall d'exemple, per trobar el cosinus de l'angle dels dos primers vectors **u** i **v** hem fet servir l'expressió clàssica de dividir el producte vectorial dels dos vectors entre el producte dels seus mòduls:


$$ cos(\alpha) = {\vec{u} \cdot \vec{v} \over ||\vec{u}|| \cdot ||\vec{v}||} $$

Expressió clàssica que queda representada en la següent línia de codi:

https://github.com/blackcub3s/programesJava/blob/02e74f75dc474e2331168dc4911930cf64bf8082/practica1/Triangle.java#L173-L175

Línia que crida a sengles funcions que hem programat per calcular el producte escalar i el mòdul d'un vector:

https://github.com/blackcub3s/programesJava/blob/02e74f75dc474e2331168dc4911930cf64bf8082/practica1/Triangle.java#L63-L73

Agafant com a exemple el triangle de l'esquerra en l'anterior figura, el que expliquem amb els vectors queda representat gràficament de la següent manera:

<p align="center">
    <img src="./practica1/auxiliars/diagramaDistanciaMinimaTriangleVECTORS.svg" alt = "imatge de vectors no carrega"> 
</p>

La funció que implementa la distància del punt **p** a un segment d'extrems P1 P2 és la funció `distanciaPuntAcatet(Punt P1, Punt P2, Punt p, boolean debugging)` i podeu veure-la completa a continuació:

https://github.com/blackcub3s/programesJava/blob/ddd205c55619b16cd3aa8901763c7ef209b79845/practica1/Triangle.java#L151-L201

Per fer la distància mínima de **p** al triangle s'ha fet amb la funció `calcularDistancia(Punt p)`. Aquesta funció calcula el mínim de les tres distàncies possibles de **p** als catets, invocant tres cops al mètode `distanciaPuntAcatet([...])`: així, de forma respectiva en cada invocació, li passarà les tres combinacions de vèrtexs possibles que generen els tres catets (segments) juntament amb el punt p:

https://github.com/blackcub3s/programesJava/blob/ddd205c55619b16cd3aa8901763c7ef209b79845/practica1/Triangle.java#L209-L224

Podeu veure un esquema visual del que faria la funció `calcularDistancia(Punt p)` prenent el triangle de la figura inicial, però ampliant-ho per als tres catets del mateix. La funció obtindria les tres distàncies mínimes de p a cada catet del triangle i, acte seguit, retornaria la mínima de les tres; éssent la distància retornada la distància mínima del punt p al triangle, la que estàvem buscant:

<p align="center">
    <img src="./practica1/auxiliars/diagramaDistanciaMinimaTriangleGLOBAL.svg" alt = "imatge de les 3 distancies no carrega"> 
</p>





# Programes d'algorismes

## Càlcul d'un nombre primer

Imaginem-nos que se'ns demana una funció que ens retorni si un nombre primer `n` que li passem per paràmetre és primer o no. Una forma de calcular si un nombre `n` és primer és comprovar si no existeixen divisors d'aquest nombre entre el 2 i el nombre `n-1`. És una forma correcta, però ineficient de fer-ho. Una implementació eficient passa per iterar fins a arrel de n. Com ho hem fet aquí:

https://github.com/blackcub3s/programesJava/blob/5d0fe3bc092bc66a256a69135b0ac0cbce3b78b7/algorismes/EsPrimoEntregableSanti.java#L2-L31





[^1]: No es podrà calcular la distància d'un punt a un triangle que tingui un catet completament vertical perquè hem definit els catets del triangle com a funcions matemàtiques (rectes en el pla de la forma y = mx + b, que no admeten múltiples valors de y per a un x donat).
[^2]: la projecció ortogonal del punt p caurà en el segment P1 P2 si i només si l'angle definit pels punts p, P1 i P2 (angle **α**), per una banda,i l'angle definit pels punts P1, P2, p (angle **β** ), per l'altra, són
tots dos compresos entre 0 i 90 graus (ambdós angles inclosos) cosa que es donarà quan els cosinus dels dos angles
estiguin dins de l'interval tancat [0,1].

# Programes amb arrays bi-dimensionals (matrius)

Podem trobar-los dins la carpeta [U06_Matrius](./U06_Matrius/). Els programbes destacats son el 4 i el 5.

## Generació d'un quadrat llatí (Exercici4)

L'exercici demanat és:

<pre>   
    4. Generar un quadrat Llatí d'ordre N.
    Un quadrat Llatí d'ordre N és una matriu quadrada en la qual la primera 
    fila conté els N primers nombres naturals, en ordre, i cadascuna de les 
    següents files conté la rotació de la fila anterior un lloc a la dreta.

    Per exemple, per a N=5

    1 2 3 4 5
    5 1 2 3 4
    4 5 1 2 3
    3 4 5 1 2
    2 3 4 5 1
    
</pre>

I la meva proposta per solucionar-lo es pot veure aquí [Exercici4.java](./U06_Matrius/Exercici4.java):

https://github.com/blackcub3s/programesJava/blob/df5d753a017d3403a696ccbde031ac5af720fb5f/U06_Matrius/Exercici4.java#L16-L79



## Sistema de gestió d'alumnes (Exercici5)

En l'exercici5 se'ns demana:

<pre>

Crear un programa per a gestionar les notes d'un grup escolar (amb un grup de 20 alumnes i tres assignatures) que permeti:

- Introduir les notes.
- Calcular la nota mitjana de cada alumne.
- Calcular la màxima nota de cada assignatura o mòdul.
- Calcular la nota mitjana per mòdul i quants alumnes la sobrepassen.

</pre>
La resolució la tenim dins el fitxer [Exercici5.java](./U06_Matrius/Exercici5.java). 


Per resoldre aquest exercici he fet servir una variable global que mostra el nombre d'estudiants que tenim actualment al sistema, la variable **nreEstudiantsAfegits**. Aquesta variable no la passem per paràmetre a les diferents funcions sino que en ser una variable global estàtica està disponible en tots els atributs estàtics:

https://github.com/blackcub3s/programesJava/blob/f3be083e339333e141b6043cd6916c4880967f7c/U06_Matrius/Exercici5.java#L24

La resta de variables amb les que treballo constantment en l'exericicis (la que guarda la matriu de notes -**notesGrup**- i la que guarda l'array d'strings que conformen els noms dels estudiants -**nomsAlumnes**-) les mantinc _dins el main_ i les passo com a paràmetre -per referència, ja que són arrays- a les diferents funcions o mètodes amb els que hem distribuit el codi[^3]:

https://github.com/blackcub3s/programesJava/blob/f3be083e339333e141b6043cd6916c4880967f7c/U06_Matrius/Exercici5.java#L383-L385

A continuació mostro la funció main del programa:

https://github.com/blackcub3s/programesJava/blob/f3be083e339333e141b6043cd6916c4880967f7c/U06_Matrius/Exercici5.java#L372-L468

Fixeu-vos que en el mmain es defineix la interfície gràfica principal (el menú d'opcions). Aquest main es mostra fins que l'usuari prem una de les opcions (A - F). Quan ho faci, s'hauria de deixar de mostrar el menú d'opcions i s'hauria de mostrar per pantalla alguna altra cosa. En aquest cas, però, com que el programa s'implementa amb la terminal, i en java no és trivial esborrar el contingut de la mateixa, cada cop que vull mostrar un canvi en la interfície senzillament he hagut de generar molts salts de línia perquè el contingut antic quedi amagat fent servir la funció **generaSaltsDeLinia(int n)**. A més a més, per aconseguir que la visualització quedi pausada fins que l'usuari premi una tecla -evitant així que el programa torni ràpidament al menú d'opcions impossibilitant a l'usuari veure el resultat de l'output- tenim la funció **pausaVisualitzacio()**:

https://github.com/blackcub3s/programesJava/blob/f3be083e339333e141b6043cd6916c4880967f7c/U06_Matrius/Exercici5.java#L262-L277

En relació als mètodes que queden per explicar en el programa de gestió d'alumnes, tenim:

- generaNotaDistribucioNormal()
- imprimirNoms()
- generaNotes_i_mitjana()
- visualitzaMatriuEstudiants()
- generaNomsEstudiantsAleatoriament()
- fesMitjanaPerModuls()
- notesMaximesIcomparacioEnMitjana()
- afegeixAlumneInotes()
- canviaNotaAssignaturaAestudiant()
- eliminaAlumne()

M'agradaria comentar alguna de les funcions anteriors:

La funció `generaNotaDistribucioNormal()`  genera un nombre que surt d'una distribució normal amb paràmetres 
"mitjana" (μ) i desviació estandard "desvEst" (σ)[^4]:

https://github.com/blackcub3s/programesJava/blob/df5d753a017d3403a696ccbde031ac5af720fb5f/U06_Matrius/Exercici5.java#L39-L47

Al seu torn, la funció `generaNotes_i_mitjana()` pren la funció anterior per generar aleatòriament bons i mals estudiants. Per a cada estudiant generat amb l'opció `D` del menú d'opcions se li assignarà aleatòriament una distribució per generar notes altes, és a dir, el que podria ser una distribució d'amb estudiants bon rendiment rendiment (μ = 8.5 i σ = 1.5) i un altra amb estudiants amb pitjor rendiment (μ = 3 i σ = 2)[^5]:

https://github.com/blackcub3s/programesJava/blob/df5d753a017d3403a696ccbde031ac5af720fb5f/U06_Matrius/Exercici5.java#L73-L89

La funció `generaNomsEstudiantsAleatoriament()` pren un llistat d'arrays d'enters constants (final -ja que no les modificarem-) amb noms preescrits que combinarà de forma aleatòria per formar noms i cognoms:

https://github.com/blackcub3s/programesJava/blob/df5d753a017d3403a696ccbde031ac5af720fb5f/U06_Matrius/Exercici5.java#L123-L144

La funció `fesMitjanaPerModuls()` simplement computa per moduls la mitjana de la matriu que conté les notes dels estudiants (**notesGrup** en el main, **m** en la funció), cosa que es calcula cada cop que visualitzem la matriu d'estudiants:

https://github.com/blackcub3s/programesJava/blob/df5d753a017d3403a696ccbde031ac5af720fb5f/U06_Matrius/Exercici5.java#L149-L165

La funció `NotesMaximesIcomparacioEnMitjana()` imprimeix les notes màximes per mòdul i el nombre d'estudiants que superen la mitjana de cada un dels mòduls:

https://github.com/blackcub3s/programesJava/blob/df5d753a017d3403a696ccbde031ac5af720fb5f/U06_Matrius/Exercici5.java#L168-L220


La funció `afegeixAlumneInotes()` senzillament permet a l'usuari introduir pel canal estàndard d'entrada el nom d'un nou l'alumne i les notes de les assignatures (sempre que hi hagi espai per a un nou alumne). Al seu torn, actualitza la variable **nreEstudiantsAfegits** incrementant-la en una unitat, si es compleix la condició per afegir-lo:

https://github.com/blackcub3s/programesJava/blob/df5d753a017d3403a696ccbde031ac5af720fb5f/U06_Matrius/Exercici5.java#L222-L255


La funció `canviaNotaAssignaturaAestudiant()` canvia una nota de l'assignatura d'un estudiant, si és que el troba afegit al sistema (en cas contrari imprimeix missatge d'error):

https://github.com/blackcub3s/programesJava/blob/df5d753a017d3403a696ccbde031ac5af720fb5f/U06_Matrius/Exercici5.java#L280-L329


Finalment, la funció `eliminaAlumne()` elimina un alumne del sistema (sempre que existeixi en el sistema, sino imprimeix missatge d'error). L'eliminació la fa de dos fronts: per una banda, de la matriu **notesGrup**, que conté les notes dels estudiants; Per l'altra, de l'array que conté els noms **nomsAlumnes**. Concretament, això ho aconsegueix fent que la fila de la matriu **notesGrup**, que ocupava l'alumne que eliminem, sigui sobreescrita pel següent alumne, i així successivament fins arribar a l'últim alumne de la matriu eliminant l'últim alumne (que queda repetit); anàlogament, segueix un procediment similar per a **nomsAlumnes**. Així ens assegurem que no queda un espai buit a la matriu de notes ni a l'array de noms. Finalment, la funció eliminaAlumne() també decrementa en una unitat la variable **nreEstudiantsAfegits**: 

https://github.com/blackcub3s/programesJava/blob/df5d753a017d3403a696ccbde031ac5af720fb5f/U06_Matrius/Exercici5.java#L336-L369

Per acabar ens queden les funcions `visualitzaMatriuEstudiants()` I `imprimirNoms()` que no tenen més complicació. Imprimeixen les dades formatejant a dos decimals i els noms un darrere l'altre, en una llista, respectivament:

https://github.com/blackcub3s/programesJava/blob/df5d753a017d3403a696ccbde031ac5af720fb5f/U06_Matrius/Exercici5.java#L93-L118

https://github.com/blackcub3s/programesJava/blob/df5d753a017d3403a696ccbde031ac5af720fb5f/U06_Matrius/Exercici5.java#L50-L62

Seria molt llarg capturar tots els outputs de pantalla així que mostraré la generació de 20 estudiants amb l'opció D que després es mostra amb l'opció A:

<pre>

-------------- NOTES ESTUDIANTS --------------
ASS1    ASS2    ASS3    AVG    NOM I COGNOMS
----------------------------------------------
9,80	6,70	4,90	7,13	Antonio Montcada Soros
8,00	6,00	7,30	7,10	Ronaldinho Ronaldo Ronaldo
1,03	2,85	3,21	2,36	Cristina Santos Llopis
5,64	3,10	0,83	3,19	Adri Puigdemont Puigdemont
8,75	9,59	7,53	8,62	Carles Prieto Hernandez
1,73	3,77	6,64	4,05	Robert Fernandez Llopis
9,45	9,52	8,38	9,12	Mavis Iberico Alvarado
0,00	4,08	3,41	2,49	Joel Uson Pineda
10,00	9,67	9,40	9,69	Melisa Prieto Santos
2,70	2,74	3,58	3,00	Carlos Ayelen Fernandez
9,92	10,00	9,56	9,82	Marcello Hernandez Sans
3,84	4,05	4,27	4,05	Robert Palausabulla Uson
0,00	4,67	2,35	2,34	Adri Santos Prieto
4,77	1,08	6,04	3,96	Jennifer Pineda Roma
3,93	2,52	4,19	3,55	Albert Sanchez Santos
5,08	1,50	4,93	3,84	Jennifer Prieto Santos
6,61	8,04	3,77	6,14	Carlos Palausabulla Hernandez
2,86	0,00	3,17	2,01	Cristina Sanchez Llopis
8,78	9,53	5,31	7,87	Amagoia Alvarado Alvarado
4,20	0,00	2,52	2,24	Cristina Pineda Delso

ASS1    ASS2    ASS3 
10,00	10,00	9,56	NOTES MAXIMES
9   	8   	8   	NOMBRE D'ESTUDIANTS AMB NOTA SUPERIOR A LA MITJANA DE L'ASSIGNATURA*
5,35	4,97	5,06	NOTA MITJANA

---------------------------------------------
---- PREMEU UNA TECLA PER TORNAR AL MENÚ ----
---------------------------------------------

</pre>



[^3]: Fixeu-vos que la variable _mitjanaPerModuls:_ no la menciono. No l'esmento perquè no és un contenidor de dades estable durant el programa a diferència de les altres dues, sino que es un contenidor usat cada cop que premem l'opció A per tal de calcular les mitjanes per mòduls (ens és indiferent l'estat que pren quan no estem visionant A perquè la mitjana per mòduls la calculem a partir de la matriu notesGrup).

[^4]: Podeu veure la capçalera de la funció `generaNotaDistribucioNormal()` damunt de la mateixa (clicant en l'arxiu).
[^5]: Podeu veure la capçalera de la funció `generaNotes_i_mitjana()` damunt de la mateixa (clicant en l'arxiu).


 