package com.ipartek.formacion.dbms.persistence.validator;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/* Damos instrucciones al interfaz para que entienda que es una anotacion con el @ */

@Documented // documentada
@Constraint(validatedBy=DniLetraValidator.class) // como
@Target({ElementType.METHOD,ElementType.FIELD}) // sobre quien
@Retention(RetentionPolicy.RUNTIME) // cuando aplica en tiempo de ejecucion
public @interface DniLetra {

   //String[] Value();
	String message() default "{DniLetra}";
	
	Class<?>[] groups() default {};
	
    Class<? extends Payload>[] payload() default {};
}