package com.ipartek.formacion.alumno;

import java.util.List;

import javax.ejb.Remote;

import com.ipartek.formacion.persistence.Alumno;

@Remote
public interface AlumnoServiceRemote {
	
	public Alumno getById(long codigo);
	
	public List<Alumno>getAll();

}
