package com.ipartek.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService{
   
	@Autowired
	private AlumnoDAO alumnoDao;
    
    @Override
	public void setAlumnoDao(AlumnoDAO alumnoDao) {
		
		this.alumnoDao = alumnoDao;
	}

	@Override
	public Alumno create(Alumno alumno) {
		
		return alumnoDao.create(alumno);
	}

	@Override
	public List<Alumno> getAll() {
		
		return alumnoDao.getAll();
	}

	@Override
	public Alumno getById(int codigo) {
		
		return alumnoDao.getById(codigo);
	}

	@Override
	public Alumno update(Alumno alumno) {
	
		return alumnoDao.update(alumno);
	}

	@Override
	public void delete(int codigo) {
		alumnoDao.delete(codigo);
		
	}

	
}
