package com.ipartek.formacion.curso;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ipartek.formacion.persistence.Curso;

/**
 * Session Bean implementation class CursoServiceBean
 */

@Stateless(name = "cursoServiceBean")
public class CursoServiceBean implements CursoServiceRemote {

	// se corresponde con el nombre incluido en el persistence.xml
	@PersistenceContext(unitName = "gestiondocente")
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public CursoServiceBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Curso> getAll() {
		
		Query cursos = entityManager.createNamedQuery("curso.getall");
		return cursos.getResultList();
	}

	@Override
	public Curso getById(long codigo) {
		Curso curso = entityManager.find(Curso.class, codigo);
		return curso;
	}

	@Override
	public Curso update(Curso curso) {
	    entityManager.persist(curso);
		return curso;
	}

	@Override
	public Curso create(Curso curso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long codigo) {
		// TODO Auto-generated method stub
		
	}



}
