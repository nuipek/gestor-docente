package com.ipartek.formacion.dbms.persistence.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;

public class ProfesorExistsValidator implements ConstraintValidator<ProfesorExists, Object> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProfesorExistsValidator.class);
	private String code;
	private String key;

	@Autowired
	private ProfesorService pS;

	@Override
	public void initialize(ProfesorExists dniexits) {
		// Se recuperan los nombres de los parametros enviados por la anotacion definida 
		// Al ser una lista una vez sera dni y otra nss
		this.code = dniexits.code();
		this.key = dniexits.key();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext ctx) {
		// ctx.
		LOGGER.info(object.toString());
		boolean valid = true;
		try {
			LOGGER.info(object.toString());

			final String codeValue = BeanUtils.getProperty(object, code);
			final String keyValue = BeanUtils.getProperty(object, key);
			//Object obj = null;
			
			if (keyValue != null && keyValue != "") {
				LOGGER.info(key + ": " + keyValue);
				if ("nSS".equalsIgnoreCase(key)) {
					LOGGER.info("nss:" + ": " + keyValue);
					valid = !(pS.profesornSSDuplicado(keyValue,Integer.parseInt(codeValue)));
				} else if ("dni".equalsIgnoreCase(key)) {
					valid = !(pS.profesorDniDuplicado(keyValue,Integer.parseInt(codeValue)));
					
				}
			
			}
		} catch (final Exception e) {
			valid = false;
			LOGGER.info(e.getMessage());
		}
		return valid;
	}

}