package com.ipartek.formacion.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.persistence.Profesor;
import com.ipartek.formacion.profesor.ProfesorServiceRemote;
import com.ipartek.formacion.service.interfaces.ProfesorServiceEJB;

@Service(value = "profesorServiceEJBImp")
public class ProfesorServiceEJBImp implements ProfesorServiceEJB {
	
	@Resource(name = "profesorServiceRemote")
	private ProfesorServiceRemote profesorServiceRemote;

	@Override
	public void setProfesorServiceRemote(ProfesorServiceRemote profesorService) {
		this.profesorServiceRemote = profesorService;
	}

	@Override
	public List<Profesor> getAll() {
		return profesorServiceRemote.getAll();
	}

	@Override
	public Profesor getById(long codigo) {
		return profesorServiceRemote.getById(codigo);
	}

}
