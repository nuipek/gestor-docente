package com.ipartek.formacion.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.Util;

public class AlumnoValidator implements Validator {
	   private static final Logger logger = LoggerFactory.getLogger(AlumnoValidator.class);

	@Override
	public boolean supports(Class<?> paramClass) {
		logger.info("Clase Aplica " +  paramClass.getName()+" " + Alumno.class.isAssignableFrom(paramClass));
		// Clase o clases que deben ser procesadas por el validador
		return Alumno.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
	// errors es la coleccion donde se incluyen los errores que nos reporta el validador para procesar
	// el hasErrors
	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre","505","Tiene que introducir un nombre");
	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos","505","Tiene que introducir un apellido");
	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","505","Tiene que introducir un email");
	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni","505","Tiene que introducir un dni");
	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono","505","Tiene que introducir un telefono");
	 
	 // Se realizaran importaciones propias al objeto
	 Alumno alum = (Alumno)obj;
	 if (alum.getCodigo()< Alumno.CODIGO_NULO){
		 errors.rejectValue("codigo", "valorNegativo", 
				 			new Object[]{"'codigo'"}, "no puede ser menor que " + Alumno.CODIGO_NULO);
	 
	  }
	 logger.info("DNI " + alum.getDni());
	 if (!Util.validarDni(alum.getDni())){ // validacion letra dni
		 errors.rejectValue("dni", "letraDNIIncorrecta", new Object[]{"'dni'"}, "La letra del DNI introducido es incorrecto"); 
	  }
	 
	 
	}

}
