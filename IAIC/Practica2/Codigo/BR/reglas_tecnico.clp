;Practica IAIC 
;Ignacio Arroyo Gómez, Luis Alberto Inga Rivera, Luis Villacastin Candil
;4ºC
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
	(printout t crlf "¿Tienes experiencia profesional? si/no ")
	(assert (exp (read)))
)

(defrule noEstudios_noExp
	(and
	(exp no)
	(nivel 0))	
	=>
	(printout t "Bueno, todo el mundo tiene su primera vez, te recomiendo lo siguiente: Deberias ir a la escuela de adultos y elegir algún curso que te guste, para así tener algo de conocimientos y/o experiencia.")
	(printout t "¿Sabes algún idioma? si/no ")
	(assert (idioma (read)))
)

(defrule noEstudios_siExp (and(exp si)(nivel 0))=>
(printout t "Debes poner en tu CV toda esa experiencia y buscar algún curso para trabajadores y asi tener algún estudio. Te recomiendo sacarte el graduado escolar cuanto antes, es lo mínimo, ahora es más facil que nunca.")
(printout t "¿Sabes algún idioma? si/no ")
(assert (idioma (read)))
)

(defrule secundaria (nivel 1)=>
(printout t "¿Tienes experiencia profesional? si/no ")
(assert (exp (read)))
)

(defrule secundaria_noExp(and(exp no)(nivel 1))=>
(printout t "Pon esos estudios en tu CV ordenados por fecha, si te apetece aprender más, te sugiero un bachillerato o una FP si quieres también trabajar después de estudiar.")
(printout t "¿Sabes algún idioma? si/no ")
(assert (idioma (read)))
)

(defrule secundaria_siExp(and(exp si)(nivel 1))=>
(printout t "Pon esos estudios en tu CV ordenados por fecha, si te apetece aprender más, te sugiero un bachillerato o una FP. Si has trabajado en varios sitios, tienes que poner más enfasis en ésa experiencia. Poniendo la experiencia primero y luego tu título de secundaria.")
(printout t "¿Sabes algún idioma? si/no " )
(assert (idioma (read)))
)


(defrule bachiller (nivel 2)=>
(printout t "¿Tienes experiencia profesional? si/no ")
(assert (exp (read)))
)

(defrule bachiller_noExp (and(nivel 2)(exp no))=>
(printout t "Es normal no tener experiencia profesional si has hecho un bachillerato, Resalta tu título en el CV, te recomiendo hacer una FP de grado superior o si te atreves con una carrera universitaria, adelante !!" crlf)
(printout t "¿Sabes algún idioma? si/no " )
(assert (idioma (read)))
)

(defrule bachiller_siExp(and(exp si)(nivel 2))=>
(printout t "Dado que tu nivel de estudios es bastante alto, te recomiendo poner primero el apartado de Conocimientos y luego tu experiencia profesional.")
(printout t "¿Sabes algún idioma? si/no ")
(assert (idioma (read)))
)

(defrule FP (nivel 3)=>
(printout t "¿Tienes experiencia profesional? si/no " )
(assert (exp (read)))
)

(defrule FP_noExp (and(nivel 3)(exp no))=>
(printout t "Viniendo de una FP seguro que has realizado prácticas en alguna empresa relacionada con un formación, te recomiendo que después del apartado de Nivel de Estudios, pongas esa experiencia."crlf)
(printout t "¿Sabes algún idioma? si/no " )
(assert (idioma (read)))
)

(defrule FP_siExp (and(nivel 3)(exp si))=>
(printout t "Viniendo de una FP seguro que has realizado prácticas en alguna empresa relacionada con un formación, te recomiendo que después del apartado de Nivel de Estudios, pongas esa experiencia y después pon tu experiencia profesional que tienes despúes de haber realizado esas prácticas hasta ahora." crlf)
(printout t "¿Sabes algún idioma? si/no " )
(assert (idioma (read)))
)

(defrule universitario (nivel 4)=>
(printout t "¿Tienes experiencia profesional? si/no " )
(assert (exp (read)))
)

(defrule universitario_noExp (and(nivel 4)(exp no))=>
(printout t "Es normal que no tengas experiencia después de haber cursado una carrera. Como primer empleo te recomiendo un puesto de becario en prácticas, ya que no tendrás responsabilidades y aprenderás mucho. Pregunta en tu facultad o escuela. Si quieres seguir estudiando mira algún Master."crlf)
(printout t "¿Sabes algún idioma? si/no " )
(assert (idioma (read)))
)

(defrule universitario_siExp (and(nivel 4)(exp si))=>
(printout t "Tienes un buen perfil, con estudios y experiencia. Te recomiendo poner primero tu Nivel de estudios seguido de tu experiencia laboral, no olvides poner tambien si realizaste alguna práctica de formación." crlf)
(printout t "¿Sabes algún idioma? si/no " )
(assert (idioma (read)))
)

(defrule idioma1 (idioma no)=>
(printout t "Bueno, nunca es tarde para aprender algún idioma o mejorar lo que sepas. A parte de lo que te dije antes, si te apetece te recomiendo que te apuntes a algún curso de inglés o de algún idioma extranjero para así resaltar en tu CV." crlf)
(printout t "¿Tienes algún carnet de conducir? si/no ")
(assert (carnet (read)))
)

(defrule idioma2 (idioma si)=>
(printout t "Aprovecha ese conocimiento para rellenar más cosas en tu CV, está muy valorado que sepas un idioma !!, ponlo justo debajo de tu nivel de estudios. Y no olvides poner al lado el nivel que tienes!!" crlf)
(printout t "¿Tienes algún carnet de conducir? si/no " )
(assert (carnet (read)))
)

(defrule carne1 (carnet no)=>
(printout t "Bueno, es lo menos importante de tu CV, pero aún así te ayudará mucho, porque muchas empresas requieren que algún trabajador pueda conducir." crlf)
)

(defrule carne1 (carnet si)=>
(printout t "Aunque sea lo menos importante, si lo tienes te recomiendo que lo pongas, siempre es bueno saber si un candidato puede conducir, algunas empresas les interesa esto, y si pones los años que llevas mejor que mejor." crlf)
)
