package com.ipartek.formacion.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.alumno.AlumnoServiceRemote;
import com.ipartek.formacion.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoServiceEJB;

@Service("alumnoServiceEJB")
public class AlumnoServiceEJBImp implements AlumnoServiceEJB {

	@Resource(name="alumnoServiceRemote")
	AlumnoServiceRemote alumnoServiceRemote;
	
	@Override
	public void setAlumnoServiceRemote(AlumnoServiceRemote alumnoServiceRemote) {
	 this.alumnoServiceRemote = alumnoServiceRemote;
		
	}

	@Override
	public Alumno getById(long codigo) {
		
		return alumnoServiceRemote.getById(codigo);
	}

	@Override
	public List<Alumno> getAll() {
		
		return alumnoServiceRemote.getAll();
	}
	
	
	


}
