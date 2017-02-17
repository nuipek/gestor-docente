package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.persistence.Alumno;

public interface AlumnoService {

	public Alumno create(Alumno alumno);
	
	public List<Alumno> getAll();
	
	public Alumno getById(int codigo);
	
	public Alumno update(Alumno alumno);
	
	public void delete(int codigo);
	
	public void setAlumnoDao(AlumnoDAO alumnoDao);
	
	public boolean alumnoDniDuplicado(String dni, int codigo);
	
}
