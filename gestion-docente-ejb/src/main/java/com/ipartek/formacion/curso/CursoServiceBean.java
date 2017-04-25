package com.ipartek.formacion.curso;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;


import com.ipartek.formacion.persistence.Curso;

/**
 * Session Bean implementation class CursoServiceBean
 */
// El nombre de una instancia del bean no de la clase

@Stateless(name = "cursoServiceBean")
public class CursoServiceBean implements CursoServiceRemote {

	private static final Logger LOGGER = Logger.getLogger(CursoServiceBean.class);
	
	// se corresponde con el nombre incluido en el persistence.xml
	@PersistenceContext(unitName = "gestiondocente")
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     
    public CursoServiceBean() {
        // TODO Auto-generated constructor stub
    }
*/
	@Override
	public List<Curso> getAll() {
		TypedQuery<Curso> cursos = entityManager.createNamedQuery("curso.getall", Curso.class);
		//Query cursos = entityManager.createNamedQuery("curso.getall");
		return cursos.getResultList();
	}

	@Override
	public Curso getById(long codigo) {
		
		Curso curso = entityManager.find(Curso.class, codigo);
		
	  //Utilizando llamada directa al nombre del procedimiento
	  //StoredProcedureQuery query = entityManager..createStoredProcedureQuery("alumnogetByCurso",Alumno.class);
	  //query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
		
	  // Utilizando NamedStoredProcedure asociado a la clase Curso
		/*
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("curso.getAlumnos");
				
		query.setParameter(1, curso.getCodigo());
	
		List<Alumno> alumnos = query.getResultList();
		
		curso.setAlumnos(alumnos);
		*/
		return curso;
	}

	@Override
	public Curso update(Curso curso) {
		// Comentamos el try catch porque lo manejaremos en el controlador
		/*EntityTransaction tx = entityManager.getTransaction();
		//tx.begin();
	    try {
			//entityManager.persist(curso);
			curso  = entityManager.merge(curso);
			entityManager.flush();
			//tx.commit();
		} catch (Exception e) {
			//tx.rollback();
			e.printStackTrace();
		}
		*/
	    curso  = entityManager.merge(curso);
		entityManager.flush();
		return curso;
	}

	@Override
	public Curso create(Curso curso) {
		// Comentamos el try catch porque lo manejaremos en el controlador
		/*
		try{
			//entityManager.persist(curso);
			 curso = entityManager.merge(curso);
		//	entityManager.merge(curso);
			//entityManager.flush();
		}catch(Exception e){
			
			System.out.println("RollBack " + e.getMessage() );
		}
		*/
		 curso = entityManager.merge(curso);
		return curso;
	}

	@Override
	public void delete(long codigo) {
		//EntityTransaction tx = entityManager.getTransaction();
		//System.out.println("Borramos " + codigo);
		entityManager.remove(entityManager.find(Curso.class,codigo));
		entityManager.flush();
		
		//tx.begin();
		/*
		try{
			entityManager.remove(entityManager.find(Curso.class,codigo));
		//	tx.commit();
		}catch(Exception e){
			//tx.rollback();
		}
		*/
		
	}



}
