package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Alumno;

/**
 * Esta Interfaz define los medtodos de consulta a base de datos 
 * de la entidad alumno que tiene su correspondencia en la clase
 * <code>Alumno</code> de la capa de persistencia
 * @author Sergio aparicio
 *
 */
public interface AlumnoDAO extends DAOSetter {

	/**
	 * Metodo que crea un <code>Alumno</code> en la base de datos. 
	 * El <code>Alumno</code> tendra los datos necesario excepto el codigo que es generado 
	 * por la base de datos. 
	 * @param alumno
	 * @return <code>Alumno</code> se devuelve el objeto enviado con el codigo generado en la base de datos.
	 */
	public Alumno create(Alumno alumno);
	
	public List<Alumno> getAll();
	
	public Alumno getById(int codigo);
	
	public Alumno update(Alumno alumno);
	
	public void delete(int codigo);
	
	public boolean alumnoDniDuplicado(String dni, int codigo);
	
	public Alumno getInforme(int codigo);
	
	
	
}
