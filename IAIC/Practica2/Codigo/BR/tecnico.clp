;PRACTICA 2: Sistema de Reglas de Produccion
;	Tema:
;		Asesor laboral enfoque tecnico
;	
;	Autores:
;		Ignacio Gil Izas
;		Eduardo Gil Ruiz
;		Carmen Martínez Ponte 
;		Alberto Megía Negrillo
;
;	Enlaces de interes: 
;		http://azcarreras.com/
;    
;   HECHOS:
;---> Datos personales
;   Edad, sexo(h-m), email(s-n), teléfono/s(s-n)), carné de conducir(s-n)), vehículo propio(s-n)
;   nacionalidad(espaniola-otra)    
;---> Formación
;   Estudios (ESO, FP, BACH, DIPLOMATURA, LIC/ING) 
;   Experiencia profesional(Empresas, fechas de permanencia, función)
;   Idiomas&&Nivel
;   Conocimientos informáticos(s-n)
;---> Disponibilidad
;   Dia(mañana, tarde, completa)
;   Desplazamientos(nacional-extranjero)

(deffacts fase-inicial
   (fase inicial))

;FUNCIONES

(deffunction inicio()
  (printout t "Vamos a comenzar con el asesoramiento tecnico en cuanto a su C.V." crlf
         "deberá responder a las preguntas ahora formuladas para poder encaminarle"
         "según sus capacidades y necesidades." crlf crlf)
  (printout t crlf "¿Es usted hombre o mujer?")
    (assert(sexo(read)))
    
  (printout t crlf "¿Cuantos años tiene? (Si no es indiscreccion... ;) )" crlf)
  (printout t "(teclée un número entre 16 y 100)" crlf)
    (assert(edad(read)))  
    
  (printout t crlf "¿Tiene usted cuenta de correo? (si-no)" crlf)
    (assert(tieneMail(read)))
    
  (printout t crlf "¿Tiene teléfono? (si-no)" crlf) 
    (assert(tieneTelf(read)))
    
  (printout t crlf "Incluya todo lo anterior en el apartado de datos personales junto con una foto actual" crlf) 
    
  (printout t crlf "Bien, ya le conocemos un poco más... ahora abordemos su formación." crlf) 
  (printout t "Los trabajos a los que usted pueda optar vienen condicionados por sus" crlf)                               
  (printout t "estudios y sus experiencia laboral" crlf)
                       
  (printout t crlf "¿Cuál es su nivel de estudios? " crlf)    
  (printout t  "(Ninguno, ESO, FP, Bach, Diplomatura, Licenciatura, Ingenieria)" crlf)  
    (assert(estudios(read)))       
                        
  (printout t crlf "¿Tiene alguna experiencia laboral? (si-no) " crlf)   
    (assert(expLab(read)))                                           
                                                             
  (printout t crlf "¿Conoce algún idioma aparte del espaniol? (si-no)" crlf)       
    (assert(idiomas(read)))
                        
  (printout t crlf "¿Tiene conocimientos informáticos?" crlf)
    (assert(informatica(read)))     
                        
  (printout t crlf "¿Tiene disponibilidad para trabajar en el extranjero" crlf)  
  (printout t  "o en otra comunidad autónoma? (si-no)" crlf) 
    (assert(disponibilidad(read)))                                   
                        
(printout t crlf "¿Tiene carné de conducir? (si-no)" crlf) 
    (assert(tieneCarne(read))))
        

(deffunction pregunta-estudios()
  (printout t "¿ha terminado sus estudios? s/n")
    (assert (estudios(read))))


(deffunction pregunta-funcional()
  (printout t "¿ha tenido muchos periodos de paro o cambios de trabajo frecuentes? s/n")
    (assert (funcional(read))))

(deffunction pregunta-actividadesTrabajo()
  (printout t "¿Cuantas actividades distintas ha desempeñado? Introduzca un dato numerico, por favor")
    (assert (actividadesTrabajo(read))))

(deffunction pregunta-empresasTrabajo()
  (printout t "¿Para cuantas empresas ha trabajado? Introduzca un dato numerico, por favor")
    (assert (empresasTrabajo(read))))

(deffunction pregunta-practicas()
  (printout t "¿Has realizado prácticas relacionadas con su formacion? s/n")
    (assert (practicas(read))))

(deffunction pregunta-otrostrabajos()
    (printout t "Has realizado algún trabajo no relacionado con tus estudios? s/n")
	(assert(otrotrab (read))))

(deffunction pregunta-extranjeroUni()
  (printout t "¿Ha realizado estudios universitarios en el extranjero? s/n")
    (assert (extranjeroUni(read))))

(deffunction pregunta-extranjeroCole()
  (printout t "¿Ha realizado estudios en el extranjero durante su etapa escolar? s/n")
    (assert (extranjeroCole(read))))

(deffunction pregunta-numIdioma()
  (printout t "¿Cuantas?")
    (assert (numIdioma(read))))
 
(deffunction otrosCarnets()
   (printout t crlf " indique si tiene otro tipo de carné aparte del B" crlf)           
    (assert(tieneOtrosCarnes(read))))

(deffunction vehiculoPropio()         
  (printout t "Además de carné, ¿tiene vehículo propio?" crlf)    
    (assert(tieneVehPropio(read))))
    

;REGLAS

;inicio
(defrule ruleinicio
	(fase inicial)
	=>
	(inicio))

(defrule incomunicadoMail
    (tieneMail no)
    =>
    (printout t "Por favor cree una cuenta de correo electronico, le facilitara la comunicacion con la empresa"))

(defrule incomunicadoTlf
    (tieneTelf no)
    =>
    (printout t "Necesita usted un telefono para que la empresa pueda comunicarle el resultado del proceso de seleccion"))

(defrule experienciaLab
	(expLab si)     
       =>
       (printout t "Es un aspecto interesante resaltar las empresas anteriores en las que usted" crlf)       
       (printout t "ha trabajado, asi como la labor que desempeniaba en dichas empresas:"crlf) 
       (pregunta-funcional))

(defrule tipoFuncional
    (funcional s)
    =>
    (printout t "Entonces es aconsajable que redacte usted un curriculum de tipo funcional" crlf)
    (printout t "que permitirá resaltar los puntos positivos y omitir los eventuales errores de recorrido" crlf)
    (pregunta-actividadesTrabajo))
	(printout t "Introduzca una entrada en su cv por cada una de las actividades realizadas" crlf)

(defrule tipoCronologico
    (funcional n)
    =>(printout t "Entonces le aconsejamos que redacte un curriculum de tipo cronológico inverso" crlf)
    (printout t " para resaltar sus experiencias mas recientes y poner de relieve su progresión profesional" crlf)
    (pregunta-empresasTrabajo)
	(printout t "Introduzca una entrada en su cv por cada una de las empresas para las que ha trabajado" crlf))

(defrule sinExperiencia(and(expLab no)(idiomas si)(estudios Licenciatura)(estudios Ingenieria)(estudios Diplomatura))
       =>
       (printout t "Ha de dejar plasmado en su curriculum que a pesar de su carencia de experiencia" crlf)
       (printout t "tiene iniciativa y ganas de abrirse al mercado laboral; ha concluido su etapa" crlf)
       (printout t "como estudiante universitario y es hora de currar"crlf))

(defrule rulepracticas
    (expLab no)
	=>
  	(pregunta-practicas))

(defrule rulepracticassi
    (practicas s)=>
    (printout t "Sus practicas son un valor a resaltar, pues reflejan su deseo de incorporarse y conocer el mundo laboral"))

(defrule otrostrabajos
    (expLab no)
	=>
  	(pregunta-otrostrabajos))

(defrule otrostrabajossi
    (otrotrab s)
    =>
    (printout t "Indique dichos trabajos en el apartado Otros datos de interes"crlf)
    (printout t "esto indicará que es usted una persona con iniciativa"crlf))

(defrule tieneIdiomas(idiomas si)
       =>
      (printout t "Tener conocimiento de otras lenguas le facilitará el ingreso las" crlf) 
      (printout t "empresas ya que estas solicitan cada vez más los idiomas a sus empleados" crlf)
      (printout t "para abrirse al mercado internacional" crlf) 
      (pregunta-numIdioma)                                                      
      (printout t "Introduzca en su cv un epigrafe idiomas yuna entrada por cada idioma junto con la titulacion que lo acredite, si la posee" crlf)
    (pregunta-extranjeroUni)
    (pregunta-extranjeroCole))

(defrule estudiosEstranjero
    (or(extranjeroUni)(extranjeroCole))
    =>(printout t "Indique en el apartado de formacion que estudios ha cursado en el extranjero" crlf))

(defrule tieneMovilidad (and(idiomas si)(disponibilidad si))
       =>
      (printout t "La movilidad en un trabajador es un factor fundamental en el modelo de " crlf)
      (printout t "empresa actual. Además gana muchos puntos gracias a su conocimiento y nivel" crlf))

(defrule sinInformatica(informatica no)
       => 
       (printout t "En la empresa actual es NECESARIO tener conocimientos informáticos" crlf) 
       (printout t "no ha de ser un experto, pero si ha de desenvolverse fluidamente con un" crlf)
       (printout t "ordenador asi como navegar por Internet. Le recomendamos que se apunte a un"crlf)
       (printout t "curso para por lo menos aprender lo básico. " crlf) )
    
(defrule resumen(and(expLab si)(idiomas si)(informatica si))
       =>
       (printout t "Enhorabuena, tiene usted mucha informacion valiosa que añadir al cv" crlf)
       (printout t "trate de resumirla en una hoja, que quede espaciosa, y adecue su informacion al puesto al que opta" crlf))

(defrule otrainfocoche
    (tieneCarnet si)
    =>
    (printout t "Si esta informacion es relevante para el puesto anadala")
    (otrosCarnets)
    (vehiculoPropio))
(reset)
(run)