package com.ipartek.formacion.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.formacion.controller.validator.AlumnoValidator;
import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService{
	 private static final Logger logger = LoggerFactory.getLogger(AlumnoServiceImp.class);
	  
	@Autowired
	private AlumnoDAO alumnoDao;
    
    @Override
	public void setAlumnoDao(AlumnoDAO alumnoDao) {
		
		this.alumnoDao = alumnoDao;
	}

	@Override
	@Transactional
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
	@Transactional
	public Alumno update(Alumno alumno) {
	
		return alumnoDao.update(alumno);
	}

	@Override
	@Transactional
	public void delete(int codigo) {
		// begin();
		// commit();
		// rollback();
		alumnoDao.delete(codigo);
		
	}

	@Override
	public Alumno alumnoDniDuplicado(String dni) {
		
		
	 return	alumnoDao.alumnoDniDuplicado(dni);
		
	}

	@Override
	public Alumno getInforme(int codigo) {
		return alumnoDao.getInforme(codigo);
	}

	
}
