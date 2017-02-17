package com.ipartek.formacion.dbms.persistence.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// documentada
@Constraint(validatedBy=PostalCodeValidator.class) // como
@Target({ElementType.METHOD,ElementType.FIELD}) // sobre quien
@Retention(RetentionPolicy.RUNTIME) // cuando aplica en tiempo de ejecucion
public @interface PostalCode {
	
	
	String message() default "{PostalCode}";
	
	Class<?>[] groups() default {};
	
    Class<? extends Payload>[] payload() default {};

}
