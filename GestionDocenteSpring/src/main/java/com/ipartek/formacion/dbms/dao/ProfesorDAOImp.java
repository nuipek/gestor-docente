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
	
	private final String SQL_MAPPER = "SELECT codigo as codigo, nombre as nombre, apellidos as apellidos, dni as dni, " +
			 "email as email , telefono as telefono, direccion as direccion, " +
		        "codigoPostal as codigoPostal, poblacion as poblacion " +
		        "FROM profesor";
	
	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(this.dataSource);

	}

	@Override
	public Profesor create(Profesor profesor) {
		final String INSERT_SQL = "INSERT INTO profesor ( `nSS`, `nombre`, `apellidos`,  `dni`, `direccion`, `poblacion`, `codigoPostal`, `telefono`, `email`) VALUES (" +
			   + profesor.getnSS() + ",'" + profesor.getNombre() + "','" + profesor.getApellidos() + 
				"','"  + "','" + profesor.getDni() + "','" + 	profesor.getDireccion() + 
				"','" + profesor.getPoblacion() + "','" + profesor.getCodigoPostal() + "', " + profesor.getTelefono() +
				", '" + profesor.getEmail() + "')";
		
		logger.info("SENTENCIA SQL INSERT - " + INSERT_SQL);
			
		template.execute(INSERT_SQL);
		return profesor;
	}

	@Override
	public Profesor getById(int codigo) {
		Profesor profesor ;
		/*
		final String SQL = "SELECT codigo as codigo, nombre as nombre, apellidos as apellidos, dni as dni, " +
							"nSS as nSS, email as email, telefono as telefono, direccion as direccion, " +
				            "codigoPostal as codigoPostal, poblacion as poblacion " +
							"FROM profesor" +  "WHERE codigo = " + codigo;
		*/
		String SQL = SQL_MAPPER + " WHERE codigo = " + codigo;
		profesor = template.queryForObject(SQL, new ProfesorMapper());
		
		return profesor;
	}

	@Override
	public List<Profesor> getAll() {
		List<Profesor>profesores=null;
		/*
		//final String SQL = "SELECT codigo as codigo, nombre as nombre, apellidos as apellidos FROM profesor";
		final String SQL = "SELECT codigo as codigo, nombre as nombre, apellidos as apellidos, dni as dni, " +
		 "email as email , telefono as telefono, direccion as direccion, " +
        "codigoPostal as codigoPostal, poblacion as poblacion " +
        "FROM profesor";*/
		
		// recuperacion de la lista de profesores de la bbdd con el mapper
		try{
			profesores = template.query(SQL_MAPPER, new ProfesorMapper());
		}catch (EmptyResultDataAccessException e){
			logger.info("La lista regresa vacia");
			profesores = new ArrayList<Profesor>();
			
		}catch (Exception e){
			logger.error("Error en la recuperacion de la lista " + e.getMessage());
			profesores = new ArrayList<Profesor>();
			profesores = null;
		}
		
		
		return profesores;
	}

	@Override
	public Profesor update(Profesor profesor) {
		
		final String UPDATE_SQL = "UPDATE profesor SET nombre = '" + profesor.getNombre() +  
								  "', apellidos = '" + profesor.getApellidos() + 
								  "', dni = '" + profesor.getDni() + 
								  "', email = '" + profesor.getEmail() + 
								  "', telefono = " + profesor.getTelefono() + 
								  ", direccion = '" + profesor.getDireccion() + 
								  "', codigoPostal = " + profesor.getCodigoPostal() + 
								  ", poblacion = '" + profesor.getPoblacion() + 
								  "'  WHERE codigo = " + profesor.getCodigo();
		
		logger.info("Sentencia SQL UPDATE  - " + UPDATE_SQL);
	
		template.update(UPDATE_SQL);
		return getById(profesor.getCodigo()) ;
	}

	@Override
	public void delete(int codigo) {
		final String DELETE_SQL = "DELETE FROM profesor WHERE codigo = " + codigo;
		template.update(DELETE_SQL);

	}

}
