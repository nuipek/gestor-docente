package com.ipartek.formacion.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CursoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

	}

}
