package com.ipartek.formacion.profesor;

import java.util.List;

import javax.ejb.Remote;

import com.ipartek.formacion.persistence.Profesor;

@Remote
public interface ProfesorServiceRemote {

	public Profesor getById(long codigo);
	
	public List<Profesor> getAll();

}
