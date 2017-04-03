package com.ipartek.formacion.controller.formater;



import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.ipartek.formacion.persistence.Profesor;
import com.ipartek.formacion.profesor.ProfesorServiceRemote;
import com.ipartek.formacion.service.interfaces.ProfesorService;

public class ProfesorConverter implements Converter<String, Profesor> {

    @Autowired
    ProfesorServiceRemote pS;
	
	@Override
	public Profesor convert(String codigo) {
		
		Profesor profesor=null;
		Long codigoProfesor = Long.parseLong(codigo);
		profesor = pS.getById(codigoProfesor); 
		return profesor;
	}


 

}
