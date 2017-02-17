package com.ipartek.formacion.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.interfaces.ClienteService;


public class ClienteValidator implements Validator{
	
@Autowired
private ClienteService cS=null;	
   private static final Logger logger = LoggerFactory.getLogger(ClienteValidator.class);
		   
	@Override
	public boolean supports(Class<?> paramClass) {
	
		logger.info("Clase Aplica " +  paramClass.getName()+" " + Cliente.class.isAssignableFrom(paramClass));
		//logger.info("Clase Cliente " + Cliente.class.toString());

		return Cliente.class.isAssignableFrom(paramClass);
	
	}

	@Override
	public void validate(Object obj, Errors errors) {
	
		Cliente cliente = (Cliente)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "form.nombreRequerido", "codigo es incorrecto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "form.apellidoRequerido", "Nombre es incorrecto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion","form.direccionRequerido", "direccion es incorrecto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.emailRequerido", "email es incorrecto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "form.telefonoRequerido", "telefono es incorrecto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identificativo", "form.dniRequerido", "Tiene que introducir un identificativo correcto");
		
		if (cliente.getCodigo()< Cliente.CODIGO_NULO){
			 errors.rejectValue("codigo", "valorNegativo", 
					 			new Object[]{"'codigo'"}, "no puede ser menor que " + Cliente.CODIGO_NULO);
		 
		 }
		
		
		if (cliente.getIdentificativo().length()>12){
			errors.rejectValue("identificativo", "Size.identificativo", new Object[]{"'identificativo'"}, "El identificativo es excesivo");
		}
		
		if ("0".equals(cliente.getTelefono())){
			errors.rejectValue("telefono", "Telefono.valorcero", new Object[]{"'telefono'"}, "El telefono no deberia ser 0");
		}
		 
		 if (cliente.getNombre().length() > 50){
			 	errors.rejectValue("nombre", "Size.nombre", new Object[]{"'nombre'"}, "El tamaño del nombre es excesivo.");
		 }
	 
		 if (cliente.getApellidos().length() > 250){
			 	errors.rejectValue("apellidos", "Size.apellidos", new Object[]{"'apellidos'"}, "El tamaño de los apellidos es excesivo.");
		 }
	 
		 if (cliente.getDireccion().length() > 250){
			 	errors.rejectValue("direccion", "Size.direccion", new Object[]{"'direccion'"}, "El tamaño de la direccion es excesivo.");
		 }
		 
				 
		 if (cliente.getEmail().length() > 150){
			 	errors.rejectValue("email", "Size.email", new Object[]{"'email'"}, "El tamaño del email es excesivo.");
		 }
		 
		 
		 if (cliente.getTelefono().length() > 9){
			 	errors.rejectValue("telefono", "Size.telefono", new Object[]{"'telefono'"}, "El tamaño del telefono es excesivo.");
		 }
		 
		 try {
			Long telefono = Long.parseLong(cliente.getTelefono());
			if (telefono < 0)
				errors.rejectValue("telefono", "Pattern.telefono", new Object[]{"'telefono'"}, "El telefono no puede ser un numero negativo");
		} catch (NumberFormatException e) {
			errors.rejectValue("telefono", "Pattern.telefono", new Object[]{"'telefono'"}, "El telefono deben ser numeros.");
		}
		
		 if (cS.clienteIdentificativoDuplicado(cliente.getIdentificativo(), cliente.getCodigo())){
				errors.rejectValue("identificativo", "Identificativo.duplicado", new Object[]{"'identificativo'"},"El Identif se encuentra duplicado");
			}
		
		
	}

}
