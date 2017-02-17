package com.ipartek.formacion.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.Util;
import com.ipartek.formacion.service.interfaces.AlumnoService;

/* Aqui se cargarian las properties que se encontrasen en el root-context.xml 
para todos los modulos de la aplicacion
*/
//@PropertySource(value = "classpath:/constantes/costantes.properties")

public class AlumnoValidator implements Validator {
	   private static final Logger logger = LoggerFactory.getLogger(AlumnoValidator.class);
  
	@Autowired
	private AlumnoService aS = null;
	
	
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "form.nombreRequerido","Tiene que introducir un nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "form.apellidoRequerido","Tiene que introducir un apellido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.emailRequerido","Tiene que introducir un email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "form.dniRequerido", "Tiene que introducir un dni");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "form.telefonoRequerido","Tiene que introducir un telefono");
 
	 // Se realizaran importaciones propias al objeto
	 Alumno alum = (Alumno)obj;
	 
	 if (alum != null){
		 if (alum.getCodigo()< Alumno.CODIGO_NULO){
			 	errors.rejectValue("codigo", "valorNegativo", 
			 						new Object[]{"'codigo'"}, "no puede ser menor que " + Alumno.CODIGO_NULO);
	 
		 }
		 
		 // logger.info("DNI " + alum.getDni());
		 if (!Util.validarDni(alum.getDni())){ // validacion letra dni
			 	errors.rejectValue("dni", "letraDNIIncorrecta", new Object[]{"'dni'"}, "La letra del DNI introducido es incorrecto"); 
		 }
	 
	 
		 if (alum.getNombre().length() > 50){
			 	errors.rejectValue("nombre", "Size.nombre", new Object[]{"'nombre'"}, "El tamaño del nombre es excesivo.");
		 }
	 
		 if (alum.getApellidos().length() > 250){
			 	errors.rejectValue("apellidos", "Size.apellidos", new Object[]{"'apellidos'"}, "El tamaño de los apellidos es excesivo.");
		 }
	 
		 if (alum.getDireccion().length() > 250){
			 	errors.rejectValue("direccion", "Size.direccion", new Object[]{"'direccion'"}, "El tamaño de la direccion es excesivo.");
		 }
		 
		 if (alum.getPoblacion().length() > 150){
			 	errors.rejectValue("poblacion", "Size.poblacion", new Object[]{"'poblacion'"}, "El tamaño de la poblacion es excesivo.");
		 }
		 
		 if (alum.getEmail().length() > 150){
			 	errors.rejectValue("email", "Size.email", new Object[]{"'email'"}, "El tamaño del email es excesivo.");
		 }
		 
		 
		 if (alum.getTelefono().length() > 9){
			 	errors.rejectValue("telefono", "Size.telefono", new Object[]{"'telefono'"}, "El tamaño del telefono es excesivo.");
		 }
		 
		 try {
			Long telefono = Long.parseLong(alum.getTelefono());
			if (telefono < 0)
				errors.rejectValue("telefono", "Pattern.telefono", new Object[]{"'telefono'"}, "El telefono no puede ser un numero negativo");
		} catch (NumberFormatException e) {
			errors.rejectValue("telefono", "Pattern.telefono", new Object[]{"'telefono'"}, "El telefono deben ser numeros.");
		}
		
		if(alum.getCodigoPostal() > 99999 || alum.getCodigoPostal() < 0){
			errors.rejectValue("codigoPostal", "Pattern.codigoPostal", new Object[]{"'codigoPostal'"},"El codigo postal debe estar entre 0 y 99999");
		}
		
		if(alum.getnHermanos() > 99 || alum.getnHermanos() < 0){
			errors.rejectValue("nHermanos", "Pattern.nHermanos", new Object[]{"'codigoPostal'"},"El numero de hermanos debe estar entre 0 y 99");
		}
		
		
		if (aS.alumnoDniDuplicado(alum.getDni(),alum.getCodigo())){
			errors.rejectValue("dni", "Dni.duplicado", new Object[]{"'dni'"},"El Dni se encuentra duplicado");
		}
	 }// fin del if de null
	 
	}

}
