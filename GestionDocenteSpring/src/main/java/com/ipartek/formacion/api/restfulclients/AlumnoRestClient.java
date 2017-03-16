package com.ipartek.formacion.api.restfulclients;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Alumno;

public interface AlumnoRestClient {
	
	// La url del rest que vamos a consumir
	final static String URL = "http://localhost:8080/gestiondocente/api/alumnos";

	public List<Alumno> getAll();
	
	public Alumno getById(int codigo);
	
	public Alumno Update(Alumno alumno);
	
	public Alumno create(Alumno alumno);
	
	public void delete (int codigo);

}
