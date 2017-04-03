package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.persistence.Profesor;
import com.ipartek.formacion.profesor.ProfesorServiceRemote;

public interface ProfesorServiceEJB {

	public void setProfesorServiceRemote (ProfesorServiceRemote profesorServiceRemote);
	public List<Profesor> getAll();
	public Profesor getById(long codigo);
	
}
