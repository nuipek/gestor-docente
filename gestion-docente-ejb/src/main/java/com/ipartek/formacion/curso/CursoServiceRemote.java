package com.ipartek.formacion.curso;

import java.util.List;

import javax.ejb.Remote;

import com.ipartek.formacion.persistence.Curso;

@Remote
public interface CursoServiceRemote {
	
	public List<Curso> getAll();
	
	public Curso getById(long codigo);
	
	public Curso update(Curso curso);
	
	public Curso create(Curso curso);
		
	public void delete(long codigo);
	
	

}
