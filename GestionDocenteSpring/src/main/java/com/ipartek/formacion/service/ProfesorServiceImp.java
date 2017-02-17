package com.ipartek.formacion.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.dao.interfaces.ProfesorDAO;
import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;

@Service // necesario para poder inyectar el objeto en su dependencia
public class ProfesorServiceImp implements ProfesorService {

	
	@Autowired
	private ProfesorDAO profesorDao;
	
	
	@Override
	public void setProfesorDao(ProfesorDAO profesorDao) {
		this.profesorDao = profesorDao;
		
	}
	@Override
	public Profesor create(Profesor profesor) {
		
		return profesorDao.create(profesor);
	}

	@Override
	public Profesor getById(int codigo) {
		
		return profesorDao.getById(codigo);
	}

	@Override
	public List<Profesor> getAll() {
		
		return profesorDao.getAll();
	}

	@Override
	public Profesor update(Profesor profesor) {
		
		return profesorDao.update(profesor);
	}

	@Override
	public void delete(int codigo) {
		profesorDao.delete(codigo);
		
	}

	@Override
	public boolean profesorDniDuplicado(String dni, int codigo) {
	 return	profesorDao.profesorDniDuplicado(dni,codigo);
		
	}
	
	@Override
	public boolean profesornSSDuplicado(String nSS, int codigo) {
	 return	profesorDao.profesornSSDuplicado(nSS,codigo);
		
	}
	



}
