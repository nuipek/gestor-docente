package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.curso.CursoServiceRemote;
import com.ipartek.formacion.dbms.dao.interfaces.ProfesorDAO;
import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.profesor.ProfesorServiceRemote;


public interface ProfesorService {
	
	public Profesor create(Profesor profesor);
	
	public Profesor getById(int codigo);
	
	public List<Profesor> getAll();
	
	public Profesor update(Profesor profesor);
	
	public void delete(int codigo);
	
	public void setProfesorDao(ProfesorDAO profesorDao);

	boolean profesorDniDuplicado(String dni, int codigo);
	
	boolean profesornSSDuplicado(String nSS, int codigo);
	
	
}
