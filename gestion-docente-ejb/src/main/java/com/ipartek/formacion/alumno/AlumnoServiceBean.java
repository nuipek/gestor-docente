package com.ipartek.formacion.alumno;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ipartek.formacion.persistence.Alumno;


@Stateless(name = "alumnoServiceBean")
public class AlumnoServiceBean implements AlumnoServiceRemote{

	@PersistenceContext(unitName = "gestiondocente")
	private EntityManager entityManager;
	
	@Override
	public Alumno getById(long codigo) {
		Alumno alumno = entityManager.find(Alumno.class, codigo);
		return alumno;
	}

	@Override
	public List<Alumno> getAll() {
		
		TypedQuery<Alumno> alumnos = entityManager.createNamedQuery("alumnos.getall", Alumno.class);
		return alumnos.getResultList();
	}

}
