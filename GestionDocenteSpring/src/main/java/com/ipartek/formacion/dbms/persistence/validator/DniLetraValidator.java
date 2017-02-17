package com.ipartek.formacion.dbms.persistence.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipartek.formacion.service.Util;

public class DniLetraValidator implements ConstraintValidator<DniLetra, String> {

	private Logger logger = LoggerFactory.getLogger(DniLetraValidator.class);
	
	
	@Override
	public void initialize(DniLetra annotation) {
	//	dni = annotation.Value();
		
	}

	@Override
	public boolean isValid(String dni, ConstraintValidatorContext ctx) {
		
		logger.info("isValid " + dni);
		return Util.validarDni(dni);
		
		
	}

}
