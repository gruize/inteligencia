(deftemplate Estado
"Estados de animo que tendremos de una persona en la memoria de trabajo"
(slot nombre)
(slot nivel)
(slot respuesta))

(defrule Inicial
=>
(printout t "¿Que podemos hacer por usted?Introduzca 1,2,3,4 o 5" crlf)
(printout t "(1)Ayudarle a buscar un trabajo"crlf)
(printout t "(2)Mejorar como persona"crlf )
(printout t "(3)Mejorar su vision de la vida"crlf )
(printout t "(4)Mejorar su condicion fisica"crlf )
(printout t "(5)Mejorar su formacion academica"crlf )
(assert (Estado (respuesta (read))))
)

(defrule buscaTrabajo
"Si la persona busca trabajo"
?estado <- (Estado {respuesta == 1})
=>
(assert(Estado (nombre buscaTrabajo) (nivel 0)))
(retract ?estado)
)

(defrule mejoraPersona
"Si la persona desea mejorar"
?estado <- (Estado {respuesta == 2})
=>
(assert(Estado (nombre mejoraPersona) (nivel 0)))
(retract ?estado)
)

(defrule mejoraVida
"Si la persona desea mejorar su vida"
?estado <- (Estado {respuesta == 3})
=>
(assert(Estado (nombre mejoraVida) (nivel 0)))
(retract ?estado)
)

(defrule condicionFisica
"Si la persona se quiere mejorar fisicamente"
?estado <- (Estado {respuesta == 4})
=>
(assert(Estado (nombre mejoraFisico) (nivel 0)))
(retract ?estado)
)

(defrule mejoraAcademica
"Si la persona quiere mejorar su formacion academica"
?estado <- (Estado {respuesta == 5})
=>
(assert(Estado (nombre mejoraAcademica) (nivel 0)))
(retract ?estado)
)

(defrule buscandoTrabajo
"Si la persona quiere encontrar trabajo"
?estado <- (Estado {nombre == buscaTrabajo} {nivel == 0})
=>
(printout t "¿Tienes hecho tu curriculum?SI(s),NO(n)")
(modify ?estado (nivel 1))
(assert(Estado (nombre Curriculum) (nivel 1) (respuesta (read))))
)

(defrule siCurriculum
"Si tiene curriculum ya puede buscar trabajo y mandar su curriculum"
?e1 <- (Estado {nombre == buscaTrabajo} {nivel == 1}) 
?e2 <- (Estado {nombre == Curriculum} {nivel == 1} {respuesta == s})
=>
(modify ?e1 (nivel 2))
(modify ?e2 (nivel 2))
(assert(Estado (nombre empiezaTrabajar) (nivel 2)))
)

(defrule noCurriculum
"Si tiene curriculum ya puede buscar trabajo y mandar su curriculum"
?e1 <- (Estado {nombre == buscaTrabajo} {nivel == 1}) 
?e2 <- (Estado {nombre == Curriculum} {nivel == 1} {respuesta == n})
=>
(modify ?e1 (nivel 2))
(modify ?e2 (nivel 2))
(assert(Estado (nombre hazCurriculum) (nivel 2)))
)

(defrule hazCurriculum
"Pequeñas instrucciones para hacer un curriculum"
?e1 <- (Estado {nombre == buscaTrabajo} {nivel == 2}) 
?e2 <- (Estado {nombre == Curriculum} {nivel == 2})
?e3 <- (Estado {nombre == hazCurriculum} {nivel == 2})
=>
(modify ?e1 (nivel 3))
(modify ?e2 (nivel 3))
(modify ?e3 (nivel 3))
(retract ?e1)
(retract ?e2)
(retract ?e3)
(printout t "Aqui te mostramos unos consejos para realizar un buen curriculum:"crlf)
(printout t "- Seguir una estructura: encabezamiento, datos personales, formacion y estudios, experiencia profesional.
- Se recomienda hacer hincapie en aquellas experiencias o formacion mas relacionadas con el puesto para el que presentas el curriculum.
- Todos los datos que se incluyan en el curriculum deben ser demostrables.
- Omite todos los aspectos negativos
- Omitir datos obvios, como los estudios que realizaste en las escuelas primaria, secundaria y bachillerato.

- Resaltar los logros alcanzados en el servicio social, practicas profesionales o trabajos anteriores, 
señalando la informacion que demuestre las competencias desarrolladas.
- No colocar sueldo pretendido a menos que la empresa o institucion lo solicite.
Si fuese el caso, investigar los rangos de sueldo del puesto deseado en relacion con la experiencia propia en ese rubro.
"crlf )
)

(defrule empiezaTrabajar
"Pequeñas instrucciones para hacer un curriculum"
?e1 <- (Estado {nombre == buscaTrabajo} {nivel == 2}) 
?e2 <- (Estado {nombre == Curriculum} {nivel == 2})
?e3 <- (Estado {nombre == empiezaTrabajar} {nivel == 2})
=>
(modify ?e1 (nivel 3))
(modify ?e2 (nivel 3))
(modify ?e3 (nivel 3))
(retract ?e1)
(retract ?e2)
(retract ?e3)
(printout t "Ya puedes empezar a trabajar"crlf)
(printout t "Aqui te mostramos unos consejos para intentar ayudarte a buscar trabajo"crlf)
(printout t "Busca en peridicos, revistas, internet,etc. empresas del sector en el que desees trabajar"crlf )
(printout t "A continuacion manda tu curriculum por la via que te pida la empresa"crlf )
(printout t "Recuerda mandar un buen curriculum, si no has seguido nuestros consejos"crlf )
(printout t "Despues de todo esto te toca esperar pacientemente"crlf )
(printout t "¡Mucha Suerte!"crlf )
)


(defrule mejorandoAcademicamente
"La persona va a mejorar su nivel academico"
?estado <- (Estado {nombre == mejoraAcademica} {nivel == 0})
=>
(printout t "¿Que nivel de estudios tienes terminado?Introduzca 1,2,3 o 4" crlf)
(printout t "(1)Graduado escolar"crlf)
(printout t "(2)ESO"crlf )
(printout t "(3)Bachicherato"crlf )
(printout t "(4)Universitario"crlf )
(modify ?estado (nivel 1))
(assert(Estado (nombre mejorandoAcademicamente) (nivel 1) (respuesta (read))))
)

(defrule Graduado
"Si tiene curriculum ya puede buscar trabajo y mandar su curriculum"
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 1}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 1} {respuesta == 1})
=>
(modify ?e1 (nivel 2))
(modify ?e2 (nivel 2))
(assert(Estado (nombre nivelGraduado) (nivel 2)))
)


(defrule nivelGraduado
"Pequeñas instrucciones para el universitario"
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 2}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 2})
?e3 <- (Estado {nombre == nivelGraduado} {nivel == 2})
=>
(modify ?e1 (nivel 3))
(modify ?e2 (nivel 3))
(modify ?e3 (nivel 3))
(retract ?e1)
(retract ?e2)
(retract ?e3)
(printout t "Si no te gusta estudiar deberias meterte al mundo laboral(para lo que puedes consultar nuestra seccion)"crlf)
(printout t "sino deberias continuar estudiando la ESO,lo que te abrira nuevas puertas, aqui tienes sus caracteristicas"crlf )
(printout t "* Es una etapa terminal para determinados estudiantes y para otros es propedeutica (permite continuar los estudios)."crlf )
(printout t "* Los objetivos son preparar al alumno para el mundo laboral y la vida academica adulta."crlf )
(printout t "* Tiene un caracter integral, es decir, es para todos los estudiantes."crlf )
(printout t "* Tiene asignaturas comunes (para todos) y optativas (elegidas por el estudiante y ofrecidas por el centro)."crlf )
(printout t "* Esta organizada en dos ciclos de dos años cada uno."crlf )
(printout t "* Las asignaturas se imparten por licenciados (aunque en el primer ciclo puede haber maestros)."crlf )
)

(defrule ESO
"Si tiene curriculum ya puede buscar trabajo y mandar su curriculum"
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 1}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 1} {respuesta == 2})
=>
(modify ?e1 (nivel 2))
(modify ?e2 (nivel 2))
(assert(Estado (nombre nivelESO) (nivel 2)))
)


(defrule nivelESO
"Pequeñas instrucciones para el universitario"
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 2}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 2})
?e3 <- (Estado {nombre == nivelESO} {nivel == 2})
=>
(modify ?e1 (nivel 3))
(modify ?e2 (nivel 3))
(modify ?e3 (nivel 3))
(retract ?e1)
(retract ?e2)
(retract ?e3)
(printout t "Si no te gusta estudiar deberias meterte al mundo laboral(para lo que puedes consultar nuestra seccion)"crlf)
(printout t "sino deberias continuar estudiando un bachillerato,ahora te mostramos cuales hay, a ver si te gustan"crlf )
(printout t "Modalidad Artes"crlf )
(printout t "	Opcion Artes Plasticas"crlf )
(printout t "	Opcion Artes Aplicadas y Diseño"crlf )
(printout t "Modalidad de Bachillerato de Humanidades y Ciencias Sociales:"crlf )
(printout t "	Opcion Humanidades"crlf )
(printout t "	Opcion Ciencias Sociales: Geografia e Historia"crlf )
(printout t "	Opcion Ciencias Sociales: Administracion y Gestion"crlf )
(printout t "Modalidad de Bachillerato Tecnologico."crlf )
(printout t "  	Opcion Ciencias e Ingenieria"crlf )
(printout t "	Opcion Tecnologia Industrial"crlf )
(printout t "Modalidad de Bachillerato de Ciencias de la Naturaleza y de la Salud."crlf )
(printout t "	Opcion Ciencias e Ingenieria"crlf )
(printout t "	Opcion Ciencias de la Salud"crlf )
)



(defrule Bachillerato
"Si tiene curriculum ya puede buscar trabajo y mandar su curriculum"
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 1}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 1} {respuesta == 3})
=>
(modify ?e1 (nivel 2))
(modify ?e2 (nivel 2))
(assert(Estado (nombre nivelBachillerato) (nivel 2)))
)


(defrule nivelBachillerato
"Pequeñas instrucciones para el universitario"
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 2}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 2})
?e3 <- (Estado {nombre == nivelBachillerato} {nivel == 2})
=>
(modify ?e1 (nivel 3))
(modify ?e2 (nivel 3))
(modify ?e3 (nivel 3))
(retract ?e1)
(retract ?e2)
(retract ?e3)
(printout t "Que bien se estaba en el instituto, pero ya estas preparado para dar el salto
La selectividad es el examen que hay que superar para poder acceder a los estudios universitarios.
Su nombre correcto es Pruebas de Aptitud para el Acceso a la Universidad (PAAU) y 
se realiza al finalizar el ultimo curso de bachillerato.
Los alumnos procedentes de bachillerato no pueden acceder a estudios universitarios 
si no superan la prueba de Selectividad.
Los alumnos que en su momento cursaron el Curso de Orientacion Universitaria (COU),
ya extinguido, tienen acceso a diplomaturas, ingenierias tecnicas y arquitectura tecnica
sin necesidad de tener superada la selectividad"crlf)
)

(defrule Universidad
"Si tiene curriculum ya puede buscar trabajo y mandar su curriculum"
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 1}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 1} {respuesta == 4})
=>
(modify ?e1 (nivel 2))
(modify ?e2 (nivel 2))
(assert(Estado (nombre nivelUniversidad) (nivel 2)))
)


(defrule nivelUniversidad
"Si el universitario quiere estudiar mas"
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 2}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 2})
=>
(printout t "¿En serio quieres seguir estudiando despues de tan dera carrera?SI(s),NO(n)")
(modify ?e1 (nivel 3))
(modify ?e2 (nivel 3))
(assert(Estado (nombre masUniversidad) (nivel 3) (respuesta (read)))))

(defrule simasUniversidad
"Si quiere estudiar mas despeus de la universidad"
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 3}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 3})
?e3 <- (Estado {nombre == masUniversidad} {nivel == 3} {respuesta == s})
=>
(modify ?e1 (nivel 4))
(modify ?e2 (nivel 4))
(modify ?e3 (nivel 4))
(assert(Estado (nombre masters) (nivel 4)))
)

(defrule nomasUniversidad
"Si quiere estudiar mas despeus de la universidad"
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 3}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 3})
?e3 <- (Estado {nombre == masUniversidad} {nivel == 3} {respuesta == n})
=>
(modify ?e1 (nivel 4))
(modify ?e2 (nivel 4))
(modify ?e3 (nivel 4))
(assert(Estado (nombre nomasters) (nivel 4)))
)


(defrule masters
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 4}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 4})
?e3 <- (Estado {nombre == masUniversidad} {nivel == 4})
?e4 <- (Estado {nombre == masters} {nivel == 4})
=>
(modify ?e1 (nivel 5))
(modify ?e2 (nivel 5))
(modify ?e3 (nivel 5))
(modify ?e4 (nivel 5))
(retract ?e1)
(retract ?e2)
(retract ?e3)
(retract ?e4)
(printout t "Un master es un grado academico otorgado a aquellos estudiantes que terminan un curso de"crlf )
(printout t "segundo nivel de posgrado, el cual tiene entre uno y tres años de duracion."crlf )
(printout t "Buscan ampliar y desarrollar los conocimientos para la solucion de problemas disciplinarios, interdisciplinarios o profesionales,"crlf )
(printout t "y ademas dotar a la persona de los instrumentos basicos que la habilitan como investigador en un area especifica"crlf )
(printout t "que le permitan profundizar teorica y conceptualmente en un campo del saber.
    * M.S.P. son las siglas de Maestria en Salud Publica.
    * M.C. son las siglas de Maestria en Ciencias y se otorga a quienes terminan 
      satisfactoriamente estudios de posgrado en alguna area cientifica o tecnologica.
    * M.A. son las siglas de Maestria en Artes.
    * M.A.A. son las siglas de Maestria en area Aplicada.
    * M.I. son las siglas de Maestria en Ingenieria.
    * M.T.E. son las siglas de Maestria en Tecnologia Educativa.
    * LL.M. son las siglas de Maestria en Jurisprudencia.
    * M.P.M. son las siglas de Master en Project Management (Gestion de Proyectos en ingles).
    * M.G.T I. son las siglas de Master en Gestion de las Tecnologias de Informacion."crlf )
)

(defrule nomasters
?e1 <- (Estado {nombre == mejoraAcademica} {nivel == 4}) 
?e2 <- (Estado {nombre == mejorandoAcademicamente} {nivel == 4})
?e3 <- (Estado {nombre == masUniversidad} {nivel == 4})
?e4 <- (Estado {nombre == nomasters} {nivel == 4})
=>
(modify ?e1 (nivel 5))
(modify ?e2 (nivel 5))
(modify ?e3 (nivel 5))
(modify ?e4 (nivel 5))
(retract ?e1)
(retract ?e2)
(retract ?e3)
(retract ?e4)
(printout t "Ya has estudiado bastante, ahora a trabajar, puedes ayudarte con nuestro asistente"crlf )
)

(defrule mejorandoVida
"Si la persona quiere mejorar su vida"
?estado <- (Estado {nombre == mejoraVida} {nivel == 0})
=>
(printout t "¿Piensas todos lo dias?SI(s),NO(n)")
(modify ?estado (nivel 1))
(assert(Estado (nombre Piensas) (nivel 1) (respuesta (read))))
)

(defrule siPiensas
?e1 <- (Estado {nombre == mejoraVida} {nivel == 1}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 1} {respuesta == s})
=>
(modify ?e1 (nivel 2))
(modify ?e2 (nivel 2))
(assert(Estado (nombre tenerMeta) (nivel 2)))
)

(defrule noPiensas
?e1 <- (Estado {nombre == mejoraVida} {nivel == 1}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 1} {respuesta == n})
=>
(modify ?e1 (nivel 2))
(modify ?e2 (nivel 2))
(assert(Estado (nombre consejoPensar) (nivel 2)))
)

(defrule consejoPensar
?e1 <- (Estado {nombre == mejoraVida} {nivel == 2}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 2})
?e3 <- (Estado {nombre == consejoPensar} {nivel == 2})
=>
(modify ?e1 (nivel 3))
(modify ?e2 (nivel 3))
(modify ?e3 (nivel 3))
(retract ?e1)
(retract ?e2)
(retract ?e3)
(printout t "Medita. Pasa algo de tiempo solo con tus pensamientos.
Hay una razon por el que es el primer consejo de la lista.
Si sigues este consejo vuelve a intentarlo."crlf )
)

(defrule tenerMeta
?e1 <- (Estado {nombre == mejoraVida} {nivel == 2}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 2})
?e3 <- (Estado {nombre == tenerMeta} {nivel == 2})
=>
(printout t "¿Tienes metas?SI(s),NO(n)")
(modify ?e1 (nivel 3))
(modify ?e2 (nivel 3))
(modify ?e3 (nivel 3))
(assert(Estado (nombre Metas) (nivel 3) (respuesta (read))))
)

(defrule siMetas
?e1 <- (Estado {nombre == mejoraVida} {nivel == 3}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 3})
?e3 <- (Estado {nombre == Metas} {nivel == 3} {respuesta == s})
=>
(modify ?e1 (nivel 4))
(modify ?e2 (nivel 4))
(modify ?e3 (nivel 4))
(assert(Estado (nombre eresFeliz) (nivel 4)))
)

(defrule noMetas
?e1 <- (Estado {nombre == mejoraVida} {nivel == 3}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 3})
?e3 <- (Estado {nombre == Metas} {nivel == 3} {respuesta == n})
=>
(modify ?e1 (nivel 4))
(modify ?e2 (nivel 4))
(modify ?e3 (nivel 4))
(assert(Estado (nombre consejoMetas) (nivel 4)))
)

(defrule consejoMetas
?e1 <- (Estado {nombre == mejoraVida} {nivel == 4}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 4})
?e3 <- (Estado {nombre == Metas} {nivel == 4})
?e4 <- (Estado {nombre == consejoMetas} {nivel == 4})
=>
(modify ?e1 (nivel 5))
(modify ?e2 (nivel 5))
(modify ?e3 (nivel 5))
(modify ?e4 (nivel 5))
(retract ?e1)
(retract ?e2)
(retract ?e3)
(retract ?e4)
(printout t "Ponte una meta que alcanzar todos los dias.
Deja la rutina aunque sea por un segundo.
Esto significa hablar con alguien que usualmente no hablas o empezar un proyecto
con el que te sientas intimidado. No es necesario pensar en el futuro,
sino simplemente tienes que tener una meta y cumplirla."crlf )
)

(defrule eresFeliz
?e1 <- (Estado {nombre == mejoraVida} {nivel == 4}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 4})
?e3 <- (Estado {nombre == Metas} {nivel == 4})
?e4 <- (Estado {nombre == eresFeliz} {nivel == 4})
=>
(printout t "¿Eres feliz?SI(s),NO(n)")
(modify ?e1 (nivel 5))
(modify ?e2 (nivel 5))
(modify ?e3 (nivel 5))
(modify ?e4 (nivel 5))
(assert(Estado (nombre Feliz) (nivel 5) (respuesta (read))))
)


(defrule siFeliz
?e1 <- (Estado {nombre == mejoraVida} {nivel == 5}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 5})
?e3 <- (Estado {nombre == Metas} {nivel == 5})
?e4 <- (Estado {nombre == eresFeliz} {nivel == 5})
?e5 <- (Estado {nombre == Feliz} {nivel == 5} {respuesta == s})
=>
(modify ?e1 (nivel 6))
(modify ?e2 (nivel 6))
(modify ?e3 (nivel 6))
(modify ?e4 (nivel 6))
(modify ?e5 (nivel 6))
(assert(Estado (nombre psicologo) (nivel 6)))
)

(defrule noFeliz
?e1 <- (Estado {nombre == mejoraVida} {nivel == 5}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 5})
?e3 <- (Estado {nombre == Metas} {nivel == 5})
?e4 <- (Estado {nombre == eresFeliz} {nivel == 5})
?e5 <- (Estado {nombre == Feliz} {nivel == 5} {respuesta == n})
=>
(modify ?e1 (nivel 6))
(modify ?e2 (nivel 6))
(modify ?e3 (nivel 6))
(modify ?e4 (nivel 6))
(modify ?e5 (nivel 6))
(assert(Estado (nombre consejoFeliz) (nivel 6)))
)

(defrule consejoFeliz
?e1 <- (Estado {nombre == mejoraVida} {nivel == 6}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 6})
?e3 <- (Estado {nombre == Metas} {nivel == 6})
?e4 <- (Estado {nombre == eresFeliz} {nivel == 6})
?e5 <- (Estado {nombre == Feliz} {nivel == 6})
?e6 <- (Estado {nombre == consejoFeliz} {nivel == 6})
=>
(modify ?e1 (nivel 6))
(modify ?e2 (nivel 6))
(modify ?e3 (nivel 6))
(modify ?e4 (nivel 6))
(modify ?e5 (nivel 6))
(modify ?e6 (nivel 6))
(retract ?e1)
(retract ?e2)
(retract ?e3)
(retract ?e4)
(retract ?e5)
(retract ?e6)
(printout t "Detecta las partes del dia que te trajeron mas satisfaccion.
¿Que te hace feliz ahora? Haz una lista de las cosas que mejor te hicieron
sentir a lo largo del dia y dales una puntuacion del 1 al 10 y concentrate
en que te hizo ponerle esa puntuacion. Trata de incorporar mas de esas cosas
que te hacieron felices ayer para que seas mas feliz hoy..

La verdad es que no pareces encontrarte del todo bien
rogamos acudas a un especialista de la psicologia para que trate tu caso"crlf )
)

(defrule psicologo
?e1 <- (Estado {nombre == mejoraVida} {nivel == 6}) 
?e2 <- (Estado {nombre == Piensas} {nivel == 6})
?e3 <- (Estado {nombre == Metas} {nivel == 6})
?e4 <- (Estado {nombre == eresFeliz} {nivel == 6})
?e5 <- (Estado {nombre == Feliz} {nivel == 6})
?e6 <- (Estado {nombre == psicologo} {nivel == 6})
=>
(modify ?e1 (nivel 6))
(modify ?e2 (nivel 6))
(modify ?e3 (nivel 6))
(modify ?e4 (nivel 6))
(modify ?e5 (nivel 6))
(modify ?e6 (nivel 6))
(retract ?e1)
(retract ?e2)
(retract ?e3)
(retract ?e4)
(retract ?e5)
(retract ?e6)
(printout t "Que envidia, como nos gustaria estar como tu, enhorabuena"crlf )
)

(defrule mejorandoEstado
"Si la persona quiere mejorar su estado fisico"
?estado <- (Estado {nombre == mejoraFisico} {nivel == 0})
=>
(printout t "¿Cual es su peso actual?")
(modify ?estado (nivel 1))
(assert(Estado (nombre Fisico) (nivel 1) (respuesta (read))))
)

(defrule peso
"Comprobamos cual es el peso de la persona"
?estado <- (Estado {nombre == Fisico} {nivel == 1}{respuesta >= 100})
=>
(printout t "Parece que padeces sobrepeso, ¿ Sigues algun tipo de dieta o realizas algun deporte ? (s)i/(n)o")
(modify ?estado (nivel 2))
(assert(Estado (nombre Fisico) (nivel 2) (respuesta (read))))
)

(defrule peso2
"Comprobamos cual es el peso de la persona"
?estado <- (Estado {nombre == Fisico} {nivel == 1}{respuesta < 100})
=>
(printout t "Parece que no tienes problemas de sobrepeso, pero aun asi , es muy importante para el cuerpo hacer deporte regularmente, ayuda a relajarse y elimina las tensiones descargando adrenalina, ademas de divirtiendonos.")
(retract ?estado)
)

(defrule deportedieta
"Comprobamos si realiza deporte/dieta"
?estado <- (Estado {nombre == Fisico} {nivel == 2}{respuesta == n})
=>
(printout t "Deberias acudir a un endocrino, el te puede crear una dieta adaptada a ti que te ayudara a perder peso de forma adecuada y progresiva. Ademas, deberias realizar periodicamente un deporte aerobico como correr o montar en bicicleta, y en seguida notaras los resultados.")
(retract ?estado )
)

(defrule deportedieta2
"Comprobamos si realiza deporte/dieta"
?estado <- (Estado {nombre == Fisico} {nivel == 2}{respuesta == s})
=>
(printout t "No te preocupes si los resultados no llegan de forma inmediata, el cuerpo requiere mucho tiempo para adaptarse y progresivamente veras los frutos de tu trabajo duro, animo!
")
(retract ?estado )
)



(reset)
(run)
