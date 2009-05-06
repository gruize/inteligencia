;SERGIO ORTIZ GIL
;DANIEL SAÑUDO VACAS
;RAUL VIUDEZ CORROTO
	
;	introducidos por el usuario:
;	anios : <27, 27-45, >45
;	experiencia: estudiando, poca, mucha
;	motivacion: dinero, realizacion
;
;	obtenidos:
;	edad : joven, adulto, mayor
;	tipoPersona : joven trabajador, promesa, competente, inexperto.
;


; Modificaciones, extraido el contenido que se imprimia por pantalla
; Añadido ANDs a las condiciones para que funcionara correctamente
; Añadido retract para dejar en ultima instancia la informacion que nos interesa
; Insertados nuevos hechos para las conclusiones



(defrule R1 (anios <27) => (assert (edad joven)) (retract-string "(anios <27)") )
(defrule R2 (anios 27-45) => (assert (edad adulto)) (retract-string "(anios <27)") )
(defrule R3 (anios >45) => (assert (edad mayor)) (retract-string "(anios >45)") )
(defrule R4 (and (edad joven) (experiencia mucha)) => (assert (tipoPersona joven trabajador)) (retract-string "(edad joven)") (retract-string "(experiencia mucha)") )
(defrule R5 (and (edad joven) (experiencia estudiando)) =>(assert (tipoPersona promesa)) (retract-string "(edad joven)") (retract-string "(experiencia estudiando)"))
(defrule R6 (and (edad joven)(experiencia poca)) => (assert (tipoPersona inexperto)) (retract-string "(edad joven)")(retract-string "(experiencia poca)"))
(defrule R7 (and (edad adulto) (experiencia estudiando)) => (assert (conclusion AdulEstud))(retract-string "(edad adulto)") (retract-string "(experiencia estudiando)") )
(defrule R8 (and (edad adulto) (experiencia poca) ) =>  (assert (conclusion AdulPoca)) (retract-string "(edad adulto)") (retract-string "(experiencia poca)") )
(defrule R9 (and (edad adulto) (experiencia mucha) ) => (assert (tipoPersona competente)) (retract-string "(edad adulto)") (retract-string "(experiencia mucha)"))
(defrule R10 (and (edad mayor) (experiencia mucha) ) => (assert (tipoPersona competente)) (retract-string "(experiencia mucha)")(retract-string "(edad mayor)"))
(defrule R11 (and (edad mayor) (experiencia poca) )	=>  (assert (conclusion MayorPocaexp)) (retract-string "(edad mayor)")(retract-string "(experiencia poca)") )
(defrule R12 (and (tipoPersona inexperto) (motivacion realizacion) )=> (assert(conclusion InexRealiza))	(retract-string "(tipoPersona inexperto)") (retract-string "(motivacion realización personal)"))
(defrule R13 (and (tipoPersona inexperto) (motivacion dinero) )	=> (retract-string "(tipoPersona inexperto) ")(retract-string "(motivacion dinero)")(assert (conclusion)) )
(defrule R14 (and (tipoPersona promesa) (motivacion realizacion) ) => (retract-string "(motivacion realizacion)")(retract-string "(tipoPersona promesa)")(assert (conclusion PromesRealiz)) )
(defrule R15 (and (tipoPersona promesa) (motivacion dinero) ) => (retract-string "(tipoPersona promesa)")(retract-string "(motivacion dinero)")(assert (conclusion PromesDine)) )
(defrule R16 (and (tipoPersona competente) (motivacion realizacion) ) => (retract-string "(tipoPersona competente)")(retract-string "(motivacion realizacion)")(assert (conclusion CompeRealiz)) )
(defrule R17 (and (tipoPersona competente) (motivacion dinero) ) => (retract-string "(motivacion dinero)")(retract-string "(tipoPersona competente)")(assert (conclusion DineCompe)) )
(defrule R18 (and (tipoPersona joven trabajador) (motivacion realizacion) ) => (retract-string "(motivacion realizacion) ")(retract-string "(tipoPersona joven trabajador)")(assert (conclusion TrabaRealiz)))
(defrule R19 (and (tipoPersona joven trabajador) (motivacion dinero) )	=> (retract-string "(tipoPersona joven trabajador) ")(retract-string "(motivacion dinero) ")(assert (conclusion TrabaDine)) )