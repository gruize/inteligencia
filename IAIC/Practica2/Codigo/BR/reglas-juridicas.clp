;PRACTICA INTELIGENCIA Pablo Escobar de la Oliva 4ºC Profesor Hector Gomez Gauchia
;
;
; Investigacion recopilada de: 
;http://www.oposiciones.de
;http://www.enlared.biz/boletines-oficiales.html
;http://oposiciones.universia.es/guia.jsp#2
;    
;   COMENTARIOS:
;   ------------
;
;   He intentado hacer la base de reglas de la manera mas simple posible 
;   en principio utilice plantillas, variables globales..
;   pero me parece que asi es mas entendible 
;   y como vamos a intercambiarnos entre la clase las diferentes bases de reglas
;   no dudeis en seguir mi esquema si no sabeis como empezar ;) no hay copyright
;
;   Jess es mas potente de lo que parece a priori
;   he visto ejemplos de juegos de Poker, viviendas inteligentes..
;
;   Mi base de reglas es fácil:
;   ---------------------------
;
;   Inicialmente creo un hecho: fase inicial mediante (deffacts fase-inicial (fase inicial))
;   que hara que se dispare la primera regla: defrule oposicion_r
;   no es totalmente necesario ya que podemos poner en la regla la parte izquierda vacia
;
;   la guardo en una variable ?fase   mediante ?fase<-(fase inicial) 
;   ?fase en realidad contendrá FACT-1
;   para luego poder quitar el hecho con retract (?fase)
;   aunque este paso se podria evitar simplemente poniendo retract(1)
;
;
;   para las demas reglas simplemente se disparan
;   cuando les llega un hecho: p.ej (defrule boe_r (oposiciones  si) => 
;   este hecho es un string y podeis ver como se disparan con (WATCH ALL) antes de la ejecucion
;   o (FACTS) despues de la ejecucion
;   si la regla requiere mas hecho para dispararse utilizo un and (notacion prefija) con varios
;   hechos:  (and
;   (oposiciones si)
;   (boe 3))
;
;   una vez que una regla se dispara le hago un printout para escribir por pantalla
;   y le inserto un nuevo hecho: p.ej  (assert (oposiciones (read))) read lee lo escrito por pantalla
;
;   no he incluido las reglas que comprueban que lo escrito es razonable (si o no, 1 2 3 o 4)
;   si lo ejecutais y respondeis mal a una pregunta jess saldra de la ejecucion
;
;   el arbol de generacion de las reglas es bastante facil e intuitivo una vez que lo ejecuteis.
;   todo depende de el primer hecho: si quiere informacion sobre oposiciones o no.  
;
;   lo que no he hecho es comentar cada regla, el codigo habla por si solo y practicamente
;   he repetido el mismo patron para todas, las he ordenado segun el nivel 
;
;   En total hay 30 reglas de las cuales 18 necesitan mas de 3 hechos para dispararse    
;/*---------------------------------------------------------------------------------------------------------------------------*/



;TEXTO DE INTRODUCCION
 
  (printout t "Bienvenido JAWL Jess Advisory Work Legal" crlf
			   "lo ultimo para asesorarte laboralmente" crlf
			   "le ayudara en el ambito juridico" crlf
			   "si esta pensando en utilizar esta aplicacion realmente" crlf
			   "contrate a un abogado ;)")
			   

;/*---------------------------------------------------------------------------------------------------------------------------*/


;1er HECHO FASE INICIAL   

(deffacts fase-inicial
   (fase inicial))
   
   
;/*---------------------------------------------------------------------------------------------------------------------------*/
;REGLA NIVEL 0 (fase inicial)
;===========================

(defrule oposicion_r
    ?fase<-(fase inicial)
    =>
   (printout t crlf crlf crlf crlf crlf crlf crlf crlf crlf crlf crlf crlf crlf crlf crlf crlf crlf 
			"Esta pensando en presentarse a la oferta de" crlf
			"empleo publico (oposiciones)? " crlf crlf
			"si/no: >" )
   (retract ?fase)
   (assert (oposiciones (read)))   
)

;/*---------------------------------------------------------------------------------------------------------------------------*/
;REGLAS 1er NIVEL OPOSICIONES si
;==============================


(defrule boe_r
   (oposiciones  si)
    =>
   (printout t crlf crlf crlf"1)Desea Buscar boletines oficiales BOE," crlf 
							 "2)Desea informacion sobre requisitos para acceder al empleo publico," crlf
							 "3)Desea buscar oposicion en funcion de su formacion" crlf
							 "4)Desea informacion sobre las clases de puestos en la funcion publica espanyola" crlf
							 "5)Administraciones donde puedes trabajar como opositor"crlf crlf
							 "1/2/3/4/5: >" )
   (assert (boe (read)))
)

;/*---------------------------------------------------------------------------------------------------------------------------*/
;REGLAS 1er NIVEL OPOSICIONES no
;==============================

(defrule oposicionesno_r 
   (oposiciones no)
    =>
   (printout t crlf crlf crlf "Deseas Informacion para trabajar por tu cuenta Autonomo (A) " crlf
							"o por cuenta ajena (C)?" crlf crlf
							"A/C >")
   (assert (trabajar (read) ))
)

;/*---------------------------------------------------------------------------------------------------------------------------*/
;REGLAS 2º NIVEL OPOSICIONES si
;===============================

(defrule boletines_r 
   (and
   (oposiciones si)
   (boe 1))
   =>
   (printout t crlf crlf crlf "Busqueda de boletines oficiales del estado" crlf
							  "==========================================" crlf	 
"-	BOE.es - Portal del Boletin Oficial del Estado Consultas al diario del BOE. Ofertas de empleo en el BOE
Perfil del contratante. Becas publicadas en el BOE. Ayudas publicadas en el BOE" crlf
"-	Consulta de boletines oficiales
Diarios y boletines oficiales autonomicos y provinciales. BOE y Union Europea: documentos recientes clasificados por materias" crlf
"-	Boletines oficiales de Comunidades Autonomas
Boletin Oficial de la Junta de Andalucia (BOJA)
Boletin Oficial de Aragon (BOA)
Boletin Oficial del Principado de Asturias (BOPA)
Boletin Oficial de Islas Baleares (BOCAIB)
Boletin Oficial de Canarias (BOC)
Boletin Oficial de Cantabria (BOC) 
Diario Oficial de Castilla-La Mancha (DOCM)
Boletin Oficial de la Junta de Castilla y Leon (BOCyL)
Diari Oficial de la Generalitat de Catalunya (DOGC)
Diario Oficial de Extremadura (DOE)
Diario Oficial de Galicia (DOG)
Boletin Oficial de La Rioja (BOR)
Boletin Oficial de la Comunidad de Madrid (BOCM)
Boletin Oficial de la Region de Murcia (BORM)
Boletin Oficial de Navarra (BON)
Boletin Oficial del Pais Vasco (BOPV)
Diari Oficial de la Comunitat Valenciana (DOCV)
Boletin Oficial de la Ciudad Autonoma de Ceuta (BOCCE)
Boletin Oficial de la Ciudad Autonoma de Melilla (BOCME)" crlf
	"-	Avisaboe.com
Servicio de consulta gratuita del BOE y envio de avisos por correo electronico segun seleccion de criterios previos." crlf
	"-	Boletines Oficiales en Internet - Biblioteca Nacional de Espanya
BOE Boletin Oficial del Estado
BOPI Boletin Oficial de la Propiedad Industrial
BOPI historico Boletin Oficial de la Propiedad Industrial (historico)
Boletines oficiales provinciales y autonomicos")
)

(defrule categorias_r
   (and
   (oposiciones si)
   (boe 2))
    =>
  (printout t crlf crlf crlf "Requisitos para acceder al empleo publico" crlf
						      "========================================"crlf   
"Segun el art. 28 del Estatuto de los Funcionarios de las Comunidades Europeas solo podran ser nombrados funcionarios 
las personas que cumplan las condiciones siguientes:" crlf

"a) que sean nacionales de uno de los Estados miembros de las Comunidades, salvo excepcion acordada por la autoridad 
facultada para proceder a los nombramientos, y que esten en pleno goce de sus derechos politicos;
b) que se encuentren en situacion regular respecto a las leyes de reclutamiento al servicio militar que les sean aplicables;
c) que ofrezcan las garantias de moralidad requeridas para el ejercicio de sus funciones;
d) que hayan superado un concurso, una oposicion o un concurso-oposicion;
e) que reunan las condiciones de aptitud fisica requeridas para el ejercicio de sus funciones;
f) que justifiquen poseer el conocimiento en profundidad de una de las lenguas de las Comunidades y un conocimiento 
satisfactorio de otra de ellas, en la medida necesaria para el desempenyo de las funciones que puedan ser llamados a ejercer.

Cabe senyalar que junto este tipo de acceso se contemplan tambien contratos de duracion determinada, estancias en 
practicas y categorias especificas en el ambito de la investigacion y de los servicios linguisticos. Puede obtenerse 
informacion adicional en la Oficina de Seleccion de Personal. ")
)


(defrule titulacion_r 
   (and
   (oposiciones si)
   (boe 3))
   =>
   (printout t crlf crlf crlf "Que titulacion  tienes actualmente: " crlf
						"Licenciado(L)"crlf
						"Diplomado(D)"crlf
						"Bachiller Superior o equivalente (B)"crlf
						"Graduado escolar (G)"crlf
						"Estudios primarios (E)"crlf crlf
						"L/D/B/G/E >")
   (assert (titulacion (read) ))
)



;/*---------------------------------------------------------------------------------------------------------------------------*/
;REGLAS 2º NIVEL OPOSICIONES no
;===============================

(defrule autonomo_r 
   (and
   (oposiciones no)
   (trabajar A))
    =>
   (printout t crlf crlf crlf "Desea saber los pasos a seguir para ser Autonomo? (P) " crlf
							"o quiere informacion sobre el IRPF y el IVA para autonomos(I)?"crlf crlf
							"P/I >")
   (assert (autonomo (read) ))
)


(defrule ajena_r 
   (and
   (oposiciones no)
   (trabajar C))
    =>
   (printout t crlf crlf crlf "Desea saber Informacion sobre la inscripcion en el inem (I) " crlf
							"o quiere informacion sobre Servicio Publico de Empleo Estatal (S) )?"crlf
							"I/S >")
   (assert (paroEmpleo (read) ))
)


;/*---------------------------------------------------------------------------------------------------------------------------*/
;REGLAS 3erº NIVEL OPOSICIONES si
;===============================

(defrule licenciado_r 
   (and
   (oposiciones si)
   (boe 3)
   (titulacion L))
   =>
   (printout t crlf crlf crlf "LICENCIADO " crlf
						      "=========="crlf
						"Titulo de Doctor, Licenciado, Ingeniero, Arquitecto o equivalente" crlf
						"Puedes optar a oposiciones de Grupo A" crlf
						"ademas de todas las anteriores: B,C,D,E" crlf
						"para mas informacion puedes ver la oferta de empleo publico" crlf
						"publicada en el boe, o acceder directamente desde esta direccion" crlf
						"http://www.inap.map.es/NR/rdonlyres/8EC35AEF-FCD8-4820-AD82-2B18D519DD14/0/OEP2009.pdf")
   
)
(defrule diplomado_r 
   (and
   (oposiciones si)
   (boe 3)
   (titulacion D))
   =>
(printout t crlf crlf crlf "DIPLOMADO " crlf
						      "=========="crlf
						"Titulo de Diplomado Universitario, Ingeniero Tecnico, Arquitecto Tecnico,  Formacion Profesional de 
						Tercer grado o equivalente"crlf
						"Puedes optar a oposiciones de Grupo B"crlf
						"ademas de todas las anteriores: C,D,E"crlf
						"para mas informacion puedes ver la oferta de empleo publico"crlf
						"publicada en el boe, o acceder directamente desde esta direccion " crlf
						"http://www.inap.map.es/NR/rdonlyres/8EC35AEF-FCD8-4820-AD82-2B18D519DD14/0/OEP2009.pdf")
   
)

(defrule bachiller_r 
   (and
   (oposiciones si)
   (boe 3)
   (titulacion B))
   =>
(printout t crlf crlf crlf "BACHILLER " crlf
						      "=========="crlf
						"Titulo de Bachiller, Formacion Profesional de Segundo Grado o equivalente."crlf
						"Puedes optar a oposiciones de Grupo C" crlf
						"ademas de todas las anteriores: D,E" crlf
						"para mas informacion puedes ver la oferta de empleo publico" crlf
						"publicada en el boe, o acceder directamente desde esta direccion " crlf
						"http://www.inap.map.es/NR/rdonlyres/8EC35AEF-FCD8-4820-AD82-2B18D519DD14/0/OEP2009.pdf")
   
)

(defrule graduado_r 
   (and
   (oposiciones si)
   (boe 3)
   (titulacion G))
   =>
(printout t crlf crlf crlf "GRADUADO " crlf
						      "=========="crlf
						"Titulo de Graduado Escolar, Formacion Profesional de Primer Grado o equivalente" crlf
						"Puedes optar a oposiciones de Grupo D" crlf
						"ademas de las oposiciones de categoria E" crlf
						"para mas informacion puedes ver la oferta de empleo publico" crlf
						"publicada en el boe, o acceder directamente desde esta direccion " crlf
						"http://www.inap.map.es/NR/rdonlyres/8EC35AEF-FCD8-4820-AD82-2B18D519DD14/0/OEP2009.pdf")
   
)

(defrule primaria_r 
   (and
   (oposiciones si)
   (boe 3)
   (titulacion E))
   =>
(printout t crlf crlf crlf "PRIMARIA " crlf
						      "=========="crlf
						"Titulo de Graduado Escolar, Formacion Profesional de Primer Grado o equivalente" crlf
						"Puedes optar a oposiciones de Grupo E" crlf
						"aunque deberias plantearte el seguir estudiando" crlf
						"para mas informacion puedes ver la oferta de empleo publico" crlf
						"publicada en el boe, o acceder directamente desde esta direccion " crlf
						"http://www.inap.map.es/NR/rdonlyres/8EC35AEF-FCD8-4820-AD82-2B18D519DD14/0/OEP2009.pdf")
   
)


(defrule puestos_r 
   (and
   (oposiciones si)
   (boe 4))
   =>
   (printout t crlf crlf crlf "Sobre que puesto quieres informacion?" crlf
						"1) Personal funcionario"crlf
						"2) Personal eventual"crlf
						"3) Personal laboral"crlf
						"4) Personal estatuario"crlf
						"1/2/3/4 >")
   (assert (puesto (read) ))
)

(defrule funcionario_r 
   (and
   (oposiciones si)
   (boe 4)
   (puesto 1)
   )
   =>
   (printout t crlf crlf crlf "Personal funcionario" crlf
						      "===================="crlf
"Su caracteristica principal radica en que no se rigen por el Derecho Laboral comun, sino que disponen
de su propia regulacion, mediante normas de Derecho Administrativo que delimitan sus derechos y sus obligaciones.
Con caracter general, los puestos de la Administracion son desempenyados por funcionarios, distribuidos en Cuerpos Generales,
que desempenyan tareas comunes a la actividad administrativa; y Cuerpos Especiales, que realizan funciones propias de una 
determinada carrera o profesion. Cada uno de estos cuerpos se engloba dentro de un Grupo (A,B, C, D, E o F), que varia en 
funcion del tipo de titulacion exigida.
Dentro de cada uno de estos grupos, los funcionarios pueden estar en una de las situaciones siguientes, que determinaran tanto 
su procedimiento de ingreso como sus derechos y deberes:
- Personal funcionario de carrera: desempenyan sus servicios con caracter permanente, una vez superadas las pruebas selectivas 
(principio de carrera) y tras el correspondiente nombramiento.
- Personal funcionario interino: prestan servicios de caracter transitorio que, por razones de urgencia o necesidad, no puedan 
proveerse por funcionarios de carrera. El personal interino ha de cumplir los requisitos generales para ocupar ese puesto de 
trabajo. Cuando finalice la causa que dio lugar a su nombramiento pierden la condicion de interino.
Un interino puede ser nombrado cuando tenga lugar alguno de los siguientes supuestos:
* Para cubrir plazas vacantes cuando no sea posible su cobertura por funcionarios de carrera.
* Para cubrir transitoriamente plazas que han de ser ocupadas definitivamente por funcionarios de carrera (por ejemplo en 
espera de que se resuelva una convocatoria).
* Para la realizacion de programas estrictamente temporales o para situaciones urgentes.
* Para ocupar puestos de trabajo en sustitucion de funcionarios que gocen del derecho de reserva de plaza y destino
 (como una excedencia por maternidad).
")
)

(defrule eventual_r 
   (and
   (oposiciones si)
   (boe 4)
   (puesto 2)
   )
   =>
   (printout t crlf crlf crlf "Personal eventual" crlf
						      "================="crlf
						"Son aquellos que, en virtud de libre nombramiento y en regimen no permanente, 
ocupan un lugar de trabajo considerado de confianza o de asesoramiento especial no reservado a funcionarios.
La prestacion de estos servicios nunca se puede considerar como merito para acceder a la condicion de funcionario ni para 
la promocion interna. Este personal esta sujeto al derecho administrativo y su cese se acuerda libremente y no genera nunca indemnizacion.
")
)

(defrule laboral_r 
   (and
   (oposiciones si)
   (boe 4)
   (puesto 3)
   )
   =>
   (printout t crlf crlf crlf "Personal laboral" crlf
						      "================"crlf
						"Presta sus servicios sujeto a normas de Derecho Laboral, vinculado con la Administracion a traves de un 
contrato de trabajo. Con caracter general, son ocupados por personal laboral los puestos de naturaleza no 
permanente, de caracter instrumental y aquellos cuyas actividades son propias de oficios o requieren conocimientos
tecnicos especializados.
En funcion de la duracion del contrato el personal laboral puede ser:
- Personal laboral permanente, con contrato de trabajo de caracter indefinido.
- Personal laboral temporal, con contrato de trabajo de duracion determinada, por obra o servicio.  ")
						
)

(defrule estatuario_r 
   (and
   (oposiciones si)
   (boe 4)
   (puesto 4)
   )
   =>
   (printout t crlf crlf crlf "Personal estatuario" crlf
						      "==================="crlf
"Es personal estatutario de carrera quienes, en virtud de nombramiento conferido por la autoridad competente, 
estan vinculados a una Administracion Publica por una relacion estatutaria regulada por el Derecho Administrativo,
para el desempenyo de servicios profesionales retribuidos de caracter permanente.
El personal que presta sus servicios en los centros e instituciones sanitarias tiene una regulacion especifica, 
en algunos casos todavia preconstitucional y en proceso de actualizacion. Aunque existe un Estatuto Marco para 
la regulacion del personal estatutario de los Servicios de Salud en Espanya, seran las correspondientes 
administraciones autonomicas quienes desarrollen su propia normativa.
El personal estatutario puede clasificarse en:
- Personal estatutario fijo, que desempenya sus servicios con caracter permanente, 
tras la superacion de un proceso selectivo y su nombramiento correspondiente.
- Personal estatutario temporal, nombrado por razones de necesidad, de urgencia o para el desarrollo de programas 
de caracter temporal, coyuntural o extraordinario. ")
)

(defrule consejos_r 
   (and
   (oposiciones si)
   (boe 5))
   =>
   (printout t crlf crlf crlf "Administraciones en Espanya: " crlf
						"1)Administracion General del Estado"crlf
						"2)Administracion Autonomica"crlf
						"3)Administracion Local"crlf
						"4)Administracion Institucional (Organismos Publicos)"crlf
						"5)Union Europea"crlf
						"1/2/3/4 >")
   (assert (consejo (read) ))
)

(defrule general_r 
   (and
   (oposiciones si)
   (boe 5)
   (consejo 1)
   )
   =>
   (printout t crlf crlf crlf "Administracion General del Estado" crlf
						      "================================"crlf
    "La Administracion General del Estado integra el conjunto de organos administrativos que ejercen sobre todo 
el territorio nacional las competencias que la Constitucion atribuye al Estado. La Administracion ejecuta materialmente
la politica del Gobierno. Con el objeto de llevar a cabo este proposito, se organiza en Ministerios, con sede central 
en Madrid y dotados de servicios perifericos en el resto del territorio nacional.
La Administracion General del Estado integra el conjunto de organos administrativos que ejercen sobre todo el territorio
nacional las competencias que la Constitucion atribuye al Estado. La Administracion ejecuta materialmente la politica del Gobierno.
Con el objeto de llevar a cabo este proposito se organiza en Ministerios, con sede central en Madrid y dotados de servicios
perifericos en el resto del territorio nacional. ")

)

(defrule autonomica_r 
   (and
   (oposiciones si)
   (boe 5)
   (consejo 2)
   )
   =>
   (printout t crlf crlf crlf "Administracion Autonomica" crlf
						      "========================="crlf
" Las Administraciones Publicas Autonomicas ejercen competencias en las materias definidas en sus respectivos Estatutos.
Cada Comunidad Autonoma ha establecido su propio modelo de organizacion administrativa estructurada en Consejerias, que
cuentan con servicios centrales y territoriales (provinciales y/o comarcales).
Actualmente existen 17 Comunidades Autonomas: Andalucia, Aragon, Asturias, Baleares, Canarias, Cantabria, Castilla y Leon,
Castilla-La Mancha, Catalunya, Extremadura, Galicia, Comunidad Foral de Navarra, Comunidad de Madrid, Comunidad Valenciana,
Region de Murcia, Pais Vasco y La Rioja. Ademas, Ceuta y Melilla se constituyeron en ciudades autonomas dotadas del correspondiente estatuto.")
)
   
(defrule local_r 
   (and
   (oposiciones si)
   (boe 5)
   (consejo 3)
   )
   =>
   (printout t crlf crlf crlf "Administracion Local" crlf
						      "===================="crlf
 "Las entidades locales basicas reconocidas en la Constitucion son el Municipio, la Provincia y la Isla (en los archipielagos balear y canario).
Ademas pueden crearse otras entidades como comarcas, areas metropolitanas o mancomunidades de municipios. En el Ministerio
de Administraciones Publicas se puede consultar el registro de entidades locales.
En los municipios, el gobierno y la administracion corresponden al Ayuntamiento, formado por el Alcalde y los Concejales;
en las provincias, a la Diputacion; y en las islas, a los Cabildos o Consejos Insulares. ")
)
   
(defrule institucional_r 
   (and
   (oposiciones si)
   (boe 5)
   (consejo 4)
   )
   =>
   (printout t crlf crlf crlf "Administracion Institucional (Organismos Publicos)" crlf
						      "=================================================="crlf
 "El termino tradicional Administracion Institucional ha sido reemplazado por la denominacion generica «Organismos Publicos»,
que agrupa a todas las Entidades de Derecho Publico dependientes o vinculadas a la Administracion General del Estado.
De manera generica se pueden definir como el conjunto de organizaciones dotadas de personalidad juridica publica propia, 
autonomia financiera y de gestion, de que se sirven las Administraciones territoriales (estatal, autonomica y local) para
la prestacion de un servicio o actividad administrativa en regimen de descentralizacion funcional.

Partiendo del concepto general, se distinguen dos modelos basicos: Organismos autonomos y Entidades publicas empresariales.
Los primeros realizan actividades fundamentalmente administrativas y se someten plenamente al Derecho Publico; mientras 
que los segundos realizan actividades de prestacion de servicios o produccion de bienes susceptibles de contraprestacion
economica y, aun cuando son regidos en general por el Derecho Privado, les resulta aplicable el regimen de Derecho
Publico, en relacion con el ejercicio de potestades publicas y con determinados aspectos de su funcionamiento.
Tambien existen "sociedades mercantiles" y otras entidades institucionales, que se rigen por una regulacion especifica.
Ley 6/1997, de 14 de Abril, de Organizacion y Funcionamiento de la Administracion General del Estado.
Disposiciones adicionales 6ª a 10ª y 12ª de LOFAGE. ")
)

(defrule europea_r 
   (and
   (oposiciones si)
   (boe 5)
   (consejo 5)
   )
   =>
   (printout t crlf crlf crlf "Union Europea" crlf
						      "============="crlf
 "La Union Europea (UE) es una estructura juridica compleja, integrada por tres organizaciones internacionales
(las Comunidades Europeas) y un sistema institucionalizado de cooperacion entre sus Estados Miembros. 
En la actualidad, forman parte de la misma: Alemania, Austria, Belgica, Bulgaria, Chipre, Dinamarca,
Eslovaquia, Eslovenia, Espanya, Estonia, Finlandia, Francia, Grecia, Hungria, Irlanda, Italia, Letonia,
Lituania, Luxemburgo, Malta, Paises Bajos, Polonia, Portugal, Reino Unido, Republica Checa, Rumania y Suecia.
Los Tratados constitutivos establecen cinco instituciones, cada de ellas con una funcion especifica, asi
como diversas instituciones y otros organos de la Union Europea:

- El Parlamento Europeo (elegido por los ciudadanos de los Estados miembros) participa en el proceso legislativo 
junto con el Consejo y ejerce el control politico sobre la Comision.
- El Consejo de la Union Europea (formado por representantes de todos los Gobiernos de los Estados miembros) 
dispone del principal poder de decision.
- La Comision Europea representa y defiende los intereses de Europa en conjunto. Es independiente de los 
Gobiernos nacionales. Tiene competencias ejecutivas, que ejerce a traves de la Administracion comunitaria.
- El Tribunal de Justicia (compuesto por un juez de cada Estado miembro) garantiza el respeto y la correcta 
interpretacion del Derecho Comunitario en todos los paises de la UE.
- El Tribunal de Cuentas efectua el control de la legalidad y la regularidad de la gestion del presupuesto de la UE.

Ademas, existen organos auxiliares que desarrollan funciones consultivas (Comite Economico y Social Europeo, 
que debe dar su opinion sobre las propuestas de decisiones de la UE en materia de empleo, gastos sociales,
formacion profesional, etc.; Comite de las Regiones, sobre las decisiones de la Union que tienen una 
repercusion directa a escala local o regional en sectores tales como transportes, sanidad, empleo o educacion),
de defensa de los derechos de los ciudadanos (Defensor del Pueblo) y financieras (Banco Central Europeo, 
Banco Europeo de Inversiones), asi como distintas agencias y organismos especializados.")
)

;/*---------------------------------------------------------------------------------------------------------------------------*/
;REGLAS 3erº NIVEL OPOSICIONES no
;===============================


(defrule irpf_r 
   (and
   (oposiciones no)
   (trabajar A)
   (autonomo I)
   )
    =>
   (printout t crlf crlf crlf "Cotizaciones, IRPF, IVA...  " crlf
                   "==========================" crlf crlf
"El mayor dolor de cabeza para los trabajadores que optan por ser autonomos esta relacionado 
con las cuotas mensuales a pagar y el significado de cada desembolso. "crlf
"Asi, los trabajadores independientes cotizan por medio de una cuota que no depende
del volumen de ingresos, sino de la base de cotizacion elegida por el autonomo. La 
cuota tambien depende de las coberturas que se han contratado: unicamente por contingencias comunes o profesionales.
Hay una base minima de cotizacion de unos 800 euros y una maxima de casi 3.000 euros, 
sobre las cuales el autonomo debe abonar un 26,5% o un 29,8%, dependiendo de si ha elegido que le 
cubra la incapacidad temporal o no. El pago de las cuotas es mensual y la cotizacion otorga el derecho a percibir
una prestacion por incapacidad, por baja maternal y jubilacion. 
Por otra parte, se debe conocer el concepto de dos impuestos fundamentales: el IRPF y el IVA,
los cuales se abonan trimestralmente: "crlf
"-	IRPF: El abono del impuesto a las personas fisicas representa la via a traves de la cual el autonomo declara sus rentas. 
Puede ser la diferencia entre los gastos y los ingresos mas el 20% (estimacion directa), o un modulo que determina la Agencia 
Tributaria (estimacion objetiva)."crlf 
"-	IVA: Se trata de un tributo de naturaleza indirecta que recae sobre el consumo y grava, en la forma y condiciones
previstas por ley, las ventas de bienes y prestaciones de servicios efectuadas por empresarios y profesionales, 
asi como las adquisiciones intracomunitarias o las importaciones de bienes. Ademas hay que diferenciar entre el 
IVA propiamente dicho (diferencia entre ingresos y gastos) y el IVA simplificado que se paga por modulos.  ")

)

(defrule pasos_r 
   (and
   (oposiciones no)
   (trabajar A)
   (autonomo P)
   )
    =>
   (printout t crlf crlf crlf "Pasos a seguir para convertirse en autonomo:  " crlf
							  "============================================" crlf crlf
"Los requisitos legales con que deben cumplir aquellos aspirantes a autonomos, son las siguientes: 
-	Licencia de Apertura y Licencia de Actividades e Instalaciones: este tramite, que se formaliza 
en el Ayuntamiento correspondiente, es necesario para poder registrar una empresa y debe ir acompanyado 
de un proyecto de instalacion. Sin embargo, no todas las actividades requieren una licencia: por ejemplo 
un profesor que imparte clases en centros educativos no propios, no esta obligado a tramitarla. "crlf
"-	Alta en el impuesto de actividades economicas (I.A.E): es una tasa que grava el ejercicio de la 
actividad profesional. Clasifica las actividades empresariales y profesionales a traves de un codigo 
que se llama "epigrafe de la actividad". Se realiza en la Agencia Tributaria. "crlf
"-	Alta Censal y opcion del regimen fiscal: tambien se tramita en la Agencia Tributaria. Se debe
completar el impreso 036 en el se opta por el regimen fiscal correspondiente e indicar la actividad 
que se va a realizar y los datos de la empresa. "crlf
"-	Inscripcion/afiliacion en el Regimen Especial de Autonomos de la Seguridad Social: se realiza en
Tesoreria General de la Seguridad Social (TGSS). Desde el alta censal, se dispone de un periodo de 30 
dias naturales para realizar la inscripcion. Se ha de llevar el modelo 036, el DNI y el modelo TA521/1 
de alta en el Regimen Especial de Trabajadores Autonomos. Este ultimo podra cumplimentarse en la misma 
oficina de la TGSS. ")				
)



(defrule paro_r 
   (and
   (oposiciones no)
   (trabajar C)
   (paroEmpleo I))
    =>
   (printout t crlf crlf crlf " Solicitud de puesto de trabajo a traves del Servicio Publico de Empleo Estatal (INEM)"crlf
							  " ====================================================================================="crlf
							  " Podran solicitar un puesto de trabajo en el INEM (SPEE):"crlf
							  "·Tener 16 anyos cumplidos.
·Ser espanyol o miembro de la Union Europea (o Espacio Economico Europeo), o trabajador no  comunitario en posesion de permiso 
de trabajo y/o residencia en vigor.
·No estar imposibilitado para el trabajo.
Se debera presentar la siguiente documentacion:
·Documento Nacional de Identidad, Tarjeta de Identidad o Pasaporte en vigor, si es espanyol o  miembro de la Union Europea (o EEE).
·Permiso de trabajo/residencia en vigor, si no es trabajador comunitario.
·Cartilla/Tarjeta de la Seguridad Social, si ha trabajado anteriormente en Espanya.
·Justificante de titulacion profesional o academica, si posee alguna.
·Certificado de minusvalia en su caso.")
)



(defrule estatal_r 
   (and
   (oposiciones no)
   (trabajar C)
   (paroEmpleo S))
    =>
   (printout t crlf crlf crlf "el Servicio Publico de Empleo Estatal (SPEE)"crlf
							  "============================================"crlf
"  es el organismo autonomo de la Administracion General del Estado al que se le encomienda la ordenacion, desarrollo y 
seguimiento de los programas y medidas de la politica de empleo.  Como organismo autonomo tiene personalidad juridica propia e 
independiente de la Administracion General del Estado, plena capacidad de obrar para el cumplimiento de sus funciones, patrimonio
y tesoreria propios.El Servicio Publico de Empleo Estatal gestiona las politicas activas de empleo relativas a la intermediacion y 
colocacion en el mercado de trabajo,fomento de empleo en el ambito estatal, formacion profesional y continua, mientras la gestion 
de la misma no haya sido objeto de transferencia a las comunidades autonomas.Se entiende por Servicio Publico de Empleo de las 
comunidades autonomas los organos o entidades de las mismas a los que dichas Administraciones encomienden, en sus respectivos 
ambitos territoriales, el ejercicio de las funciones necesarias para la gestion de la intermediacion laboral y de las politicas
activas de empleo. " crlf)
   
)






 
;RESET Y RUN
;===========   

(reset)
(run)