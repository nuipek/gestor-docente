package com.ipartek.formacion.dbms.persistence.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipartek.formacion.service.Util;

public class DniDuplicadoValidator implements ConstraintValidator<DniDuplicado,ValidacionProfesor> {

	private Logger logger = LoggerFactory.getLogger(DniDuplicadoValidator.class);
	
	
	@Override
	public void initialize(DniDuplicado annotation) {
	//	dni = annotation.Value();
		
	}

	@Override
	public boolean isValid(ValidacionProfesor valid, ConstraintValidatorContext ctx) {
		
		logger.info("Antes de isValid ");
		return valid.isValid();
	
	
		
		
	}

}
