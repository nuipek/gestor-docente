package com.ipartek.formacion.controller.formater;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.ipartek.formacion.alumno.AlumnoServiceRemote;
import com.ipartek.formacion.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoServiceEJB;

public class AlumnoConverter implements Converter<String, Alumno> {
	
	@Autowired 
	AlumnoServiceRemote alumnoEJB;

	@Override
	public Alumno convert(String codigo) {
	
		// Aunque se recuperan multiples llamada solo se recoge un objeto codigo y se genera un objeto alumno , 
		// se llamana tantas veces como objetos seleccionados tengamos generando automaticamente una lista 
		return alumnoEJB.getById(Long.parseLong(codigo));
	}

}
