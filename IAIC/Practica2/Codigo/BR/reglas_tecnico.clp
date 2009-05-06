;Practica IAIC 
;Ignacio Arroyo G�mez, Luis Alberto Inga Rivera, Luis Villacastin Candil
;4�C
;Grupo 10

;TEXTO DE INTRODUCCION ----------------------------------------
 

  (printout t "Bienvenido al ayudante de CV" crlf
			   "te intentaremos ayudar a" crlf
			   "construir tu CV de la forma mas optima")


; Regla 0 ----------------------------------------------------
(defrule inicio 
=>
(printout t crlf
"Que estudios tienes?" crlf crlf
"0) ninguno" crlf
"1) secundaria" crlf
"2) Bachiller" crlf
"3) FP grado medio/alto" crlf
"4) Diplomatura/licenciatura" crlf
"introduce: 1, 2, 3 o 4: ")
(assert (nivel (read)))
)

(defrule noEstudios 
	(nivel 0)
	=>
	(printout t crlf "�Tienes experiencia profesional? si/no ")
	(assert (exp (read)))
)

(defrule noEstudios_noExp
	(and
	(exp no)
	(nivel 0))	
	=>
	(printout t "Bueno, todo el mundo tiene su primera vez, te recomiendo lo siguiente: Deberias ir a la escuela de adultos y elegir alg�n curso que te guste, para as� tener algo de conocimientos y/o experiencia.")
	(printout t "�Sabes alg�n idioma? si/no ")
	(assert (idioma (read)))
)

(defrule noEstudios_siExp (and(exp si)(nivel 0))=>
(printout t "Debes poner en tu CV toda esa experiencia y buscar alg�n curso para trabajadores y asi tener alg�n estudio. Te recomiendo sacarte el graduado escolar cuanto antes, es lo m�nimo, ahora es m�s facil que nunca.")
(printout t "�Sabes alg�n idioma? si/no ")
(assert (idioma (read)))
)

(defrule secundaria (nivel 1)=>
(printout t "�Tienes experiencia profesional? si/no ")
(assert (exp (read)))
)

(defrule secundaria_noExp(and(exp no)(nivel 1))=>
(printout t "Pon esos estudios en tu CV ordenados por fecha, si te apetece aprender m�s, te sugiero un bachillerato o una FP si quieres tambi�n trabajar despu�s de estudiar.")
(printout t "�Sabes alg�n idioma? si/no ")
(assert (idioma (read)))
)

(defrule secundaria_siExp(and(exp si)(nivel 1))=>
(printout t "Pon esos estudios en tu CV ordenados por fecha, si te apetece aprender m�s, te sugiero un bachillerato o una FP. Si has trabajado en varios sitios, tienes que poner m�s enfasis en �sa experiencia. Poniendo la experiencia primero y luego tu t�tulo de secundaria.")
(printout t "�Sabes alg�n idioma? si/no " )
(assert (idioma (read)))
)


(defrule bachiller (nivel 2)=>
(printout t "�Tienes experiencia profesional? si/no ")
(assert (exp (read)))
)

(defrule bachiller_noExp (and(nivel 2)(exp no))=>
(printout t "Es normal no tener experiencia profesional si has hecho un bachillerato, Resalta tu t�tulo en el CV, te recomiendo hacer una FP de grado superior o si te atreves con una carrera universitaria, adelante !!" crlf)
(printout t "�Sabes alg�n idioma? si/no " )
(assert (idioma (read)))
)

(defrule bachiller_siExp(and(exp si)(nivel 2))=>
(printout t "Dado que tu nivel de estudios es bastante alto, te recomiendo poner primero el apartado de Conocimientos y luego tu experiencia profesional.")
(printout t "�Sabes alg�n idioma? si/no ")
(assert (idioma (read)))
)

(defrule FP (nivel 3)=>
(printout t "�Tienes experiencia profesional? si/no " )
(assert (exp (read)))
)

(defrule FP_noExp (and(nivel 3)(exp no))=>
(printout t "Viniendo de una FP seguro que has realizado pr�cticas en alguna empresa relacionada con un formaci�n, te recomiendo que despu�s del apartado de Nivel de Estudios, pongas esa experiencia."crlf)
(printout t "�Sabes alg�n idioma? si/no " )
(assert (idioma (read)))
)

(defrule FP_siExp (and(nivel 3)(exp si))=>
(printout t "Viniendo de una FP seguro que has realizado pr�cticas en alguna empresa relacionada con un formaci�n, te recomiendo que despu�s del apartado de Nivel de Estudios, pongas esa experiencia y despu�s pon tu experiencia profesional que tienes desp�es de haber realizado esas pr�cticas hasta ahora." crlf)
(printout t "�Sabes alg�n idioma? si/no " )
(assert (idioma (read)))
)

(defrule universitario (nivel 4)=>
(printout t "�Tienes experiencia profesional? si/no " )
(assert (exp (read)))
)

(defrule universitario_noExp (and(nivel 4)(exp no))=>
(printout t "Es normal que no tengas experiencia despu�s de haber cursado una carrera. Como primer empleo te recomiendo un puesto de becario en pr�cticas, ya que no tendr�s responsabilidades y aprender�s mucho. Pregunta en tu facultad o escuela. Si quieres seguir estudiando mira alg�n Master."crlf)
(printout t "�Sabes alg�n idioma? si/no " )
(assert (idioma (read)))
)

(defrule universitario_siExp (and(nivel 4)(exp si))=>
(printout t "Tienes un buen perfil, con estudios y experiencia. Te recomiendo poner primero tu Nivel de estudios seguido de tu experiencia laboral, no olvides poner tambien si realizaste alguna pr�ctica de formaci�n." crlf)
(printout t "�Sabes alg�n idioma? si/no " )
(assert (idioma (read)))
)

(defrule idioma1 (idioma no)=>
(printout t "Bueno, nunca es tarde para aprender alg�n idioma o mejorar lo que sepas. A parte de lo que te dije antes, si te apetece te recomiendo que te apuntes a alg�n curso de ingl�s o de alg�n idioma extranjero para as� resaltar en tu CV." crlf)
(printout t "�Tienes alg�n carnet de conducir? si/no ")
(assert (carnet (read)))
)

(defrule idioma2 (idioma si)=>
(printout t "Aprovecha ese conocimiento para rellenar m�s cosas en tu CV, est� muy valorado que sepas un idioma !!, ponlo justo debajo de tu nivel de estudios. Y no olvides poner al lado el nivel que tienes!!" crlf)
(printout t "�Tienes alg�n carnet de conducir? si/no " )
(assert (carnet (read)))
)

(defrule carne1 (carnet no)=>
(printout t "Bueno, es lo menos importante de tu CV, pero a�n as� te ayudar� mucho, porque muchas empresas requieren que alg�n trabajador pueda conducir." crlf)
)

(defrule carne1 (carnet si)=>
(printout t "Aunque sea lo menos importante, si lo tienes te recomiendo que lo pongas, siempre es bueno saber si un candidato puede conducir, algunas empresas les interesa esto, y si pones los a�os que llevas mejor que mejor." crlf)
)
