package com.ipartek.formacion.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.curso.CursoServiceRemote;
import com.ipartek.formacion.persistence.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;

@Service("cursoServiceImp")
public class CursoServiceImp implements  CursoService{
	
	@Resource(name="cursoServiceRemote")
	private CursoServiceRemote cursoServiceRemote;
	
	@Override
	public void setCursoServiceRemote(CursoServiceRemote cursoService) {
		this.cursoServiceRemote = cursoService;
	}

	@Override
	public Curso create(Curso curso) {
		
		return cursoServiceRemote.create(curso);
	}

	@Override
	public List<Curso> getAll() {
		return this.cursoServiceRemote.getAll();

	}

	@Override
	public Curso getById(long codigo) {
		
		return this.cursoServiceRemote.getById(codigo);
		
	}

	@Override
	public Curso update(Curso curso) {
		
		return cursoServiceRemote.update(curso);
	}

	@Override
	public void delete(long codigo) {
		// TODO Auto-generated method stub
		
	}


	

}
