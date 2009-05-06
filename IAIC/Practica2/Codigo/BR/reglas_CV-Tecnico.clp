Componentes:
Victor Acón Aceña
Eva García Vega
Alfonso Jiménez Regalado
Javier Sangrador Martín

GRUPO 23


; Hechos:
; -disponibilidad (manana, tarde, completa)
; -experiencia laboral (si,no)
; -tiempo de trabajando {nula,1/2,1,2,mas}
; -trabajo que buscas {programador,diseñador grafico,jefe proyecto,administrador bbdd,analista}
; -edad {20_30,30_40,40_50,mas}
; -formación  {formación profesional, ingeniería, licenciatura, diplomatura,no}
; -formación escolar {graduado escolar, eso, bachillerato}
; -coche {si,no}
; -ingles (si,no)
; -idiomaIngles {bajo,medio,alto,nativo}

(defrule inicio
	=>
	(printout t crlf crlf "[ASESOR]:" 
			crlf "- Debera responder a algunas preguntas para que pueda asesorarle lo mejor posible "
			"- Una vez responda dichas preguntas, iremos concretando mas cosas (con mas preguntas) "
			"para que tenga un curriculum acorde a sus necesidades." crlf crlf)

	(printout t crlf crlf 
			"o) Que edad tiene? (20_30, 30_40, 40_50, mas)" crlf)
	(assert (edad (read)))

	(printout t crlf crlf 
			"o) Ha trabajado alguna vez? (si,no)" crlf)
	(assert (expTrabajo (read)))

	(printout t crlf crlf 
			"o) Que tipo de trabajo busca? {programador,diseñador grafico,"
			"jefe proyecto,administrador bbdd,analista}" crlf)
	(assert (trabajo (read)))

	(printout t crlf crlf 
			"o) Cual es su formacion escolar? (graduado escolar, eso, bachillerato)" crlf)
	(assert (formacionE (read)))	

	(printout t crlf crlf 
			"o) Cual es su formacion ademas de la escolar? (diplomatura,licenciatura,ingenieria,"
			"fp, ninguna)" crlf)
	(assert (formacion (read)))	

	(printout t crlf crlf 
			"o) Habla ingles? (si, no)" crlf)
	(assert (ingles (read)))

	(printout t crlf crlf 
			"o) Cual es su disponibilidad? (manana, tarde, completa)" crlf)
	(assert (disponibilidad (read)))

	(printout t crlf crlf 
			"o) Tiene coche? (si,no)" crlf)
	(assert (coche (read)))	
)


(defrule experiencia_nula
	(and(expTrabajo no)(or(formacion ingenieria)(formacion licenciatura)(formacion diplomatura)))
	=>
	(printout t crlf crlf "[ASESOR] (experiencia,formacion)"  
			crlf "Bueno no se preocupe, que nadie nace sabiendo...pero "
			"no mencione nada acerca de su experiencia en el curriculum. "
			"Recuerde incluir que es estudiante universitario y su interes en aprender, "
			"puede ser positivo para su curriculum.¿ Esta ahora en proceso de busqueda de trabajo? (si/no)" crlf crlf)
	(assert (buscoCurro (read)))
)

(defrule info_curro
	(and(buscoCurro si)(or(formacion ingenieria)(formacion licenciatura)(formacion diplomatura)))
	=>
	(printout t crlf crlf "Acuda al INEM para apuntarse como solicitante de empleo"
			crlf " Visite las siguientes páginas, le seran muy utiles a la hora de encontrar empleo"
			crlf " PrimerEmpleo, Infojobs, Tecnoempleo..., podra encontrar becas y primero empleos acordes a su experiencia "crlf)
	(assert (infoCarrera si))
)

(defrule info_carrera
	(infoCarrera si)
	=>
	(printout t crlf crlf "Aproveche los recursos que su facultad pone a su alcance"
			crlf " apuntese a cursos y seminarios, siempre luce en el curriculum y muestra que es una persona activa"
			crlf " Si esta buscando un perfil tecnico, muestre claramente en el cv los lenguajes "
			     " de programacion que conoce, su nivel de dominio y demas conocimientos adquiridos en la"
			     " carrera que crea oportuno destacar " crlf)
)

(defrule sin_carrera
	(and(formacion nula)(edad 20_30)(formacionE bachillerato))
	=>
	(printout t crlf crlf "Con su formacion puede acceder a cursar formacion profesional de grado superior"
			crlf " Son 2 anios de formacion practica y teorica, con una ultima etapa de formacion"
			crlf " en empresa. Con este titulo puede acceder directamente a estudios universitarios " crlf)
)

(defrule experiencia_medio_uno
	(or (expTiempo 1/2)(expTiempo 1))
	=>
	(printout t crlf crlf "[ASESOR] (experiencia)" 
			crlf "Ya tiene suficiente experiencia como para poder mencionarla "
			"en su curriculum, ademas de anadir alguna empresas en la que halla trabajado." crlf crlf
			"o) Ha ido de empresa en empresa sin asentarse en ninguna y asi es como ha "
			"conseguido esos anos de experiencia o por el contrario ha estado varios "
			"anos en la misma? (variasE, mismaE)" crlf)
	(assert (modoEmpresas (read)))
)	

(defrule expeciencia_alta
	(or (expTiempo 2)(expTiempo mas))
	=>
	(printout t crlf crlf "[ASESOR] (experiencia)" 
			crlf "Tiene bastante experiencia, asi que debera ponerlo en el curriculum." crlf crlf
			"o) Ha ido de empresa en empresa sin asentarse en ninguna y asi es como ha "
			"conseguido esos anos de experiencia o por el contrario ha estado varios "
			"anos en la misma? (variasE, mismaE)" crlf)
	(assert (modoEmpresas (read)))
)

(defrule empresas_varias
	(modoEmpresas variasE)
	=>
	(printout t crlf crlf "[ASESOR] (modoEmpresas)"  
			crlf "No le recomiendo que ponga las empresas en las que ha trabajado menos de un anio "
			"o no tenga carta de recomendacion de la misma, no es interesante en su curriculum."
			"indique el puesto en el que tiene mas experiencia( programador, con BBDDs, u otro)" crlf crlf)
	(assert(curroAnterior(read)))
)

(defrule empresas_misma
	(modoEmpresas mismaE)
	=>
	(printout t crlf crlf "[ASESOR] (modoEmpresas)" 
			crlf "Ponga la empresa en la que ha estado junto con el tiempo que ha estado trabajando en ella,"
			"las labores desempeñadas y conocimientos adquiridos." crlf
			"o) Trabajo de programador, con BBDDs, u otro ?"crlf crlf)
	(assert(curroAnterior(read)))
)

(defrule exp_program
	(and(curroAnterior programador)(ExpTiempo mas)(formacion ingenieria))
	=>
	(printout t crlf crlf "[ASESOR] (tipo de trabajo BBDD)"  
			crlf "Puede empezar a optar a puestos de analista y jefe de proyecto. "
			"Estan bastante mejor pagados que los puestos de programador." crlf) 
)

(defrule exp_bd
	(and(curroAnterior BBDDs)(or(ExpTiempo 2)(Exptiempo mas)))
	=>
	(printout t crlf crlf "[ASESOR] (tipo de trabajo BBDD)"  
			crlf "Tiene suficiente experiencia como para optar por un puesto "
			"de administrador de bases de datos, el sueldo suele ser a partir de "
			"30.000 euros/ano , si no tiene ningun titulo que acredite esos conocimientos "
			"debera defender su experiencia en el tema" crlf crlf)
)

(defrule exp_otra
	(and(curroAnterior otra)(ExpTiempo mas)(formacion ingenieria)(ingles no)(edad 30_40))
	=>
	(printout t crlf crlf "[ASESOR] (edad)" 
			crlf "Aprender ingles es imprescindible si quiere optar por un puesto mayor. "
			"Todavia esta a tiempo de conseguir un ingles fluido, pida formacion en su empresa "
			"o apuntese a cursos intensivos" crlf crlf) 
)

(defrule edadBaja
	(edad 20_30)
	=>
	(printout t crlf crlf "[ASESOR] (edad)" 
			crlf "Refleje en el curriculum su iniciativa y ganas de aprender. "
			"Recalque los conocimientos adquiridos en sus estudios." crlf crlf)
)

(defrule inglesSI
	(ingles si)
	=>
	(printout t crlf crlf 		
			"o) Nivel de ingles? (bajo, medio, alto, nativo)" crlf)
	(assert (idiomaIngles (read)))
)

(defrule inglesNO
	(ingles no)
	=>
	(printout t crlf crlf "[ASESOR] (ingles)" 
			crlf "Deberia aprender ingles, le vendria muy bien para casi "
			"cualquier trabajo y para su curriculum." crlf crlf)
)

(defrule inglesAlto
	(or (idiomaIngles alto)(idiomaIngles nativo))
	=>
	(printout t crlf crlf "[ASESOR] (ingles)" 
			crlf " Destaque siempre este conocimiento, las empresas lo valoran mucho a la hora de escoger. "
			"Ponga en el curriculum los diplomas que tenga de centros reconocidos. "
			"Si sabe algun idioma que no sea ingles aunque sea poco, anadalo con nivel 'nociones basicas' ". crlf crlf)
)

(defrule inglesBajo
	(or(idiomaIngles bajo)(idiomaIngles medio))
	=>
	(printout t crlf crlf "[ASESOR] (ingles)" 
			crlf "Ponga las notas que saco en selectividad, si fueron buenas. " crlf
			"En caso contrario no las mencione." crlf crlf)
)

(defrule disp_coche_noformacion
	(and(disponibilidad completa)(coche si)(formacion ninguna))
	=>
	(printout t crlf crlf "[ASESOR] (disponibilidad, coche, formacion)" 
			crlf "Si carece de formacion fuera de la escolar, recalque el tema del coche y su disponibilidad "
			"por encima lo academico en su curriculum." crlf crlf)
)

(defrule expTrabajo_
	(expTrabajo si)
	=>
	(printout t crlf crlf "[ASESOR] (experiencia laboral)" crlf
			"o) Que experiencia tiene(en anos)? ( 1/2, 1, 2, mas)" crlf)
	(assert (expTiempo (read)))
)

(reset)
(run)