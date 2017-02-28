package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.curso.CursoServiceRemote;
import com.ipartek.formacion.persistence.Curso;

public interface CursoService {

	public void setCursoServiceRemote(CursoServiceRemote cursoService);
	
	public Curso create(Curso curso);
	
	public List<Curso> getAll();
	
	public Curso getById(long codigo);
	
	public Curso update(Curso curso);
	
	public void delete (long codigo);
}
