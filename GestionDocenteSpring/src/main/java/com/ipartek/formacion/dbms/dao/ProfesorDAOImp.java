package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.ProfesorDAO;
import com.ipartek.formacion.dbms.mappers.AlumnoMapper;
import com.ipartek.formacion.dbms.mappers.ProfesorMapper;
import com.ipartek.formacion.dbms.persistence.Profesor;

@Repository("profesorDaoImp")
public class ProfesorDAOImp implements ProfesorDAO {
    @Autowired
	private DataSource dataSource;
    private JdbcTemplate template;
	private SimpleJdbcCall jdbcCall;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProfesorDAOImp.class);
	

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(this.dataSource);
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);
	}

	@Override
	public Profesor create(Profesor profesor) {
		final String SQL = "profesorCreate" ;
		
		jdbcCall = new SimpleJdbcCall(dataSource);
		
		jdbcCall.withProcedureName(SQL);
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnss", Long.parseLong(profesor.getnSS()))
				.addValue("pnombre", profesor.getNombre())
				.addValue("papellidos", profesor.getApellidos())
				.addValue("pfNacimiento", profesor.getfNacimiento())
				.addValue("pdni", profesor.getDni())
				.addValue("pdireccion", profesor.getDireccion())
				.addValue("ppoblacion", profesor.getPoblacion())
				.addValue("pCodigoPostal", profesor.getCodigoPostal())
				.addValue("ptelefono", profesor.getTelefono())
				.addValue("pemail", profesor.getEmail());
				
		logger.info(profesor.toString());
			
		Map<String,Object> out = jdbcCall.execute(in);
		
		profesor.setCodigo((Integer)out.get("pcodigo"));
		
		return profesor;
	}

	@Override
	public Profesor getById(int codigo) {
		Profesor profesor ;
		
		final String SQL = "CALL profesorgetById(?);";
	
		
		try {
			profesor = template.queryForObject(SQL, new ProfesorMapper(),new Object[]{codigo});
			
		} catch (EmptyResultDataAccessException e) {
			profesor = new Profesor();
			logger.info("No hay profesor con codigo " + codigo);
			
		}catch (Exception e){
			logger.error("Error al recuperar el profesor con codigo " + codigo + " " + e.getMessage());
			profesor = new Profesor();
		}
		
		return profesor;
	}

	@Override
	public List<Profesor> getAll() {
		List<Profesor>profesores=null;
		
		final String SQL = "CALL profesorgetAll();";
	
		// recuperacion de la lista de profesores de la bbdd con el mapper
		try{
			profesores = template.query(SQL, new ProfesorMapper());
			
		}catch (EmptyResultDataAccessException e){
			logger.info("La lista regresa vacia");
			profesores = new ArrayList<Profesor>();
			
		}catch (Exception e){
			logger.error("Error en la recuperacion de la lista de profesores" + e.getMessage());
			profesores = new ArrayList<Profesor>();
			
		}
		
		
		return profesores;
	}

	@Override
	public Profesor update(Profesor profesor) {
		
		final String SQL = "profesorUpdate";
		jdbcCall = new SimpleJdbcCall(dataSource);
		
		jdbcCall.withProcedureName(SQL);
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", profesor.getCodigo())
				.addValue("pnss", Long.parseLong(profesor.getnSS()))
				.addValue("pnombre", profesor.getNombre())
				.addValue("papellidos", profesor.getApellidos())
				.addValue("pfNacimiento", profesor.getfNacimiento())
				.addValue("pdni", profesor.getDni())
				.addValue("pdireccion", profesor.getDireccion())
				.addValue("ppoblacion", profesor.getPoblacion())
				.addValue("pCodigoPostal", profesor.getCodigoPostal())
				.addValue("ptelefono", profesor.getTelefono())
				.addValue("pemail", profesor.getEmail());
	   logger.info(profesor.toString());			
		
		try {
			jdbcCall.execute(in);
		} catch (Exception e) {
			logger.error("Error en la update de profesor " + e.getMessage());
			e.printStackTrace();
		}
		
		return profesor;
		
	}

	@Override
	public void delete(int codigo) {
		
		String SQL = "profesorDelete";
		
		jdbcCall = new SimpleJdbcCall(dataSource);
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("pcodigo", codigo);
		
		jdbcCall.withProcedureName(SQL);
			
		try {
			logger.info(jdbcCall.getProcedureName());
			jdbcCall.execute(in);
		} catch (Exception e) {
			logger.error("Error en el delete de profesor " + e.getMessage());
			e.printStackTrace();
		}
	
	}

	@Override
	public boolean profesorDniDuplicado(String dni, int codigo) {
		final String SQL = "profesorgetByDni";
		
		logger.info(SQL);
		logger.info(dni);
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);
		// Se asigna el nombre del procedimiento almacenado
		jdbcCall.withProcedureName(SQL);
		
		// Crear un mapa con los parametros de procedimiento almacenado  de entrada IN
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pdni", dni)
				.addValue("pcodigo", codigo);
		
		logger.info(dni);
		
		// Ejecuta la sentencia
		Map<String,Object>out = jdbcCall.execute(in);
	    // En out se han recogido los parametros out de la consulta de BBDD.
	    int total = (Integer)out.get("presultado");
		logger.info("El valor de la recuperacion Dni Duplicado es "+ total);
		if (total > 0) return true;
		else return false;
	}

	@Override
	public boolean profesornSSDuplicado(String nSS, int codigo) {
		final String SQL = "profesorgetBynSS";
		logger.info(SQL);
		logger.info(nSS);
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);
		// Se asigna el nombre del procedimiento almacenado
		jdbcCall.withProcedureName(SQL);
		
		// Crear un mapa con los parametros de procedimiento almacenado  de entrada IN
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnSS",nSS )
				.addValue("pcodigo", codigo);
		
		logger.info(nSS);
		
		// Ejecuta la sentencia
		Map<String,Object>out = jdbcCall.execute(in);
	    // En out se han recogido los parametros out de la consulta de BBDD.
	    int total = (Integer)out.get("presultado");
		logger.info("El valor de la recuperacion nSS Duplicado es "+ total);
		if (total > 0) return true;
		else return false;
	}

}
