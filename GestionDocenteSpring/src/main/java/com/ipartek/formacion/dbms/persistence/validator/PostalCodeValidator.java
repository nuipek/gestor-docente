package com.ipartek.formacion.dbms.persistence.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostalCodeValidator implements ConstraintValidator<PostalCode,Integer>{

	@Override
	public void initialize(PostalCode param) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public boolean isValid(Integer codigoPostal, ConstraintValidatorContext ctx) {
		boolean valido = true;
		int valor = 0;
		
		try {
			valor = codigoPostal.intValue();
			if (valor <  0 || valor > 99999)
				valido = false;
		} catch (Exception e) {
			valido = false;
		}
		
		/*
		if (Integer.parseInt(codigoPostal.intValue() > 99999 || codigoPostal.intValue() < 0 )
			return false;
		else
			return true;
			*/
		return valido;
	}

}
