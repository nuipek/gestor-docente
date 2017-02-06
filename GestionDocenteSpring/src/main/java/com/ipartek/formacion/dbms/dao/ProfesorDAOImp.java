package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.ProfesorDAO;
import com.ipartek.formacion.dbms.mappers.AlumnoMapper;
import com.ipartek.formacion.dbms.mappers.ProfesorMapper;
import com.ipartek.formacion.dbms.persistence.Profesor;

@Repository("profesorDaoImp")
public class ProfesorDAOImp implements ProfesorDAO {
    @Autowired
	private DataSource dataSource;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfesorDAOImp.class);
	private JdbcTemplate template;
	
	
	
	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(this.dataSource);

	}

	@Override
	public Profesor create(Profesor profesor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profesor getById(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profesor> getAll() {
		List<Profesor>profesores=null;
		final String SQL = "SELECT codigo as codigo, nombre as nombre, apellidos as apellidos FROM profesor";
		// recuperacion de la lista de profesores de la bbdd con el mapper
		try{
			profesores = template.query(SQL, new ProfesorMapper());
		}catch (EmptyResultDataAccessException e){
			logger.info("La lista regresa vacia");
			profesores = new ArrayList<Profesor>();
			
		}catch (Exception e){
			logger.error("Error en la recuperacion de la lista " + e.getMessage());
			profesores = null;
		}
		
		
		return profesores;
	}

	@Override
	public Profesor update(Profesor profesor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int codigo) {
		// TODO Auto-generated method stub

	}

}
