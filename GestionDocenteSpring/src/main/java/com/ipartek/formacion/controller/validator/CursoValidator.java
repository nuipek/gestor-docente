package com.ipartek.formacion.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ipartek.formacion.persistence.Curso;

public class CursoValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return Curso.class.equals(paramClass);
		//return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

	}

}
