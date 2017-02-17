package com.ipartek.formacion.dbms.persistence.validator;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ipartek.formacion.service.Util;

public class PhoneValidator implements ConstraintValidator<Phone,String> {
	 

	 
	@Override
	public void initialize(Phone annotation) {
	
	
	}

	@Override
	public boolean isValid(String phoneN, ConstraintValidatorContext ctx) {
	    boolean valido = true;
	    
	    if (phoneN != null){
	    	
	    	if(!phoneN.matches("\\d{9}")){
	    		valido = false;
	    	}
	    	
	      }
		return valido;
	}


}
