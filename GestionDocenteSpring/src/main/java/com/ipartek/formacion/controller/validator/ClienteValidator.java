package com.ipartek.formacion.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.ipartek.formacion.dbms.persistence.Cliente;


public class ClienteValidator implements Validator{
	
   private static final Logger logger = LoggerFactory.getLogger(ClienteValidator.class);
		   
	@Override
	public boolean supports(Class<?> paramClass) {
	
		logger.info("Clase Aplica " +  paramClass.getName()+" " + Cliente.class.isAssignableFrom(paramClass));
		//logger.info("Clase Cliente " + Cliente.class.toString());

		return Cliente.class.isAssignableFrom(paramClass);
		//return Cliente.class.equals(paramClass);
		//return Cliente.class.equals(claseAplica);
	}

	@Override
	public void validate(Object obj, Errors errors) {
	
		Cliente cliente = (Cliente)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", "form.nombreRequerido", "codigo es incorrecto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "form.apellidoRequerido", "Nombre es incorrecto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion","form.direccionRequerido", "direccion es incorrecto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.emailRequerido", "email es incorrecto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "form.telefonoRequerido", "telefono es incorrecto");
		
		if (cliente.getCodigo()< Cliente.CODIGO_NULO){
			 errors.rejectValue("codigo", "valorNegativo", 
					 			new Object[]{"'codigo'"}, "no puede ser menor que " + Cliente.CODIGO_NULO);
		 
		  }
		
		if ("0".equals(cliente.getTelefono())){
			errors.rejectValue("telefono", "106", new Object[]{"'telefono'"}, "El telefono no deberia ser 0");
		}
		 
	
		
	}

}
