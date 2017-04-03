	package com.ipartek.formacion.profesor;

	import java.util.List;

	import javax.ejb.Stateless;
	import javax.persistence.EntityManager;
	import javax.persistence.PersistenceContext;
	import javax.persistence.TypedQuery;


	import com.ipartek.formacion.persistence.Profesor;

	@Stateless(name = "profesorServiceBean")
	public class ProfesorServiceBean implements ProfesorServiceRemote {

		
		@PersistenceContext(unitName = "gestiondocente")
		private EntityManager entityManager;
		
		@Override
		public Profesor getById(long codigo) {
			Profesor profesor = entityManager.find(Profesor.class, codigo);
			return profesor;
		}

		@Override
		public List<Profesor> getAll() {
			TypedQuery<Profesor> profesores = entityManager.createNamedQuery("profesor.getall", Profesor.class);
			return profesores.getResultList();
		}


}
